
package acme.features.authenticated.auditsJob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAuditsOfAJobShowService implements AbstractShowService<Authenticated, AuditRecord> {

	@Autowired
	AuthenticatedAuditsOfAJobRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("workerUsername", entity.getAuditor().getUserAccount().getUsername());

		request.unbind(entity, model, "title", "status", "moment", "body", "job.title");

	}

	@Override
	public AuditRecord findOne(final Request<AuditRecord> request) {
		assert request != null;

		AuditRecord res = this.repository.findOne(request.getModel().getInteger("id"));

		return res;
	}

}
