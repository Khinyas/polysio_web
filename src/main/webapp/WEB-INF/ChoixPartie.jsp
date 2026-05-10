<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title></title>
</head>
<body>

<%@ include file="header.jsp" %>

<form action="${pageContext.request.contextPath}/ControllerChoixPartie" class="choix-partie" method="POST">
    <h3>Nombre de joueurs</h3>
    <input type="radio" name="nbJoueurs" value="2" checked> 2 Joueurs
    <input type="radio" name="nbJoueurs" value="3"> 3 Joueurs
    <input type="radio" name="nbJoueurs" value="4"> 4 Joueurs

    <h3>Durée de la partie</h3>
    <select name="tempsJeu">
        <option value="1">1 minute</option>
        <option value="15">15 minutes</option>
        <option value="30">30 minutes</option>
        <option value="60">60 minutes</option>
    </select>

    <br><br>
    <button type="submit">Lancer la partie !</button>
</form>

</body>
</html>