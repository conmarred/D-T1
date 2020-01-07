
package acme.features.worker.applications;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.jobs.JobStatus;
import acme.entities.roles.Worker;
import acme.entities.spams.Spam;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findOneById(int id);

	@Query("select a from Application a where a.worker.id = ?1")
	Collection<Application> findManyAll(int id);

	@Query("select count(a) > 0 from Application a where a.id = ?1 and a.worker.id = ?2")
	boolean isAuthorisedShow(int idA, int idU);

	@Query("select count(j) > 0 from Job j where j.id = ?1 and j.status = ?2 and now()<=j.deadline")
	boolean isAuthoriseCreate(int idJob, JobStatus js);

	@Query("select j from Job j where j.id = ?1 ")
	Job findJobApplied(int id);

	@Query("select w from Worker w where w.id = ?1 ")
	Worker findWorker(int id);

	@Query("select j.justification from Justification j where j.application.id = ?1")
	String findJustification(int id);

	@Query("select s from Spam s")
	Spam findSpam();

	@Query("select a from Application a where a.reference = ?1")
	Application findReference(String reference);

}
