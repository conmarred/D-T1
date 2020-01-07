
package acme.features.provider.requests;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.Requests;
import acme.entities.roles.Provider;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ProviderRequestCreateService implements AbstractCreateService<Provider, Requests> {

	@Autowired
	ProviderRequestRepository repository;


	@Override
	public boolean authorise(final Request<Requests> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Requests> request, final Requests entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");

	}

	@Override
	public void unbind(final Request<Requests> request, final Requests entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("accept", "false");

		request.unbind(entity, model, "title", "deadline", "description", "reward", "ticker");
	}

	@Override
	public Requests instantiate(final Request<Requests> request) {
		assert request != null;

		Requests res = new Requests();

		return res;
	}

	@Override
	public void validate(final Request<Requests> request, final Requests entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "provider.request.error.must-accept");

		if (!errors.hasErrors("deadline")) {
			Date dateNow = Date.from(Instant.now());
			boolean deadlineAfterNow = entity.getDeadline().after(dateNow);
			errors.state(request, deadlineAfterNow, "deadline", "provider.request.error.deadline");
		}

		boolean isDuplicateTicker = this.repository.findTickers(entity.getTicker()) != null;
		errors.state(request, !isDuplicateTicker, "ticker", "provider.request.error.duplicated");

		if (!errors.hasErrors("reward")) {
			boolean correctCurrency = entity.getReward().getCurrency().equals("EUR");
			errors.state(request, correctCurrency, "reward", "provider.request.error.correct-currency");
		}
	}

	@Override
	public void create(final Request<Requests> request, final Requests entity) {
		assert request != null;
		assert entity != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
