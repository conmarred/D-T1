
package acme.features.authenticated.duties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.jobs.JobStatus;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedDutiesShowService implements AbstractShowService<Authenticated, Duty> {

	@Autowired
	AuthenticatedDutiesRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		return this.repository.isCorrectDuty(request.getModel().getInteger("id"), JobStatus.PUBLISHED);
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String jobTitle = this.repository.findJobTitle(entity.getDescriptor().getId());
		model.setAttribute("jobTitle", jobTitle);

		request.unbind(entity, model, "title", "description", "time");

	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;

		Duty res = this.repository.findOne(request.getModel().getInteger("id"));
		return res;
	}

}
