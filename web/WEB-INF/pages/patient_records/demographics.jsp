<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri='/WEB-INF/tlds/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:choose>
    <c:when test="${! empty param.template}">
    <c:set var="template" value="/WEB-INF/templates/${appTemplateDir}/template-print.jsp"/>
    </c:when>
    <c:otherwise>
    <c:set var="template" value="/WEB-INF/templates/${appTemplateDir}/template.jsp"/>
    </c:otherwise>
</c:choose>
<template:insert template='${template}'>
<template:put name='title' direct='true'>Patient Demographics</template:put>
<template:put name='content' direct='true'>
<c:choose>
    <c:when test="${! empty param.template}">
    <div id="form-print">
    </c:when>
    <c:otherwise>
    <div id="demoSum">
    </c:otherwise>
</c:choose>
<h2>Demographics Summary</h2>
<logic:present name="encounterRecord">
<jsp:include page="patient_record.jsp"/>
</logic:present>
</div>
</template:put>
</template:insert>