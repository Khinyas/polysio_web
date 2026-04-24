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
Et voici l'accueil, ${NomRecu} ! Et voici ton mail: ${Email} !
	<form action="/polysio_web/ControllerInscription" method="POST">
		<button type="submit">Déconnexion</button>
	</form>
</body>
</html>