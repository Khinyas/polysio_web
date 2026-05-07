<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<<<<<<< HEAD
<meta charset="UTF-8">
<style>
@import url('https://fonts.googleapis.com/css2?family=Lexend:wght@100..900&display=swap');
<%@include file="/WEB-INF/styles.css"%>
</style>
<title>Inscription</title>
</head>
<body>
Page inscription
	<div>
	
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
		
		<form action="ControllerConnexion" method="POST">
			<table border="0">
		<td colspan="2" style="text-align:center;">
						<button type="submit">Se connecter</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	</div>
=======
    <title>Inscription - Polysio</title>
</head>
<body>
    <!--  %@ include file="header.jsp" % -->


    <div class="form-container">
        <h2>Inscription</h2>

        <% if(request.getParameter("erreur") != null) { %>
            <div class="error">Erreur lors de l'inscription (pseudo déjà utilisé ?).</div>
        <% } %>

        <form action="${pageContext.request.contextPath}/ControllerInscription" method="POST">
            <input type="text" name="username" placeholder="Pseudo" required>
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="password" placeholder="Mot de passe" required>
            <button type="submit">Créer mon compte</button>
        </form>

        <p style="text-align: center; margin-top: 15px;">
            Déjà inscrit ? <a href="${pageContext.request.contextPath}/connexion">Se connecter</a>
        </p>
    </div>
>>>>>>> jluc
</body>
</html>