
package acme.features.authenticated.requests;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requests.Requests;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedRequestRepository extends AbstractRepository {

	@Query("Select r from Requests r where r.id = ?1")
	Requests findOneById(int id);

	@Query("select r from Requests r where now()<=r.deadline")
	Collection<Requests> findManyAll();
}
