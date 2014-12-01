<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body dir="rtl">
	<h1>${message}</h1>
    <spring:message code="message.hi" />
</body>
<form:form method="post" action="/mvc1/search">
    <td><form:label path="search">search</form:label></td>
    <td><form:input path="search"/></td>
    <button type="submit">send</button>
</form:form>
</html>