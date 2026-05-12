<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.ModelJoueur, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Polysio - Plateau</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <style>
        *, *::before, *::after { box-sizing: border-box; margin: 0; padding: 0; }
        html, body  { height: 100%; overflow: hidden; }
        .game-container { display: flex; height: 100dvh; gap: 16px; padding: 8px; }
        .jeu-container {
            flex-shrink: 0;
            height: 100%;
            aspect-ratio: 1 / 1;
            overflow: hidden;
        }
        .plateau-grid {
            display: grid;
            grid-template-columns: 12.5% repeat(9, 8.3%) 12.5%;
            grid-template-rows:    12.5% repeat(9, 8.3%) 12.5%;
            width: 100%;
            height: 100%;
        }
        .case {
            border: 1px solid #333;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            overflow: hidden;
        }
        .case img { width: 100%; height: 100%; object-fit: fill; display: block; }
        .sidebar    { flex: 1; background: #f5f0e8; border: 1px solid #ccc;
                      border-radius: 8px; padding: 16px; overflow-y: auto;
                      display: flex; flex-direction: column; gap: 12px; }
        .btn        { display: inline-block; padding: 8px 16px; background: #4a7c59;
                      color: white; border-radius: 4px; text-decoration: none; }
        #chrono-container { background: #333; color: #ffeb3b; padding: 10px;
                            text-align: center; border-radius: 5px;
                            font-family: monospace; font-size: 1.5rem; }
        .modal-voile { position: fixed; inset: 0; background: rgba(0,0,0,0.6);
                       display: flex; align-items: center; justify-content: center;
                       z-index: 1000; }
        .modal-box  { background: white; border-radius: 20px; padding: 30px;
                      display: flex; flex-direction: column; align-items: center; gap: 20px; }
    </style>
</head>
<body>

<%-- ── POPUP DÉ (visible seulement si dernierDe est défini) ── --%>
<% if (request.getAttribute("dernierDe") != null) { %>
    <div class="modal-voile">
        <div class="modal-box">
            <p>🎲 Vous avez fait un <strong><%= request.getAttribute("dernierDe") %></strong> !</p>
            <a href="<%= request.getContextPath() %>/ViewPlateau" class="btn">Continuer</a>
        </div>
    </div>
<% } %>

<%-- ── PAGE PRINCIPALE ── --%>
<div class="game-container">

<%-- Plateau — pas de div wrapper, jeu-container est déjà dedans --%>
<%= request.getAttribute("plateauHTML") %>

    <%-- Barre latérale --%>
    <div class="sidebar">

        <%-- Chrono --%>
        <div id="chrono-container">⏱️ <span id="timer">00:00</span></div>

        <%-- Boutons d'action --%>
        <a href="<%= request.getContextPath() %>/ViewPlateau?action=lancerDe"   class="btn">🎲 Lancer les dés</a>
        <a href="<%= request.getContextPath() %>/ViewPlateau?action=acheter"    class="btn">🏠 Acheter</a>
        <a href="<%= request.getContextPath() %>/ViewPlateau?action=construire" class="btn">🔨 Construire</a>
        <a href="<%= request.getContextPath() %>/ViewPlateau?action=finirTour"  class="btn">⏭ Finir le tour</a>

        <hr>

        <%-- Liste des joueurs — on boucle en Java --%>
        <h4>Joueurs</h4>
        <%
            List<ModelJoueur> joueurs = (List<ModelJoueur>) request.getAttribute("listeJoueurs");
            if (joueurs != null) {
                for (ModelJoueur j : joueurs) {
        %>
            <div class="joueur-info">
                <h4><%= j.getPseudonyme() %></h4>
                <p>💰 <%= j.getPointsCompetences() %> pts</p>
            </div>
            <hr>
        <%
                }
            }
        %>

        <%-- Inventaire --%>
        <h4>Mes propriétés</h4>
        <%= request.getAttribute("inventaireHTML") %>

    </div>
</div>

<%-- ── SCRIPT CHRONO ── --%>
<script>
    let tempsRestant = <%= request.getAttribute("tempsRestantSec") %>;
    let urlAccueil   = "<%= request.getContextPath() %>";

    const display = document.getElementById('timer');
    if (display) {
        let m = Math.floor(tempsRestant / 60);
        let s = tempsRestant % 60;
        display.textContent = String(m).padStart(2,'0') + ':' + String(s).padStart(2,'0');
    }

    const interval = setInterval(function () {
        let minutes = Math.floor(tempsRestant / 60);
        let seconds = tempsRestant % 60;
        display.textContent =
            String(minutes).padStart(2, '0') + ':' +
            String(seconds).padStart(2, '0');
        if (--tempsRestant < 0) {
            clearInterval(interval);
            alert("⏰ TEMPS ÉCOULÉ !");
            window.location.href = urlAccueil + "/accueil";
        }
    }, 1000);
</script>
</body>
</html>