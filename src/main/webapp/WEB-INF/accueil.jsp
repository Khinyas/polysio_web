<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lexend:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
<title>Accueil</title>
</head>
<style>
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
<title>Accueil</title>
</head>
<body>

<%@ include file="header.jsp" %>
<% if (request.getAttribute("message") != null) { %>
    <div class="alerte">
        <%= request.getAttribute("message") %>
    </div>
<% } %>
	<div class="form-container">
	
	<form action="${pageContext.request.contextPath}/ControllerAccueil" method="GET">
    <input type="hidden" name="action" value="jouer">
    
</form>



<a href="${pageContext.request.contextPath}/ControllerAccueil?action=choisirPartie"> Lancer une partie </a>
</div>

</body>
</html>