<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Omen - ${character.characterName}</title>
</head>
<body>
<div class="container">
    <div class="page-header">${character.characterName}</div>
    <div class="row">
        <div class="col-sm-3">Afkomst</div>
        <div class="col-sm-3">${character.lineageName}</div>
    </div>
    <div class="row">
        <div class="col-sm-3">Klasse</div>
        <div class="col-sm-3">${character.className}</div>
    </div>
</div>
</body>
</html>
