/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Ingredient;
import dto.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import mylib.DBUtil;

/**
 *
 * @author Tan Phat
 */
public class ItemDAO {

    public List<Ingredient> getIngredientsByItemid(int itemid) {
        List<Ingredient> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "SELECT i.id,i.[name] as ingredient_name, i.total_quantity, i.unit, i.[status]\n"
                        + "FROM [DBFOODWEB].[dbo].[Dishes] as d\n"
                        + "JOIN [dbo].[Dish_Ingredients] as di on d.id = di.dish_id\n"
                        + "JOIN [dbo].[Ingredients] as i ON di.ingredient_id = i.id \n"
                        + "WHERE d.[id] = ? AND i.[status] = 1;";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, itemid);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int id = table.getInt("id");
                        String name = table.getString("ingredient_name");
                        float quantity = table.getFloat("total_quantity");
                        String unit = table.getString("unit");
                        boolean status = table.getBoolean("status");

                        Ingredient ingredient = new Ingredient(id, name, quantity, unit, status);
                        list.add(ingredient);
                    }
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

    public ArrayList<Item> getAllItems() {
        ArrayList<Item> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "SELECT [id], [name], [price], [status], [description], [category], [calories], [image], [recipe]\n"
                        + "FROM [DBFOODWEB].[dbo].[Dishes]";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int itemid = table.getInt("id");
                        String itemname = table.getString("name");
                        int price = table.getInt("price");
                        boolean status = table.getBoolean("status");
                        String desc = table.getString("description");
                        String category = table.getString("category");
                        int calories = table.getInt("calories");
                        String image = table.getString("image");
                        String recipe = table.getString("recipe");
                        Item it = new Item(itemid, itemname, price, status, desc, category, calories, image, recipe);
                        list.add(it);
                    }
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

    public ArrayList<Item> getItemsByName(String findName) {
        ArrayList<Item> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "SELECT [id], [name], [price], [status], [description], [category], [calories], [image], [recipe]\n"
                        + "FROM [DBFOODWEB].[dbo].[Dishes]\n"
                        + "WHERE LOWER([name]) LIKE ? OR LOWER([category]) LIKE ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + findName + "%");
                pst.setString(2, "%" + findName + "%");
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int id = table.getInt("id");
                        String itemname = table.getString("name");
                        int price = table.getInt("price");
                        boolean status = table.getBoolean("status");
                        String desc = table.getString("description");
                        String category = table.getString("category");
                        int calories = table.getInt("calories");
                        String image = table.getString("image");
                        String recipe = table.getString("recipe");
                        Item it = new Item(id, itemname, price, status, desc, category, calories, image, recipe);
                        list.add(it);
                    }
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
    
    public ArrayList<Item> getDishesByIngredientNameOrDishNameOrCategory(String searchQuery) {
        ArrayList<Item> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "SELECT d.id, d.name, d.price, d.description, d.category, d.status, d.calories, d.image, d.recipe\n"
                        + "FROM [DBFOODWEB].[dbo].[Dishes] d\n"
                        + "WHERE d.id IN (\n"
                        + "    SELECT DISTINCT d.id\n"
                        + "    FROM [DBFOODWEB].[dbo].[Dishes] d\n"
                        + "    JOIN [dbo].[Dish_Ingredients] di ON d.id = di.dish_id\n"
                        + "    JOIN [dbo].[Ingredients] i ON di.ingredient_id = i.id \n"
                        + "    WHERE LOWER(i.[name]) LIKE ? OR LOWER(d.[name]) LIKE ? OR LOWER(d.[category]) LIKE ?\n"
                        + ")";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + searchQuery.toLowerCase() + "%");
                pst.setString(2, "%" + searchQuery.toLowerCase() + "%");
                pst.setString(3, "%" + searchQuery.toLowerCase() + "%");
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int id = table.getInt("id");
                        String itemname = table.getString("name");
                        int price = table.getInt("price");
                        boolean status = table.getBoolean("status");
                        String desc = table.getString("description");
                        String category = table.getString("category");
                        int calories = table.getInt("calories");
                        String image = table.getString("image");
                        String recipe = table.getString("recipe");
                        Item it = new Item(id, itemname, price, status, desc, category, calories, image, recipe);
                        list.add(it);
                    }
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



    public Item getItemById(int itemid) {
        Item rs = null;
        Connection cn = null;
        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "SELECT [id], [name], [price], [status], [description], [category], [calories], [image], [recipe]\n"
                        + "FROM [DBFOODWEB].[dbo].[Dishes]\n"
                        + "WHERE [id] = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, itemid);
                ResultSet table = pst.executeQuery();

                if (table != null) {
                    while (table.next()) {
                        String itemname = table.getString("name");
                        int price = table.getInt("price");
                        boolean status = table.getBoolean("status");
                        String desc = table.getString("description");
                        String category = table.getString("category");
                        int calories = table.getInt("calories");
                        String image = table.getString("image");
                        String recipe = table.getString("recipe");
                        rs = new Item(itemid, itemname, price, status, desc, category, calories, image, recipe);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("sql error");
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }

    private int getPriceRangeLowerBound(String priceFilter) {
        switch (priceFilter) {
            case "1":
                return 0; // Lower $10
            case "2":
                return 10; // Between $10 and $15
            case "3":
                return 20; // Upper $20
            default:
                return 0;
        }
    }

    private int getPriceRangeUpperBound(String priceFilter) {
        switch (priceFilter) {
            case "1":
                return 10; // Lower $10
            case "2":
                return 15; // Between $10 and $15
            case "3":
                return Integer.MAX_VALUE; // No upper bound for Upper $20
            default:
                return Integer.MAX_VALUE;
        }
    }

    public ArrayList<Item> getItemsFilter(String type, String price) {
        ArrayList<Item> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "SELECT [id], [name], [price], [status], [description], [category], [calories], [image], [recipe]\n"
                        + "FROM [DBFOODWEB].[dbo].[Dishes]\n"
                        + "WHERE [price] BETWEEN ? AND ?";

                if (type != null && !type.isEmpty()) {
                    sql += " AND [category] = ?";
                }

                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, getPriceRangeLowerBound(price));
                pst.setInt(2, getPriceRangeUpperBound(price));

                if (type != null && !type.isEmpty()) {
                    pst.setString(3, type);
                }

                ResultSet table = pst.executeQuery();
                while (table.next()) {
                    int itemid = table.getInt("id");
                    String itemname = table.getString("name");
                    int itemprice = table.getInt("price");
                    boolean status = table.getBoolean("status");
                    String desc = table.getString("description");
                    String category = table.getString("category");
                    int calories = table.getInt("calories");
                    String image = table.getString("image");
                    String recipe = table.getString("recipe");

                    Item it = new Item(itemid, itemname, itemprice, status, desc, category, calories, image, recipe);
                    list.add(it);
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

    public boolean addItem(Item item) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO [DBFOODWEB].[dbo].[Dishes] ([name], [price], [status], [description], [category], [calories], [image], [recipe])\n"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, item.getName());
                pst.setInt(2, item.getPrice());
                pst.setBoolean(3, item.isStatus());
                pst.setString(4, item.getDesc());
                pst.setString(5, item.getCategory());
                pst.setInt(6, item.getCalories());
                pst.setString(7, item.getImage1());
                pst.setString(8, item.getRecipe());
                result = pst.executeUpdate() > 0;
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

    public boolean deleteItem(int id) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "DELETE FROM [DBFOODWEB].[dbo].[Dishes] WHERE [id] = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, id);
                result = pst.executeUpdate() > 0;
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

    public boolean updateItemStatus(int id) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [DBFOODWEB].[dbo].[Dishes] SET [status] = ~[status] WHERE [id] = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, id);
                result = pst.executeUpdate() > 0;
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

    public boolean updateItem(Item item) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [DBFOODWEB].[dbo].[Dishes] SET [name] = ?, [price] = ?, [status] = ?, [description] = ?, [category] = ?, [calories] = ?, [image] = ?, [recipe] = ?\n"
                        + "WHERE [id] = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, item.getName());
                pst.setInt(2, item.getPrice());
                pst.setBoolean(3, item.isStatus());
                pst.setString(4, item.getDesc());
                pst.setString(5, item.getCategory());
                pst.setInt(6, item.getCalories());
                pst.setString(7, item.getImage1());
                pst.setString(8, item.getRecipe());
                pst.setInt(9, item.getId());
                result = pst.executeUpdate() > 0;
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
}
