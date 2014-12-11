<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html lang="he">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="message.title"/></title>
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