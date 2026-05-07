<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<<<<<<< HEAD
<meta charset="UTF-8">
<style>
@import url('https://fonts.googleapis.com/css2?family=Lexend:wght@100..900&display=swap');
<%@include file="/WEB-INF/styles.css"%>
</style>
<title>Connexion</title>
</head>
<body>
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
=======
    <title>Connexion - Polysio</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
</head>
<body>
<%@ include file="header.jsp" %>   
  

    <div class="form-container">
        <h2>Connexion</h2>
        
        <% if(request.getParameter("erreur") != null) { %>
            <div class="error">Identifiants incorrects. Veuillez réessayer.</div>
        <% } %>

        <form action="${pageContext.request.contextPath}/ControllerConnexion" method="POST">
            <input type="text" name="username" placeholder="Pseudo" required>
            <input type="password" name="password" placeholder="Mot de passe" required>
            <button type="submit">Se connecter</button>
        </form>
        
        <p style="text-align: center; margin-top: 15px;">
            Pas de compte ? <a href="${pageContext.request.contextPath}/inscription">S'inscrire</a>
        </p>
    </div>
>>>>>>> jluc
</body>
</html>