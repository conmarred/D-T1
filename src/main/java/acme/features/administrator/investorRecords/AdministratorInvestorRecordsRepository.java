
package acme.features.administrator.investorRecords;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investorRecords.InvestorRecords;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorInvestorRecordsRepository extends AbstractRepository {

	@Query("select ir from InvestorRecords ir where ir.id = ?1")
	InvestorRecords findOneById(int id);

	@Query("select ir from InvestorRecords ir")
	Collection<InvestorRecords> findManyAll();

}
