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
	<acme:form-textbox code="anonymous.announcement.form.label.title" path="title"/>
	<acme:form-moment code="anonymous.announcement.form.label.moment" path="moment"/>
	<acme:form-url code="anonymous.announcement.form.label.link" path="link"/>
	<acme:form-textarea code="anonymous.announcement.form.label.description" path="description"/>
	
  	<acme:form-return code="anonymous.announcement.form.button.return"/>
</acme:form>
