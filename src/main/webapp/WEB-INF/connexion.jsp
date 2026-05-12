<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<style>
@import url('https://fonts.googleapis.com/css2?family=Lexend:wght@100..900&display=swap');
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
</style>
<title>Connexion</title>
</head>
<body>
<%@ include file="header.jsp" %>
Page connexion
	<div>
	
	<div class="form-container">
	<!--  Redirige au ControllerConnexion une fois le bouton d'inscription cliqué -->
		<form action="ControllerConnexion" method="POST">
		
			<table border="0">
				<tr>
					<td>
						Nom
					</td>
					<td>
						<input type="text" name="username" class="input-field">
					</td>
				</tr>
				<tr>
					<td>
						Mot de passe
					</td>
					<td>
						<input type="password" name="password" class="input-field">
					</td>
				</tr>
				
				<!-- Inclure champ mdp et confirmation mdp  -->
				
				<tr>
					<td colspan="2" style="text-align:center;">
						<button type="submit">Se connecter</button>
					</td>
				</tr>
			</table>
		</form>
		
		
		<a href="ControllerInscription" class="button">Pas de compte ? S'inscrire</a>
	</div>
	</div>
</body>
</html>