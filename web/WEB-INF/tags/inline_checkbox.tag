<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ attribute name="pageItem" required="true" type="org.rti.zcore.PageItem" %>
<%@ attribute name="inlineFieldIdentifier" required="false" type="java.lang.String" %>
<c:set var="field" value="${pageItem.form_field}" />
<c:choose>
    <c:when test="${! empty pageItem.childIdentifier1}">
    	<input type="checkbox" name="${inlineFieldIdentifier}${field.identifier}" id="${inlineFieldIdentifier}${field.identifier}" value="1" onclick="toggleField('checkbox','1','${pageItem.childIdentifier1}', '${field.identifier}');"/><bean:message key="${encounterForm.classname}.${field.identifier}" bundle="${encounterForm.classname}Messages" /> <c:if test='${field.required}'><span class="asterix">*</span></c:if>
    </c:when>
    <c:otherwise><%--<bean:define id="thisValue" name="${encounterForm.classname}" property="${field.identifier}"/>${thisValue}--%>
		<input type="checkbox" name="${inlineFieldIdentifier}${field.identifier}" id="${inlineFieldIdentifier}${field.identifier}" value="1"/>  <bean:message key="${encounterForm.classname}.${field.identifier}" bundle="${encounterForm.classname}Messages" /> <c:if test='${field.required}'><span class="asterix">*</span></c:if>
    </c:otherwise>
</c:choose>