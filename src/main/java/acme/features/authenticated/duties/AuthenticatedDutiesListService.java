
package acme.features.authenticated.duties;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.jobs.JobStatus;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedDutiesListService implements AbstractListService<Authenticated, Duty> {

	@Autowired
	AuthenticatedDutiesRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		return this.repository.isCorrectDuty(request.getModel().getInteger("idJob"), JobStatus.PUBLISHED);
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String titleJob = this.repository.findJobTitle(entity.getDescriptor().getId());
		model.setAttribute("jobTitle", titleJob);

		request.unbind(entity, model, "title", "time");

	}

	@Override
	public Collection<Duty> findMany(final Request<Duty> request) {
		assert request != null;

		Collection<Duty> res = this.repository.findMany(request.getModel().getInteger("idJob"));
		return res;
	}

}
