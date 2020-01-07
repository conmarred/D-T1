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

	<jstl:if test="${command != 'create' }">
		<acme:form-textbox code="administrator.company-record.form.label.Nameincorporated" path="incorporatedName" readonly="true" />
	</jstl:if>
	<acme:form-textbox code="administrator.company-record.form.label.name" path="name" />
	<acme:form-textbox code="administrator.company-record.form.label.sector" path="sector" />
	<acme:form-textbox code="administrator.company-record.form.label.nameCEO" path="nameCEO" />
	<acme:form-textarea code="administrator.company-record.form.label.activities" path="activities" />
	<acme:form-url code="administrator.company-record.form.label.webSite" path="webSite" />
	<acme:form-textbox code="administrator.company-record.form.label.email" path="email" />
	<acme:form-checkbox code="administrator.company-record.form.label.incorporated" path="incorporated" />
	<acme:form-integer code="administrator.company-record.form.label.stars" path="stars" />
	<acme:form-textbox code="administrator.company-record.form.label.phoneNumber" path="phoneNumber" />


	<acme:form-submit test="${command == 'show'}" code="administrator.company-record.button.update"
		action="/administrator/company-record/update" />
	<acme:form-submit test="${command == 'show'}" code="administrator.company-record.button.delete"
		action="/administrator/company-record/delete" />
	<acme:form-submit test="${command == 'update'}" code="administrator.company-record.button.update"
		action="/administrator/company-record/update" />
	<acme:form-submit test="${command == 'delete'}" code="administrator.company-record.button.delete"
		action="/administrator/company-record/delete" />
	<acme:form-submit test="${command == 'create'}" code="administrator.company-record.button.create"
		action="/administrator/company-record/create" />
	<acme:form-return code="administrator.company-record.button.return" />
</acme:form>
