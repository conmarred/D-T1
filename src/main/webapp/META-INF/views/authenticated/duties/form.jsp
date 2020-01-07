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
	<acme:form-textbox code="authenticated.duties.form.label.title" path="title"/>
	<acme:form-double code="authenticated.duties.form.label.time" path="time"/>
	<acme:form-textarea code="authenticated.duties.form.label.description" path="description"/>
	<jstl:if test="${command == 'show'}">
		<acme:form-textbox code="auditor.duties.form.label.job-title" path="jobTitle" readonly="true"/>
	</jstl:if>	
	
  	<acme:form-return code="authenticated.duties.form.button.return"/>
</acme:form>
