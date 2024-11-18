package controllers;

import dao.AccountDAO;
import dto.Account;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserManagerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("LoginedUser");
        if (acc != null && acc.getRole().equals("admin")) {
            String action = request.getParameter("action");
            AccountDAO d = new AccountDAO();

            try {
                switch (action) {
                    case "add":
                        addUser(request, d);
                        break;
                    case "delete":
                        deleteUser(request, d);
                        break;
                    case "block":
                        blockUser(request, d);
                        break;
                    case "update":
                        updateUser(request, d);
                        System.out.println("updateAcc: ");
                        break;
                    case "search":
                        searchUser(request, d);
                        break;
                    default:
                        ArrayList<Account> list = d.getAllAccounts();
                        request.setAttribute("ListUser", list);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Redirect back to the user management page
//        ArrayList<Account> list = d.getAllAccounts();
//        request.setAttribute("ListUser", list);
            request.getRequestDispatcher("userManager.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("403page.jsp").forward(request, response);
        }
    }

    private void addUser(HttpServletRequest request, AccountDAO d) throws Exception {
        String fullName = request.getParameter("userName");
        String email = request.getParameter("userEmail");
        String phone = request.getParameter("userPhone");
        String address = request.getParameter("userAddress");
        int wardId = Integer.parseInt(request.getParameter("userWardId"));
        int districtId = Integer.parseInt(request.getParameter("userDistrictId"));
        int cityId = Integer.parseInt(request.getParameter("userCityId"));
        String role = request.getParameter("userRole");
        boolean status = Boolean.parseBoolean(request.getParameter("userStatus"));

        Account newAccount = new Account(fullName, email, phone, address, wardId, districtId, cityId, role, status);
        d.addAccount(newAccount);
    }

    private void updateUser(HttpServletRequest request, AccountDAO d) throws Exception {
        int id = Integer.parseInt(request.getParameter("userId"));
        String fullName = request.getParameter("userName");
        String email = request.getParameter("userEmail");
        String phone = request.getParameter("userPhone");
        String address = request.getParameter("userAddress");
        int wardId = Integer.parseInt(request.getParameter("userWardId"));
        int districtId = Integer.parseInt(request.getParameter("userDistrictId"));
        int cityId = Integer.parseInt(request.getParameter("userCityId"));
        String role = request.getParameter("userRole");
        boolean status = Boolean.parseBoolean(request.getParameter("userStatus"));

        Account updatedAccount = new Account(id, fullName, email, phone, address, wardId, districtId, cityId, role, status);
        System.out.println("updateAcc: ");
        d.updateAccount(updatedAccount);
        
        ArrayList<Account> list = d.getAllAccounts();
        request.setAttribute("ListUser", list);
    }

    private void deleteUser(HttpServletRequest request, AccountDAO d) throws Exception {
        String id = request.getParameter("userId");
        if(!d.deleteAccount(id)){
            request.setAttribute("Error", "Can not delete this User");
        }
        ArrayList<Account> list = d.getAllAccounts();
        request.setAttribute("ListUser", list);
        

    }

    private void blockUser(HttpServletRequest request, AccountDAO d) throws Exception {
        String id = request.getParameter("userId");
        boolean currentStatus = Boolean.parseBoolean(request.getParameter("userStatus"));

        // Toggle the status (block/unblock)
        boolean newStatus = !currentStatus;

        // Update account status
        d.updateAccountStatus(id, newStatus);
        ArrayList<Account> list = d.getAllAccounts();
        request.setAttribute("ListUser", list);
    }

    private void searchUser(HttpServletRequest request, AccountDAO d) throws Exception {
        String searchQuery = request.getParameter("searchQuery").toLowerCase();
        System.out.println(searchQuery);
        ArrayList<Account> searchAccounts = d.getAccountsByInfo(searchQuery);
        request.setAttribute("ListUser", searchAccounts);
        System.out.println(searchAccounts);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
