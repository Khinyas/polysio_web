<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
            Déjà inscrit ? <a href="${pageContext.request.contextPath}/ControllerConnexion">Se connecter</a>
        </p>
    </div>
</body>
</html>