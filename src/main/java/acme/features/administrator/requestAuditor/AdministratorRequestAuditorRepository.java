
package acme.features.administrator.requestAuditor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requestAuditor.AuditorStatus;
import acme.entities.requestAuditor.RequestAuditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorRequestAuditorRepository extends AbstractRepository {

	@Query("select a from RequestAuditor a where a.status=?1")
	Collection<RequestAuditor> findMany(AuditorStatus status);

	@Query("select a from RequestAuditor a where a.id = ?1")
	RequestAuditor findOneById(int id);

}
