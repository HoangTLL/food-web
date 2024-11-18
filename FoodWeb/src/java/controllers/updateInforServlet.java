/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.AccountDAO;
import dto.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tan Phat
 */
public class updateInforServlet extends HttpServlet {

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
        try {
            // Capture form data
            String fullName = request.getParameter("txtname");
            String email = request.getParameter("txtemail");
            String phone = request.getParameter("txtphone");
            String address = request.getParameter("txtaddress");
            String wardStr = request.getParameter("txtward");
            String districtStr = request.getParameter("txtdistrict");
            String cityStr = request.getParameter("txtcity");
            String accountIdStr = request.getParameter("accountId");

            // Check for null or empty strings before parsing
            int wardId = (wardStr != null && !wardStr.isEmpty()) ? Integer.parseInt(wardStr) : 0;
            int districtId = (districtStr != null && !districtStr.isEmpty()) ? Integer.parseInt(districtStr) : 0;
            int cityId = (cityStr != null && !cityStr.isEmpty()) ? Integer.parseInt(cityStr) : 0;
            int accountId = (accountIdStr != null && !accountIdStr.isEmpty()) ? Integer.parseInt(accountIdStr) : 0;

            // Assuming role and status are static for this example
            String role = "user";
            boolean status = true;

            // Create account object
            Account account = new Account(accountId, fullName, email, phone, address, wardId, districtId, cityId, role, status);

            // Update the account in the database
            AccountDAO accountDAO = new AccountDAO();
            try {
                accountDAO.updateAccount(account);

                // Retrieve the updated account from the database
                Account updatedAccount = accountDAO.getAccountById(accountId);

                // Update the account in the session
                HttpSession session = request.getSession(false);
                session.setAttribute("LoginedUser", updatedAccount);

                request.setAttribute("message", "Account updated successfully!");
                request.setAttribute("color", "text-success");
            } catch (Exception e) {
                request.setAttribute("message", "Account update failed!");
                request.setAttribute("color", "text-danger");
                e.printStackTrace();
            }

        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid data format!");
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("message", "An error occurred!");
            e.printStackTrace();
        }

        request.getRequestDispatcher("MainController?action=user").forward(request, response);
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
