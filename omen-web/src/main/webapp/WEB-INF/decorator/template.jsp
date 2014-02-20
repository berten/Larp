<!DOCTYPE HTML>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
           prefix="decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="${pageContext.request.contextPath}/img/favicon.ico" rel="shortcut icon"/>
    <decorator:head/>
    <title><decorator:title/></title>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Omen</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <c:forEach items="${menu.menuItems}" var="item">
                    <c:choose>
                        <c:when test="${empty item.menuItems}">
                            <li><a href="${item.url}">${item.display}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">${item.display} <b
                                        class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <c:forEach items="${item.menuItems}" var="childItem">
                                        <c:choose>
                                            <c:when test="${empty childItem.display}">
                                                <li class="divider"></li>
                                            </c:when>
                                            <c:otherwise>
                                                <c:choose>
                                                    <c:when test="${empty childItem.url}">
                                                        <li class="dropdown-header">${childItem.display}</li>
                                                        <c:forEach items="${childItem.menuItems}" var="smallerChild">
                                                            <li>
                                                                <a href="${smallerChild.url}">${smallerChild.display}</a>
                                                            </li>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:otherwise>

                                                        <li><a href="${childItem.url}">${childItem.display}</a></li>
                                                    </c:otherwise>
                                                </c:choose>

                                            </c:otherwise>

                                        </c:choose>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</div>

<div class="container">

    <div class="starter-template">
        <decorator:body/>
    </div>

</div>
<!-- /.container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-2.1.0.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>