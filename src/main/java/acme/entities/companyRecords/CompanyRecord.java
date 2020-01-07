
package acme.entities.companyRecords;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CompanyRecord extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				name;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				nameCEO;

	@NotBlank
	private String				activities;

	@NotBlank
	@URL
	private String				webSite;

	@NotBlank
	@Email
	private String				email;

	@Range(min = 0, max = 5)
	private int					stars;

	private boolean				incorporated;

	@Pattern(regexp = "(\\+[1-9][0-9]{1,2}\\s)?(\\([0-9]{1,4}\\)\\s)?[0-9]{6,10}")
	@NotBlank
	private String				phoneNumber;


	@Transient
	public String incorporatedCompany() {
		StringBuilder res = new StringBuilder();
		if (this.incorporated == true) {
			res.append(this.name);
			res.append(", Inc.");
		} else if (this.incorporated == false) {
			res.append(this.name);
			res.append(", LLC.");
		}
		return res.toString();
	}

}
