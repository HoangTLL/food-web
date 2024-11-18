<%-- 
    Document   : checkOutOrder
    Created on : Jul 16, 2024, 9:19:57 AM
    Author     : htduy
--%>

<%@page import="dto.Account"%>
<%@page import="dto.CartItem"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="author" content="Duy Hoang Thanh & Phat Tran Tan" />
        <link rel="shortcut icon" href="./img/logo.jpg" type="image/jpg" />
        <title>Order Page</title>
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

        <link rel="stylesheet" href="./css/styleDish.css" />
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
                text-align: center;
            }
            thead{
                text-align: center;
                background-color: rgb(2, 72, 157);
                color: white;
            }
        </style>
    </head>
    <%
        ArrayList<CartItem> cart = (ArrayList<CartItem>) session.getAttribute("cart");
        int total = 0;
        Account acc = (Account) session.getAttribute("LoginedUser");
    %>
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
                                    href="MainController?action=openmenu"
                                    role="button"
                                    aria-haspopup="true"
                                    aria-expanded="false"
                                    >
                                    Menus
                                </a>
                            </li>

                            <%
                                if (acc != null) {
                            %>
                            <li class="nav-item text-center">
                                <a
                                    class="nav-link fs-4 fw-bold"
                                    href="MainController?action=user&accid=<%= acc.getId()%>"
                                    role="button"
                                    aria-haspopup="true"
                                    aria-expanded="false"
                                    >
                                    <i class="fa fa-user"></i>
                                </a>
                            </li>
                            <%
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
        <!-- body -->
        <div class="container mt-5 mb-5">
            <h1 class="text-primary">Your order:</h1>
            <table class="table table-striped my-1">
                <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Price</th>
                    </tr>
                </thead>
                <tbody id="cartList">
                    <%
                        if (cart != null) {
                            for (CartItem item : cart) {
                                total += item.getQuantity() * item.getItem().getPrice();
                    %>
                    <tr>
                        <th scope="row"><%= item.getItem().getName()%></th>
                        <td>
                            <p class="fw-bold"><%= item.getQuantity()%></p>
                            <!-- Hidden fields to pass data -->
                            <input type="hidden" name="itemId" value="<%= item.getItem().getId()%>">
                            <input type="hidden" name="itemCategory" value="<%= item.getItem().getCategory()%>">
                            <input type="hidden" name="itemQuantity" value="<%= item.getQuantity()%>">
                            <input type="hidden" name="itemPrice" value="<%= item.getItem().getPrice()%>">
                        </td>
                        <td><%= item.getItem().getPrice()%>$</td>                           
                    </tr>
                    <% }
        }%>
                </tbody>
            </table>

            <h5 class="text-end text-success">Free Shipping</h5>
            <h3 class="text-end text-danger">Total: <%= total%>$</h3>

            <h3 class="mt-3 text-start text-primary">Detail Information:</h3>
            <form action="MainController" method="post">
                <div class="">
                    <div class="form-floating mb-4">
                        <input
                            name="txtaddress"
                            type="text"
                            class="form-control"
                            id="floatingInput"
                            placeholder="address"
                            value="<%= acc.getAddress()%>"
                            required=""
                            />
                        <label for="floatingInput">Address</label>
                    </div>

                    <div class="mb-4">
                        <select
                            class="form-select"
                            name="txtward"
                            required=""
                            value =""
                            >
                            <option>Ward</option>
                            <option value="1" <%= acc.getWardId() == 1 ? "selected" : ""%>>Ward 1</option>
                            <option value="2" <%= acc.getWardId() == 2 ? "selected" : ""%>>Ward 2</option>
                            <option value="3" <%= acc.getWardId() == 3 ? "selected" : ""%>>Ward 3</option>
                            <option value="4" <%= acc.getWardId() == 4 ? "selected" : ""%>>Ward 4</option>
                            <option value="5" <%= acc.getWardId() == 5 ? "selected" : ""%>>Ward 5</option>
                        </select>
                    </div>

                    <div class="mb-4">
                        <select
                            class="form-select"
                            name="txtdistrict"
                            required=""
                            >
                            <option>District</option>
                            <option value="1" <%= acc.getDistrictId() == 1 ? "selected" : ""%>>District 1</option>
                            <option value="2" <%= acc.getDistrictId() == 2 ? "selected" : ""%>>District 2</option>
                            <option value="3" <%= acc.getDistrictId() == 3 ? "selected" : ""%>>District 3</option>
                            <option value="4" <%= acc.getDistrictId() == 4 ? "selected" : ""%>>District 4</option>
                            <option value="5" <%= acc.getDistrictId() == 5 ? "selected" : ""%>>District 5</option>
                        </select>
                    </div>

                    <div class="mb-4">
                        <select
                            class="form-select"
                            name="txtcity"
                            required=""
                            >
                            <option>City (Province)</option>
                            <option value="1" <%= acc.getCityId() == 1 ? "selected" : ""%>>Ho Chi Minh City</option>
                            <option value="2" <%= acc.getCityId() == 2 ? "selected" : ""%>>Binh Duong Province</option>
                            <option value="3" <%= acc.getCityId() == 3 ? "selected" : ""%>>Dong Nai</option>
                        </select>
                    </div>

                    <div class="form-floating mb-4">
                        <input
                            name="txtNote"
                            type="text"
                            class="form-control"
                            id="floatingInput"
                            placeholder="Note"
                            />
                        <label for="floatingInput">Note</label>
                    </div>
                </div>
                <div class="col-12">
                    <button
                        type="submit"
                        class="btn btn-success w-100 py-3 px-4 fs-5 fw-bold"
                        value="saveOrder"
                        name="action"
                        >
                        Pay Now
                    </button>
                </div>

            </form>              
        </div>

    </div>
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
</body>
</html>
