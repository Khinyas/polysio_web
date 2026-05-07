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

			<!-- Affichage erreur si elle existe -->
			<p style="color:red;">${msgErreur}</p>
						
	   	</form>
	</div>
	</body>
</html>