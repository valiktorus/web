<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.gsu.epamlab.controllers.ControllerConstants" %>
<td>
    <c:choose>
        <c:when test="${not empty currentTask.fileName}">
            ${currentTask.fileName}
            <form class="fileFormBox" method="post" action="${pageContext.request.contextPath}<%=ControllerConstants.DOWNLOAD_FILE_CONTROLLER%>">
                <input type="hidden" name="taskDescription" value="${currentTask.description}">
                <input type="hidden" name="taskDate" value="${currentTask.date}">
                <input type="hidden" name="fileName" value="${currentTask.fileName}">
                <input formaction="${pageContext.request.contextPath}<%=ControllerConstants.DOWNLOAD_FILE_CONTROLLER%>" type="submit" value="download">
                <input formaction="${pageContext.request.contextPath}<%=ControllerConstants.DELETE_FILE_CONTROLLER%>" type="submit" value="delete file">
            </form>
        </c:when>
        <c:otherwise>
            <form class="fileFormBox" enctype="multipart/form-data" action="${pageContext.request.contextPath}<%=ControllerConstants.UPLOAD_FILE_CONTROLLER%>" method="post">
                <input type="hidden" name="taskDescription" value="${currentTask.description}">
                <input type="hidden" name="taskDate" value="${currentTask.date}">
                <input type="file" name="fileUpload">
                <input type="submit" value="upload">
            </form>
        </c:otherwise>
    </c:choose>
</td>