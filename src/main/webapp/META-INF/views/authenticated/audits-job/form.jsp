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
	<acme:form-moment code="authenticated.audits-of-a-job.form.label.moment" path="moment"/>
	<acme:form-textbox code="authenticated.audits-of-a-job.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.audits-of-a-job.form.label.status" path="status"/>
	<acme:form-textarea code="authenticated.audits-of-a-job.form.label.body" path="body"/>
	<acme:form-textbox code="authenticated.audits-of-a-job.form.label.job.title" path="job.title"/>
	<acme:form-textbox code="authenticated.audits-of-a-job.form.label.workerUsername" path="workerUsername"/>
	
  	<acme:form-return code="authenticated.audits-of-a-job.form.button.return"/>
</acme:form>
