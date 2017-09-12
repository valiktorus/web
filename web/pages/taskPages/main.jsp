<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ToDo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <script> var contextPath = "${pageContext.request.contextPath}"</script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
</head>
<body>
<div id="wrapper">
    <c:import url="/pages/fragments/header.jsp"/>
    <div id="bodyContainer">
        <c:import url="/pages/fragments/bodyMenuButtons.jsp"/>
        <div id="tasksContainer">
            <div id="today" class="todo visible withOutDate">
                <div class="todoHeader">
                    <p>Today</p>
                </div>
                <c:set var="currentTaskList" scope="request" value="${todayList}"/>
                <c:import url="/pages/fragments/todoList.jsp"/>
            </div>
            <div id="tomorrow" class="todo withOutDate">
                <div class="todoHeader">
                    <p>Tomorrow</p>
                </div>
                <c:set var="currentTaskList" scope="request" value="${tomorrowList}"/>
                <c:import url="/pages/fragments/todoList.jsp"/>
            </div>
            <div id="someDay" class="todo">
                <div class="todoHeader">
                    <p>Some days</p>
                </div>
                <c:set var="currentTaskList" scope="request" value="${otherDayList}"/>
                <c:import url="/pages/fragments/todoList.jsp"/>
            </div>
            <div id="fixed" class="todo">
                <div class="todoHeader">
                    <p>Fixed</p>
                </div>
                <c:set var="currentTaskList" scope="request" value="${fixedList}"/>
                <c:import url="/pages/fragments/todoList.jsp"/>
            </div>
            <div id="recycleBin" class="todo">
                <div class="todoHeader">
                    <p>Recycle bin</p>
                </div>
                <c:set var="currentTaskList" scope="request" value="${deletedList}"/>
                <c:import url="/pages/fragments/todoList.jsp"/>
                <c:remove var="currentTaskList"/>
            </div>
            <c:import url="/pages/fragments/toDoFooter.jsp"/>
        </div>
    </div>
    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>
</html>