package controllers;

import dao.ItemDAO;
import dto.Account;
import dto.Item;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tan Phat
 */
public class DishManagerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("LoginedUser");
        if (acc != null && acc.getRole().equals("admin")) {
            String action = request.getParameter("action");
            ItemDAO d = new ItemDAO();

            try {
                switch (action) {
                    case "add":
                        addItem(request, d);
                        break;
                    case "delete":
                        deleteItem(request, d);
                        break;
                    case "updateStatus":
                        updateItemStatus(request, d);
                        break;
                    case "update":
                        updateItem(request, d);
                        break;
                    case "search":
                        searchItems(request, d);
                        break;
                    default:
                        ArrayList<Item> list = d.getAllItems();
                        request.setAttribute("ListDishes", list);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("dishManager.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("403page.jsp").forward(request, response);
        }
    }

    private void addItem(HttpServletRequest request, ItemDAO d) throws Exception {
        String name = request.getParameter("itemName");
        int price = Integer.parseInt(request.getParameter("itemPrice"));
        boolean status = Boolean.parseBoolean(request.getParameter("itemStatus"));
        String desc = request.getParameter("itemDescription");
        String category = request.getParameter("itemCategory");
        int calories = Integer.parseInt(request.getParameter("itemCalories"));
        String image = request.getParameter("itemImage");
        String recipe = request.getParameter("itemRecipe");

        Item newItem = new Item(0, name, price, status, desc, category, calories, image, recipe);
        d.addItem(newItem);
        
        ArrayList<Item> list = d.getAllItems();
        request.setAttribute("ListDishes", list);
    }

    private void deleteItem(HttpServletRequest request, ItemDAO d) throws Exception {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        if (!d.deleteItem(itemId)) {
            request.setAttribute("Error", "Can not delete this Dish");
        }
        ArrayList<Item> list = d.getAllItems();
        request.setAttribute("ListDishes", list);
    }

    private void updateItemStatus(HttpServletRequest request, ItemDAO d) throws Exception {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        d.updateItemStatus(itemId);
        ArrayList<Item> list = d.getAllItems();
        request.setAttribute("ListDishes", list);
    }

    private void updateItem(HttpServletRequest request, ItemDAO d)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("itemId"));
        String name = request.getParameter("itemName");
        String description = request.getParameter("itemDescription");
        double price = Double.parseDouble(request.getParameter("itemPrice"));
        boolean status = Boolean.parseBoolean(request.getParameter("itemStatus"));
        String category = request.getParameter("itemCategory");
        int calories = Integer.parseInt(request.getParameter("itemCalories"));
        String image = request.getParameter("itemImage");
        String recipe = request.getParameter("itemRecipe");

        Item item = new Item(id, name, (int) price, status, description, category, calories, image, recipe);
        // Assuming there's a method in DAO to update the item
        d.updateItem(item);

//        response.sendRedirect("adminindex.jsp");
    }

    private void searchItems(HttpServletRequest request, ItemDAO d) throws Exception {
        String searchQuery = request.getParameter("searchQuery").toLowerCase();
        ArrayList<Item> filteredDishes = d.getItemsByName(searchQuery);

        request.setAttribute("ListDishes", filteredDishes);
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
