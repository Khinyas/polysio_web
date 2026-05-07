<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
@import url('https://fonts.googleapis.com/css2?family=Lexend:wght@100..900&display=swap');
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
<title>Accueil</title>
</head>
<body>

<%@ include file="header.jsp" %>

	<div class="form-container">

  Et voici l'accueil, ${NomRecu} ! Et voici ton mail: ${Email} !
	<form action="${pageContext.request.contextPath}/deconnexion" method="GET">
		<button type="submit">Déconnexion</button>
	</form> 
	
	<form action="${pageContext.request.contextPath}/ControllerAccueil" method="GET">
    <input type="hidden" name="action" value="jouer">
    
    <input type="text" name="parametre" placeholder="Ton Parametre" requested>
    <button type="submit">Valider Joueur</button>
</form>




<a href="${pageContext.request.contextPath}/ChoixPartie"> Lancer une partie </a>

</body>
</html>