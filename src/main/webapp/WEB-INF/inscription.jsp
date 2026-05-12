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
<title>Inscription</title>
</head>
<body>


<%@ include file="header.jsp" %>
	<div>
	Page inscription
	<div class="form-container">
	<!--  Redirige au ControllerInscription une fois le bouton d'inscription cliqué -->
		<form action="ControllerInscription" method="POST">
		
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
						E-mail
					</td>
					<td>
						<input type="text" name="email" class="input-field">
					</td>
				</tr>
				<tr>
					<td>
						Mot de passe
						<!-- Affichage erreur si elle existe -->
						<p style="color:red;">${msgErreur}</p>
					</td>
					<td>
						<input type="password" name="password" class="input-field">
					</td>
				</tr>
				
				<tr>
					<td>
						Confirmer le mot de passe
					</td>
					<td>
						<input type="password" name="confirmPassword" class="input-field">
					</td>
				</tr>
				
				
				<tr>
					<td colspan="2" style="text-align:center;">
						<button type="submit">S'inscrire</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	</div>
</body>
</html>