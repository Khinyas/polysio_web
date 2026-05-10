package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ControllerConstructeurPlateau
 */
@WebServlet("/ControllerPlateau")
public class ControllerPlateau extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerPlateau() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        //response.getWriter().append("Served at: ").append(request.getContextPath());
        String param = request.getParameter("action");
        if (param == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action inconnue : " + param);
        }
        if (param != null) {
            System.out.println("test valeur null param");
            switch (param) {
                case "jouer": {
                    List<ModelCase> listeCases = new ArrayList<>();
                    listeCases = ControllerCase.plateauBuilder();

                    List<ModelPropriete> listeProprietes = new ArrayList<>();
                    listeProprietes = ControllerPropriete.proprietePlateau();

                    // Todo Liste de joueur à rajouter en parametre (et du constructeur ModelPlateau)
                    ModelPlateau modelPlateau = new ModelPlateau(listeCases, listeProprietes, request.getContextPath());
                    request.getSession().setAttribute("plateau", modelPlateau);

                    request.getRequestDispatcher("/ViewPlateau").forward(request, response);
                    return;
                }
                default: {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action inconnue : " + param);
                }
            }
        }

    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
