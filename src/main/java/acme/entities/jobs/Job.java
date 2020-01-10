
package acme.entities.jobs;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.descriptors.Descriptor;
import acme.entities.roles.Employer;
import acme.entities.solim.Solim;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "status, deadline"), @Index(columnList = "status")
})
public class Job extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Length(min = 5, max = 10)
	@Column(unique = true)
	private String				reference;

	private JobStatus			status;

	@NotBlank
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@Valid
	@NotNull
	private Money				salary;

	@URL
	private String				link;

	// Relationships ----------------------------------------------------------

	@Valid
	@ManyToOne(optional = false)
	private Employer			employer;

	@Valid
	@OneToOne(optional = false)
	private Descriptor			descriptor;

	@Valid
	@OneToOne(optional = true)
	private Solim				solim;

}
