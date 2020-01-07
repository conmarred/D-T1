
package acme.entities.roles;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "user_account_id")
})
public class Worker extends UserRole {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				qualifications;

	@NotBlank
	private String				skills;

}
