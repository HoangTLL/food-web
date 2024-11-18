<%-- Document : index Created on : Jun 25, 2024, 4:26:52 PM Author : htduy --%>
<%@page import="dto.Account"%>
<%@page import="dto.CartItem"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="author" content="Duy Hoang Thanh & Phat Tran Tan" />
        <link rel="shortcut icon" href="./img/logo.jpg" type="image/jpg" />
        <title>Do Food</title>
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
        <link rel="stylesheet" href="./css/styleIndex.css" />
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

                            <%
                                Account acc = (Account) session.getAttribute("LoginedUser");
                                if (acc != null) {
                                    if (acc.getRole().equals("user")) {
                            %>
                            <li class="nav-item text-center">
                                <a
                                    class="nav-link fs-4 fw-bold"
                                    href="MainController?action=user"
                                    role="button"
                                    aria-haspopup="true"
                                    aria-expanded="false"
                                    >
                                    <i class="fa fa-user "></i>
                                    <%= acc.getFullName()%>
                                </a>
                            </li>       
                            <%
                            } else if (acc.getRole().equals("admin")) {
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

                            <li class="nav-item text-center">
                                <a
                                    class="nav-link js-toggle-cart"
                                    role="button"
                                    aria-haspopup="true"
                                    aria-expanded="false"
                                    >
                                    <div>
                                        <i class="fa-solid fa-xl fa-shopping-cart mb-1"></i>
                                        <span
                                            class="badge rounded-pill badge-notification bg-light text-dark"
                                            id="cartCount"
                                            ></span>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>

        <!-- carousel -->
        <div
            id="carouselExampleSlidesOnly"
            class="carousel slide"
            data-bs-ride="carousel"
            >
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img
                        src="https://hf-ui-assets.s3.eu-west-1.amazonaws.com/assets/factor/icons/Secondary+image+(2).png"
                        class="d-block w-100"
                        alt="Baner"
                        />
                </div>
            </div>
        </div>

        <!-- body -->
        <div class="dishes my-5">
            <div class="container">
                <div class="text-center">
                    <h2 class="mb-2 fs-1">Check out our dishes!</h2>
                    <p class="w-75 m-auto px-1 py-0 fs-5">
                        Expertly curated meals by chefs for a diverse and flavorful dining
                        experience
                    </p>
                </div>

                <div class="row my-4 align-items-center justify-content-center">
                    <div
                        id="dishBanner"
                        class="carousel slide carousel1"
                        data-bs-ride="carousel1"
                        >
                        <div class="carousel-inner carousel-inner1" role="listbox">
                            <div class="carousel-item active">
                                <div class="col-3">
                                    <div class="card text-white">
                                        <img
                                            src="https://images.everyplate.com/c_fill,f_auto,fl_lossy,h_800,q_auto,w_1336/everyplate_s3/image/thai-roasted-vegetable-green-curry-w16-77f8a9cf.jpg"
                                            class="card-img"
                                            style="width: 100%"
                                            alt="Thai Roasted Vegetable Green Curryg"
                                            />
                                        <div class="img-overlay">
                                            <div class="card-text-overlay">
                                                <h5 class="card-title">
                                                    Thai Roasted Vegetable Green Curry
                                                </h5>
                                                <p class="card-text">with Coconut Lime Rice</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="carousel-item">
                                <div class="col-3">
                                    <div class="card text-white">
                                        <img
                                            src="https://images.everyplate.com/c_fill,f_auto,fl_lossy,h_800,q_auto,w_1336/everyplate_s3/image/643d78435d43c53662003f65-44c90c92.jpg"
                                            class="card-img"
                                            style="width: 100%"
                                            alt="Chicken"
                                            />
                                        <div class="img-overlay">
                                            <div class="card-text-overlay">
                                                <h5 class="card-title">Smoky Gouda Chicken</h5>
                                                <p class="card-text">
                                                    with Roasted Red Potatoes & Parmesan Green Beans
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="carousel-item">
                                <div class="col-3">
                                    <div class="card text-white">
                                        <img
                                            src="https://img.hellofresh.com/q_60,w_640,f_auto,c_limit,fl_lossy/hellofresh_s3/image/6459394e66ab47fb9e0f057e-05702414.jpg"
                                            class="card-img"
                                            style="width: 100%"
                                            alt="..."
                                            />
                                        <div class="img-overlay">
                                            <div class="card-text-overlay">
                                                <h5 class="card-title">Vegetarian Tamale Bowl</h5>
                                                <p class="card-text">
                                                    with Spiced Sweet Potatoes & Pickled Jalapeño Crema
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="carousel-item">
                                <div class="col-3">
                                    <div class="card text-white">
                                        <img
                                            src="https://images.everyplate.com/c_fill,f_auto,fl_lossy,h_800,q_auto,w_1336/everyplate_s3/image/657759e8ddd2bdfd232ca9de-59628266.jpeg"
                                            class="card-img"
                                            style="width: 100%"
                                            alt="Ground Pork Cheddar Chili & Mac"
                                            />
                                        <div class="img-overlay">
                                            <div class="card-text-overlay">
                                                <h5 class="card-title">
                                                    Ground Pork Cheddar Chili & Mac
                                                </h5>
                                                <p class="card-text">
                                                    with Garlic-Chive Corn & Broccoli
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <div class="col-3">
                                    <div class="card text-white">
                                        <img
                                            src="https://images.everyplate.com/c_fill,f_auto,fl_lossy,h_800,q_auto,w_1336/everyplate_s3/image/64593c4d3582da1bba0f174f-1d24fd99.jpg"
                                            class="card-img"
                                            style="width: 100%"
                                            alt="Peanut Buddha Bowl"
                                            />
                                        <div class="img-overlay">
                                            <div class="card-text-overlay">
                                                <h5 class="card-title">Peanut Buddha Bowl</h5>
                                                <p class="card-text">
                                                    with Cilantro Quinoa, Gochugaru-Spiced Peanuts &
                                                    Sesame Broccoli
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <div class="col-3">
                                    <div class="card text-white">
                                        <img
                                            src="https://img.hellofresh.com/q_60,w_384,f_auto,c_limit,fl_lossy/hellofresh_s3/image/658f42ef050f2b642e9a137a-90c7d554.jpeg"
                                            class="card-img"
                                            style="width: 100%"
                                            alt="Smoky Tofu & Baked Beans"
                                            />
                                        <div class="img-overlay">
                                            <div class="card-text-overlay">
                                                <h5 class="card-title">Smoky Tofu & Baked Beans</h5>
                                                <p class="card-text">
                                                    with Roasted Corn & Chile Cheddar Mac
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <div class="col-3">
                                    <div class="card text-white">
                                        <img
                                            src="https://images.everyplate.com/c_fill,f_auto,fl_lossy,h_800,q_auto,w_1336/everyplate_s3/image/63f8f22b452183ea9d071fcd-ec432645.jpg"
                                            class="card-img"
                                            style="width: 100%"
                                            alt="Beef"
                                            />
                                        <div class="img-overlay">
                                            <div class="card-text-overlay">
                                                <h5 class="card-title">Tex-Mex Beef Bowl</h5>
                                                <p class="card-text">
                                                    with Green Chile Cheese Sauce & Cauliflower Rice
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <div class="col-3">
                                    <div class="card text-white">
                                        <img
                                            src="https://images.everyplate.com/c_fill,f_auto,fl_lossy,h_800,q_auto,w_1336/everyplate_s3/image/655277de4ad73d5e5556be5f-39d5c6b5.jpeg"
                                            class="card-img"
                                            style="width: 100%"
                                            alt="Creamy Tomato Pork Chop"
                                            />
                                        <div class="img-overlay">
                                            <div class="card-text-overlay">
                                                <h5 class="card-title">Creamy Tomato Pork Chop</h5>
                                                <p class="card-text">
                                                    with Sautéed Spinach & Garlic-Chive Cauliflower
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <a
                            class="carousel-control-prev bg-transparent w-aut"
                            href="#dishBanner"
                            role="button"
                            data-bs-slide="prev"
                            >
                            <span
                                class="carousel-control-prev-icon visually-hidden"
                                aria-hidden="true"
                                ></span>
                        </a>
                        <a
                            class="carousel-control-next bg-transparent w-aut"
                            href="#dishBanner"
                            role="button"
                            data-bs-slide="next"
                            >
                            <span
                                class="carousel-control-next-icon visually-hidden"
                                aria-hidden="true"
                                ></span>
                        </a>
                    </div>
                </div>
                <div class="text-center mt-4">
                    <a href="MainController?action=opendish">
                        <button id="btnViewMore" class="rounded py-3 w-50 fw-bold fs-3">
                            View More
                        </button>
                    </a>
                </div>
            </div>
        </div>
        
        <%
            ArrayList<CartItem> cart = (ArrayList<CartItem>) session.getAttribute("cart");
            int total = 0;
            int cartSize = (cart != null) ? cart.size() : 0;
        %>
        <!-- Cart -->
        <div class="cart is-hidden">
            <div class="cart__overlay"></div>
            <div class="cart__contents">
                <div class="cart__products">
                    <h2>Shopping Cart</h2>
                    <div class="products">
                        <table class="table table-striped my-1">
                            <thead>
                                <tr>
                                    <th scope="col">Name</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Delete</th>
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
                                        <div style="display: flex; align-items: center; gap: 7px">
                                            <form action="ModifyCartServlet" method="post" style="display:inline;">
                                                <input type="hidden" name="itemid" value="<%= item.getItem().getId()%>">
                                                <input type="hidden" name="action" value="decrease">
                                                <input type="hidden" name="nextAction" value="mainindex">
                                                <button type="submit" class="btn btn-danger rounded-circle">-</button>
                                            </form>
                                            <span class="fw-bold"><%= item.getQuantity()%></span>
                                            <form action="ModifyCartServlet" method="post" style="display:inline;">
                                                <input type="hidden" name="itemid" value="<%= item.getItem().getId()%>">
                                                <input type="hidden" name="action" value="increase">
                                                <input type="hidden" name="nextAction" value="mainindex">
                                                <button type="submit" class="btn btn-primary rounded-circle">+</button>
                                            </form>
                                        </div>
                                    </td>
                                    <td><%= item.getItem().getPrice()%>$</td>
                                    <td>
                                        <form action="ModifyCartServlet" method="post">
                                            <input type="hidden" name="itemid" value="<%= item.getItem().getId()%>">
                                            <input type="hidden" name="action" value="remove">
                                            <input type="hidden" name="nextAction" value="mainindex">
                                            <button type="submit" class="btn btn-danger">X</button>
                                        </form>
                                    </td>
                                </tr>
                                <% }
                                } else { %> 
                                <tr>
                                    <td class="text-center fw-bold fs-3" colspan="4">Cart is empty</td>
                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="cart__totals">
                    <h3 class="text-start">Total: <%= total%>$</h3>

                    <div class="form__footer mt-3">
                        <%
                            if (cartSize != 0 && acc!=null) {
                        %>
                        <a style="width: 100%" href="checkOutOrder.jsp">
                            <button
                                class="btn btn-success w-100 py-3 px-4 fs-5 fw-bold"
                                >
                                Pay Now
                            </button>
                        </a>
                        <%
                        } else {
                        %>
                            <a style="width: 100%" href="signin.jsp"> 
                            <button
                                class="btn btn-success w-100 py-3 px-4 fs-5 fw-bold js-toggle-cart"
                                >
                                Pay Now
                            </button>
                            </a>
                        <%
                            }
                        %>
                        <button
                            class="btn btn-secondary py-3 px-4 fs-5 fw-bold js-toggle-cart"
                            >
                            Continue Shopping
                        </button>
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
            // dishes
            let items = document.querySelectorAll(".carousel1 .carousel-item");

            items.forEach((el) => {
                const minPerSlide = 4;
                let next = el.nextElementSibling;
                for (var i = 1; i < minPerSlide; i++) {
                    if (!next) {
                        // wrap carousel by using first child
                        next = items[0];
                    }
                    let cloneChild = next.cloneNode(true);
                    el.appendChild(cloneChild.children[0]);
                    next = next.nextElementSibling;
                }
            });

            // count cart
            document.getElementById("cartCount").innerHTML = <%= cartSize%>;

            // toggle shopping cart
            $(".js-toggle-cart, .cart__overlay").on("click", function () {
                $(".cart").toggleClass("is-hidden");
            });

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
