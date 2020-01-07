
package acme.features.authenticated.messageThreads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.entities.spams.Spam;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageThreadCreateService implements AbstractCreateService<Authenticated, MessageThread> {

	@Autowired
	AuthenticatedMessageThreadRepository repository;


	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");

	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title");
	}

	@Override
	public MessageThread instantiate(final Request<MessageThread> request) {
		assert request != null;
		UserAccount ua = this.repository.findUserAccountById(request.getPrincipal().getAccountId());

		MessageThread res = new MessageThread();
		Collection<UserAccount> users = new ArrayList<UserAccount>();
		users.add(ua);
		res.setUsers(users);
		return res;
	}

	@Override
	public void validate(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Spam spam = this.repository.findSpam();
		String[] spamWords = spam.getSpam().split(",");

		int spamTitle = (int) (Arrays.asList(spamWords).stream().filter(x -> entity.getTitle().toLowerCase().contains(x.toLowerCase().trim())).count() * 100 / spamWords.length);
		boolean isMessageThreadSpam = spamTitle <= spam.getThreshold();
		errors.state(request, isMessageThreadSpam, "title", "authenticated.message-thread.error.spam-entity");

	}

	@Override
	public void create(final Request<MessageThread> request, final MessageThread entity) {
		assert request != null;
		assert entity != null;

		entity.setMoment(new Date(System.currentTimeMillis() - 1));
		this.repository.save(entity);
	}

}
