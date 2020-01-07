
package acme.features.auditor.auditsJob;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.entities.spams.Spam;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuditorAuditRecordUpdateService implements AbstractUpdateService<Auditor, AuditRecord> {

	@Autowired
	AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "body", "title");
	}

	@Override
	public AuditRecord findOne(final Request<AuditRecord> request) {
		assert request != null;
		return this.repository.findOne(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Spam spam = this.repository.findSpam();
		String[] spams = spam.getSpam().split(",");

		int spamTitle = (int) (Arrays.asList(spams).stream().filter(x -> entity.getTitle().toLowerCase().contains(x.toLowerCase().trim())).count() * 100 / spams.length);
		int spamBody = (int) (Arrays.asList(spams).stream().filter(x -> entity.getBody().toLowerCase().contains(x.toLowerCase().trim())).count() * 100 / spams.length);

		int sumSpam = spamTitle + spamBody;
		boolean isAuditRecordSpam = sumSpam < spam.getThreshold();
		errors.state(request, isAuditRecordSpam, "body", "employer.job.error.audit-record-spam");
	}

	@Override
	public void update(final Request<AuditRecord> request, final AuditRecord entity) {
		assert request != null;
		assert entity != null;

		entity.setMoment(new Date(System.currentTimeMillis() - 1));
		this.repository.save(entity);

	}

}
