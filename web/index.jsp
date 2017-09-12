<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>ToDo</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<div id="wrapper">
  <c:import url="/pages/fragments/header.jsp"/>
  <div id="bodyContainer">
    Please Login or register
  </div>
  <jsp:include page="pages/fragments/footer.jsp"/>
</div>

</body>
</html>
