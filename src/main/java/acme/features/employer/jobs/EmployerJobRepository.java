
package acme.features.employer.jobs;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.entities.spams.Spam;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.employer.id = ?1")
	Collection<Job> findMany(int id);

	@Query("select j from Job j where j.id = ?1")
	Job findOne(int id);

	@Query("select j from Job j where j.reference = ?1")
	Job findReference(String reference);

	@Query("select sum(d.time) from Duty d where d.descriptor.id = (select j.descriptor.id from Job j where j.id = ?1)")
	double sumTime(int id);

	@Query("select s from Spam s")
	Spam findSpam();

	@Query("select e from Employer e where e.id = ?1")
	Employer employerById(int id);

	@Query("select count(d) > 0 from Duty d where d.descriptor.id = (select j.descriptor.id from Job j where j.id = ?1)")
	boolean existsDutiesOfAJob(int id);

	@Query("select count(a) > 0 from Application a where a.job.id = ?1")
	boolean existsApplicationsJob(int id);

	@Query("select d from Duty d where d.descriptor.id = ?1")
	Collection<Duty> dutiesOfADescriptor(int id);

	@Query("select a from AuditRecord a where a.job.id = ?1")
	Collection<AuditRecord> auditsOfAJob(int id);

	@Query("select count(j) > 0 from Job j where j.id = ?1 and j.employer.id = ?2")
	boolean isEmployer(int idJob, int idEmployer);

	@Query("select d from Duty d where d.descriptor.id = ?1")
	Collection<Duty> dutiesOfAJob(int idDescriptor);

}
