<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
@import url('https://fonts.googleapis.com/css2?family=Lexend:wght@100..900&display=swap');
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
<title>Accueil</title>
</head>
<body>

<%@ include file="header.jsp" %>

	<div class="form-container">
	
	<form action="${pageContext.request.contextPath}/ControllerAccueil" method="GET">
    <input type="hidden" name="action" value="jouer">
    
</form>



<a href="${pageContext.request.contextPath}/ControllerAccueil?action=choisirPartie"> Lancer une partie </a>
</div>

</body>
</html>