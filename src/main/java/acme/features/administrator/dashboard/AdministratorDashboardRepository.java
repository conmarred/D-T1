
package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select 1.0 * count(j) / (select count(j) from Job j) from Job j where j.solim.description !='' ")
	Double ratioOfJobHaveSolim();

	@Query("select 1.0 * count(d) / (select count(d) from Answer d) from Answer d where d.keylet != '' ")
	Double ratioOfSolimsThatHaveAKeylet();

	@Query("select 1.0 * count(a) / (select count(a) from Application a) from Application a where a.answer.password != '' ")
	Double ratioOfApplicationsThatHaveAPassword();

}
