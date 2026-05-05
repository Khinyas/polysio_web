package view;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelCase;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ViewPlateau
 */
@WebServlet("/ViewPlateau")
public class ViewPlateau extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPlateau() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String nom = request.getParameter("parametre");
        List<ModelCase> listeCases = (List<ModelCase>) request.getAttribute("listeCasesBdd");
        StringBuilder cases = new StringBuilder();
        for (ModelCase casePlateau : listeCases) {
            // On convertit les positions 0-10 en positions CSS 1-11
            int cssX = casePlateau.getPositionX() + 1;
            int cssY = casePlateau.getPositionY() + 1;

            if (casePlateau.getNom().equals("centre")) {
                cases.append(String.format(
                    "<div id='%s' class='case' style='grid-column:%d / span 9; grid-row:%d / span 9; '>" +
                    "<img src='%s' alt='%s' width='100%%' height='100%%' />" + 
                    "</div>",
                    casePlateau.getIdCSS(),
                    2, // Le centre commence à la colonne 2 (après la 1ère case)
                    2, // Le centre commence à la ligne 2
                    request.getContextPath() + casePlateau.getCheminSvg(),
                    casePlateau.getNom()
                ));
            } else {
                cases.append(String.format(
                    "<div id='%s' class='case' style='grid-column:%d; grid-row:%d; '>" +
                    "<img src='%s' alt='%s' width='100%%' height='100%%' />" + 
                    "</div>",
                    casePlateau.getIdCSS(),
                    cssX, // Utilise la position corrigée (+1)
                    cssY, // Utilise la position corrigée (+1)
                    request.getContextPath() + casePlateau.getCheminSvg(),
                    casePlateau.getNom()
                ));
            }
        }
        
        String html = """
        	    <!DOCTYPE html>
        	    <html>
        	        <head>
        	            <title>Mon Plateau</title>
        	            <link rel="stylesheet" type="text/css" href="%s/css/style.css">
        	            <style>
        	                .plateau {
        	                    display: grid;
        	                    grid-template-columns: repeat(11, 1fr);
        	                    grid-template-rows: repeat(11, 1fr);
        	                    width: 95vmin; 
        	                    height: 95vmin;
        	                    margin: auto;
        	                    grid-gap: 0; 
        	                    border: 2px solid black;
        	                }
        	                .case {
        	                    border: 1px solid black;
        	                    padding: 0;
        	                    margin: 0;
        	                    display: flex;
        	                    overflow: hidden;
        	                }
        	                .case img {
        	                    width: 100%%; /* DOUBLE POURCENTAGE ICI */
        	                    height: 100%%; /* DOUBLE POURCENTAGE ICI */
        	                    object-fit: fill; 
        	                    display: block;
        	                }
        	            </style>
        	        </head>
        	        <body>
        	            <div class="plateau">
        	                %s
        	            </div>
        	        </body>
        	    </html>
        	    """.formatted(request.getContextPath(), cases.toString());

        out.print(html);
        // On vide le buffer vers le navigateur sans fermer brutalement
        out.flush();
    }

    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
