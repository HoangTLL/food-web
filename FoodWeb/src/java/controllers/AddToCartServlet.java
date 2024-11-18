/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ItemDAO;
import dao.MenuDAO;
import dto.CartItem;
import dto.Item;
import dto.Menu;
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
 * @author Tan Phat
 */
public class AddToCartServlet extends HttpServlet {

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
            String itemid = request.getParameter("itemid");
            String actionFrom = request.getParameter("actionFrom");

            HttpSession session = request.getSession();
            ArrayList<CartItem> cart = (ArrayList<CartItem>) session.getAttribute("cart");

            ItemDAO d = new ItemDAO();
            MenuDAO md = new MenuDAO();

            Item it = null;
            Menu menu = null;

            // Determine item type and fetch from DAO accordingly
            if ("dish".equals(actionFrom)) {
                it = d.getItemById(Integer.parseInt(itemid));
            } else if ("menu".equals(actionFrom)) {
                menu = md.getMenuById(Integer.parseInt(itemid));
                if (menu != null) {
                    it = new Item(menu.getId(), menu.getName(), menu.getTotalPrice(), menu.isStatus(), menu.getDescription(), menu.getCategory(), menu.getTotalCalories(), menu.getImages().get(0), menu.getImages().get(1), menu.getImages().get(2), menu.getImages().get(3));
                }
            } else {
                return; // Handle this case appropriately
            }

            if (cart == null) {
                cart = new ArrayList<>();
                cart.add(new CartItem(it, 1));
            } else {
                boolean found = false;
                for (CartItem cartItem : cart) {
                    if (cartItem.getItem().getId() == Integer.parseInt(itemid)) {
                        cartItem.setQuantity(cartItem.getQuantity() + 1);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    cart.add(new CartItem(it, 1));
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
