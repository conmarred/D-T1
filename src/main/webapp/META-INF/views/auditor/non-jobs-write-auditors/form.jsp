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

<acme:form>
	<acme:form-textbox code="auditor.non-jobs-write-auditors.form.label.title" path="title"/>
	<acme:form-textbox code="auditor.non-jobs-write-auditors.form.label.reference" path="reference"/>
	<acme:form-textbox code="auditor.non-jobs-write-auditors.form.label.status" path="status"/>
	<acme:form-moment code="auditor.non-jobs-write-auditors.form.label.deadline" path="deadline"/>
	<acme:form-money code="auditor.non-jobs-write-auditors.form.label.salary" path="salary"/>
	<acme:form-url code="auditor.non-jobs-write-auditors.form.label.link" path="link"/>
	<acme:form-panel code="auditor.non-jobs-write-auditors.form.panel.descriptor">
		<acme:form-textbox code="auditor.non-jobs-write-auditors.form.panel.descriptor.description" path="descriptor.description"/>
	</acme:form-panel>
	
	<acme:form-panel code="auditor.non-jobs-write-auditors.form.panel.jobChallenge">
		<acme:form-textbox code="auditor.non-jobs-write-auditors.form.panel.jobChallenge.description" path="jobChallenge.description"/>
		<acme:form-textbox code="auditor.non-jobs-write-auditors.form.panel.jobChallenge.moreInfo" path="jobChallenge.moreInfo"/>
	</acme:form-panel>
	
	<acme:form-submit code="auditor.non-jobs-write-auditors.form.button.duties" action="/auditor/duty/list?id=${id}" method="get"/>
	<acme:form-submit code="auditor.non-jobs-write-auditors.form.button.list-audits-job" action="/auditor/audit-record/list-audits-job?id=${id}" method="get"/>
  	<acme:form-return code="auditor.non-jobs-write-auditors.form.button.return"/>
</acme:form>
