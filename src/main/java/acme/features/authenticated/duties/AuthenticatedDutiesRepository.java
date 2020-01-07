
package acme.features.authenticated.duties;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duties.Duty;
import acme.entities.jobs.JobStatus;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedDutiesRepository extends AbstractRepository {

	@Query("select d from Duty d where d.descriptor.id = (select j.descriptor.id from Job j where j.id = ?1)")
	Collection<Duty> findMany(int id);

	@Query("select d from Duty d where d.id = ?1")
	Duty findOne(int id);

	@Query("select j.title from Job j where j.descriptor.id = ?1")
	String findJobTitle(int id);

	@Query("select count(d) > 0 from Duty d where d.descriptor.id = (select j.descriptor.id from Job j where j.id = ?1 and now()<j.deadline and j.status = ?2)")
	boolean isCorrectDuty(int idDescriptor, JobStatus jobStatus);
}
