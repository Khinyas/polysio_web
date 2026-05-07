<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<<<<<<< HEAD
<style>
@import url('https://fonts.googleapis.com/css2?family=Lexend:wght@100..900&display=swap');
<%@include file="/WEB-INF/styles.css"%>
</style>
<title>Profil</title>
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
	   	
	   	<!-- Test modif pseudo -->
	    <form action="ControllerProfil" method="POST">
	    <input type="text" name="newusername" class="input-field">
	        <button type="submit">Modifier le pseudo</button>
	   	</form>
	</div>
=======
<title>	Profil</title>
</head>
<body>

<h1> Profil  de ${user} </h1>

<label for="Pseudo-changement">Changement de mot de passe</label>
<input type="text" id="username-change"></input>



>>>>>>> jluc
</body>
</html>