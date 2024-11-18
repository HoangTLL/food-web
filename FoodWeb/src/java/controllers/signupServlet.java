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

/**
 *
 * @author htduy
 */
public class signupServlet extends HttpServlet {

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
            // Get user input from the form
            String fullname = request.getParameter("txtname");
            String password = request.getParameter("txtpassword");
            String email = request.getParameter("txtemail");
            String phone = request.getParameter("txtphone");
            String address = request.getParameter("txtaddress");
            String ward = request.getParameter("txtward");
            String district = request.getParameter("txtdistrict");
            String city = request.getParameter("txtcity");

            // Validate input (simple validation example)
            if (fullname == null || password == null || email == null || phone == null
                    || address == null || ward == null || district == null || city == null
                    || fullname.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()
                    || address.isEmpty() || ward.isEmpty() || district.isEmpty() || city.isEmpty()) {
                out.print("<div style='text-align: center'><h1>All fields are required.</h1>");
                out.print("<p><a href='registerForm.jsp'>Back to Register Form</a></p></div>");
                return;
            }

            // Check if email already exists
            AccountDAO d = new AccountDAO();
            boolean isExist = d.checkEmail(email);

            if (isExist) {
                out.print("<div style='text-align: center'><h1>Email already exists.</h1>");
                out.print("<p><a href='registerForm.jsp'>Back to Register Form</a></p></div>");
            } else {
                try {
                    int rs = d.insert(fullname, password, email, phone, address, Integer.parseInt(ward), Integer.parseInt(district), Integer.parseInt(city));
                    if (rs >= 1) {
                        request.getRequestDispatcher("MainController?action=welcome").forward(request, response);
                    } else {
                        request.getRequestDispatcher("MainController?action=ERROR").forward(request, response);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
