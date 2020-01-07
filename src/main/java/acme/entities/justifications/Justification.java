
package acme.entities.justifications;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import acme.entities.applications.Application;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Justification extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				justification;

	// Relationships ----------------------------------------------------------

	@Valid
	@OneToOne(optional = false)
	private Application			application;

}
