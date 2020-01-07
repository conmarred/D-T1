
package acme.features.administrator.investorRecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investorRecords.InvestorRecords;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorInvestorRecordsCreateService implements AbstractCreateService<Administrator, InvestorRecords> {

	@Autowired
	AdministratorInvestorRecordsRepository repository;


	@Override
	public boolean authorise(final Request<InvestorRecords> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<InvestorRecords> request, final InvestorRecords entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<InvestorRecords> request, final InvestorRecords entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "sector", "stars");
	}

	@Override
	public InvestorRecords instantiate(final Request<InvestorRecords> request) {
		InvestorRecords result;

		result = new InvestorRecords();

		return result;
	}

	@Override
	public void validate(final Request<InvestorRecords> request, final InvestorRecords entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<InvestorRecords> request, final InvestorRecords entity) {

		this.repository.save(entity);
	}

}
