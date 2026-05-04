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
        	cases.append(String.format("<div id='%s' class='case' style='grid-column:%d; grid-row:%d;'>%s</div>",
        		casePlateau.getId(),
        		casePlateau.getNom(),
        		casePlateau.getPositionX(),
        		casePlateau.getPositionY(),
        		casePlateau.getTypeCase(),
        		casePlateau.getCheminSvg(),
        		casePlateau.getIdCSS()
        		))
        }
        /*String html = """
            <!DOCTYPE html>
            <html>
                <head>
                    <title>Mon Plateau</title>
                </head>
                <body>
                    <h1>Bonjour %s !</h1>
                    <p>Le HTML est enfin lisible dans mon code Java.</p>
                </body>
            </html>
            """.formatted(nom); // On injecte la variable avec %s

        out.print(html);
        
        // On vide le buffer vers le navigateur sans fermer brutalement
        out.flush(); 
        */
    }

    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
