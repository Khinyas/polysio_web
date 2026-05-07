<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ModelUser" %>
<%@ page import="connexion.DAOUser" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css"
href="${pageContext.request.contextPath}/CSS/style.css">

<title>Accueil</title>
</head>

<body>

<%@ include file="header.jsp" %>

<br><br>

<%
String afficher = request.getParameter("afficher");

if("true".equals(afficher)) {
%>

<h1>PANNEAU D'ADMINISTRATION</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Pseudo</th>
        <th>Email</th>
        <th>Rôle</th>
        <th>Action</th>
    </tr>

    <%
    ArrayList<ModelUser> liste = DAOUser.reqListeTousLesUtilisateurs();

    for(ModelUser u : liste){
    %>

    <tr>
        <td><%= u.getId() %></td>
        <td><%= u.getUsername() %></td>
        <td><%= u.getEmail() %></td>
        <td><%= u.getRole() %></td>
        <td>
            <button type="button"
                onclick="ouvrirPopup(
                    '<%= u.getId() %>',
                    '<%= u.getUsername() %>',
                    '<%= u.getEmail() %>',
                    '<%= u.getRole() %>'
                )">
                Modifier
            </button>

            <a href="${pageContext.request.contextPath}/supprimerUser?id=<%= u.getId() %>"
               onclick="return confirm('Voulez-vous vraiment supprimer cet utilisateur ?');">
                <button type="button">Supprimer</button>
            </a>
        </td>
    </tr>

    <% } %>
</table>

<br>

<a href="${pageContext.request.contextPath}/accueil">
    <button>Accueil</button>
</a>

<div id="popupModifier" style="display:none; border:1px solid black; padding:20px; width:300px; margin-top:20px;">
    <h2>Modifier utilisateur</h2>

    <form method="post" action="${pageContext.request.contextPath}/modifierUser">

        <input type="hidden" name="id" id="idUser">

        <label>Pseudo :</label><br>
        <input type="text" name="pseudo" id="pseudoUser"><br><br>

        <label>Email :</label><br>
        <input type="text" name="email" id="emailUser"><br><br>

        <label>Rôle :</label><br>
        <select name="role" id="roleUser">
            <option value="UTILISATEUR">UTILISATEUR</option>
            <option value="ADMIN">ADMIN</option>
        </select>

        <br><br>

        <button type="submit">OK</button>
        <button type="button" onclick="fermerPopup()">Annuler</button>
    </form>
</div>

<script>
function ouvrirPopup(id, pseudo, email, role) {
    document.getElementById("idUser").value = id;
    document.getElementById("pseudoUser").value = pseudo;
    document.getElementById("emailUser").value = email;
    document.getElementById("roleUser").value = role;
    document.getElementById("popupModifier").style.display = "block";
}

function fermerPopup() {
    document.getElementById("popupModifier").style.display = "none";
}
</script>

<%
}
else {
%>

<button>Lancer une partie</button>

<br><br>

<button>Charger une partie</button>

<br><br>

<button>Quitter le jeu</button>

<%
}
%>

</body>
</html>