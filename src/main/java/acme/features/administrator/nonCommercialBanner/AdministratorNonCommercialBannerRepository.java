
package acme.features.administrator.nonCommercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.NonCommercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorNonCommercialBannerRepository extends AbstractRepository {

	@Query("select ncb from NonCommercialBanner ncb")
	Collection<NonCommercialBanner> findMany();

	@Query("select ncb from NonCommercialBanner ncb where ncb.id = ?1")
	NonCommercialBanner findOneById(int id);

}
