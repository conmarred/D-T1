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

<acme:form readonly="false">
	<acme:form-textarea code="employer.justification.form.label.justification" path="justification" readonly="false"/>
	
	<acme:form-hidden path="applicationId"/>
	<acme:form-submit code="employer.justification.form.button.create" action="/employer/justification/create"/>
  	<acme:form-return code="employer.duties.form.button.return"/>
</acme:form>
