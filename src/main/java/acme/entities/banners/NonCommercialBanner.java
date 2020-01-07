
package acme.entities.banners;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NonCommercialBanner extends Banner {

	private static final long	serialVersionUID	= 1L;

	private String				jingle;

}
