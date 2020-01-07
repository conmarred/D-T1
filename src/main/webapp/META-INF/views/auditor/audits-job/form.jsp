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
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="auditor.audits-of-a-job.form.label.moment" path="moment" readonly="true"/>
		<acme:form-textbox code="auditor.audits-of-a-job.form.label.job.title" path="job.title" readonly="true"/>
		<acme:form-textbox code="auditor.audits-of-a-job.form.label.username" path="username" readonly="true"/>
		<acme:form-select code="auditor.audits-of-a-job.form.label.status" path="status">
			<acme:form-option code="auditor.audits-of-a-job.form.label.status-DRAFT" value="DRAFT"/>
			<acme:form-option code="auditor.audits-of-a-job.form.label.status-PUBLISHED" value="PUBLISHED" selected="${status == 'PUBLISHED'}"/>
		</acme:form-select>
	</jstl:if>
	<acme:form-textbox code="auditor.audits-of-a-job.form.label.title" path="title" />
	<acme:form-textarea code="auditor.audits-of-a-job.form.label.body" path="body" />
		
	<acme:form-hidden path="idJob" />
	<acme:form-submit test="${command == 'update' or (command == 'show' and status == 'DRAFT')}" code="auditor.audits-for-this-job.form.button.update" action="/auditor/audit-record/update" />
	<acme:form-submit test="${command == 'create'}" code="auditor.audits-for-this-job.form.button.audit" action="/auditor/audit-record/create" />

	<acme:form-return code="auditor.audits-of-a-job.form.button.return" />
</acme:form>
