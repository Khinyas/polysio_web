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
			if (casePlateau.getNom().equals("centre")) {
				cases.append(String.format(
					"<div id='%d' class='case' style='grid-column:%d / span 2; grid-row:%d / span 2; '>" +
							"<img src='%s' alt='%s' width='100%' height='100%' />" +
							"</div>",
					casePlateau.getIdCSS(),
					casePlateau.getPositionX(),
					casePlateau.getPositionY(),
					/*request.getContextPath() + */casePlateau.getCheminSvg(),
					casePlateau.getNom()
				));
			} else {
				cases.append(String.format(
				"<div id='%d' class='case' style='grid-column:%d; grid-row:%d; '>" +
							"<img src='%s' alt='%s' width='100%' height='100%' />" +
						"</div>",
						casePlateau.getIdCSS(),
						casePlateau.getPositionX(),
						casePlateau.getPositionY(),
						/*request.getContextPath() + */casePlateau.getCheminSvg(),
						casePlateau.getNom()
						));
			}
        }
        String html = """
            <!DOCTYPE html>
            <html>
                <head>
                    <title>Mon Plateau</title>
                </head>
                <body>
                    <div class="plateau">
                    	%s
                    </div>
                </body>
            </html>
            """.formatted(cases.toString());

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
