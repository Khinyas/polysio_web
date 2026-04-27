<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/styles.css"%></style>
<title>Inscription</title>
</head>
<body>
Page inscription
	<div>
	
	<!--  Redirige au ControllerInscription une fois le bouton d'inscription cliqué -->
		<form action="ControllerInscription" method="POST">
		
			<table border="0">
				<tr>
					<td>
						Nom
					</td>
					<td>
						<input type="text" name="username">
					</td>
				</tr>
				<tr>
					<td>
						E-mail
					</td>
					<td>
						<input type="text" name="email">
					</td>
				</tr>
				<tr>
					<td>
						Mot de passe
					</td>
					<td>
						<input type="password" name="password">
					</td>
				</tr>
				
				<!-- Inclure champ mdp et confirmation mdp  -->
				
				<tr>
					<td colspan="2" style="text-align:center;">
						<button type="submit">S'inscrire</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>