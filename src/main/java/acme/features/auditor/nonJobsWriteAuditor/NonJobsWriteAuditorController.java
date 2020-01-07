
package acme.features.auditor.nonJobsWriteAuditor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/auditor/non-job/")
public class NonJobsWriteAuditorController extends AbstractController<Auditor, Job> {

	@Autowired
	NonJobsWriteAuditorListService	listService;

	@Autowired
	NonJobsWriteAuditorShowService	showService;


	@PostConstruct
	private void inicialise() {
		super.addCustomCommand(CustomCommand.LIST_NON_JOBS_WRITE_AUDITOR, BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
