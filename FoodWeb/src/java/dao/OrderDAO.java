/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CartItem;
import dto.Order;
import dto.OrderDetail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mylib.DBUtil;

/**
 *
 * @author Tan Phat
 */
public class OrderDAO {

    // Method to save order 
    public List<OrderDetail> getOrderDetails(int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "SELECT od.detail_id, od.order_id, d.name AS item_name, od.quantity, od.price_per_unit, d.category "
                        + "FROM Order_Details od "
                        + "JOIN Dishes d ON od.dish_id = d.id "
                        + "WHERE od.order_id = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderId);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    int detailId = rs.getInt("detail_id");
                    int orderIdFromDb = rs.getInt("order_id");
                    String itemName = rs.getString("item_name");
                    int quantity = rs.getInt("quantity");
                    int pricePerUnit = rs.getInt("price_per_unit");
                    String category = rs.getString("category");
                    OrderDetail orderDetail = new OrderDetail(detailId, orderIdFromDb, itemName, quantity, pricePerUnit, category);
                    orderDetails.add(orderDetail);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderDetails;
    }

    public int saveOrder(int accid, String ship_address, int ship_city_id, int ship_district_id, int ship_ward_id, String note, ArrayList<CartItem> cart) {
        int orderId = 0;

        // Calculate total price from the cart
        int total = 0;
        for (CartItem item : cart) {
            total += item.getQuantity() * item.getItem().getPrice();
        }

        Connection cn = null;
        try {
            cn = DBUtil.makeConnection(); // Create connection to the server
            if (cn != null) {
                cn.setAutoCommit(false);

                // Insert a row into the Orders table
                String sql = "INSERT INTO [dbo].[Orders] "
                        + "([account_id],[order_date],[ship_date],[ship_address],[ship_city_id],[ship_district_id],[ship_ward_id],[total_price],[customer_notes],[status]) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,1);";

                PreparedStatement pst = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                Date order_date = new Date(System.currentTimeMillis());
                Calendar cal = Calendar.getInstance();
                cal.setTime(order_date);
                cal.add(Calendar.DATE, 3);
                Date ship_date = new Date(cal.getTimeInMillis());

                pst.setInt(1, accid);
                pst.setDate(2, order_date);
                pst.setDate(3, ship_date);
                pst.setString(4, ship_address);
                pst.setInt(5, ship_city_id);
                pst.setInt(6, ship_district_id);
                pst.setInt(7, ship_ward_id);
                pst.setInt(8, total);
                pst.setString(9, note);

                int affectedRows = pst.executeUpdate();

                if (affectedRows > 0) {
                    // Retrieve the generated order ID
                    ResultSet rs = pst.getGeneratedKeys();
                    if (rs.next()) {
                        orderId = rs.getInt(1);
                    }
                }

                cn.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.setAutoCommit(true);
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderId; // Return the new order ID
    }

    public int saveOrderDetail(int orderId, int itemId, String itemCategory, int quantity, int price) {
        int result = 0;
        Connection cn = null;
        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO [Order_Details] ([order_id], [dish_id], [item_type], [quantity], [price_per_unit]) VALUES (?,?,?,?,?);";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderId);
                pst.setInt(2, itemId);
                pst.setString(3, itemCategory);
                pst.setInt(4, quantity);
                pst.setInt(5, price);
                result = pst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // Method to retrieve all orders
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBUtil.makeConnection();
            if (conn != null) {
                String sql = "SELECT o.id AS orderId, a.full_name AS accountName, o.order_date AS orderDate, "
                        + "o.ship_date AS shipDate, o.ship_address AS shipAddress, c.name AS shipCity, "
                        + "d.name AS shipDistrict, w.name AS shipWard, o.total_price AS totalPrice, "
                        + "o.customer_notes AS customerNote, o.status AS status "
                        + "FROM Orders o "
                        + "JOIN Accounts a ON o.account_id = a.id "
                        + "JOIN City c ON o.ship_city_id = c.id "
                        + "JOIN District d ON o.ship_district_id = d.id "
                        + "JOIN Ward w ON o.ship_ward_id = w.id";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt("orderId");
                    String accountName = rs.getString("accountName");
                    Date orderDate = rs.getDate("orderDate");
                    Date shipDate = rs.getDate("shipDate");
                    String shipAddress = rs.getString("shipAddress");
                    String shipCity = rs.getString("shipCity");
                    String shipDistrict = rs.getString("shipDistrict");
                    String shipWard = rs.getString("shipWard");
                    double totalPrice = rs.getDouble("totalPrice");
                    String customerNote = rs.getString("customerNote");
                    int status = rs.getInt("status");

                    // Create Order object from the retrieved data
                    Order order = new Order(orderId, accountName, orderDate, shipDate, shipAddress, shipCity, shipDistrict, shipWard, totalPrice, customerNote, status);
                    orderList.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderList;
    }

    // Method to retrieve orders grouped by address
    public Map<String, List<Order>> getOrdersGroupedByAddress() {
        Map<String, List<Order>> orderMap = new HashMap<>();
        Connection conn = null;
        try {
            conn = DBUtil.makeConnection();
            if (conn != null) {
                String sql = "SELECT o.id AS orderId, a.full_name AS accountName, o.order_date AS orderDate, "
                        + "o.ship_date AS shipDate, o.ship_address AS shipAddress, c.name AS shipCity, "
                        + "d.name AS shipDistrict, w.name AS shipWard, o.total_price AS totalPrice, "
                        + "o.customer_notes AS customerNote, o.status AS status "
                        + "FROM Orders o "
                        + "JOIN Accounts a ON o.account_id = a.id "
                        + "JOIN City c ON o.ship_city_id = c.id "
                        + "JOIN District d ON o.ship_district_id = d.id "
                        + "JOIN Ward w ON o.ship_ward_id = w.id";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt("orderId");
                    String accountName = rs.getString("accountName");
                    Date orderDate = rs.getDate("orderDate");
                    Date shipDate = rs.getDate("shipDate");
                    String shipAddress = rs.getString("shipAddress");
                    String shipCity = rs.getString("shipCity");
                    String shipDistrict = rs.getString("shipDistrict");
                    String shipWard = rs.getString("shipWard");
                    double totalPrice = rs.getDouble("totalPrice");
                    String customerNote = rs.getString("customerNote");
                    int status = rs.getInt("status");

                    // Create Order object from the retrieved data
                    Order order = new Order(orderId, accountName, orderDate, shipDate, shipAddress, shipCity, shipDistrict, shipWard, totalPrice, customerNote, status);

                    // Group orders by address
                    String fullAddress = /*shipAddress + ", " +*/ shipWard + ", " + shipDistrict + ", " + shipCity;
                    if (!orderMap.containsKey(fullAddress)) {
                        orderMap.put(fullAddress, new ArrayList<>());
                    }
                    orderMap.get(fullAddress).add(order);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderMap;
    }

    // Method to search orders by specific date
    public List<Order> searchOrdersByDate(Date date) {
        List<Order> orderList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBUtil.makeConnection();
            if (conn != null) {
                String sql = "SELECT o.id AS orderId, a.full_name AS accountName, o.order_date AS orderDate, "
                        + "o.ship_date AS shipDate, o.ship_address AS shipAddress, c.name AS shipCity, "
                        + "d.name AS shipDistrict, w.name AS shipWard, o.total_price AS totalPrice, "
                        + "o.customer_notes AS customerNote, o.status AS status "
                        + "FROM Orders o "
                        + "JOIN Accounts a ON o.account_id = a.id "
                        + "JOIN City c ON o.ship_city_id = c.id "
                        + "JOIN District d ON o.ship_district_id = d.id "
                        + "JOIN Ward w ON o.ship_ward_id = w.id "
                        + "WHERE o.order_date = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setDate(1, date);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt("orderId");
                    String accountName = rs.getString("accountName");
                    Date orderDate = rs.getDate("orderDate");
                    Date shipDate = rs.getDate("shipDate");
                    String shipAddress = rs.getString("shipAddress");
                    String shipCity = rs.getString("shipCity");
                    String shipDistrict = rs.getString("shipDistrict");
                    String shipWard = rs.getString("shipWard");
                    double totalPrice = rs.getDouble("totalPrice");
                    String customerNote = rs.getString("customerNote");
                    int status = rs.getInt("status");

                    // Create Order object from the retrieved data
                    Order order = new Order(orderId, accountName, orderDate, shipDate, shipAddress, shipCity, shipDistrict, shipWard, totalPrice, customerNote, status);
                    orderList.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderList;
    }

    // Method to search orders within a specific date range
    public List<Order> searchOrdersByDateRange(Date fromDate, Date toDate) {
        List<Order> orderList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBUtil.makeConnection();
            if (conn != null) {
                String sql = "SELECT o.id AS orderId, a.full_name AS accountName, o.order_date AS orderDate, "
                        + "o.ship_date AS shipDate, o.ship_address AS shipAddress, c.name AS shipCity, "
                        + "d.name AS shipDistrict, w.name AS shipWard, o.total_price AS totalPrice, "
                        + "o.customer_notes AS customerNote, o.status AS status "
                        + "FROM Orders o "
                        + "JOIN Accounts a ON o.account_id = a.id "
                        + "JOIN City c ON o.ship_city_id = c.id "
                        + "JOIN District d ON o.ship_district_id = d.id "
                        + "JOIN Ward w ON o.ship_ward_id = w.id "
                        + "WHERE o.order_date BETWEEN ? AND ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setDate(1, fromDate);
                pstmt.setDate(2, toDate);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt("orderId");
                    String accountName = rs.getString("accountName");
                    Date orderDate = rs.getDate("orderDate");
                    Date shipDate = rs.getDate("shipDate");
                    String shipAddress = rs.getString("shipAddress");
                    String shipCity = rs.getString("shipCity");
                    String shipDistrict = rs.getString("shipDistrict");
                    String shipWard = rs.getString("shipWard");
                    double totalPrice = rs.getDouble("totalPrice");
                    String customerNote = rs.getString("customerNote");
                    int status = rs.getInt("status");

                    // Create Order object from the retrieved data
                    Order order = new Order(orderId, accountName, orderDate, shipDate, shipAddress, shipCity, shipDistrict, shipWard, totalPrice, customerNote, status);
                    orderList.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderList;
    }

    // Method to search orders by customer phone or email
    public List<Order> searchOrdersByCustomerInfo(String customerInfo) {
        List<Order> orderList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBUtil.makeConnection();
            if (conn != null) {
                String sql = "SELECT o.id AS orderId, a.full_name AS accountName, o.order_date AS orderDate, "
                        + "o.ship_date AS shipDate, o.ship_address AS shipAddress, c.name AS shipCity, "
                        + "d.name AS shipDistrict, w.name AS shipWard, o.total_price AS totalPrice, "
                        + "o.customer_notes AS customerNote, o.status AS status "
                        + "FROM Orders o "
                        + "JOIN Accounts a ON o.account_id = a.id "
                        + "JOIN City c ON o.ship_city_id = c.id "
                        + "JOIN District d ON o.ship_district_id = d.id "
                        + "JOIN Ward w ON o.ship_ward_id = w.id "
                        + "WHERE a.phone LIKE ? OR a.email LIKE ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "%" + customerInfo + "%");
                pstmt.setString(2, "%" + customerInfo + "%");
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt("orderId");
                    String accountName = rs.getString("accountName");
                    Date orderDate = rs.getDate("orderDate");
                    Date shipDate = rs.getDate("shipDate");
                    String shipAddress = rs.getString("shipAddress");
                    String shipCity = rs.getString("shipCity");
                    String shipDistrict = rs.getString("shipDistrict");
                    String shipWard = rs.getString("shipWard");
                    double totalPrice = rs.getDouble("totalPrice");
                    String customerNote = rs.getString("customerNote");
                    int status = rs.getInt("status");

                    // Create Order object from the retrieved data
                    Order order = new Order(orderId, accountName, orderDate, shipDate, shipAddress, shipCity, shipDistrict, shipWard, totalPrice, customerNote, status);
                    orderList.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderList;
    }

    // Method to filter orders by city, district, ward
    public List<Order> filterOrders(String city, String district, String ward) {
        List<Order> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.makeConnection();
            if (conn != null) {
                String sql = "SELECT o.id AS orderId, a.full_name AS accountName, o.order_date AS orderDate, "
                        + "o.ship_date AS shipDate, o.ship_address AS shipAddress, c.name AS shipCity, "
                        + "d.name AS shipDistrict, w.name AS shipWard, o.total_price AS totalPrice, "
                        + "o.customer_notes AS customerNote, o.status AS status "
                        + "FROM Orders o "
                        + "JOIN Accounts a ON o.account_id = a.id "
                        + "JOIN City c ON o.ship_city_id = c.id "
                        + "JOIN District d ON o.ship_district_id = d.id "
                        + "JOIN Ward w ON o.ship_ward_id = w.id "
                        + "WHERE (? IS NULL OR c.name = ?) "
                        + "AND (? IS NULL OR d.name = ?) "
                        + "AND (? IS NULL OR w.name = ?)";

                pstmt = conn.prepareStatement(sql);

                // Set parameters
                pstmt.setString(1, city);  // 1 corresponds to :city parameter
                pstmt.setString(2, city);
                pstmt.setString(3, district);  // 2 corresponds to :district parameter
                pstmt.setString(4, district);  // 2 corresponds to :district parameter
                pstmt.setString(5, ward);  // 3 corresponds to :ward parameter
                pstmt.setString(6, ward);  // 3 corresponds to :ward parameter

                rs = pstmt.executeQuery();

                while (rs.next()) {
                    int orderId = rs.getInt("orderId");
                    String accountName = rs.getString("accountName");
                    Date orderDate = rs.getDate("orderDate");
                    Date shipDate = rs.getDate("shipDate");
                    String shipAddress = rs.getString("shipAddress");
                    String shipCity = rs.getString("shipCity");
                    String shipDistrict = rs.getString("shipDistrict");
                    String shipWard = rs.getString("shipWard");
                    double totalPrice = rs.getDouble("totalPrice");
                    String customerNote = rs.getString("customerNote");
                    int status = rs.getInt("status");

                    Order order = new Order(orderId, accountName, orderDate, shipDate, shipAddress, shipCity, shipDistrict, shipWard, totalPrice, customerNote, status);
                    orderList.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderList;
    }

    // Method to retrieve all cities
    public List<String> getCities() {
        List<String> cities = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBUtil.makeConnection();
            if (conn != null) {
                String sql = "SELECT DISTINCT name FROM City";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String city = rs.getString("name");
                    cities.add(city);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cities;
    }

    // Method to retrieve districts by city
    public List<String> getDistricts(String city) {
        List<String> districts = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBUtil.makeConnection();
            if (conn != null) {
                String sql = "SELECT DISTINCT d.name "
                        + "FROM District d "
                        + "JOIN City c ON d.city_id = c.id "
                        + "WHERE c.name = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, city);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String district = rs.getString("name");
                    districts.add(district);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return districts;
    }

    // Method to retrieve wards by district
    public List<String> getWards(String district) {
        List<String> wards = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBUtil.makeConnection();
            if (conn != null) {
                String sql = "SELECT DISTINCT w.name "
                        + "FROM Ward w "
                        + "JOIN District d ON w.district_id = d.id "
                        + "WHERE d.name = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, district);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String ward = rs.getString("name");
                    wards.add(ward);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return wards;
    }

    // Method to update order status by orderId
    public void updateOrderStatus(int orderId, int status) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.makeConnection();
            if (conn != null) {
                String sql = "UPDATE Orders SET status = ? WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, status);
                pstmt.setInt(2, orderId);
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Method get all order by accid
    public List<Order> getAllOrdersByAccID(int accid) {
        List<Order> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.makeConnection();
            if (conn != null) {
                String sql = "SELECT o.[id] as OrderID, a.full_name as AccountName, [order_date], [ship_date], [ship_address], [ship_city_id], [ship_district_id], [ship_ward_id], [total_price], [customer_notes], o.[status] "
                        + "FROM [DBFOODWEB].[dbo].[Orders] as o "
                        + "JOIN [dbo].[Accounts] as a ON o.account_id = a.id "
                        + "WHERE [account_id] = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, accid);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt("OrderID");
                    String accountName = rs.getString("AccountName");
                    Date orderDate = rs.getDate("order_date");
                    Date shipDate = rs.getDate("ship_date");
                    String shipAddress = rs.getString("ship_address");
                    String shipCity = rs.getString("ship_city_id");
                    String shipDistrict = rs.getString("ship_district_id");
                    String shipWard = rs.getString("ship_ward_id");
                    double totalPrice = rs.getDouble("total_price");
                    String customerNote = rs.getString("customer_notes");
                    int status = rs.getInt("status");

                    // Create Order object from the retrieved data
                    Order order = new Order(orderId, accountName, orderDate, shipDate, shipAddress, shipCity, shipDistrict, shipWard, totalPrice, customerNote, status);
                    orderList.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderList;
    }
}
