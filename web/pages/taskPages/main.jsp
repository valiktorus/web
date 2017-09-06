<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <jsp:include page="../fragments/styles.jsp"/>
    <script> var contextPath = "${pageContext.request.contextPath}"</script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
</head>
<body>
<div id="wrapper">
    <jsp:include page="../fragments/header.jsp"/>
    <div id="bodyContainer">
        <jsp:include page="../fragments/bodyMenuButtons.jsp"/>
        <div id="tasksContainer">
            <div id="today" class="todo visible withOutDate">
                <div class="todoHeader">
                    <p>Today</p>
                </div>
                ${pageContext.request.setAttribute("currentTaskList", todayList)}
                <jsp:include page="../fragments/todoList.jsp"/>
            </div>
            <div id="tomorrow" class="todo withOutDate">
                <div class="todoHeader">
                    <p>Tomorrow</p>
                </div>
                ${pageContext.request.setAttribute("currentTaskList", tomorrowList)}
                <jsp:include page="../fragments/todoList.jsp"/>
            </div>
            <div id="someDay" class="todo">
                <div class="todoHeader">
                    <p>Some days</p>
                </div>
                ${pageContext.request.setAttribute("currentTaskList", otherDayList)}
                <jsp:include page="../fragments/todoList.jsp"/>
            </div>
            <div id="fixed" class="todo">
                <div class="todoHeader">
                    <p>Fixed</p>
                </div>
                ${pageContext.request.setAttribute("currentTaskList", fixedList)}
                <jsp:include page="../fragments/todoList.jsp"/>
            </div>
            <div id="recycleBin" class="todo">
                <div class="todoHeader">
                    <p>Recycle bin</p>
                </div>
                ${pageContext.request.setAttribute("currentTaskList", deletedList)}
                <jsp:include page="../fragments/todoList.jsp"/>
            </div>
            <jsp:include page="../fragments/toDoFooter.jsp"/>
        </div>
    </div>
    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>
</html>