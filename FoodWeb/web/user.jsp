<%-- 
    Document   : user
    Created on : Jul 11, 2024, 7:25:47 AM
    Author     : htduy
--%>

<%@page import="java.util.Collections"%>
<%@page import="dto.Order"%>
<%@page import="java.util.List"%>
<%@page import="dto.MealPlanItem"%>
<%@page import="dto.Item"%>
<%@page import="dto.Account"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="author" content="Duy Hoang Thanh & Phat Tran Tan" />
        <link rel="shortcut icon" href="./img/logo.jpg" type="image/jpg" />
        <title>User Page</title>
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

        <link rel="stylesheet" href="./css/styleDetail.css" />
        <style>
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
                    <a class="navbar-brand me-5 py-2 fs-4 fw-bold" href="MainController?action=mainindex">
                        <i class="fa fa-utensils"></i>
                        Do Food - Delicious Food
                    </a>

                    <div class="collapse navbar-collapse" id="navbarColor01">
                        <ul class="navbar-nav me-auto nav1">
                            <li class="nav-item text-center">
                                <a
                                    class="nav-link fs-4 fw-bold"
                                    href="MainController?action=opendish"
                                    role="button"
                                    aria-haspopup="true"
                                    aria-expanded="false"
                                    >
                                    Dishes
                                </a>
                            </li>

                            <li class="nav-item text-center">
                                <a
                                    class="nav-link fs-4 fw-bold"
                                    href="MainController?action=signout"
                                    role="button"
                                    aria-haspopup="true"
                                    aria-expanded="false"
                                    >
                                    Log Out
                                </a>
                            </li>                            
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
        <%
            Account acc = (Account) session.getAttribute("LoginedUser");
            String msg = (String) request.getAttribute("message");
            String color = (String) request.getAttribute("color");
        %>

        <div class="container my-5">
            <h1 class="text-center text-primary">Welcome <%= acc.getFullName()%>!</h1>
            <div id="user-infor">
                <h5>Your Information:</h5>
                <form action="MainController" method="post">
                    <input type="hidden" name="accountId" value="<%= acc.getId()%>" />
                    <div class="row mt-2">
                        <div class="col-6">
                            <div class="form-floating mb-4 pb-2">
                                <input
                                    name="txtname"
                                    type="text"
                                    class="form-control"
                                    id="floatingInput"
                                    placeholder="name"
                                    value="<%= acc.getFullName()%>"
                                    />
                                <label for="floatingInput">Full Name</label>
                            </div>

                            <div class="form-floating mb-4 pb-2">
                                <input
                                    name="txtemail"
                                    type="email"
                                    class="form-control"
                                    id="floatingInput"
                                    placeholder="email"
                                    value="<%= acc.getEmail()%>"
                                    />
                                <label for="floatingInput">Email</label>
                            </div>

                            <div class="form-floating mb-4 pb-2">
                                <input
                                    name="txtaddress"
                                    type="text"
                                    class="form-control"
                                    id="floatingInput"
                                    placeholder="address"
                                    value="<%= acc.getAddress()%>"
                                    />
                                <label for="floatingInput">Address</label>
                            </div>

                            <div class="form-floating mb-4">
                                <input
                                    name="txtphone"
                                    type="phone"
                                    class="form-control"
                                    id="floatingInput"
                                    placeholder="phone"
                                    value="<%= acc.getPhone()%>"
                                    />
                                <label for="floatingInput">Phone Number</label>
                            </div>

                        </div>

                        <div class="col-6">
                            <div class="mb-4 pb-2">
                                <select
                                    class="form-select"
                                    name="txtward"
                                    >
                                    <option>Ward</option>
                                    <option value="1" <%= acc.getWardId() == 1 ? "selected" : ""%>>Ward 1</option>
                                    <option value="2" <%= acc.getWardId() == 2 ? "selected" : ""%>>Ward 2</option>
                                    <option value="3" <%= acc.getWardId() == 3 ? "selected" : ""%>>Ward 3</option>
                                    <option value="4" <%= acc.getWardId() == 4 ? "selected" : ""%>>Ward 4</option>
                                    <option value="5" <%= acc.getWardId() == 5 ? "selected" : ""%>>Ward 5</option>
                                </select>
                            </div>

                            <div class="mb-4 pb-2">
                                <select
                                    class="form-select"
                                    name="txtdistrict"
                                    >
                                    <option>District</option>
                                    <option value="1" <%= acc.getDistrictId() == 1 ? "selected" : ""%>>District 1</option>
                                    <option value="2" <%= acc.getDistrictId() == 2 ? "selected" : ""%>>District 2</option>
                                    <option value="3" <%= acc.getDistrictId() == 3 ? "selected" : ""%>>District 3</option>
                                    <option value="4" <%= acc.getDistrictId() == 4 ? "selected" : ""%>>District 4</option>
                                    <option value="5" <%= acc.getDistrictId() == 5 ? "selected" : ""%>>District 5</option>
                                </select>
                            </div>

                            <div class="mb-4 pb-2">
                                <select
                                    class="form-select"
                                    name="txtcity"
                                    >
                                    <option>City (Province)</option>
                                    <option value="1" <%= acc.getCityId() == 1 ? "selected" : ""%>>Ho Chi Minh City</option>
                                    <option value="2" <%= acc.getCityId() == 2 ? "selected" : ""%>>Binh Duong Province</option>
                                    <option value="3" <%= acc.getCityId() == 3 ? "selected" : ""%>>Dong Nai</option>
                                    <option value="4" <%= acc.getCityId() == 4 ? "selected" : ""%>>Ba Ria - Vung Tau</option>
                                    <option value="5" <%= acc.getCityId() == 5 ? "selected" : ""%>>Long An</option>
                                </select>
                            </div>

                            <button
                                type="submit"
                                class="btn btn-success"  
                                value="updateInfor"
                                name="action"
                                >
                                Update
                            </button>
                        </div>
                    </div>
                </form>
                <!-- Display feedback message -->
                <div class="<%= color%> fw-bold">
                    <p><%= msg != null ? msg : ""%></p>
                </div>
            </div>
            <hr/>
            <%
                List<Order> orderList = (List<Order>) request.getAttribute("orderList");
                if (orderList != null) {
                    Collections.reverse(orderList);
                }
            %>  
            <div id="Order-history" class="mb-5">
                <h5>Your Order History:</h5>
                <table class="table table-striped my-1 text-center">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Order ID</th>
                            <th>Order Date</th>
                            <th>Ship Date</th>
                            <th>Total Price</th>
                            <th>Status</th>
                            <th>Action</th>
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
                            <th><%= index++%></th>
                            <td><%= od.getOrderId()%></td>
                            <td><%= od.getOrderDate()%></td>
                            <td><%= od.getShipDate()%></td>
                            <td><%= od.getTotalPrice()%>$</td>
                            <td><%= statusOrder%></td>
                            <td>
                                <%
                                    if (od.getStatus() == 3) {
                                %>
                                <form action="" method="POST">
                                    <input type="hidden" name="orderId" value="<%= od.getOrderId()%>" />
                                    <button disabled="" type="submit" value="" name="action" class="btn btn-success">Completed</button>
                                </form>
                                <%
                                } else if (od.getStatus() == 2) {
                                %>
                                <form action="MainController" method="POST">
                                    <input type="hidden" name="orderId" value="<%= od.getOrderId()%>" />
                                    <button type="submit" value="updateOrder" name="action" class="btn btn-warning">Delivery Yet?</button>
                                </form>
                                <%
                                } else if (od.getStatus() == 1) {
                                %>
                                <form action="" method="POST">
                                    <input type="hidden" name="orderId" value="<%= od.getOrderId()%>" />
                                    <button disabled="" type="submit" value="" name="action" class="btn btn-warning">Delivery Yet?</button>
                                </form>
                                <%
                                    }
                                %>

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
                            <td colspan="7">No orders found.</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
            <hr/>
            <%
                List<MealPlanItem> mealPlan = (List<MealPlanItem>) request.getAttribute("mealPlan");
            %>
            <div id="Meal-plan">
                <h5>Your Customer Plan:</h5>
                <table class="table table-striped my-1 text-center">
                    <thead>
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Name</th>
                            <th scope="col">Image</th>
                            <th scope="col">Price</th>
                            <th scope="col">Dish Status</th>
                            <th scope="col">Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if (mealPlan
                                    != null && !mealPlan.isEmpty()) {
                                int count = 0;
                                String status = "";
                                for (MealPlanItem plan : mealPlan) {
                                    Item item = plan.getItem();
                                    status = item.isStatus() ? "<span class='text-success'>Available</span>" : "<span class='text-danger'>Out of order</span>";

                        %>
                        <tr>
                            <th scope="row"><%= ++count%></th>
                            <td class="text-start">
                                <a href="MainController?action=detailDishes&itemid=<%= item.getId()%>">
                                    <%= item.getName()%>
                                </a>
                            </td>
                            <td style="width: 20%"><img src="<%=item.getImage1()%>" class="w-100"/></td>
                            <td><%= item.getPrice()%>$</td>
                            <td><%= status%></td>
                            <td>
                                <a href="MainController?action=deleteMealPlan&itemid=<%=item.getId()%>">
                                    <button class="btn btn-danger">X</button>
                                </a>
                            </td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="6">No items in the plan</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
                <div class="text-end mt-3">
                    <%
                        String message = (String) request.getAttribute("message");
                    %>
                    <div class="fw-bold">
                        <span class="text-success"><%= message != null ? message : ""%></span>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <footer
            id="footer"
            class="text-center text-lg-start text-light gradient-custom"
            >
            <section class="p-4">
                <div class="container mt-5">
                    <div class="row mt-3">
                        <div class="col-lg-4 mb-4">
                            <h6 class="text-uppercase fw-bold mb-4">
                                <i class="fa fa-utensils"></i>
                                Do Food - Delicious Food
                            </h6>
                            <hr />
                            <p style="width: 80%">
                                We pride ourselves on efficiency, hard work, and delivering the
                                best quality products and ingredients at the best value. Your
                                satisfaction is our top priority.
                            </p>
                        </div>

                        <div class="col-lg-4 mb-4">
                            <h6 class="text-uppercase fw-bold mb-4">Our networks</h6>
                            <hr />
                            <p>
                                <a href="#!" class="text-reset">Facebook</a>
                            </p>
                            <p>
                                <a href="#!" class="text-reset">Instagram</a>
                            </p>
                            <p>
                                <a href="#!" class="text-reset">Twitter</a>
                            </p>
                            <p>
                                <a href="#!" class="text-reset">Google</a>
                            </p>
                        </div>

                        <div class="col-lg-4 mb-4">
                            <h6 class="text-uppercase fw-bold mb-4">Contact Us</h6>
                            <hr />
                            <p>
                                <i class="fas fa-home me-3"></i> Ho Chi Minh, Dictrict 9, Viet
                                Nam
                            </p>
                            <p>
                                <i class="fas fa-envelope me-3"></i>
                                dofoodmail.work@mail.com
                            </p>
                            <p><i class="fas fa-phone me-3"></i> + 01 234 567 88</p>
                            <p><i class="fas fa-print me-3"></i> + 01 234 567 89</p>
                        </div>
                    </div>
                </div>
            </section>

            <div
                class="text-center p-4"
                style="background-color: rgba(0, 0, 0, 0.05)"
                >
                &copy; May 2024
                <a class="text-reset fw-bold" href="#">Do Food Store</a>
            </div>
        </footer>

        <!-- Back to top button -->
        <button onclick="topFunction()" id="myBtn">
            <i class="fa fa-angle-up"></i>
        </button>

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
            // back to top
            let mybutton = document.getElementById("myBtn");

            // When the user scrolls down 20px from the top of the document, show the button
            window.onscroll = function () {
                scrollFunction();
            };

            function scrollFunction() {
                if (
                        document.body.scrollTop > 20 ||
                        document.documentElement.scrollTop > 20
                        ) {
                    mybutton.style.display = "block";
                } else {
                    mybutton.style.display = "none";
                }
            }

            // When the user clicks on the button, scroll to the top of the document
            function topFunction() {
                document.body.scrollTop = 0;
                document.documentElement.scrollTop = 0;
            }
        </script>
    </body>
</html>
