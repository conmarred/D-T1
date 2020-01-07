
package acme.entities.XXXXJobChallenge;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class JobChallenge extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@Length(max = 30)
	private String				description;

	@URL
	private String				moreInfo;

	// Relationships ----------------------------------------------------------

}
