<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
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
</body>
</html>