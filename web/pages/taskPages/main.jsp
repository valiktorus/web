<%@ page import="by.gsu.epamlab.controllers.ControllerConstants" %>
<%@ page import="by.gsu.epamlab.model.beans.Task" %>
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
            <div id="today" class="todo visible">
                <div class="todoHeader">
                    <p>Today</p>
                </div>
                <table>
                    <c:forEach items="${todayList}" var="task">
                        <tr>
                            <td><input type="checkbox" name="taskDescription" data-description="${task.description}" data-date="${task.date}" data-fileName="${task.fileName}"></td>
                            <td>${task.description}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty task.fileName}">
                                        <form method="post" action="${pageContext.request.contextPath}<%=ControllerConstants.DOWNLOAD_FILE_CONTROLLER%>">
                                            <input type="hidden" name="taskDescription" value="${task.description}">
                                            <input type="hidden" name="taskDate" value="${task.date}">
                                            <input type="hidden" name="fileName" value="${task.fileName}">
                                            <input formaction="${pageContext.request.contextPath}<%=ControllerConstants.DOWNLOAD_FILE_CONTROLLER%>" type="submit" value="download">
                                            <input formaction="${pageContext.request.contextPath}<%=ControllerConstants.DELETE_FILE_CONTROLLER%>" type="submit" value="delete file">
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form enctype="multipart/form-data" action="${pageContext.request.contextPath}<%=ControllerConstants.UPLOAD_FILE_CONTROLLER%>" method="post">
                                            <input type="hidden" name="taskDescription" value="${task.description}">
                                            <input type="hidden" name="taskDate" value="${task.date}">
                                            <input type="file" name="fileUpload">
                                            <input type="submit" value="upload">
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div id="tomorrow" class="todo">
                <div class="todoHeader">
                    <p>Tomorrow</p>
                </div>
                <table>
                    <c:forEach items="${tomorrowList}" var="task">
                        <tr>
                            <td><input type="checkbox" name="taskDescription" data-description="${task.description}" data-date="${task.date}" data-fileName="${task.fileName}"></td>
                            <td>${task.description}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div id="someDay" class="todo">
                <div class="todoHeader">
                    <p>Some days</p>
                </div>
                <form method="post" name="someDayForm">
                    <table>
                        <c:forEach items="${otherDayList}" var="task">
                            <tr>
                                <td><input type="checkbox" name="taskDescription" data-description="${task.description}" data-date="${task.date}" data-fileName="${task.fileName}"></td>
                                <td>${task.description}</td>
                                <td>${task.date}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </form>
            </div>
            <div id="fixed" class="todo">
                <div class="todoHeader">
                    <p>Fixed</p>
                </div>
                <table>
                    <c:forEach items="${fixedList}" var="task">
                        <tr>
                            <td><input type="checkbox" name="taskDescription" data-description="${task.description}" data-date="${task.date}" data-fileName="${task.fileName}"></td>
                            <td>${task.description}</td>
                            <td>${task.date}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div id="recycleBin" class="todo">
                <div class="todoHeader">
                    <p>Recycle bin</p>
                </div>
                <table>
                    <c:forEach items="${deletedList}" var="task">
                        <tr>
                            <td><input type="checkbox" name="taskDescription" data-description="${task.description}" data-date="${task.date}" data-fileName="${task.fileName}"></td>
                            <td>${task.description}</td>
                            <td>${task.date}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <jsp:include page="../fragments/toDoFooter.jsp"/>
        </div>
    </div>
    <jsp:include page="../fragments/footer.jsp"/>
</div>

</body>
</html>