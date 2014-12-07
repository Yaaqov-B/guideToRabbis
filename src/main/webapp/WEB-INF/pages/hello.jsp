<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="he">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body dir="rtl">
	<h1>${message}</h1>
    <spring:message code="message.hi" />
</body>
<form method="post" action="/mvc1/">
    <td><label id="search_label"><spring:message code="message.search"/></label></td>
    <td><input id="search_input" name="search"/></td>
    <button type="submit">send</button>
</form>
</html>