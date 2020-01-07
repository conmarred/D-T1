
package acme.features.authenticated.messageThreads;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedMessageThreadUpdateService implements AbstractUpdateService<Authenticated, MessageThread> {

	@Autowired
	AuthenticatedMessageThreadRepository repository;


	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;
		return this.repository.isCreatorUser(request.getModel().getInteger("messageThreadId")).get(0).equals(request.getPrincipal().getAccountId());
	}

	@Override
	public void bind(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("username", "");
		model.setAttribute("creatorUser", this.repository.isCreatorUser(entity.getId()).get(0).equals(request.getPrincipal().getAccountId()));
		model.setAttribute("messageThreadId", request.getModel().getInteger("messageThreadId"));

		request.unbind(entity, model, "title", "moment");
	}

	@Override
	public MessageThread findOne(final Request<MessageThread> request) {
		assert request != null;
		MessageThread res = this.repository.findOneMessageThreadById(request.getModel().getInteger("messageThreadId"));

		return res;
	}

	@Override
	public void validate(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String username = request.getModel().getString("username");
		UserAccount ua = this.repository.findUserAccountByUsername(username);
		errors.state(request, ua != null, "username", "authenticated.message-thread.error.username");

	}

	@Override
	public void update(final Request<MessageThread> request, final MessageThread entity) {
		assert request != null;
		assert entity != null;

		String username = request.getModel().getString("username");
		UserAccount ua = this.repository.findUserAccountByUsername(username);
		Collection<UserAccount> users = this.repository.findUserOfMessageThread(entity.getId());
		ua.getRoles().size();
		if (users.contains(ua)) {
			users.remove(ua);
		} else {
			users.add(ua);
		}
		if (users.isEmpty()) {
			Collection<Integer> messagesId = this.repository.findMessagesId(request.getModel().getInteger("messageThreadId"));
			for (Integer i : messagesId) {
				this.repository.deleteById(i);
			}
			this.repository.delete(entity);
		} else {
			entity.setUsers(users);
			entity.getUsers().size();
			this.repository.save(entity);
		}
	}

}
