<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<script type="text/javascript">

    $(function() {
//        var className="highlite";

        //   var myHilitor = new Hilitor("content");
        // myHilitor.apply("סעדיה");
//        var str ="סעדיה";
//        var regex = new RegExp(str, "gi");
////        return this.each(function () {
//                $(this).contents().filter(function() {
//                    return this.nodeType == 3 && regex.test(this.nodeValue);
//                }).replaceWith(function() {
//                    return (this.nodeValue || "").replace(regex, function(match) {
//
//                        return "<span class=\"" + className + "\">" + match + "</span>";
//                    });
//                });
//            });
//        var replaced = $("body").html().replace(/סעדיה/,'עובדיה');
//        $("body").html(replaced);
    });
</script>

<c:forEach var="rabbi" items="${rabbis}">
    <h2>(${rabbi.num}) ${rabbi.name}</h2>
    <table id="details">
        <tr id="name">
            <td><b><spring:message code="message.name" /></b></td>
            <td>${rabbi.name}
            <c:if test="${rabbi.nickname.length() >0}">
                , <a href='/mvc1/searchRabbiNickname/<c:out value="${rabbi.nickname}"/>'>${rabbi.nickname}</a>
        </c:if>
            </td>
        </tr>
        <c:if test="${rabbi.born.length() >0 || rabbi.birthLocation.length() > 0}">

        <tr id="born">
            <td><b><spring:message code="message.born" /></b></td>
            <c:if test="${rabbi.born.length() >0}">
                <td>
                    <a href='/mvc1/searchYear/<c:out value="${rabbi.born}"/>'>${rabbi.born}</a> (${rabbi.bornGeorgian})
                </td>
            </c:if>
            <c:if test="${rabbi.birthLocation.length() >0}">
                <td><td><a href='/mvc1/searchLocation/<c:out value="${rabbi.birthLocation}"/>'>${rabbi.birthLocation}</a></td>
            </c:if>
        </tr>
        </c:if>
        <c:if test="${rabbi.died.length() >0 || rabbi.deathLocation.length() > 0}">

        <tr id="died">
            <td><b><spring:message code="message.died" /></b></td>
            <c:if test="${rabbi.died.length() >0}">
                <td><a href='/mvc1/searchYear/<c:out value="${rabbi.died}"/>'>${rabbi.died}</a> (${rabbi.diedGeorgian})</td>
            </c:if>
            <c:if test="${rabbi.deathLocation.length() >0}">
                <td><td><a href='/mvc1/searchLocation/<c:out value="${rabbi.deathLocation}"/>'>${rabbi.deathLocation}</a></td>
            </c:if>
        </tr>
        </c:if>
    </table>

    <c:if test="${rabbi.books.size()>0}">
        <h3><spring:message code="message.books" /></h3>
        <table id="books">
            <c:forEach var="book" items="${rabbi.books}">
                <tr class="book">
                    <td><a href='/mvc1/searchBook/<c:out value="${book.name}"/>'>${book.name}</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${rabbi.teachers.size()>0}">
        <h3><spring:message code="message.teachers" /></h3>
        <table id="teachers">
            <c:forEach var="teacher" items="${rabbi.teachers}">
                <tr>
                    <td><a href='/mvc1/searchRabbi/<c:out value="${teacher.name}"/>'>${teacher.name}</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${rabbi.students.size()>0}">
        <h3><spring:message code="message.students" /></h3>
        <table id="students">
            <c:forEach var="student" items="${rabbi.students}">
                <tr>
                    <td><a href='/mvc1/searchRabbi/<c:out value="${student.name}"/>'>${student.name}</a></td>

                </tr>
            </c:forEach>
        </table>
        <br/>
    </c:if>

    <a href='/mvc1/update/<c:out value="${rabbi.num}"/>'><spring:message code="message.updateRabbi"/></a>
    <br/>
    <a href='/mvc1/remove/<c:out value="${rabbi.num}"/>'><spring:message code="message.removeRabbi"/></a>
</c:forEach>