
package acme.features.employer.jobs;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.jobs.JobStatus;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "status");

	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "link", "descriptor.description", "jobChallenge.description", "jobChallenge.moreInfo");

	}

	@Override
	public Job instantiate(final Request<Job> request) {
		assert request != null;

		Job res = new Job();
		res.setStatus(JobStatus.DRAFT);
		res.setEmployer(this.repository.employerById(request.getPrincipal().getActiveRoleId()));
		return res;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("deadline")) {
			Date dateNow = Date.from(Instant.now());
			boolean deadlineAfterNow = entity.getDeadline().after(dateNow);
			errors.state(request, deadlineAfterNow, "deadline", "employer.job.error.deadline");
		}

		if (!errors.hasErrors("salary")) {
			boolean correctCurrency = entity.getSalary().getCurrency().equals("EUR");
			errors.state(request, correctCurrency, "salary", "employer.job.error.correct-currency");
		}

		boolean isDuplicateTicker = this.repository.findReference(entity.getReference()) != null;
		errors.state(request, !isDuplicateTicker, "reference", "employer.reference.error.duplicated");

		if (!entity.getJobChallenge().getMoreInfo().isEmpty()) {
			boolean correctJobChallenge = !entity.getJobChallenge().getDescription().isEmpty();
			errors.state(request, correctJobChallenge, "jobChallenge.description", "employer.jobChallenge.error.correct-job-challenge");
		}
	}

	@Override
	public void create(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity.getDescriptor());
		this.repository.save(entity.getJobChallenge());
		this.repository.save(entity);
	}

}
