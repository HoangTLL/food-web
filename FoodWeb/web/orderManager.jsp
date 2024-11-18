<%-- 
    Document   : orderManager
    Created on : Jul 12, 2024, 12:07:27 AM
    Author     : Tan Phat
--%>

<%@page import="java.util.Collections"%>
<%@page import="dto.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.Account"%>
<%@page import="dto.Order"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="author" content="Duy Hoang Thanh & Phat Tran Tan" />
        <link rel="shortcut icon" href="./img/logo.jpg" type="image/jpg" />
        <title>Order Management</title>
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.1/css/bootstrap.min.css"
            integrity="sha512-Ez0cGzNzHR1tYAv56860NLspgUGuQw16GiOOp/I2LuTmpSK9xDXlgJz3XN4cnpXWDmkNBKXR/VDMTCnAaEooxA=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css"
            />
        <link href="https://cdn.lineicons.com/2.0/LineIcons.css" rel="stylesheet" />
        <link
            rel="stylesheet"
            href="https://use.fontawesome.com/releases/v6.1.1/css/all.css"
            />
        <link rel="stylesheet" href="./css/navbar.css" />
        <style>
            div{
                font-family: Arial, sans-serif;
                font-size: 16px;
            }
            table {
                border-collapse: collapse;
                width: 100%;
                color: #333;
                font-family: Arial, sans-serif;
                font-size: 16px;
                text-align: left;
                border-radius: 10px;
                overflow: hidden;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
                margin: auto;
                margin-top: 50px;
                margin-bottom: 50px;
            }
            thead{
                text-align: center;
                background-color: rgb(2, 72, 157);
                color: white;
            }
        </style>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg">
                <div class="container">
                    <a class="navbar-brand me-5 py-2 fs-4 fw-bold" href="MainController?action=adminindex">
                        <i class="fa fa-utensils"></i>
                        Do Food - Delicious Food
                    </a>
                    <div class="collapse navbar-collapse" id="navbarColor01">
                        <ul class="navbar-nav me-auto nav1">
                            <li class="nav-item text-center">
                                <a class="nav-link fs-4 fw-bold" href="MainController?action=orderManager" role="button" aria-haspopup="true" aria-expanded="false">Order Management</a>
                            </li>
                            <li class="nav-item text-center">
                                <a class="nav-link fs-4 fw-bold" href="MainController?action=dishManager" role="button" aria-haspopup="true" aria-expanded="false">Dishes Management</a>
                            </li>
                            <li class="nav-item text-center">
                                <a class="nav-link fs-4 fw-bold" href="MainController?action=userManager" role="button" aria-haspopup="true" aria-expanded="false">User Management</a>
                            </li>
                            <%
                                Account acc = (Account) session.getAttribute("LoginedUser");
                                if (acc != null) {
                                    if (acc.getRole().equals("admin")) {
                            %>
                            <li class="nav-item text-center">
                                <a
                                    class="nav-link fs-4 fw-bold"
                                    href="MainController?action=adminindex"
                                    role="button"
                                    aria-haspopup="true"
                                    aria-expanded="false"
                                    >
                                    <i class="fa fa-user"></i>
                                </a>
                            </li> 
                            <%                                }
                            } else {
                            %>
                            <li class="nav-item text-center">
                                <a
                                    class="nav-link fs-4 fw-bold"
                                    href="MainController?action=welcome"
                                    role="button"
                                    aria-haspopup="true"
                                    aria-expanded="false"
                                    >
                                    <i class="fa fa-sign-in-alt"></i>
                                </a>
                            </li>
                            <%
                                }
                            %>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
        <div class="container mt-4">
            <!-- Search Orders -->
            <h1 class="mb-2 fw-bold">Order Management</h1>
            <div class="background" style="border-collapse: collapse;
                 width: 100%;
                 color: #333;
                 font-family: Arial, sans-serif;
                 font-size: 16px;
                 text-align: left;
                 border-radius: 10px;
                 overflow: hidden;
                 box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
                 margin: auto;
                 margin-top: 50px;
                 padding: 10px;
                 margin-bottom: 50px;" >
                <div class="mb-4">
                    <form class="form-inline row" action="OrderManagerServlet" method="Post">
                        <div class="col-5">
                            <label for="orderDateFrom" class="mr-2">From Date:</label>
                            <input type="date" class="form-control mr-2" id="orderDateFrom" name="orderDateFrom">
                        </div>
                        <div class="col-5">
                            <label for="orderDateTo" class="mr-2">To Date:</label>
                            <input type="date" class="form-control mr-2" id="orderDateTo" name="orderDateTo">
                        </div>
                        <div class="mt-4 col-2">
                            <input type="hidden" name="action" value="searchByDateRange">
                            <button type="submit" class="btn btn-primary">Apply</button>
                        </div>
                    </form>
                </div>
                <div class="mb-4">
                    <form class="row form-inline" action="OrderManagerServlet" method="Post">
                        <div class="form-group col-11">
                            <input type="hidden" name="action" value="searchByCustomerInfo">
                            <label for="customerInfo" class="mr-2">Search by Phone/Email:</label>
                            <input type="text" class="form-control mr-2" id="customerInfo" name="customerInfo">
                        </div>
                        <div class="col-1">
                            <button type="submit" class="mt-4 btn btn-primary">Search</button>
                        </div>
                    </form>
                </div>

                <!-- Filter Orders -->
                <form method="Post" action="OrderManagerServlet">
                    <input type="hidden" name="action" value="filterOrders">

                    <div class="form-group">
                        <label for="city">City:</label>
                        <select id="city" name="city" class="form-control" onchange="this.form.submit()">
                            <option value="">All</option>
                            <%
                                List<String> CityList = (List<String>) request.getAttribute("CityList");
                                if (CityList != null) {
                                    for (String city : CityList) {
                            %>
                            <option value="<%= city%>" <%= city.equals(request.getParameter("city")) ? "selected" : ""%>><%= city%></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="district">District:</label>
                        <select id="district" name="district" class="form-control" onchange="this.form.submit()">
                            <option value="">All</option>
                            <%
                                List<String> DistrictList = (List<String>) request.getAttribute("DistrictList");
                                if (DistrictList != null) {
                                    for (String district : DistrictList) {
                            %>
                            <option value="<%= district%>" <%= district.equals(request.getParameter("district")) ? "selected" : ""%>><%= district%></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="ward">Ward:</label>
                        <select id="ward" name="ward" class="form-control" onchange="this.form.submit()">
                            <option value="">All</option>
                            <%
                                List<String> WardList = (List<String>) request.getAttribute("WardList");
                                if (WardList != null) {
                                    for (String ward : WardList) {
                            %>
                            <option value="<%= ward%>" <%= ward.equals(request.getParameter("ward")) ? "selected" : ""%>><%= ward%></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                </form>

            </div>

            <div class="mb-4">
                <form class="form-inline" action="OrderManagerServlet" method="GET">
                    <input type="hidden" name="action" value="groupByAddress">
                    <button type="submit" class="btn btn-success">Group by Address</button>
                </form>
            </div>
            <!-- Display Orders -->

            <%
                Map<String, List<Order>> OrdersGroupedByAddress = (Map<String, List<Order>>) request.getAttribute("OrdersGroupedByAddress");
                if (OrdersGroupedByAddress == null || OrdersGroupedByAddress.isEmpty()) {
                    List<Order> orderList = (List<Order>) request.getAttribute("OrderList");
                    if (orderList != null) {
                        Collections.reverse(orderList);
                    }
            %>  
            <div id="Order-history" class="mb-5">
                <h5>Your Order History:</h5>
                <table class="table table-striped my-1 text-center">
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Customer Name</th>
                            <th>Order Date</th>
                            <th>Ship Date</th>
                            <th>Address</th>
                            <th>City</th>
                            <th>District</th>
                            <th>Ward</th>
                            <th>Total Price</th>
                            <th>Customer Note</th>
                            <th>Status</th>
                            <th>Status change</th>
                            <th>Detail</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if (orderList != null && !orderList.isEmpty()) {
                                int index = 1;
                                String statusOrder = "";
                                for (Order od : orderList) {
                                    if (od.getStatus() == 1) {
                                        statusOrder = "<span class='fw-bold'>Pending</span>";
                                    } else if (od.getStatus() == 2) {
                                        statusOrder = "<span class='text-primary fw-bold'>Delivery</span>";
                                    } else if (od.getStatus() == 3) {
                                        statusOrder = "<span class='text-success fw-bold'>Completed</span>";
                                    }
                        %>
                        <tr>
                            <td><%= od.getOrderId()%></td>
                            <td><%= od.getAccountName()%></td>
                            <td><%= od.getOrderDate()%></td>
                            <td><%= od.getShipDate()%></td>
                            <td><%= od.getShipAddress()%></td>
                            <td><%= od.getShipCity()%></td>
                            <td><%= od.getShipDistrict()%></td>
                            <td><%= od.getShipWard()%></td>
                            <td><%= od.getTotalPrice()%>$</td>
                            <td><%= od.getCustomerNote()%>$</td>
                            <td id="status-<%= od.getOrderId()%>">
                                <%
                                    String statusText = "Unknown";
                                    if (od.getStatus() == 1) {
                                        statusText = "Pending";
                                    } else if (od.getStatus() == 2) {
                                        statusText = "Delivery";
                                    } else if (od.getStatus() == 3) {
                                        statusText = "Completed";
                                    }
                                %>
                                <%= statusText%>
                            </td>
                            <td>
                                <form class="status-update-form" data-order-id="<%= od.getOrderId()%>">
                                    <select name="status" class="status-select form-control">
                                        <option value="1" <%= od.getStatus() == 1 ? "selected" : ""%>>Pending</option>
                                        <option value="2" <%= od.getStatus() == 2 ? "selected" : ""%>>Delivery</option>
                                        <option value="3" <%= od.getStatus() == 3 ? "selected" : ""%>>Completed</option>
                                    </select>
                                </form>
                            </td>
                            <td>
                                <form action="MainController" method="GET">
                                    <input type="hidden" name="action" value="Orderdetail" />
                                    <input type="hidden" name="orderId" value="<%= od.getOrderId()%>" />
                                    <button type="submit" class="btn btn-info">Detail</button>
                                </form>
                            </td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="13">No orders found.</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>

            <%
                }
                if (OrdersGroupedByAddress != null && !OrdersGroupedByAddress.isEmpty()) {
            %>
            <h2>Orders Grouped by Address</h2>
            <%                for (Map.Entry<String, List<Order>> entry : OrdersGroupedByAddress.entrySet()) {
            %>
            <h3><%= entry.getKey()%></h3>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Customer Name</th>
                        <th>Order Date</th>
                        <th>Ship Date</th>
                        <th>Total Price</th>
                        <th>Customer Note</th>
                        <th>Status</th>
                        <th>Status change</th>
                        <th>Details</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Order order : entry.getValue()) {%>
                    <tr>
                        <td><%= order.getOrderId()%></td>
                        <td><%= order.getAccountName()%></td>
                        <td><%= order.getOrderDate()%></td>
                        <td><%= order.getShipDate()%></td>
                        <td><%= order.getTotalPrice()%></td>
                        <td><%= order.getCustomerNote()%></td>
                        <td id="status-<%= order.getOrderId()%>">
                            <%
                                String statusText = "Unknown";
                                if (order.getStatus() == 1) {
                                    statusText = "Pending";
                                } else if (order.getStatus() == 2) {
                                    statusText = "Delivery";
                                } else if (order.getStatus() == 3) {
                                    statusText = "Completed";
                                }
                            %>
                            <%= statusText%>
                        </td>
                        <td>
                            <form class="status-update-form" data-order-id="<%= order.getOrderId()%>">
                                <select name="status" class="status-select form-control">
                                    <option value="1" <%= order.getStatus() == 1 ? "selected" : ""%>>Pending</option>
                                    <option value="2" <%= order.getStatus() == 2 ? "selected" : ""%>>Delivery</option>
                                    <option value="3" <%= order.getStatus() == 3 ? "selected" : ""%>>Completed</option>
                                </select>
                            </form>
                        </td>
                        <td>
                            <form action="MainController" method="GET">
                                <input type="hidden" name="action" value="Orderdetail" />
                                <input type="hidden" name="orderId" value="<%= order.getOrderId()%>" />
                                <button type="submit" class="btn btn-info">Detail</button>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% } %>
            <% }%>


        </div>
        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"
            integrity="sha512-n/4gHW3atM3QqRcbCn6ewmpxcLAHGaDjpEBu4xZd47N0W2oQ+6q7oc3PXstrJYXcbNU1OHdQ1T7pAP+gi5Yu8g=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
        ></script>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.1/js/bootstrap.min.js"
            integrity="sha512-EKWWs1ZcA2ZY9lbLISPz8aGR2+L7JVYqBAYTq5AXgBkSjRSuQEGqWx8R1zAX16KdXPaCjOCaKE8MCpU0wcHlHA=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
        ></script>
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="//cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <script>
                            $(document).ready(function () {
                                $(".status-select").change(function () {
                                    var form = $(this).closest(".status-update-form");
                                    var orderId = form.data("order-id");
                                    var status = $(this).val();

                                    $.ajax({
                                        type: "POST",
                                        url: "OrderManagerServlet",
                                        data: {
                                            action: "updateStatus",
                                            orderId: orderId,
                                            status: status
                                        },
                                        success: function (response) {
                                            // Cập nhật trạng thái trong cột "Status"
                                            var statusText;
                                            if (status == 1) {
                                                statusText = "Pending";
                                            } else if (status == 2) {
                                                statusText = "Delivery";
                                            } else if (status == 3) {
                                                statusText = "Completed";
                                            } else {
                                                statusText = "Unknown";
                                            }
                                            $("#status-" + orderId).text(statusText);
                                            alert("Order status updated successfully!");
                                        },
                                        error: function () {
                                            alert("Failed to update order status. Please try again.");
                                        }
                                    });
                                });
                            });
        </script>

    </body>
</html>


