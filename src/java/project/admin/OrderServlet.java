/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import project.business.Cart;
import project.business.Order;
import project.business.User;
import project.data.OrderDB;
import project.data.UserDB;

/**
 *
 * @author shah
 */
public class OrderServlet extends HttpServlet {

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

            if (action.equals("makePay")) {
                String amount = session.getAttribute("totalWithTax").toString();
                Order order = new Order();
                order.setAmount(amount);
                order.setCardName(request.getParameter("name"));
                order.setCardNum(request.getParameter("cardNum"));
                order.setExpMM(Integer.parseInt(request.getParameter("expMonth")));
                order.setExpYY(Integer.parseInt(request.getParameter("expYear")));
                order.setCvv(Integer.parseInt(request.getParameter("cvv")));
                order.setCustEmail(user.getEmail());

                OrderDB.insert(order);
            } else if (action.equals("cancel")) {
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                OrderDB.delete(orderId);
            }
            ArrayList<Order> orders = OrderDB.selectOrdersByEmail(user.getEmail());
            session.setAttribute("orders", orders);
            url = "/view_orders.jsp";
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
