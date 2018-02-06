/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import project.business.User;
import project.data.UserDB;

/**
 *
 * @author shah
 */
public class ProfileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "";
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            // get current action
            String action = request.getParameter("action");
            if (action == null) {
                action = "";  // default action
            }

            // perform action and set URL to appropriate page
            url = "/profile.jsp";
            if (action.equals("user")) {
                user.setPassword(request.getParameter("pwd"));
                user.setName(request.getParameter("name"));
                UserDB.updateUser(user);
            } else if (action.equals("contact")) {
                user.setAddress(request.getParameter("address"));
                user.setCity(request.getParameter("city"));
                user.setStateCode(request.getParameter("state"));
                user.setZipCode(request.getParameter("zip"));
                user.setPhone(request.getParameter("phone"));
                UserDB.updateContact(user);
            }
            if (user != null) {
                session.setAttribute("user", user);
            }
        } catch (Exception ex) {
            request.setAttribute("message", ex.getMessage());
        } finally {

        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
