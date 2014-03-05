<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Omen - Vaardigheden</title>
</head>
<body>
<div class="container">
    <c:forEach items="${skills}" var="skill">
        <div class="page-header"><a href="${pageContext.request.contextPath}/omen/skill/${skill.id}">${skill.name}</a></div>
        <div class="row">
            <div class="col-sm-6">${skill.description}</div>
        </div>
    </c:forEach>
</div>
</body>
</html>
