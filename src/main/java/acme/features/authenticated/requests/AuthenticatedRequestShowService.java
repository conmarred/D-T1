
package acme.features.authenticated.requests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.Requests;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedRequestShowService implements AbstractShowService<Authenticated, Requests> {

	@Autowired
	private AuthenticatedRequestRepository repository;


	@Override
	public boolean authorise(final Request<Requests> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Requests> request, final Requests entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "deadline", "description", "reward", "ticker");

	}

	@Override
	public Requests findOne(final Request<Requests> request) {
		assert request != null;

		Requests result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
