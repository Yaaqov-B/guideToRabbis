<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" %>


<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Submitted Rabbi Information</h2>
<table>
    <tr>
        <td>Name</td>
        <td>${name}</td>
    </tr>
    <tr>
        <td>ID</td>
        <td>${id}</td>
    </tr>
    <%--<c:forEach var="book" items="${books}" varStatus="idx">--%>
        <%--<tr>--%>
            <%--<td><form:label path="rabbi.books[${idx.index}].name"><spring:message code="message.name" /></form:label></td>--%>
            <%--<td><form:input path="rabbi.books[${idx.index}].name" /></td>--%>
        <%--</tr>--%>
    <%--</c:forEach>--%>
    <c:forEach var="book" items="${books}">
        <tr>
            <td><label value="${book.name}"><spring:message code="message.name" /></label></td>
            <td><input value="${book.name}" /></td>
        </tr>
    </c:forEach>

    <c:forEach var="student" items="${students}">
        <tr>
            <td><label value="${student.id}"><spring:message code="message.name" /></label></td>
            <td><input value="${student.id}" /></td>
        </tr>
        <tr>
            <td><label value="${student.name}"><spring:message code="message.name" /></label></td>
            <td><input value="${student.name}" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>