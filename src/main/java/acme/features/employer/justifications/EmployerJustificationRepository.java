
package acme.features.employer.justifications;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.spams.Spam;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerJustificationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findOne(int id);

	@Query("select count(a) > 0 from Application a where a.job.employer.id = ?1")
	boolean isCorrectEmployer(int id);

	@Query("select s from Spam s")
	Spam findSpam();
}
