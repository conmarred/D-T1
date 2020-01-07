
package acme.features.employer.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerJobDeleteService implements AbstractDeleteService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		boolean res = this.repository.isEmployer(request.getModel().getInteger("id"), request.getPrincipal().getActiveRoleId());
		return res;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "status", "title", "deadline", "salary", "link", "descriptor.description", "jobChallenge.description", "jobChallenge.moreInfo");
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job res = this.repository.findOne(request.getModel().getInteger("id"));
		return res;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		this.repository.deleteAll(this.repository.dutiesOfADescriptor(entity.getDescriptor().getId()));
		this.repository.deleteAll(this.repository.auditsOfAJob(entity.getId()));
		this.repository.delete(entity.getJobChallenge());
		this.repository.delete(entity);
	}
}
