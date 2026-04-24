<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Polysio - Web Portal</title>
    <style>
        body { font-family: sans-serif; text-align: center; background: #2c3e50; color: white; }
        .menu { display: flex; flex-direction: column; gap: 10px; align-items: center; margin-top: 50px; }
        a { color: #3498db; text-decoration: none; font-size: 1.2em; border: 1px solid #3498db; padding: 10px 20px; border-radius: 5px; width: 200px; }
        a:hover { background: #3498db; color: white; }
    </style>
</head>
<body>
    <h1>Bienvenue sur Polysio Web</h1>
    <div class="menu">
        <a href="ControllerConnexion.java">Se connecter</a>
        
        <a href="Controllerinscription.java">S'inscrire</a>
        
        <a href="test_db.jsp">Tester la Connexion BDD</a>
        
        <a href="ControllerAccueil.java">Accueil</a>
    </div>
</body>
</html>