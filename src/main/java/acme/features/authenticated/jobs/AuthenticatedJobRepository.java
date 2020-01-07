
package acme.features.authenticated.jobs;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.entities.jobs.JobStatus;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.status = ?1 and now()<=j.deadline")
	Collection<Job> findActiveJobs(JobStatus status);

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select count(j) > 0 from Job j where j.id = ?1 and j.status = ?2 and now()<=j.deadline")
	boolean isDraftJob(int idJob, JobStatus js);
}
