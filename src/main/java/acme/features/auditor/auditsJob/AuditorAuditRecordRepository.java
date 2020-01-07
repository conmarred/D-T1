
package acme.features.auditor.auditsJob;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.auditRecords.AuditRecordStatus;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.entities.spams.Spam;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRecordRepository extends AbstractRepository {

	@Query("select a from AuditRecord a where a.job.id = ?1 and ((a.status = ?2 and a.auditor.id = ?3) or a.status= ?4)")
	Collection<AuditRecord> findAuditsOfAJob(int id, AuditRecordStatus ad, int id1, AuditRecordStatus ad1);

	@Query("select a from AuditRecord a where a.id = ?1")
	AuditRecord findOne(int id);

	@Query("select au from Auditor au where au.id = ?1")
	Auditor findAuditor(int id);

	@Query("select j from Job j where j.id = ?1")
	Job findJobAudited(int id);

	@Query("select s from Spam s")
	Spam findSpam();

}
