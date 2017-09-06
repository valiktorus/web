<%@ page import="by.gsu.epamlab.controllers.ControllerConstants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table>
    <c:forEach items="${currentTaskList}" var="task">
        <tr>
            <td><input type="checkbox" name="taskDescription" data-id="${task.id}"></td>
            <td>${task.description}</td>
            <td class="dateLine">${task.date}</td>
            <td>
                <c:choose>
                    <c:when test="${not empty task.fileName}">
                        ${task.fileName}
                        <form class="fileFormBox" method="post" action="${pageContext.request.contextPath}<%=ControllerConstants.DOWNLOAD_FILE_CONTROLLER%>">
                            <input type="hidden" name="idTask" value="${task.id}">
                            <input type="hidden" name="fileName" value="${task.fileName}">
                            <input formaction="${pageContext.request.contextPath}<%=ControllerConstants.DOWNLOAD_FILE_CONTROLLER%>" type="submit" value="download">
                            <input formaction="${pageContext.request.contextPath}<%=ControllerConstants.DELETE_FILE_CONTROLLER%>" type="submit" value="delete file">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form class="fileFormBox" enctype="multipart/form-data" action="${pageContext.request.contextPath}<%=ControllerConstants.UPLOAD_FILE_CONTROLLER%>" method="post">
                            <input type="hidden" name="idTask" value="${task.id}">
                            <input type="file" name="fileUpload">
                            <input type="submit" value="upload">
                        </form>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>