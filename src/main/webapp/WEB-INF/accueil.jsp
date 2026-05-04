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


</body>
</html>