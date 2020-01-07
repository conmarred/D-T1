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

<acme:form readonly="${isPublished}">
	<acme:form-textbox code="employer.duties.form.label.title" path="title"/>
	<acme:form-double code="employer.duties.form.label.time" path="time"/>
	<acme:form-textarea code="employer.duties.form.label.description" path="description"/>
	<jstl:if test="${command == 'show'}">
		<acme:form-textbox code="auditor.duties.form.label.job-title" path="jobTitle" readonly="true"/>
	</jstl:if>	
	
	<acme:form-submit test="${(command == 'show' or command == 'update') and !isPublished}" code="employer.duties.form.button.update" action="/employer/duty/update"/>
	<acme:form-submit test="${(command == 'show' or command == 'update') and !isPublished}" code="employer.duties.form.button.delete" action="/employer/duty/delete"/>
	<acme:form-submit test="${command == 'create'}" code="employer.duties.form.button.create" action="/employer/duty/create?descriptorId=${descriptorId}"/>
  	<acme:form-return code="employer.duties.form.button.return"/>
</acme:form>
