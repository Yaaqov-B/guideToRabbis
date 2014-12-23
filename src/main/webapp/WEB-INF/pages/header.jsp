<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1><spring:message code="message.title"/></h1>

<form method="post" action="/mvc1/">
    <table>
        <tr>
            <%--<td><label id="search_label"><spring:message code="message.search"/></label></td>--%>
            <td><input id="search_input" name="search"/></td>
            <td>  <button type="submit"><spring:message code="message.search"/></button></td>

        </tr>
        <tr>
            <%--<td><label id="search_label"><spring:message code="message.search"/></label></td>--%>

        </tr>
    </table>
</form>
