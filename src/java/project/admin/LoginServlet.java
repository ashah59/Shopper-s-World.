/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.admin;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import project.business.Cart;
import project.business.LineItem;
import project.business.Order;
import project.business.Product;
import project.business.User;
import project.business.User.UserType;
import project.data.OrderDB;
import project.data.ProductDB;
import project.data.UserDB;

/**
 *
 * @author shah
 */
public class LoginServlet extends HttpServlet {

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
        User user = null;
        try {
            // get current action
            String action = request.getParameter("action");
            if (action == null) {
                action = "";  // default action
            }

            String email = request.getParameter("email");
            String pwd = request.getParameter("pwd");
            if (action.equals("login")) {
                user = UserDB.selectUser(email, pwd);
                if (user.getUserType() == UserType.seller) {
                    ArrayList<Product> products = ProductDB.selectProductsBySeller(user.getEmail());
                    session.setAttribute("products", products);
                    url = "/view_products.jsp";
                } else {
                    ArrayList<Product> products = ProductDB.selectProducts();
                    session.setAttribute("products", products);
                    url = "/index.jsp";
                }
            } else if (action.equals("signup")) {
                String name = request.getParameter("name");
                String userType = request.getParameter("userType");
                user = new User(name, email, pwd, UserType.valueOf(userType));
                UserDB.insert(user);
                url = "/profile.jsp";
            } else if (action.equals("logout")) {
                url = "/index.jsp";
                session.setAttribute("user", null);
                session.setAttribute("total", null);
                session.setAttribute("tax", null);
                session.setAttribute("totalWithTax", null);
                session.setAttribute("cart", null);
                session.setAttribute("orders", null);
                user = null;
            }
            if (user != null) {
                session.setAttribute("user", user);
                ArrayList<Order> orders = OrderDB.selectOrdersByEmail(user.getEmail());
                session.setAttribute("orders", orders);
            }
            if (session.getAttribute("cartAction") != null && session.getAttribute("cartAction").equals("addToCart")) {

                int productCode = (Integer) session.getAttribute("cartProdId");
                String quantityString = request.getParameter("quantity");

                Cart cart = (Cart) session.getAttribute("cart");
                if (cart == null) {
                    cart = new Cart();
                }

                //if the user enters a negative or invalid quantity,
                //the quantity is automatically reset to 1.
                int quantity;
                try {
                    quantity = Integer.parseInt(quantityString);
                    if (quantity < 0) {
                        quantity = 1;
                    }
                } catch (NumberFormatException nfe) {
                    quantity = 1;
                }

                Product product = ProductDB.selectProduct(productCode);

                LineItem lineItem = new LineItem();
                lineItem.setProduct(product);
                lineItem.setQuantity(quantity);
                if (quantity > 0) {
                    cart.addItem(lineItem);
                } else if (quantity == 0) {
                    cart.removeItem(lineItem);
                }

                double total = 0.0;
                for (LineItem row : cart.getItems()) {
                    total += row.getTotal();
                }

                NumberFormat currency = NumberFormat.getCurrencyInstance();
                session.setAttribute("total", currency.format(total));
                session.setAttribute("tax", currency.format(total * 0.0725));
                total += total * 0.0725;
                session.setAttribute("totalWithTax", currency.format(total));

                session.setAttribute("cart", cart);
                session.setAttribute("cartAction", null);
                url = "/cart.jsp";
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
