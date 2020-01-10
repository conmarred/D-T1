<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="${status == 'PUBLISHED' and command == 'show'}">
	<acme:form-textbox code="employer.job.form.label.reference" path="reference" placeholder="EEEE-JJJJ" readonly="${command == 'show' or command == 'update'}"/>
	<acme:form-textbox code="employer.job.form.label.title" path="title"/>
	<jstl:if test="${command != 'create' }">
	<acme:form-select code="employer.job.form.label.status" path="status">
		<acme:form-option code="employer.job.form.status.DRAFT" value="DRAFT"/>
		<acme:form-option code="employer.job.form.status.PUBLISHED" value="PUBLISHED" selected="${status == 'PUBLISHED'}"/>
	</acme:form-select>
	</jstl:if>
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="employer.job.form.label.salary" path="salary"/>
	<acme:form-url code="employer.job.form.label.link" path="link"/>
	<acme:form-panel code="employer.job.form.panel.descriptor">
		<acme:form-textarea code="employer.job.form.panel.descriptor.description" path="descriptor.description"/>
	</acme:form-panel>
	
	<acme:form-panel code="employer.job.form.panel.solim">
		<acme:form-textarea code="employer.job.form.panel.solim.description" path="solim.description"/>
		<acme:form-url code="employer.job.form.panel.solim.keylet" path="solim.keylet"/>
	</acme:form-panel>
	<acme:form-submit test="${haveApplications == false and command == 'show'}" code="employer.job.button.delete" action="/employer/job/delete" />
	<jstl:if test="${haveApplications and command == 'show'}">
		<acme:message code="employer.job.message.no-delete"/> <br/><br/>
	</jstl:if>
	<acme:form-submit test="${command == 'update' or (command == 'show' and status == 'DRAFT')}" code="employer.job.button.update" action="/employer/job/update"/>
	<acme:form-submit test="${command == 'show'}" code="employer.job.form.button.duties" action="/employer/duty/list?idJob=${id}" method="get"/>
	<acme:form-submit test="${command == 'show' and status == 'DRAFT'}" code="employer.job.form.button.create-duty" action="/employer/duty/create?descriptorId=${descriptorId}" method="get"/>
	<acme:form-submit test="${command == 'show'}" code="employer.job.form.button.list-audits-job" action="/employer/audit-record/list-audits-job?idJob=${id}" method="get"/>
	<acme:form-submit test="${command == 'create'}" code="employer.job.button.create" action="/employer/job/create"/>
  	<acme:form-return code="employer.job.form.button.return"/>
</acme:form>


