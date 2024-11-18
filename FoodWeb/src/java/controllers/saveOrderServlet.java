/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.OrderDAO;
import dto.Account;
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
public class saveOrderServlet extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        Account acc = (Account) session.getAttribute("LoginedUser");
        if (acc != null) {
            OrderDAO od = new OrderDAO();
            ArrayList<CartItem> cart = (ArrayList<CartItem>) session.getAttribute("cart");

            String ship_address = request.getParameter("txtaddress");
            int city_id = Integer.parseInt(request.getParameter("txtcity"));
            int district_id = Integer.parseInt(request.getParameter("txtdistrict"));
            int ward_id = Integer.parseInt(request.getParameter("txtward"));
            String note = request.getParameter("txtNote");

            if (cart != null) {
                int orderId = od.saveOrder(acc.getId(), ship_address, city_id, district_id, ward_id, note, cart);
                if (orderId > 0) {
                    for (CartItem item : cart) {
                        int itemId = item.getItem().getId();
                        String itemCategory = item.getItem().getCategory();
                        int itemQuantity = item.getQuantity();
                        int itemPrice = item.getItem().getPrice();
                        od.saveOrderDetail(orderId, itemId, itemCategory, itemQuantity, itemPrice);
                    }
                    session.removeAttribute("cart");
                    request.getRequestDispatcher("MainController?action=mainindex").forward(request, response);
                }
            }
        } else {
            request.getRequestDispatcher("MainController?action=welcome").forward(request, response);
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
