<%-- 
    Document   : dishManager
    Created on : Jul 10, 2024, 1:41:42 PM
    Author     : Tan Phat
--%>

<%@page import="dto.Account"%>
<%@page import="dto.Item"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="author" content="Duy Hoang Thanh & Phat Tran Tan" />
        <link rel="shortcut icon" href="./img/logo.jpg" type="image/jpg" />
        <title>Dish Management</title>
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
                    <a class="navbar-brand me-5 py-2 fs-4 fw-bold" href="MainController?action=mainindex">
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
            <h1 class="fw-bold">Dish Management</h1>

            <!-- Search Form -->
            <form class="row" method="get" action="DishManagerServlet">
                <div class="form-group col-11">
                    <label for="searchQuery">Search Dishes:</label>
                    <input type="text" class="form-control" id="searchQuery" name="searchQuery" placeholder="Enter dish name or category">
                </div>
                <div class="col-1">
                    <button type="submit" class="btn btn-primary mt-4">Search</button>
                    <input type="hidden" name="action" value="search">          
                </div>
            </form>

            <!-- Display Orders -->
            <button type="button" class="btn btn-success mt-4" data-bs-toggle="modal" data-bs-target="#addItemModal">Add New Item</button>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Image</th>
                        <th>Dish Name</th>
                        <th>Price</th>
                        <th>Status</th>
                        <th>Description</th>
                        <th>Category</th>
                        <th>Calories</th>
                        <th>Recipe</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<Item> list = (ArrayList<Item>) request.getAttribute("ListDishes");
                        if (list != null) {
                            for (Item it : list) {
                    %>
                    <tr>
                        <td><%= it.getId()%></td>
                        <td style="width: 200px; text-align: center"><img src="<%= it.getImage1()%>" alt="Dish Image"></td>
                        <td><%= it.getName()%></td>
                        <td>$<%= it.getPrice()%></td>
                        <td><%= it.isStatus() ? "Available" : "Unavailable"%></td>
                        <td style="width: 20%"><%= it.getDesc()%></td>
                        <td><%= it.getCategory()%></td>
                        <td><%= it.getCalories()%></td>
                        <td><%= it.getRecipe()%></td>
                        <td style="width: 71px">

                            <form method="post" action="DishManagerServlet" style="display:inline-block;" onsubmit="return confirmDelete();">
                                <input type="hidden" name="itemId" value="<%= it.getId()%>">
                                <input type="hidden" name="action" value="delete">
                                <button type="submit" style="width: 71px" class="btn btn-sm btn-danger mb-2">Delete</button>
                            </form>
                            <form method="post" action="DishManagerServlet" style="display:inline-block;">
                                <input type="hidden" name="itemId" value="<%= it.getId()%>">
                                <input type="hidden" name="action" value="updateStatus">
                                <button type="submit" class="btn btn-warning btn-sm mb-2 w-100">Update Status</button>
                            </form>
                            <button type="button" 
                                    class="btn btn-primary btn-sm" 
                                    style="width: 71px"
                                    data-bs-toggle="modal" 
                                    data-bs-target="#editItemModal"
                                    data-id="<%= it.getId()%>"
                                    data-name="<%= it.getName().replace("'", "&apos;")%>"
                                    data-description="<%= it.getDesc().replace("'", "&apos;")%>"
                                    data-price="<%= it.getPrice()%>"
                                    data-status="<%= it.isStatus()%>"
                                    data-category="<%= it.getCategory().replace("'", "&apos;")%>"
                                    data-calories="<%= it.getCalories()%>"
                                    data-image="<%= it.getImage1()%>"
                                    data-recipe="<%= it.getRecipe().replace("'", "&apos;").replace("\n", "\\n").replace("\r", "\\r")%>"
                                    onclick="editItem(this)">Edit</button>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>

            <!-- Add New Menu Item Modal -->
            <div class="modal fade" id="addItemModal" tabindex="-1" aria-labelledby="addItemModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addItemModalLabel">Add New Menu Item</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form method="post" action="DishManagerServlet">
                                <input type="hidden" name="action" value="add">
                                <div class="form-group">
                                    <label for="itemName">Item Name:</label>
                                    <input type="text" class="form-control" id="itemName" name="itemName" required>
                                </div>
                                <div class="form-group">
                                    <label for="itemDescription">Item Description:</label>
                                    <textarea class="form-control" id="itemDescription" name="itemDescription" rows="3" required></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="itemPrice">Item Price:</label>
                                    <input type="number" step="0.01" class="form-control" id="itemPrice" name="itemPrice" required>
                                </div>
                                <div class="form-group">
                                    <label for="itemStatus">Item Status:</label>
                                    <select class="form-control" id="itemStatus" name="itemStatus">
                                        <option value="true">Available</option>
                                        <option value="false">Unavailable</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="itemCategory">Item Category:</label>
                                    <input type="text" class="form-control" id="itemCategory" name="itemCategory" required>
                                </div>
                                <div class="form-group">
                                    <label for="itemCalories">Item Calories:</label>
                                    <input type="number" class="form-control" id="itemCalories" name="itemCalories" required>
                                </div>
                                <div class="form-group">
                                    <label for="itemImage">Item Image URL:</label>
                                    <input type="text" class="form-control" id="itemImage" name="itemImage" required>
                                </div>
                                <div class="form-group">
                                    <label for="itemRecipe">Item Recipe:</label>
                                    <textarea class="form-control" id="itemRecipe" name="itemRecipe" rows="3" required></textarea>
                                </div>
                                <button type="submit" class="btn btn-primary">Add Item</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Edit Menu Item Modal -->
            <div class="modal fade" id="editItemModal" tabindex="-1" aria-labelledby="editItemModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editItemModalLabel">Edit Menu Item</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form method="post" action="DishManagerServlet?action=update">
                                <input type="hidden" id="editItemId" name="itemId">
                                <div class="form-group">
                                    <label for="editItemName">Item Name:</label>
                                    <input type="text" class="form-control" id="editItemName" name="itemName" required>
                                </div>
                                <div class="form-group">
                                    <label for="editItemDescription">Item Description:</label>
                                    <textarea class="form-control" id="editItemDescription" name="itemDescription" rows="3" required></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="editItemPrice">Item Price:</label>
                                    <input type="number" step="0.01" class="form-control" id="editItemPrice" name="itemPrice" required>
                                </div>
                                <div class="form-group">
                                    <label for="editItemStatus">Item Status:</label>
                                    <select class="form-control" id="editItemStatus" name="itemStatus">
                                        <option value="true">Available</option>
                                        <option value="false">Unavailable</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="editItemCategory">Item Category:</label>
                                    <input type="text" class="form-control" id="editItemCategory" name="itemCategory" required>
                                </div>
                                <div class="form-group">
                                    <label for="editItemCalories">Item Calories:</label>
                                    <input type="number" class="form-control" id="editItemCalories" name="itemCalories" required>
                                </div>
                                <div class="form-group">
                                    <label for="editItemImage">Item Image URL:</label>
                                    <input type="text" class="form-control" id="editItemImage" name="itemImage" required>
                                </div>
                                <div class="form-group">
                                    <label for="editItemRecipe">Item Recipe:</label>
                                    <textarea class="form-control" id="editItemRecipe" name="itemRecipe" rows="3" required></textarea>
                                </div>
                                <button type="submit" class="btn btn-primary">Save Changes</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer -->
        <footer>
            <jsp:include page="footer.jsp"/>
        </footer>
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
                                        window.onload = function () {
                                            var errorMessage = "<%= (request.getAttribute("Error") != null) ? request.getAttribute("Error") : ""%>";
                                            if (errorMessage) {
                                                alert(errorMessage);
                                            }
                                        }

                                        function confirmDelete() {
                                            return confirm("Are you sure you want to delete this dish?");
                                        }

                                        function editItem(button) {
                                            const id = button.getAttribute('data-id');
                                            const name = button.getAttribute('data-name');
                                            const description = button.getAttribute('data-description');
                                            const price = button.getAttribute('data-price');
                                            const status = button.getAttribute('data-status');
                                            const category = button.getAttribute('data-category');
                                            const calories = button.getAttribute('data-calories');
                                            const image = button.getAttribute('data-image');
                                            const recipe = button.getAttribute('data-recipe');

                                            document.getElementById('editItemId').value = id;
                                            document.getElementById('editItemName').value = name;
                                            document.getElementById('editItemDescription').value = description;
                                            document.getElementById('editItemPrice').value = price;
                                            document.getElementById('editItemStatus').value = status;
                                            document.getElementById('editItemCategory').value = category;
                                            document.getElementById('editItemCalories').value = calories;
                                            document.getElementById('editItemImage').value = image;
                                            document.getElementById('editItemRecipe').value = recipe;
                                        }
        </script>
    </body>
</html>

