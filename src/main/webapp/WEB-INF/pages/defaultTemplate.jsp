<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="he">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="message.title"/></title>
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>

    </head>
<body dir="rtl">
<div class="page">
    <tiles:insertAttribute name="header" />
    <div class="content">
        <%--<tiles:insertAttribute name="menu" />--%>
        <tiles:insertAttribute name="body" />
    </div>
    <%--<tiles:insertAttribute name="footer" />--%>
</div>
</body>
</html>