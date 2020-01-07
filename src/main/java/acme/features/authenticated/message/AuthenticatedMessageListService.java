
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageListService implements AbstractListService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		return this.repository.isCorrectMessageThread(request.getPrincipal().getAccountId(), request.getModel().getInteger("messageThreadId"));
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("messageThreadId", request.getModel().getInteger("messageThreadId"));
		model.setAttribute("username", entity.getUser().getUsername());

		request.unbind(entity, model, "title", "moment");

	}

	@Override
	public Collection<Message> findMany(final Request<Message> request) {
		assert request != null;

		Collection<Message> res = this.repository.findMany(request.getModel().getInteger("messageThreadId"));
		return res;
	}

}
