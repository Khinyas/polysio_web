<%@ page import="model.ModelUser" %>

<%
ModelUser user = (ModelUser) session.getAttribute("utilisateur");
%>

<a href="${pageContext.request.contextPath}/accueil">
    <button>ACCUEIL</button>
</a>

<% if (user != null && user.getRole().toString().equals("ADMIN")) { %>

    <a href="${pageContext.request.contextPath}/accueil?afficher=true">
    	<button>Administration</button>
	</a>

<% } %>

<% if (user == null) { %>

    <a href="${pageContext.request.contextPath}/ControllerConnexion">
        <button>CONNEXION</button>
    </a>

<% } else { %>

    <a href="${pageContext.request.contextPath}/deconnexion">
        <button>DECONNEXION</button>
    </a>

<% } %>