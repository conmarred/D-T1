
package acme.features.auditor.nonJobsWriteAuditor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.entities.jobs.JobStatus;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface NonJobsWriteAuditorRepository extends AbstractRepository {

	@Query("select j from Job j where j.id not in(select a.job.id from AuditRecord a where a.auditor.id = ?1) and j.status = ?2 and now()<=j.deadline")
	Collection<Job> jobsAuditorHasNotWrittenAudit(int id, JobStatus jobStatus);

	@Query("select j from Job j where j.id = ?1")
	Job findOne(int id);

	@Query("select count(j) > 0 from Job j where j.id = ?1 and j.status = ?2")
	boolean isDraftJob(int idJob, JobStatus js);

}
