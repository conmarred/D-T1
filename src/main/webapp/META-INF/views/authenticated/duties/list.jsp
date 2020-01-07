
<%--
- list.jsp
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

<acme:list readonly="false">
	<acme:list-column code="authenticated.duties.list.label.title" path="title" width="20%"/>
	<acme:list-column code="authenticated.duties.list.label.time" path="time" width="20%"/>
	<acme:list-column code="authenticated.duties.list.label.job-title" path="jobTitle" width="20%"/>
</acme:list>

	<acme:form-return code="authenticated.duties.list.button.return"/>


