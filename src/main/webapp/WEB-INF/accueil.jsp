<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/styles.css"%></style>
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
</body>
</html>