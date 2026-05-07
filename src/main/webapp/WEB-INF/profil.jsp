<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
@import url('https://fonts.googleapis.com/css2?family=Lexend:wght@100..900&display=swap');
<%@include file="/WEB-INF/styles.css"%>
</style>
<title>Accueil</title>
</head>
<body>
	<div class="form-container">
	<h1>PROFIL</h1>
	    Nom d'utilisateur: ${username} 
	    <br>
	    Adresse E-mail : ${email} 
	    
	   	<!-- Bouton d'accueil (redirige vers le Servlet d'accueil) -->
	    <form action="ControllerAccueil" method="GET">
	        <button type="submit">Accueil</button>
	   	</form>
	</div>
</body>
</html>