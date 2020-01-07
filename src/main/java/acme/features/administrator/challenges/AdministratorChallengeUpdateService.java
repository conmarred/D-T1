
package acme.features.administrator.challenges;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorChallengeUpdateService implements AbstractUpdateService<Administrator, Challenge> {

	@Autowired
	AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "goalGOLD", "goalSILVER", "goalBRONZE", "rewardGOLD", "rewardSILVER", "rewardBRONZE");
	}

	@Override
	public Challenge findOne(final Request<Challenge> request) {
		assert request != null;

		Challenge res = this.repository.findOneById(request.getModel().getInteger("id"));

		return res;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("deadline")) {
			Date dateNow = Date.from(Instant.now());
			boolean deadlineAfterNow = entity.getDeadline().after(dateNow);
			errors.state(request, deadlineAfterNow, "deadline", "administrator.offer.error.deadline");
		}

		if (!errors.hasErrors("rewardSILVER") && !errors.hasErrors("rewardGOLD")) {
			boolean correctRange1 = entity.getRewardGOLD().getAmount() > entity.getRewardSILVER().getAmount();
			errors.state(request, correctRange1, "rewardSILVER", "administrator.offer.error.range-money1");
		}

		if (!errors.hasErrors("rewardSILVER") && !errors.hasErrors("rewardBRONZE")) {
			boolean correctRange2 = entity.getRewardSILVER().getAmount() > entity.getRewardBRONZE().getAmount();
			errors.state(request, correctRange2, "rewardBRONZE", "administrator.offer.error.range-money2");
		}

		if (!errors.hasErrors("rewardGOLD")) {
			boolean correctCurrency = entity.getRewardGOLD().getCurrency().equals("EUR");
			errors.state(request, correctCurrency, "rewardGOLD", "administrator.challenge.error.correct-currency");
		}

		if (!errors.hasErrors("rewardSILVER")) {
			boolean correctCurrency = entity.getRewardSILVER().getCurrency().equals("EUR");
			errors.state(request, correctCurrency, "rewardSILVER", "administrator.challenge.error.correct-currency");
		}

		if (!errors.hasErrors("rewardBRONZE")) {
			boolean correctCurrency = entity.getRewardBRONZE().getCurrency().equals("EUR");
			errors.state(request, correctCurrency, "rewardBRONZE", "administrator.challenge.error.correct-currency");
		}

	}

	@Override
	public void update(final Request<Challenge> request, final Challenge entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
