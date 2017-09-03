<%@ page import="by.gsu.epamlab.controllers.ControllerConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="header">
    <div id="currentUser">
        User:
        <c:choose>
            <c:when test="${empty sessionScope.user}">
                <c:out value="guest"/>
            </c:when>
            <c:when test="${not empty sessionScope.user}">
                <c:out value="${sessionScope.user}"/>
            </c:when>
        </c:choose>
    </div>
    <div id="toMain" class="menuElement">
        <a href="${pageContext.request.contextPath}<%=ControllerConstants.TASK_CONTROLLER%>">To main Page </a>
    </div>
    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <div id="registration" class="menuElement">
                <a href="${pageContext.request.contextPath}<%=ControllerConstants.REGISTRATION_PAGE%>">Registration</a>
            </div>
            <div id="login" class="menuElement">
                <a href="${pageContext.request.contextPath}<%=ControllerConstants.LOGIN_PAGE%>">Login </a>
            </div>
        </c:when>
        <c:when test="${not empty sessionScope.user}">
            <form name="logoutForm" method="POST" action="${pageContext.request.contextPath}<%=ControllerConstants.LOGOUT_CONTROLLER%>">
                <div id="logout" class="menuElement">
                    <a href="javascript:document.logoutForm.submit()">Logout</a>
                </div>
            </form>
        </c:when>
    </c:choose>

</div>