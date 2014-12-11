<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%--<html>--%>
<%--<head>--%>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=utf-8">--%>
    <%--<title><spring:message code="message.title"/></title>--%>
<%--</head>--%>
<%--<body dir="rtl">--%>

<c:forEach var="rabbi" items="${rabbis}">

    <h2>${rabbi.name}</h2>
    <table id="details">
        <tr id="num">
            <td><spring:message code="message.num" /></td>
            <td>${rabbi.num}</td>
        </tr>
        <tr id="name">
            <td><spring:message code="message.name" /></td>
            <td>${rabbi.name}</td>
        </tr>
        <tr id="nickname">
            <td><spring:message code="message.nickname" /></td>
            <td>${rabbi.nickname}</td>
        </tr>
        <tr id="born">
            <td><spring:message code="message.born" /></td>
            <td>${rabbi.born}</td>
        </tr>
        <tr id="bornGeorgian">
            <td><spring:message code="message.bornGeorgian" /></td>
            <td>${rabbi.bornGeorgian}</td>
        </tr>
        <tr id="birth_location">
            <td><spring:message code="message.birth_location" /></td>
            <td>${rabbi.birthLocation}</td>
        </tr>
        <tr id="died">
            <td><spring:message code="message.died" /></td>
            <td>${rabbi.died}</td>
        </tr>
        <tr id="diedGeorgian">
            <td><spring:message code="message.diedGeorgian" /></td>
            <td>${rabbi.diedGeorgian}</td>
        </tr>
        <tr id="death_location">
            <td><spring:message code="message.death_location" /></td>
            <td>${rabbi.deathLocation}</td>
        </tr>
    </table>

    <h3><spring:message code="message.books" /></h3>
    <table id="books">
        <c:forEach var="book" items="${rabbi.books}">
            <tr class="book">
                <td>${book.name}</td>
            </tr>
        </c:forEach>
    </table>

    <h3><spring:message code="message.teachers" /></h3>
    <table id="teachers">
        <c:forEach var="teacher" items="${rabbi.teachers}">
            <tr>
                <td>${teacher.name}</td>
            </tr>
        </c:forEach>
    </table>

    <h3><spring:message code="message.students" /></h3>
    <table id="students">
        <c:forEach var="student" items="${rabbi.students}">

            <tr>
                <td>${student.name}</td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <a href='/mvc1/update/<c:out value="${rabbi.num}"/>'><spring:message code="message.updateRabbi"/></a>
    <br/>
    <a href='/mvc1/remove/<c:out value="${rabbi.num}"/>'><spring:message code="message.removeRabbi"/></a>
</c:forEach>
<%--</body>--%>
<%--</html>--%>