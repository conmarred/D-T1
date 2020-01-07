
package acme.entities.spams;

import java.beans.Transient;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Spam extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				spam;

	@Range(min = 0, max = 100)
	@NotNull
	private double				threshold;


	@Transient
	public String threshold() {
		StringBuilder res = new StringBuilder();
		res.append(this.threshold);
		res.append("%");
		return res.toString();
	}

}
