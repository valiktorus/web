<%@ page import="by.gsu.epamlab.controllers.ControllerConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
    <p>Error page</p>
    <c:if test="${not empty errorMessage}">
        <h1 class="errorMessage"><c:out value="${errorMessage}"/></h1>
    </c:if>
    <a href="${pageContext.request.contextPath}<%=ControllerConstants.TASK_CONTROLLER%>">Go to Main page</a>
</body>
</html>
