
package acme.features.authenticated.auditsJob;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.auditRecords.AuditRecordStatus;
import acme.entities.jobs.JobStatus;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditsOfAJobRepository extends AbstractRepository {

	@Query("select a from AuditRecord a where a.job.id = ?1 and a.status = ?2")
	Collection<AuditRecord> findAuditsOfAJob(int id, AuditRecordStatus ad);

	@Query("select a from AuditRecord a where a.id = ?1")
	AuditRecord findOne(int id);

	@Query("select count(j) > 0 from Job j where j.id = (select a.job.id from AuditRecord a where a.id = ?1 and a.status = ?2) and now()<j.deadline")
	boolean isCorrectAuditRecord(int id, AuditRecordStatus auditStatus);

	@Query("select count(j) > 0 from Job j where j.id = ?1 and j.status = ?2 and now()<j.deadline")
	boolean isCorrectJob(int idJob, JobStatus jobStatus);

}
