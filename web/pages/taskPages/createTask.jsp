<%@ page import="by.gsu.epamlab.controllers.ControllerConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <script> var contextPath = "${pageContext.request.contextPath}"</script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
</head>
<body>
<div id="wrapper">
    <c:import url="/pages/fragments/header.jsp"/>
    <div id="bodyContainer">
        <form action="${pageContext.request.contextPath}<%=ControllerConstants.CREATE_TASK_CONTROLLER%>" method="post"  class="userForm">
            <p>Task description:</p>
            <input type="text" name="taskDescription" class="formInput">
            <p id="dateCreateTaskHeader">Date:</p>
            <input type="date" name="date" class="formInput" id="importDate"><br/>
            <input type="submit" value="Submit" class="formInput">
        </form>
    </div>
    <jsp:include page="../fragments/footer.jsp"/>
</div>

</body>
</html>