
package acme.features.authenticated.message;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.entities.messages.Message;
import acme.entities.spams.Spam;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;
		return this.repository.isCorrectMessageThread(request.getPrincipal().getAccountId(), request.getModel().getInteger("messageThreadId"));
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");

	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("accept", "false");
		model.setAttribute("messageThreadId", request.getModel().getInteger("messageThreadId"));

		request.unbind(entity, model, "title", "tags", "body");

	}

	@Override
	public Message instantiate(final Request<Message> request) {
		assert request != null;

		UserAccount user = this.repository.findUserAccount(request.getPrincipal().getAccountId());
		MessageThread mThread = this.repository.findMessageThread(request.getModel().getInteger("messageThreadId"));

		if (!mThread.getUsers().contains(user)) {
			mThread.getUsers().add(user);
		}

		Message res = new Message();

		res.setUser(user);
		res.setMessageThread(mThread);
		return res;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "authenticated.message.error.must-accept");

		Spam spam = this.repository.findSpam();
		String[] spams = spam.getSpam().split(",");

		int spamTitle = (int) (Arrays.asList(spams).stream().filter(x -> entity.getTitle().toLowerCase().contains(x.toLowerCase().trim())).count() * 100 / spams.length);
		int spamBody = (int) (Arrays.asList(spams).stream().filter(x -> entity.getBody().toLowerCase().contains(x.toLowerCase().trim())).count() * 100 / spams.length);
		int spamTags = (int) (Arrays.asList(spams).stream().filter(x -> entity.getTags().toLowerCase().contains(x.toLowerCase().trim())).count() * 100 / spams.length);

		int sumaSpams = spamTitle + spamBody + spamTags;
		boolean isMessageSpam = sumaSpams < spam.getThreshold();
		errors.state(request, isMessageSpam, "accept", "authenticated.message.error.spam-words");

	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		entity.setMoment(new Date(System.currentTimeMillis() - 1));
		this.repository.save(entity);
	}

}
