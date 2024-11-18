/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.OrderDAO;
import dto.Account;
import dto.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author htduy
 */
public class OrderManagerServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("LoginedUser");
        if (acc != null && acc.getRole().equals("admin")) {
            String action = request.getParameter("action");
            OrderDAO orderDAO = new OrderDAO();

            try {
                switch (action) {
                    case "searchByDate":
                        searchOrdersByDate(request, orderDAO);
                        break;
                    case "searchByDateRange":
                        searchOrdersByDateRange(request, orderDAO);
                        break;
                    case "searchByCustomerInfo":
                        searchOrdersByCustomerInfo(request, orderDAO);
                        break;
                    case "groupByAddress":
                        groupOrdersByAddress(request, orderDAO);
                        break;
                    case "filterOrders":
                        filterOrders(request, orderDAO);
                        break;
                    case "updateStatus":
                        updateOrderStatus(request, orderDAO);
                        break;
                    default:
                        getAllOrders(request, orderDAO);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            setFilterAttributes(request, orderDAO); // Set filter attributes regardless of action
            request.getRequestDispatcher("orderManager.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("403page.jsp").forward(request, response);
        }
    }

    private void searchOrdersByDate(HttpServletRequest request, OrderDAO orderDAO) throws Exception {
        Date date = Date.valueOf(request.getParameter("orderDate"));
        List<Order> orders = orderDAO.searchOrdersByDate(date);
        request.setAttribute("OrderList", orders);
    }

    private void searchOrdersByDateRange(HttpServletRequest request, OrderDAO orderDAO) throws Exception {
        Date fromDate = Date.valueOf(request.getParameter("orderDateFrom"));
        Date toDate = Date.valueOf(request.getParameter("orderDateTo"));
        List<Order> orders = orderDAO.searchOrdersByDateRange(fromDate, toDate);
        request.setAttribute("OrderList", orders);
    }

    private void searchOrdersByCustomerInfo(HttpServletRequest request, OrderDAO orderDAO) throws Exception {
        String customerInfo = request.getParameter("customerInfo");
        List<Order> orders = orderDAO.searchOrdersByCustomerInfo(customerInfo);
        request.setAttribute("OrderList", orders);
    }

    private void groupOrdersByAddress(HttpServletRequest request, OrderDAO orderDAO) throws Exception {
        Map<String, List<Order>> ordersGroupedByAddress = orderDAO.getOrdersGroupedByAddress();
        request.setAttribute("OrdersGroupedByAddress", ordersGroupedByAddress);
    }

    private void getAllOrders(HttpServletRequest request, OrderDAO orderDAO) throws Exception {
        List<Order> orders = orderDAO.getAllOrders();
        request.setAttribute("OrderList", orders);
    }

    private void filterOrders(HttpServletRequest request, OrderDAO orderDAO) throws Exception {
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");

        // Check if parameters are empty strings and set them to null
        city = (city != null && !city.isEmpty()) ? city : null;
        district = (district != null && !district.isEmpty()) ? district : null;
        ward = (ward != null && !ward.isEmpty()) ? ward : null;

        List<Order> orders = orderDAO.filterOrders(city, district, ward);

        // For debugging purposes, print the parameters and the resulting orders
        System.out.println("City: " + city);
        System.out.println("District: " + district);
        System.out.println("Ward: " + ward);
        System.out.println("Filtered Orders: " + orders);

        request.setAttribute("OrderList", orders);
    }

    private void setFilterAttributes(HttpServletRequest request, OrderDAO orderDAO) {
        try {
            List<String> cities = orderDAO.getCities();
            List<String> districts = new ArrayList<>();
            List<String> wards = new ArrayList<>();

            // Only populate districts and wards if city is selected
            String selectedCity = request.getParameter("city");
            if (selectedCity != null && !selectedCity.isEmpty()) {
                districts = orderDAO.getDistricts(selectedCity);
                String selectedDistrict = request.getParameter("district");
                if (selectedDistrict != null && !selectedDistrict.isEmpty()) {
                    wards = orderDAO.getWards(selectedDistrict);
                }
            }

            request.setAttribute("CityList", cities);
            request.setAttribute("DistrictList", districts);
            request.setAttribute("WardList", wards);
        } catch (Exception e) {
            e.printStackTrace();
            // Optionally handle the exception or log it
        }
    }

    private void updateOrderStatus(HttpServletRequest request, OrderDAO orderDAO) throws Exception {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int status = Integer.parseInt(request.getParameter("status"));
        orderDAO.updateOrderStatus(orderId, status);
        getAllOrders(request, orderDAO); // Refresh the order list after updating status
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
