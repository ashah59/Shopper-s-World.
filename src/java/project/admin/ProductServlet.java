/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import project.business.Cart;
import project.business.LineItem;
import project.business.Product;
import project.business.User;
import project.data.ProductDB;
import project.data.UserDB;

/**
 *
 * @author shah
 */
public class ProductServlet extends HttpServlet {

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
                action = "getAvailable";  // default action
            }

            // perform action and set URL to appropriate page
            url = "/index.jsp";
            int prodId = 0;
            if (request.getParameter("prodId") != null && !request.getParameter("prodId").isEmpty()) {
                prodId = Integer.parseInt(request.getParameter("prodId"));
            }
            if (action.equals("add")) {
                Product product = new Product(request.getParameter("name"), request.getParameter("descr"), user.getEmail(), Double.valueOf(request.getParameter("price")), Integer.parseInt(request.getParameter("qty")));

                if (prodId == 0) {
                    ProductDB.insert(product);
                } else {
                    product.setId(prodId);
                    ProductDB.update(product);
                }
                ArrayList<Product> products = ProductDB.selectProductsBySeller(user.getEmail());
                session.setAttribute("products", products);
                url = "/view_products.jsp";
            } else if (action.equals("edit")) {
                Product prod = ProductDB.selectProduct(prodId);
                request.setAttribute("prod", prod);
                url = "/add_product.jsp";
            } else if (action.equals("getAvailable")) {
                ArrayList<Product> products = ProductDB.selectProducts();
                session.setAttribute("products", products);
            } else if (action.equals("addToCart")) {
                if (user == null) {
                    url = "/login.jsp";
                    session.setAttribute("cartAction", "addToCart");
                    session.setAttribute("cartProdId", prodId);
                } else {
                    int productCode = Integer.parseInt(request.getParameter("prodId"));
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
