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
    <script type="text/javascript">
        $(function() {
            var bookIndex = 1;
            var html = '<tr class="book"><td><form:label path="books[99].name">';
            html += '<spring:message code="message.book"/>';
            html += '</form:label></td>';
            html += '<td><form:input path="books[99].name"/></td></tr>';
            $("#addBook").off("click").on("click", function() {
                $(".book:last").after(function(){
                    var fixHtml = html.replace(/99/g, bookIndex);
                    return fixHtml;
                });
                bookIndex++;
                return false;
            });
            $("#removeBook").off("click").on("click", function() {
                if (bookIndex > 1){
                    $(".book:last").remove();
                    bookIndex--;
                }
                return false;
            });
        });
        $(function() {
            var teachersIndex = 1;
            var rabbiHtml = '<tr class="teacher"><td><form:label path="teachers[99].name">';
            rabbiHtml += '<spring:message code="message.teacher"/>';
            rabbiHtml += '</form:label></td>';
            rabbiHtml += '<td><form:input path="teachers[99].name"/></td></tr>';
            $("#addTeacher").off("click").on("click", function() {
                $(".teacher:last").after(function(){
                    var fixrabbiHtml = rabbiHtml.replace(/99/g, teachersIndex);
                    return fixrabbiHtml;
                });
                teachersIndex++;
                return false;
            });
            $("#removeTeacher").off("click").on("click", function() {
                if (teachersIndex > 1){
                    $(".teacher:last").remove();
                    teachersIndex--;
                }
                return false;
            });
        });
        $(function() {
            var studentsIndex = 1;
            var studentHtml = '<tr class="student"><td><form:label path="students[99].name">';
            studentHtml += '<spring:message code="message.student"/>';
            studentHtml += '</form:label></td>';
            studentHtml += '<td><form:input path="students[99].name"/></td></tr>';
            $("#addStudent").off("click").on("click", function() {
                $(".student:last").after(function(){
                    var fixstudentHtml = studentHtml.replace(/99/g, studentsIndex);
                    return fixstudentHtml;
                });
                studentsIndex++;
                return false;
            });
            $("#removeStudent").off("click").on("click", function() {
                if (studentsIndex > 1){
                    $(".student:last").remove();
                    studentsIndex--;
                }
                return false;
            });
        });
    </script>
    <table id="main_table">
        <tr id="num">
            <td><form:label path="num"><spring:message code="message.num" /></form:label></td>
            <td><form:input path="num" /></td>
        </tr>
        <tr id="name">
            <td><form:label path="name"><spring:message code="message.name" /></form:label></td>
            <td><form:input path="name" /></td>
        </tr>
        <tr id="nickname">
            <td><form:label path="nickname"><spring:message code="message.nickname" /></form:label></td>
            <td><form:input path="nickname" /></td>
        </tr>
        <tr id="born">
            <td><form:label path="born"><spring:message code="message.born" /></form:label></td>
            <td><form:input path="born" /></td>
        </tr>
        <tr id="birth_location">
            <td><form:label path="birthLocation"><spring:message code="message.birth_location" /></form:label></td>
            <td><form:input path="birthLocation" /></td>
        </tr>
        <tr id="died">
            <td><form:label path="died"><spring:message code="message.died" /></form:label></td>
            <td><form:input path="died" /></td>
        </tr>
        <tr id="death_location">
            <td><form:label path="deathLocation"><spring:message code="message.death_location" /></form:label></td>
            <td><form:input path="deathLocation" /></td>
        </tr>
    <%--</table>--%>
    <%--<table id="books_table">--%>
        <tr class="book">
                <td><form:label path="books[0].name"><spring:message code="message.book" /></form:label></td>
                <td><form:input path="books[0].name"/></td>
        </tr>
        <c:forEach begin="1" end="${nbooks}" var="book" items="${command.books}" varStatus="loop" >
            <tr class="book">
                <td><form:label path="books[${loop.index}].name"><spring:message code="message.book" /></form:label></td>
                <td><form:input path="books[${loop.index}].name"/></td>
            </tr>
        </c:forEach>
        <tr class="buttons">
            <td>
                <input type='button' value='<spring:message code="message.addBook"/>' id='addBook'>
                <input type='button' value='<spring:message code="message.removeBook"/>' id='removeBook'>

            </td>
        </tr>
    <%--</table>--%>
    <%--<table id="teachers_table">--%>
        <tr class="teacher">
            <td><form:label path="teachers[0].name"><spring:message code="message.teacher" /></form:label></td>
            <td><form:input path="teachers[0].name"/></td>
        </tr>
        <c:forEach begin="1" end="${nteachers}" var="teacher" items="${command.teachers}" varStatus="loop" >
            <tr class="teacher">
                <td><form:label path="teachers[${loop.index}].name"><spring:message code="message.teacher" /></form:label></td>
                <td><form:input path="teachers[${loop.index}].name"/></td>
            </tr>
        </c:forEach>
        <tr class="buttons">
            <td>
                <input type='button' value='<spring:message code="message.addTeacher"/>' id='addTeacher'>
                <input type='button' value='<spring:message code="message.removeTeacher"/>' id='removeTeacher'>

            </td>
        </tr>
    <%--</table>--%>
    <%--<table id ="students_table">--%>
        <tr class="student">
            <td><form:label path="students[0].name"><spring:message code="message.student" /></form:label></td>
            <td><form:input path="students[0].name"/></td>
        </tr>
        <c:forEach begin="1" end="${nstudents}" var="student" items="${command.students}" varStatus="loop" >
        <tr class="student">
            <td><form:label path="students[${loop.index}].name"><spring:message code="message.student" /></form:label></td>
            <td><form:input path="students[${loop.index}].name"/></td>
        </tr>
        </c:forEach>
        <tr class="buttons">
            <td>
                <input type='button' value='<spring:message code="message.addStudent"/>' id='addStudent'>
                <input type='button' value='<spring:message code="message.removeStudent"/>' id='removeStudent'>

            </td>
        </tr>
    <%--<table>--%>
    <%--<table id="submit_table">--%>
        <tr id="submit_button">
            <td colspan="2">
                <input type="submit" value="<spring:message code="message.send" />"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>