<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="false">
	<acme:list-column code="administrator.investor-records.list.label.name" path="name" width="20%"/>
	<acme:list-column code="administrator.investor-records.list.label.sector" path="sector" width="20%"/>	
	<acme:list-column code="administrator.investor-records.list.label.stars" path="stars" width="20%"/>	
</acme:list>