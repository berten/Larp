<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Overview</title>
</head>
<body>
<c:forEach var="user" items="${users}">
    <p>${user.username} pw: ${user.password}</p>
</c:forEach>
</body>
</html>
