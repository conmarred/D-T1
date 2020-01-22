<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not application any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="employer.application.form.label.reference" path="reference" placeholder="EEEE-JJJJ"/>
	<acme:form-textbox code="employer.application.form.label.job.title" path="job.title"/>
	<acme:form-textbox code="employer.application.form.label.username" path="username"/>
	<acme:form-moment code="employer.application.form.label.moment" path="moment"/>
	<acme:form-textbox code="employer.application.form.label.status" path="status"/>
	<acme:form-textbox code="employer.application.form.label.statement" path="statement"/>
	<acme:form-textarea code="employer.application.form.label.skills" path="skills"/>
	<acme:form-textarea code="employer.application.form.label.qualification" path="qualifications"/>
	
	<acme:form-panel code="employer.application.form.panel.answer">
  		<acme:form-textarea code="employer.application.form.panel.answer.answer" path="answer.answer"/>
  		<acme:form-textbox code="employer.application.form.panel.answer.password" path="answer.password"/>
  		<acme:form-url code="employer.application.form.panel.answer.keylet" path="answer.keylet"/>
   	</acme:form-panel>
	
	<jstl:if test="${status == 'REJECTED'}">
		<acme:form-textarea code="employer.application.form.label.justification" path="justification.justification"/>
	</jstl:if>
	
	<jstl:if test="${status == 'PENDING' }">
		<acme:form-submit test="${command == 'show'}" code="employer.application.form.button.accept" action="/employer/application/update"/>
		<acme:form-submit test="${command == 'show'}" code="employer.application.form.button.reject" action="/employer/justification/create?applicationId=${id}" method="get"/>
	</jstl:if>
	
	<acme:form-return code="employer.application.form.button.return"/>

</acme:form>
