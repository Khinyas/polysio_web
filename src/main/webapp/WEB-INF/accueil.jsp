<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Accueil</title>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lexend:wght@100..900&display=swap" rel="stylesheet">
</head>


<body>

<%@ include file="header.jsp" %>
<% if (request.getAttribute("message") != null) { %>
    <div class="alerte">
        <%= request.getAttribute("message") %>
    </div>
<% } %>


<div class="form-container">

    <% if (session.getAttribute("userSession") != null) { %>
        <%---   Connecté → on affiche le bouton --%>
        Bienvenue <%= ((ModelUser) session.getAttribute("userSession")).getUsername() %>
        <a href="${pageContext.request.contextPath}/ControllerAccueil?action=choisirPartie">
            Lancer une partie
        </a>
    <% } else { %>
        <%-- Non connecté → message ou rien --%>
        <p>Connectez-vous pour lancer une partie.</p>
    <% } %>

</div>



</body>
</html>