
package acme.features.authenticated.auditsJob;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.auditRecords.AuditRecordStatus;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAuditsOfAJobListService implements AbstractListService<Authenticated, AuditRecord> {

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
		request.unbind(entity, model, "title", "moment", "job.title");
	}

	@Override
	public Collection<AuditRecord> findMany(final Request<AuditRecord> request) {
		assert request != null;

		Collection<AuditRecord> res = this.repository.findAuditsOfAJob(Integer.parseInt(request.getServletRequest().getParameter("id")), AuditRecordStatus.DRAFT, request.getPrincipal().getActiveRoleId(), AuditRecordStatus.PUBLISHED);

		return res;
	}

}
