<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Omen - ${character.characterName}</title>
</head>
<body>
<div class="jumbotron">
    <h1>${character.characterName}</h1>
    <div>Lineage</div><div>${character.lineageName}</div>
</div>
</body>
</html>