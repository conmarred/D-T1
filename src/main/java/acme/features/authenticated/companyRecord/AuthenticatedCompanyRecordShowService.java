
package acme.features.authenticated.companyRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companyRecords.CompanyRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedCompanyRecordShowService implements AbstractShowService<Authenticated, CompanyRecord> {

	@Autowired
	AuthenticatedCompanyRecordRepository repository;


	@Override
	public boolean authorise(final Request<CompanyRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<CompanyRecord> request, final CompanyRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("incorporatedName", entity.incorporatedCompany());

		request.unbind(entity, model, "name", "sector", "nameCEO", "activities", "webSite", "email", "stars", "phoneNumber");

	}

	@Override
	public CompanyRecord findOne(final Request<CompanyRecord> request) {
		assert request != null;

		CompanyRecord res;
		res = this.repository.findOneById(request.getModel().getInteger("id"));
		return res;
	}

}
