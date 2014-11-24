<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><spring:message code="message.title"/></title>
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
</head>
<body dir="rtl">
<h2><spring:message code="message.addRabbi"/> </h2>
<form:form method="POST" action="/mvc1/addRabbi">
    <table>
        <tr>
            <td><form:label path="name"><spring:message code="message.name" /></form:label></td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td><form:label path="num"><spring:message code="message.id" /></form:label></td>
            <td><form:input path="num" /></td>
        </tr>
        <tr>
                <td><form:label path="books[0].name"><spring:message code="message.book" /></form:label></td>
                <td><form:input path="books[0].name"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <button id="add" type="button">add</button>
            </td>
        </tr>
        <script type="text/javascript">
            $(function() {
                var index = 1;

                var path= 'path="books[' + index + '].name"';
                var html = '<tr><td><form:label path="books[1].name">';
                html += '<spring:message code="message.book"/>';
                html += '</form:label></td>';
                html += '<td><form:input path="books[1].name"/></td></tr>';
                var parsedHtml = $.parseHTML(html);
                $("#add").off("click").on("click", function() {
                    $(this).before(function(){
                        var fixHtml = html.replace(/1/g, index);
                        alert(fixHtml + index);
                        return fixHtml;
                    });
                    index++;
                    return false;
                });
            });
        </script>
        <tr>
            <td><form:label path="students[0].name"><spring:message code="message.student" /></form:label></td>
            <td><form:input path="students[0].name"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>