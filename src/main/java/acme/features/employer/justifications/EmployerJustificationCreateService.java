
package acme.features.employer.justifications;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.applications.ApplicationStatus;
import acme.entities.justifications.Justification;
import acme.entities.roles.Employer;
import acme.entities.spams.Spam;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJustificationCreateService implements AbstractCreateService<Employer, Justification> {

	@Autowired
	EmployerJustificationRepository repository;


	@Override
	public boolean authorise(final Request<Justification> request) {
		assert request != null;
		return this.repository.isCorrectEmployer(request.getPrincipal().getActiveRoleId());
	}

	@Override
	public void bind(final Request<Justification> request, final Justification entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Justification> request, final Justification entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("applicationId", request.getModel().getInteger("applicationId"));

		request.unbind(entity, model, "justification");

	}

	@Override
	public Justification instantiate(final Request<Justification> request) {
		assert request != null;

		Justification res = new Justification();
		Application application;

		application = this.repository.findOne(request.getModel().getInteger("applicationId"));
		res.setApplication(application);
		return res;
	}

	@Override
	public void validate(final Request<Justification> request, final Justification entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Spam spam = this.repository.findSpam();
		String[] spams = spam.getSpam().split(",");

		boolean isSpamJustification = Arrays.asList(spams).stream().filter(x -> entity.getJustification().toLowerCase().contains(x.toLowerCase().trim())).count() * 100 / spams.length < spam.getThreshold();
		errors.state(request, isSpamJustification, "justification", "employer.justification.error.justification-spam");
	}

	@Override
	public void create(final Request<Justification> request, final Justification entity) {
		assert request != null;
		assert entity != null;

		entity.getApplication().setStatus(ApplicationStatus.REJECTED);
		this.repository.save(entity.getApplication());
		this.repository.save(entity);
	}

}
