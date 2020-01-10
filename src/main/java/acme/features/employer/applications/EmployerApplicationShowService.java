
package acme.features.employer.applications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerApplicationShowService implements AbstractShowService<Employer, Application> {

	@Autowired
	EmployerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		return this.repository.isApplicationAtMyJob(request.getModel().getInteger("id"), request.getPrincipal().getActiveRoleId());
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("username", entity.getWorker().getUserAccount().getUsername());
		model.setAttribute("justification.justification", this.repository.findJustification(entity.getId()));

		request.unbind(entity, model, "reference", "moment", "status", "statement", "skills", "qualifications", "job.title", "answer.answer", "answer.password", "answer.keylet");

	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application res = this.repository.findOne(request.getModel().getInteger("id"));
		return res;
	}

}
