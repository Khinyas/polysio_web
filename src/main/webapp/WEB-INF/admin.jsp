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
        <a href="${pageContext.request.contextPath}/accueil">
		    <button>ACCUEIL</button>
		</a>

        <a href="${pageContext.request.contextPath}/admin">
            <button>Administration</button>
        </a>

        <a href="${pageContext.request.contextPath}/deconnexion">
            <button>DECONNEXION</button>
        </a>
    </nav>

        <h1>PANNEAU D'ADMINISTRATION</h1>

        <table border="1">
    <tr>
        <th>ID</th>
        <th>Pseudo</th>
        <th>Email</th>
        <th>Rôle</th>
        <th>Actions</th>
    </tr>

    <% if (utilisateurs != null) { %>
        <% for (ModelUser u : utilisateurs) { %>
            <tr>
                <form action="admin" method="post">
                    <input type="hidden" name="action" value="modifier">
                    <input type="hidden" name="id" value="<%= u.getId() %>">

                    <td><%= u.getId() %></td>
                    
                    <td><input type="text" name="pseudo" value="<%= u.getUsername() %>"></td>
                    <td><input type="text" name="email" value="<%= u.getEmail() %>"></td>
                    <td>
                        <select name="role">
                            <option value="ADMIN" <%= u.getRole().toString().equals("ADMIN") ? "selected" : "" %>>ADMIN</option>
                            <option value="USER" <%= u.getRole().toString().equals("USER") ? "selected" : "" %>>USER</option>
                        </select>
                    </td>
                    <td>
                        <button type="submit">Enregistrer</button>
                        
                        <a href="admin?action=supprimer&id=<%= u.getId() %>" 
                           onclick="return confirm('Supprimer <%= u.getUsername() %> ?')">
                           <button type="button" style="color:red;">Supprimer</button>
                        </a>
                    </td>
                </form>
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

    

</body>
</html>