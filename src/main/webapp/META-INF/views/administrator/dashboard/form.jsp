<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="false">

<acme:form-double code="administrator.dashboard.form.label.ratioOfJobHaveSolim" path="ratioOfJobHaveSolim" />
<acme:form-double code="administrator.dashboard.form.label.ratioOfSolimsThatHaveAKeylet" path="ratioOfSolimsThatHaveAKeylet" />
<acme:form-double code="administrator.dashboard.form.label.ratioOfApplicationsThatHaveAPassword" path="ratioOfApplicationsThatHaveAPassword" />

<acme:form-return code="administrator.dashboard.button.return" />
</acme:form>