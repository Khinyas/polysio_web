<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ModelUser" %>

<%
    ArrayList<ModelUser> utilisateurs =
        (ArrayList<ModelUser>) request.getAttribute("utilisateurs");

    String afficher = request.getParameter("afficher");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Administration</title>
</head>
<body>

    <nav>
        <a href="${pageContext.request.contextPath}/admin">
		    <button>ACCUEIL</button>
		</a>

        <a href="${pageContext.request.contextPath}/admin?afficher=true">
            <button>Administration</button>
        </a>

        <a href="${pageContext.request.contextPath}/deconnexion">
            <button>DECONNEXION</button>
        </a>
    </nav>

    <% if (!"true".equals(afficher)) { %>

        <h1>POLYSIO</h1>

        <div>
            <button>Lancer une partie</button>
            <br><br>

            <button>Charger une partie</button>
            <br><br>

            <button>Quitter le jeu</button>
        </div>

    <% } else { %>

        <h1>PANNEAU D'ADMINISTRATION</h1>

        <table border="1">
            <tr>
                <th>ID</th>
                <th>Pseudo</th>
                <th>Email</th>
                <th>Rôle</th>
            </tr>

            <% if (utilisateurs != null) { %>
                <% for (ModelUser u : utilisateurs) { %>
                    <tr>
                        <td><%= u.getId() %></td>
                        <td><%= u.getUsername() %></td>
                        <td><%= u.getEmail() %></td>
                        <td><%= u.getRole() %></td>
                    </tr>
                <% } %>
            <% } %>
        </table>

        <br>

        <a href="${pageContext.request.contextPath}/admin">
            <button>Accueil</button>
        </a>

        <button>Modifier</button>
        <button>Supprimer</button>

    <% } %>

</body>
</html>