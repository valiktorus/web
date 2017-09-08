<%@ page import="by.gsu.epamlab.controllers.ControllerConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Todo</title>
    <jsp:include page="../fragments/styles.jsp"/>
    <script> var contextPath = "${pageContext.request.contextPath}"</script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
</head>
<body>
    <div id="wrapper">
        <jsp:include page="../fragments/header.jsp"/>
        <div id="bodyContainer">
            <c:if test="${not empty errorMessage}">
                <p class="errorMessage"><c:out value="${errorMessage}"/></p>
            </c:if>
            <form action="${pageContext.request.contextPath}<%=ControllerConstants.LOGIN_CONTROLLER%>" method="post"  class="userForm">
                <p>Login:</p>
                <input type="text" name="login" class="formInput">
                <p>Password:</p>
                <input type="password" name="password" class="formInput"><br/>
                <input type="submit" value="Submit" class="formInput">
            </form>
        </div>
        <jsp:include page="../fragments/footer.jsp"/>
    </div>
</body>
</html>
