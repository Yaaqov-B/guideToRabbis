<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Book Information</h2>
<form:form method="POST" action="/mvc1/addBook">
    <table>
        <tr>
            <td><form:label path="name"><spring:message code="message.name" /></form:label></td>
            <td><form:input path="name" /></td>
        </tr>
        <%--<tr>--%>
            <%--<td><form:label path="id"><spring:message code="message.id" /></form:label></td>--%>
            <%--<td><form:input path="id" /></td>--%>
        <%--</tr>--%>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>