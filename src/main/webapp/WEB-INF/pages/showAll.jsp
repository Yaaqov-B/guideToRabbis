<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" %>


<c:forEach var="rabbi" items="${rabbis}">

    <h2>(${rabbi.num}) ${rabbi.name}</h2>
    <table class="details">
        <tr class="name">
            <td><b><spring:message code="message.name" /></b></td>
            <td>${rabbi.name}
                <c:if test="${rabbi.nickname.length() >0}">
                    , <a href='/mvc1/searchRabbiNickname/<c:out value="${rabbi.nickname}"/>'>${rabbi.nickname}</a>
                </c:if>
            </td>
        </tr>
        <c:if test="${rabbi.born.length() >0 || rabbi.birthLocation.length() > 0}">

            <tr class="born">
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

            <tr class="died">
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
        <table class="books">
            <c:forEach var="book" items="${rabbi.books}">
                <tr class="book">
                    <td><a href='/mvc1/searchBook/<c:out value="${book.name}"/>'>${book.name}</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${rabbi.teachers.size()>0}">
        <h3><spring:message code="message.teachers" /></h3>
        <table class="teachers">
            <c:forEach var="teacher" items="${rabbi.teachers}" >
                <tr>
                    <c:choose>
                        <c:when test="${teacher.num != null}">
                            <td><a href='/mvc1/findByNum/<c:out value="${teacher.num}"/>'>${teacher.name}</a></td>
                        </c:when>
                        <c:otherwise>
                            <td><a href='/mvc1/searchRabbi/<c:out value="${teacher.name}"/>'>${teacher.name}</a></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${rabbi.students.size()>0}">
        <h3><spring:message code="message.students" /></h3>
        <table class="students">
            <c:forEach var="student" items="${rabbi.students}">
                <tr>
                    <c:choose>
                        <c:when test="${student.num > 0}">
                            <td><a href='/mvc1/rabbi/<c:out value="${student.num}"/>'>${student.name} </a></td>
                        </c:when>
                        <c:otherwise>
                            <td><a href='/mvc1/searchRabbi/<c:out value="${student.name}"/>'>${student.name}${student.num}</a></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
        <br/>
    </c:if>

    <a href='/mvc1/update/<c:out value="${rabbi.num}"/>'><spring:message code="message.updateRabbi"/></a>
    <br/>
    <a href='/mvc1/remove/<c:out value="${rabbi.num}"/>'><spring:message code="message.removeRabbi"/></a>
</c:forEach>