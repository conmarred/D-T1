
package acme.features.anonymous.investorRecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investorRecords.InvestorRecords;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousInvestorRecordsShowService implements AbstractShowService<Anonymous, InvestorRecords> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousInvestorRecordsRepository repository;

	// AbstractCreateService<Authenticated, InvestorRecords> ---------------------------


	@Override
	public boolean authorise(final Request<InvestorRecords> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<InvestorRecords> request, final InvestorRecords entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "sector", "investingStatement", "stars");
	}

	@Override
	public InvestorRecords findOne(final Request<InvestorRecords> request) {
		assert request != null;

		InvestorRecords result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
