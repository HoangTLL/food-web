/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dto.CartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author htduy
 */
public class ModifyCartServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            ArrayList<CartItem> cart = (ArrayList<CartItem>) session.getAttribute("cart");

            if (cart == null) {
                cart = new ArrayList<>();
            }

            String itemid = request.getParameter("itemid");
            String action = request.getParameter("action");

            if (itemid != null && action != null) {
                int itemId = Integer.parseInt(itemid);
                switch (action) {
                    case "increase":
                        for (CartItem item : cart) {
                            if (item.getItem().getId() == itemId) {
                                item.setQuantity(item.getQuantity() + 1);
                                break;
                            }
                        }
                        break;
                    case "decrease":
                        for (CartItem item : cart) {
                            if (item.getItem().getId() == itemId) {
                                if (item.getQuantity() > 1) {
                                    item.setQuantity(item.getQuantity() - 1);
                                } else {
                                    cart.remove(item);
                                }
                                break;
                            }
                        }
                        break;
                    case "remove":
                        cart.removeIf(item -> item.getItem().getId() == itemId);
                        break;
                }
            }

            // Save cart into session memory
            session.setAttribute("cart", cart);
            // Determine the next page to forward or redirect
            String nextAction = request.getParameter("nextAction");
            if (nextAction != null) {
                request.getRequestDispatcher("MainController?action=" + nextAction).forward(request, response);
            }
        }
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
