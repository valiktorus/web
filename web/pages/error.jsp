<%@ page import="by.gsu.epamlab.controllers.ControllerConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isErrorPage="true" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
    <p>Error page</p>
    <c:choose>
        <c:when test="${not empty errorMessage}">
            <h2 class="errorMessage"><c:out value="${errorMessage}"/></h2>
        </c:when>
        <c:when test="${not empty pageContext.errorData.requestURI}">
            <h2>Page requested ${pageContext.errorData.requestURI} Not found on the server</h2>
        </c:when>
        <c:otherwise>
            Unexpected application error
        </c:otherwise>
    </c:choose>

    <a href="${pageContext.request.contextPath}<%=ControllerConstants.TASK_CONTROLLER%>">Go to Main page</a>
</body>
</html>
