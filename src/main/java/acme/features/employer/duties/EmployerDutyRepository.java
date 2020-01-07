
package acme.features.employer.duties;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.descriptors.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.jobs.JobStatus;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDutyRepository extends AbstractRepository {

	@Query("select d from Duty d where d.descriptor.id = (select j.descriptor.id from Job j where j.id = ?1)")
	Collection<Duty> findMany(int id);

	@Query("select d from Duty d where d.id = ?1")
	Duty findOne(int id);

	@Query("select d from Descriptor d where d.id = ?1")
	Descriptor findDescriptorOfJob(int id);

	@Query("select j from Job j where j.descriptor.id = ?1")
	Job findJob(int idDescriptor);

	@Query("select count(j) > 0 from Job j where j.id = ?1 and j.employer.id = ?2")
	boolean isEmployer(int idJob, int idEmployer);

	@Query("select count(j) > 0 from Job j where j.descriptor.id = (select d.descriptor.id from Duty d where d.id = ?1) and j.employer.id = ?2")
	boolean isCorrectDuty(int id, int idEmployer);

	@Query("select count(j) > 0 from Job j where j.descriptor.id = ?1 and j.status = ?2 and j.employer.id = ?3")
	boolean isCorrectDescriptor(int idDescriptor, JobStatus jobStatus, int idEmployer);

}
