
package acme.features.employer.duties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.jobs.JobStatus;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerDutyShowService implements AbstractShowService<Employer, Duty> {

	@Autowired
	EmployerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Job job = this.repository.findJob(entity.getDescriptor().getId());
		model.setAttribute("jobTitle", job.getTitle());
		model.setAttribute("isPublished", job.getStatus().equals(JobStatus.PUBLISHED));

		request.unbind(entity, model, "title", "description", "time");

	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;

		Duty res = this.repository.findOne(request.getModel().getInteger("id"));
		return res;
	}

}
