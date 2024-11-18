/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Item;
import dto.MealPlanItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mylib.DBUtil;

/**
 *
 * @author htduy
 */
public class MealPlanDAO {

    public boolean checkExist(int accid, int itemid) {
        boolean exists = false;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            // Make connection between backend and SQL Server
            cn = DBUtil.makeConnection();
            if (cn != null) {
                // Write SQL and execute SQL
                String sql = "SELECT [account_id],[dish_id],[status]\n"
                        + "FROM [DBFOODWEB].[dbo].[Customer_Plan]\n"
                        + "WHERE [account_id] = ? AND [dish_id] = ? AND [status] = 1";
                pst = cn.prepareStatement(sql);

                pst.setInt(1, accid);
                pst.setInt(2, itemid);
                // Execute SQL
                rs = pst.executeQuery();
                // Check if any result exists
                if (rs != null && rs.next()) {
                    exists = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return exists;
    }

    public boolean insertMealPlan(int accid, int itemid, boolean status) {
        boolean isInsert = false;
        Connection cn = null;
        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO [dbo].[Customer_Plan]([account_id],[dish_id],[status])\n"
                        + "VALUES(?,?,?);";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, accid);
                pst.setInt(2, itemid);
                pst.setBoolean(3, status);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    isInsert = true;
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
        return isInsert;
    }
    
    public List<Item> getLikedByAccId(int accid) {
        List<Item> list = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "SELECT [account_id], c.dish_id, c.status as meal_status, d.name, d.price, d.image, d.status as dish_status, d.category\n"
                        + "FROM [DBFOODWEB].[dbo].[Customer_Plan] as c\n"
                        + "JOIN [dbo].[Dishes] as d ON c.dish_id = d.id\n"
                        + "WHERE [account_id] = ? and c.status = 1";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, accid);

                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    int accountId = rs.getInt("account_id");
                    int dishId = rs.getInt("dish_id");
                    boolean meal_status = rs.getBoolean("meal_status");
                    String name = rs.getString("name");
                    int price = rs.getInt("price");
                    String image1 = rs.getString("image");
                    boolean dish_status = rs.getBoolean("dish_status");
                    String category = rs.getString("category");

                    Item item = new Item(dishId, name, price, dish_status, category, image1);
                    list.add(item);
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
        return list;
    }
    
    public List<MealPlanItem> getMealPlanByAccId(int accid) {
        List<MealPlanItem> list = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "SELECT [account_id], c.dish_id, c.status as meal_status, d.name, d.price, d.image, d.status as dish_status, d.category\n"
                        + "FROM [DBFOODWEB].[dbo].[Customer_Plan] as c\n"
                        + "JOIN [dbo].[Dishes] as d ON c.dish_id = d.id\n"
                        + "WHERE [account_id] = ? and c.status = 1";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, accid);

                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    int accountId = rs.getInt("account_id");
                    int dishId = rs.getInt("dish_id");
                    boolean meal_status = rs.getBoolean("meal_status");
                    String name = rs.getString("name");
                    int price = rs.getInt("price");
                    String image1 = rs.getString("image");
                    boolean dish_status = rs.getBoolean("dish_status");
                    String category = rs.getString("category");

                    Item item = new Item(dishId, name, price, dish_status, category, image1);
                    MealPlanItem mealPlanItem = new MealPlanItem(accountId, item, meal_status);
                    list.add(mealPlanItem);
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
        return list;
    }

    public boolean deleteMealPlan(int accid, int itemid) {
        boolean isDel = false;
        Connection cn = null;
        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [dbo].[Customer_Plan] SET [status] = 0\n"
                        + "WHERE [account_id] = ? AND [dish_id] = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, accid);
                pst.setInt(2, itemid);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    isDel = true;
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
        return isDel;
    }

}
