package controller;

import java.io.IOException;
import java.util.ArrayList;

import connexion.DAOUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelUser;

@WebServlet("/admin")
public class ControllerAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ControllerAdmin() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<ModelUser> utilisateurs = DAOUser.reqListeTousLesUtilisateurs();

        request.setAttribute("utilisateurs", utilisateurs);

        request.getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}