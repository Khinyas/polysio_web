<%-- Import du modèle pour que le JSP reconnaisse l'objet ModelUser --%>
<%@ page import="model.ModelUser" %>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
</head>

<nav class="header-nav">
    <div class="logo">Polysio</div>
    
    <div class="user-menu">
        <% 
            // On récupère l'utilisateur stocké en session par le ControllerConnexion
            ModelUser user = (ModelUser) session.getAttribute("utilisateur"); 
            
            if (user != null) { 
        %>
            <%-- Cas : Utilisateur connecté --%>
            <span class="welcome-text">Bonjour, <strong><%= user.getUsername() %></strong></span>
            <a href="${pageContext.request.contextPath}/profil" class="btn-profil">Profil</a>
        <% 
            } else { 
        %>
            <%-- Cas : Personne n'est connecté --%>
            <a href="${pageContext.request.contextPath}/connexion" class="btn-login">Connexion</a>
            <a href="${pageContext.request.contextPath}/inscription" class="btn-register">S'inscrire</a>
        <% 
            } 
        %>
    </div>
</nav>