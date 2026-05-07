<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<<<<<<< HEAD
<style>
@import url('https://fonts.googleapis.com/css2?family=Lexend:wght@100..900&display=swap');
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
<title>Accueil</title>
</head>
<body>
	<div class="form-container">
	    Et voici l'accueil, ${username} ! Et voici ton mail : ${email} !
	    
	    <!-- Bouton de déconnexion (redirige vers le Servlet de connexion) -->
	    <form action="ControllerConnexion" method="GET">
	        <button type="submit">Déconnexion</button>
	   	</form>
	   	
	   	<!-- Bouton de profil (redirige vers le Servlet profil) -->
	    <form action="ControllerProfil" method="GET">
	        <button type="submit">Profil</button>
	    </form>
	</div>
=======

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
<title>Accueil</title>
</head>
<body>
<%@ include file="header.jsp" %>
Et voici l'accueil, ${NomRecu} ! Et voici ton mail: ${Email} !
	<form action="/polysio_web/ControllerInscription" method="POST">
		<button type="submit">Déconnexion</button>
	</form>
	
	<form action="${pageContext.request.contextPath}/ControllerAccueil" method="GET">
    <input type="hidden" name="action" value="jouer">
    
    <input type="text" name="parametre" placeholder="Ton Parametre" requested>
    <button type="submit">Valider Joueur</button>
</form>


=======

<a href="${pageContext.request.contextPath}/ChoixPartie"> Lancer une partie </a>
<br>
<button> Charger une partie</button>
<br>
<button> Quitter le jeu</button>
	
>>>>>>> jluc
</body>
</html>