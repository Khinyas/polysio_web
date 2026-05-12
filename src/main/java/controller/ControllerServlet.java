package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelUser;
import model.ModelUserRole;

import java.io.IOException;
import java.util.ArrayList;

import connexion.DAOUser;

/**
 * Servlet implementation class ControllerServlet
 */


@WebServlet(urlPatterns = { "/connexion", "/index", "/accueil", "/inscription", "/profil", "/ChoixPartie", "/admin", "/supprimerUser", "/modifierUser" })
public class ControllerServlet extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

String path = request.getServletPath();
String vue  = "";

ModelUser user = (ModelUser) request.getSession().getAttribute("userSession");

// --- Garde-fous d'accès ---
if (path.equals("/admin") || path.equals("/supprimerUser") || path.equals("/modifierUser")) {
if (user == null || user.getRole() != ModelUserRole.ADMIN) {
request.setAttribute("message", "Accès refusé : Identifiants insuffisants.");
request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
return;
}
}

if (path.equals("/connexion") || path.equals("/inscription")) {
if (user != null) {
response.sendRedirect(request.getContextPath() + "/accueil");
return;
}
}

if (path.equals("/profil")) {
if (user == null) {
response.sendRedirect(request.getContextPath() + "/connexion");
return;
}
}

// --- Routage ---
switch (path) {

case "/":
request.setAttribute("message", "Page d'accueil");
vue = "/WEB-INF/accueil.jsp";
break;

case "/index":
request.setAttribute("message", "Bienvenue sur l'index");
vue = "/WEB-INF/index.jsp";
break;

case "/connexion":
request.setAttribute("message", "Bienvenue sur le plateau");
vue = "/WEB-INF/connexion.jsp";
break;

case "/inscription":
request.setAttribute("message", "Page d'inscription");
vue = "/WEB-INF/inscription.jsp";
break;

case "/accueil":
request.setAttribute("message", "Page d'accueil");
vue = "/WEB-INF/accueil.jsp";
break;

case "/profil":
request.setAttribute("message", "Page de profil");
vue = "/WEB-INF/profil.jsp";
break;

case "/ChoixPartie":
request.setAttribute("message", "Choix de partie");
vue = "/WEB-INF/ChoixPartie.jsp";
break;

case "/admin":
request.setAttribute("utilisateurs", DAOUser.reqListeTousLesUtilisateurs());
vue = "/WEB-INF/admin.jsp";
break;

case "/supprimerUser": {
int id = Integer.parseInt(request.getParameter("id"));
DAOUser.deleteUSer(id);
response.sendRedirect(request.getContextPath() + "/accueil?afficher=true");
return;
}

default:
vue = "/WEB-INF/404.jsp";
break;
}

request.getRequestDispatcher(vue).forward(request, response);
}

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

String path = request.getServletPath();

if (path.equals("/modifierUser")) {
ModelUser user = (ModelUser) request.getSession().getAttribute("userSession");
if (user == null || user.getRole() != ModelUserRole.ADMIN) {
response.sendError(HttpServletResponse.SC_FORBIDDEN);
return;
}
int    id     = Integer.parseInt(request.getParameter("id"));
String pseudo = request.getParameter("pseudo");
String email  = request.getParameter("email");
String role   = request.getParameter("role");
DAOUser.modifierUtilisateur(id, pseudo, email, role);
response.sendRedirect(request.getContextPath() + "/accueil?afficher=true");
return;
}

doGet(request, response);
}
}
