/*
 * AuthenticatedInvestorRecordsCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.anonymous.investorRecords;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investorRecords.InvestorRecords;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousInvestorRecordsListService implements AbstractListService<Anonymous, InvestorRecords> {

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

		request.unbind(entity, model, "name", "sector", "stars");
	}

	@Override
	public Collection<InvestorRecords> findMany(final Request<InvestorRecords> request) {
		assert request != null;
		Collection<InvestorRecords> result;
		result = this.repository.findManyAll();

		return result;
	}

}
