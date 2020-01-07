
package acme.features.employer.jobs;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.jobs.JobStatus;
import acme.entities.roles.Employer;
import acme.entities.spams.Spam;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert request != null;
		assert request != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("haveApplications", this.repository.existsApplicationsJob(entity.getId()));

		request.unbind(entity, model, "reference", "status", "title", "deadline", "salary", "link", "descriptor.description", "jobChallenge.description", "jobChallenge.moreInfo");
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job res = this.repository.findOne(request.getModel().getInteger("id"));
		return res;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (entity.getStatus().equals(JobStatus.PUBLISHED)) {

			Spam spam = this.repository.findSpam();
			String[] spams = spam.getSpam().split(",");

			int sumSpam = 0;
			Collection<Duty> listDuties = this.repository.dutiesOfADescriptor(entity.getDescriptor().getId());
			if (!listDuties.isEmpty()) {
				double time = this.repository.sumTime(entity.getId());
				boolean is100 = time == 100;
				errors.state(request, is100, "descriptor.description", "employer.job.error.time");
				for (Duty d : listDuties) {
					int spamTitleDuty = (int) (Arrays.asList(spams).stream().filter(x -> d.getTitle().toLowerCase().contains(x.toLowerCase().trim())).count() * 100 / spams.length);
					int spamDescriptionDuty = (int) (Arrays.asList(spams).stream().filter(x -> d.getDescription().toLowerCase().contains(x.toLowerCase().trim())).count() * 100 / spams.length);
					sumSpam += spamTitleDuty + spamDescriptionDuty;
				}
			} else {
				errors.state(request, !listDuties.isEmpty(), "descriptor.description", "employer.job.error.duties");
			}

			boolean descriptionNotBlank = entity.getDescriptor().getDescription().isEmpty();
			errors.state(request, !descriptionNotBlank, "descriptor.description", "employer.job.error.descriptor-description");

			int isSpamReference = (int) (Arrays.asList(spams).stream().filter(x -> entity.getReference().toLowerCase().contains(x.toLowerCase().trim())).count() * 100 / spams.length);
			int isSpamTitle = (int) (Arrays.asList(spams).stream().filter(x -> entity.getTitle().toLowerCase().contains(x.toLowerCase().trim())).count() * 100 / spams.length);
			int isSpamDescription = (int) (Arrays.asList(spams).stream().filter(x -> entity.getDescriptor().getDescription().toLowerCase().contains(x.toLowerCase().trim())).count() * 100 / spams.length);
			int isSpamJobDescription = (int) (Arrays.asList(spams).stream().filter(x -> entity.getJobChallenge().getDescription().toLowerCase().contains(x.toLowerCase().trim())).count() * 100 / spams.length);
			int isSpamJobMoreInfo = (int) (Arrays.asList(spams).stream().filter(x -> entity.getJobChallenge().getMoreInfo().toLowerCase().contains(x.toLowerCase().trim())).count() * 100 / spams.length);

			sumSpam += isSpamReference + isSpamTitle + isSpamDescription + isSpamJobDescription + isSpamJobMoreInfo;
			boolean isSpamEntity = sumSpam <= spam.getThreshold();
			errors.state(request, isSpamEntity, "status", "employer.job.error.entity-spam");
		}

		if (!errors.hasErrors("deadline")) {
			Date dateNow = Date.from(Instant.now());
			boolean deadlineAfterNow = entity.getDeadline().after(dateNow);
			errors.state(request, deadlineAfterNow, "deadline", "employer.job.error.deadline");
		}

		if (!errors.hasErrors("salary")) {
			boolean correctCurrency = entity.getSalary().getCurrency().equals("EUR") || entity.getSalary().getCurrency().equals("€");
			errors.state(request, correctCurrency, "salary", "employer.job.error.correct-currency");
		}

		if (!entity.getJobChallenge().getMoreInfo().isEmpty()) {
			boolean correctJobChallenge = !entity.getJobChallenge().getDescription().isEmpty();
			errors.state(request, correctJobChallenge, "jobChallenge.description", "employer.jobChallenge.error.correct-job-challenge");
		}
	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity.getDescriptor());
		this.repository.save(entity.getJobChallenge());
		this.repository.save(entity);

	}

}
