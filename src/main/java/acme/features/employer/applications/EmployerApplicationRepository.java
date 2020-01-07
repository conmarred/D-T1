
package acme.features.employer.applications;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findOne(int id);

	@Query("select count(a) > 0 from Application a where a.id = ?1 and a.job.employer.id = ?2")
	boolean isApplicationAtMyJob(int idApplication, int idEmployer);

	@Query("select a from Application a where a.job.employer.id = ?1 order by a.status, a.moment desc")
	Collection<Application> findApplicacionAtMyJobsByStatus(int id);

	@Query("select j.justification from Justification j where j.application.id = ?1")
	String findJustification(int id);
}
