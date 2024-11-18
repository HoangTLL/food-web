<%@ page import="java.util.List" %>
<%@ page import="dto.OrderDetail" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="author" content="Duy Hoang Thanh & Phat Tran Tan" />
        <link rel="shortcut icon" href="./img/logo.jpg" type="image/jpg" />
        <title>Order Details</title>
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
            thead {
                text-align: center;
                background-color: rgb(2, 72, 157);
                color: white;
            }
            th, td {
                padding: 12px;
                text-align: center;
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
                                <a class="nav-link fs-4 fw-bold" href="MainController?action=opendish" role="button">
                                    Dishes
                                </a>
                            </li>
                            <li class="nav-item text-center">
                                <a class="nav-link fs-4 fw-bold" href="MainController?action=signout" role="button">
                                    Log Out
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
        <div class="container my-5">
            <h1 class="text-center text-primary">Order Details</h1>
            <%
                List<OrderDetail> orderDetails = (List<OrderDetail>) request.getAttribute("orderDetails");
                if (orderDetails != null && !orderDetails.isEmpty()) {
            %>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Item Name</th>
                        <th>Quantity</th>
                        <th>Price per Unit</th>
                        <th>Category</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (OrderDetail detail : orderDetails) {
                    %>
                    <tr>
                        <td><%= detail.getOrderId()%></td>
                        <td><%= detail.getItemName()%></td>
                        <td><%= detail.getQuantity()%></td>
                        <td><%= detail.getPrice_per_unit()%>$</td>
                        <td><%= detail.getCategory()%></td>
                        <td><%= detail.getQuantity()*detail.getPrice_per_unit()%></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            <%
            } else {
            %>
            <p>No order details found.</p>
            <%
                }
            %>
        </div>
        <!-- Footer -->
        <footer id="footer" class="text-center text-lg-start text-light gradient-custom fixed-bottom">
            <section class="p-4">
                <div class="container mt-5">
                    <div class="row mt-3">
                        <div class="col-lg-4 mb-4">
                            <h6 class="text-uppercase fw-bold mb-4">
                                <i class="fa fa-utensils"></i> Do Food - Delicious Food
                            </h6>
                            <hr />
                            <p style="width: 80%">
                                We pride ourselves on efficiency, hard work, and delivering the best quality products and ingredients at the best value. Your satisfaction is our top priority.
                            </p>
                        </div>

                        <div class="col-lg-4 mb-4">
                            <h6 class="text-uppercase fw-bold mb-4">Our Networks</h6>
                            <hr />
                            <p><a href="#!" class="text-reset">Facebook</a></p>
                            <p><a href="#!" class="text-reset">Instagram</a></p>
                            <p><a href="#!" class="text-reset">Twitter</a></p>
                            <p><a href="#!" class="text-reset">Google</a></p>
                        </div>

                        <div class="col-lg-4 mb-4">
                            <h6 class="text-uppercase fw-bold mb-4">Contact Us</h6>
                            <hr />
                            <p><i class="fas fa-home me-3"></i> Ho Chi Minh, District 9, Vietnam</p>
                            <p><i class="fas fa-envelope me-3"></i> dofoodmail.work@mail.com</p>
                            <p><i class="fas fa-phone me-3"></i> +01 234 567 88</p>
                            <p><i class="fas fa-print me-3"></i> +01 234 567 89</p>
                        </div>
                    </div>
                </div>
            </section>

            <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05)">
                &copy; May 2024 <a class="text-reset fw-bold" href="#">Do Food Store</a>
            </div>
        </footer>
        
        <button onclick="topFunction()" id="myBtn">
            <i class="fa fa-angle-up"></i>
        </button>
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.1/js/bootstrap.min.js"></script>
        <script>
            let mybutton = document.getElementById("myBtn");
            window.onscroll = function () {
                scrollFunction();
            };
            function scrollFunction() {
                if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                    mybutton.style.display = "block";
                } else {
                    mybutton.style.display = "none";
                }
            }
            function topFunction() {
                document.body.scrollTop = 0;
                document.documentElement.scrollTop = 0;
            }
        </script>
    </body>
</html>
