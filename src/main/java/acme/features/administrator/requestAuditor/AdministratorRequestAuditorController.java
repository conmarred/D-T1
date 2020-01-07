
package acme.features.administrator.requestAuditor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.requestAuditor.RequestAuditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/request-auditor/")
public class AdministratorRequestAuditorController extends AbstractController<Administrator, RequestAuditor> {

	@Autowired
	private AdministratorRequestAuditorListService		listService;

	@Autowired
	private AdministratorRequestAuditorShowService		showService;

	@Autowired
	private AdministratorRequestAuditorUpdateService	updateService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
