
package acme.features.employer.duties;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.descriptors.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
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

}
