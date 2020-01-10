
package acme.entities.solim;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Solim extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@Length(max = 256)
	private String				description;

	@URL
	private String				keylet;

	// Relationships ----------------------------------------------------------

}
