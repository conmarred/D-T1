
package acme.features.administrator.commercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.CommercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCommercialBannerRepository extends AbstractRepository {

	@Query("select cb from CommercialBanner cb")
	Collection<CommercialBanner> findMany();

	@Query("select cb from CommercialBanner cb where cb.id = ?1")
	CommercialBanner findOneById(int id);

}
