<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="false">
	<acme:list-column code="administrator.request-auditor.list.label.firm" path="firm" width="20%"/>
	<acme:list-column code="administrator.request-auditor.list.label.responsibility" path="responsibility" width="20%"/>
	<acme:list-column code="administrator.request-auditor.list.label.username" path="username" width="20%"/>		
</acme:list>