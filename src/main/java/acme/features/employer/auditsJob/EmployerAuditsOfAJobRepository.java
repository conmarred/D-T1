
package acme.features.employer.auditsJob;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.auditRecords.AuditRecordStatus;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerAuditsOfAJobRepository extends AbstractRepository {

	@Query("select a from AuditRecord a where a.job.id = ?1 and ((a.status = ?2 and a.auditor.id = ?3) or a.status = ?4)")
	Collection<AuditRecord> findAuditsOfAJob(int id, AuditRecordStatus ad, int id1, AuditRecordStatus ad1);

	@Query("select a from AuditRecord a where a.id = ?1")
	AuditRecord findOne(int id);

	@Query("select count(j) > 0 from Job j where j.id = ?1 and j.employer.id = ?2")
	boolean isCorrectJob(int idJob, int idEmployer);

	@Query("select count(j) > 0 from Job j where j.id = (select a.job.id from AuditRecord a where a.id = ?1 and a.status = ?2) and j.employer.id = ?3")
	boolean isCorrectAuditRecord(int id, AuditRecordStatus auditRecordStatus, int idEmployer);
}
