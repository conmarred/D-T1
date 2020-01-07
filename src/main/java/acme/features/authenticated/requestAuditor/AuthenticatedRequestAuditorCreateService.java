
package acme.features.authenticated.requestAuditor;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requestAuditor.AuditorStatus;
import acme.entities.requestAuditor.RequestAuditor;
import acme.entities.roles.Auditor;
import acme.entities.spams.Spam;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedRequestAuditorCreateService implements AbstractCreateService<Authenticated, RequestAuditor> {

	@Autowired
	AuthenticatedRequestAuditorRepository repository;


	@Override
	public boolean authorise(final Request<RequestAuditor> request) {
		assert request != null;

		return !request.getPrincipal().hasRole(Auditor.class) && !this.repository.existRequestAuditor(request.getPrincipal().getAccountId());
	}

	@Override
	public void bind(final Request<RequestAuditor> request, final RequestAuditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "status");
	}

	@Override
	public void unbind(final Request<RequestAuditor> request, final RequestAuditor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsibility");
	}

	@Override
	public RequestAuditor instantiate(final Request<RequestAuditor> request) {
		assert request != null;

		RequestAuditor result = new RequestAuditor();
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		result.setStatus(AuditorStatus.PENDING);

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);
		result.setUserAccount(userAccount);
		return result;
	}

	@Override
	public void validate(final Request<RequestAuditor> request, final RequestAuditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Spam spam = this.repository.findSpam();
		String[] spamWords = spam.getSpam().split(",");

		int spamFirm = (int) (Arrays.asList(spamWords).stream().filter(x -> entity.getFirm().toLowerCase().contains(x.toLowerCase().trim())).count() * 100 / spamWords.length);
		int SpamResposibility = (int) (Arrays.asList(spamWords).stream().filter(x -> entity.getResponsibility().toLowerCase().contains(x.toLowerCase().trim())).count() * 100 / spamWords.length);
		int sumSpam = spamFirm + SpamResposibility;
		boolean isAuditorSpam = sumSpam <= spam.getThreshold();
		errors.state(request, isAuditorSpam, "responsibility", "authenticated.request-auditor.error.spam-entity");
	}

	@Override
	public void create(final Request<RequestAuditor> request, final RequestAuditor entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
