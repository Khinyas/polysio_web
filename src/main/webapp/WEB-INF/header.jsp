<%-- Import du mod�le pour que le JSP reconnaisse l'objet ModelUser --%>
<%@ page import="model.ModelUser" %>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
</head>

<nav class="header-nav">
    <div class="logo">Polysio</div>
    
    <div class="user-menu">
        <% 
            // On récupère l'utilisateur stocké en session par le ControllerConnexion
            ModelUser user = (ModelUser) session.getAttribute("userSession"); 
            
            if (user != null) { 
        %>
            <%-- Cas : Utilisateur connecté --%>
            <span class="welcome-text">Bonjour, <strong><%= user.getUsername() %></strong></span>
        <form action="ControllerProfil" method="GET">
	        <button type="submit">Profil</button>
	    </form>
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
    </div>
</nav>

<a href="${pageContext.request.contextPath}/accueil">
    <button>ACCUEIL</button>
</a>

<% if (user != null && user.getRole().toString().equals("ADMIN")) { %>

    <a href="${pageContext.request.contextPath}/admin?afficher=true">
    	<button>Administration</button>
	</a>

<% } %>

