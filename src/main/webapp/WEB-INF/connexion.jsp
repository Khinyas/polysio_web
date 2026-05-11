<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lexend:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
<title>Connexion</title>
</head>
<body>
<%@ include file="header.jsp" %>
Page connexion
    <div>

    <div class="form-container">
       <form action="ControllerConnexion" method="POST">

          <table border="0">
             <tr>
                <td>
                   Nom
                </td>
                <td>
                   <input type="text" name="username" class="input-field">
                </td>
             </tr>
             <tr>
                <td>
                   Mot de passe
                </td>
                <td>
                   <input type="password" name="password" class="input-field">
                </td>
             </tr>

             <tr>
                <td colspan="2" style="text-align:center;">
                   <button type="submit">Se connecter</button>
                </td>
             </tr>
          </table>
       </form>
          <p style="color:red;">${msgErreur}</p>

       <a href="ControllerInscription" class="button">Pas de compte ? S'inscrire</a>
    </div>
    </div>
</body>
</html>