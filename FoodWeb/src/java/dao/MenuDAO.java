/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mylib.DBUtil;

/**
 *
 * @author htduy
 */
public class MenuDAO {

    public ArrayList<Menu> getAllMenus() {
        ArrayList<Menu> menus = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "SELECT\n"
                        + "    m.id AS menu_id,\n"
                        + "    m.name AS menu_name,\n"
                        + "    m.status AS menu_status,\n"
                        + "    m.description AS menu_description, \n"
                        + "    m.category AS menu_category,\n"
                        + "    d.price AS dish_price,\n"
                        + "    d.calories AS dish_calories,\n"
                        + "    d.image AS dish_image\n"
                        + "FROM \n"
                        + "    Menu m\n"
                        + "JOIN \n"
                        + "    Menu_Dishes md ON m.id = md.menu_id\n"
                        + "JOIN \n"
                        + "    Dishes d ON md.dish_id = d.id;";

                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                HashMap<Integer, Menu> menuMap = new HashMap<>();
                while (rs.next()) {
                    int menuId = rs.getInt("menu_id");
                    String menuName = rs.getString("menu_name");
                    boolean menuStatus = rs.getBoolean("menu_status");
                    String menuDescription = rs.getString("menu_description");
                    String menuCategory = rs.getString("menu_category");
                    int dishPrice = rs.getInt("dish_price");
                    int dishCalories = rs.getInt("dish_calories");
                    String dishImage = rs.getString("dish_image");

                    Menu menu;
                    if (menuMap.containsKey(menuId)) {
                        menu = menuMap.get(menuId);
                    } else {
                        menu = new Menu(menuId, menuName, 0, menuStatus, menuDescription, menuCategory, 0, new ArrayList<>());
                        menuMap.put(menuId, menu);
                    }

                    menu.setTotalPrice(menu.getTotalPrice() + dishPrice);
                    menu.setTotalCalories(menu.getTotalCalories() + dishCalories);

                    List<String> images = menu.getImages();
                    if (images.size() < 4) {
                        images.add(dishImage);
                    }
                }

                menus.addAll(menuMap.values());
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
        return menus;
    }

    public Menu getMenuById(int menuId) {
        Menu menu = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "SELECT\n"
                        + "    m.name AS menu_name,\n"
                        + "    d.price AS dish_price,\n"
                        + "    m.status AS menu_status,\n"
                        + "    m.description AS menu_description, \n"
                        + "    m.category AS menu_category,\n"
                        + "    d.calories AS dish_calories,\n"
                        + "    d.image AS dish_image,\n"
                        + "    m.period AS menu_period,\n"
                        + "    m.day_of_week AS menu_weekly\n"
                        + "FROM \n"
                        + "    Menu m\n"
                        + "JOIN \n"
                        + "    Menu_Dishes md ON m.id = md.menu_id\n"
                        + "JOIN \n"
                        + "    Dishes d ON md.dish_id = d.id\n"
                        + "WHERE \n"
                        + "    m.id = ?;";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, menuId);
                rs = pst.executeQuery();

                String menuName = null;
                String menuDescription = null;
                int totalPrice = 0;
                int totalCalories = 0;
                List<String> dishImages = new ArrayList<>();
                String menuCategory = null;
                int menuPeriod = 0;
                int menuWeekly = 0;
                boolean menuStatus = false;

                while (rs.next()) {
                    if (menuName == null) {  // Initialize once
                        menuName = rs.getString("menu_name");
                        menuDescription = rs.getString("menu_description");
                        menuCategory = rs.getString("menu_category");
                        menuPeriod = rs.getInt("menu_period");
                        menuWeekly = rs.getInt("menu_weekly");
                        menuStatus = rs.getBoolean("menu_status");
                    }

                    int dishPrice = rs.getInt("dish_price");
                    int dishCalories = rs.getInt("dish_calories");
                    String dishImage = rs.getString("dish_image");

                    dishImages.add(dishImage);

                    totalPrice += dishPrice;
                    totalCalories += dishCalories;
                }

                if (menuName != null) {
                    menu = new Menu(menuId, menuName, totalPrice, menuPeriod, menuWeekly, menuStatus, menuDescription, menuCategory, totalCalories, dishImages);
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
        return menu;
    }

    public ArrayList<Menu> getMenuByName(String findName) {
        ArrayList<Menu> menus = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "SELECT\n"
                        + "    m.id AS menu_id,\n"
                        + "    m.name AS menu_name,\n"
                        + "    m.status AS menu_status,\n"
                        + "    m.description AS menu_description,\n"
                        + "    m.category AS menu_category,\n"
                        + "    d.price AS dish_price,\n"
                        + "    d.calories AS dish_calories,\n"
                        + "    d.image AS dish_image\n"
                        + "FROM\n"
                        + "    Menu m\n"
                        + "JOIN\n"
                        + "    Menu_Dishes md ON m.id = md.menu_id\n"
                        + "JOIN\n"
                        + "    Dishes d ON md.dish_id = d.id\n"
                        + "WHERE\n"
                        + "    m.name LIKE ?;";

                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + findName + "%");
                ResultSet rs = pst.executeQuery();

                HashMap<Integer, Menu> menuMap = new HashMap<>();
                while (rs.next()) {
                    int menuId = rs.getInt("menu_id");
                    String menuName = rs.getString("menu_name");
                    boolean menuStatus = rs.getBoolean("menu_status");
                    String menuDescription = rs.getString("menu_description");
                    String menuCategory = rs.getString("menu_category");
                    int dishPrice = rs.getInt("dish_price");
                    int dishCalories = rs.getInt("dish_calories");
                    String dishImage = rs.getString("dish_image");

                    Menu menu;
                    if (menuMap.containsKey(menuId)) {
                        menu = menuMap.get(menuId);
                    } else {
                        menu = new Menu(menuId, menuName, 0, menuStatus, menuDescription, menuCategory, 0, new ArrayList<>());
                        menuMap.put(menuId, menu);
                    }

                    menu.setTotalPrice(menu.getTotalPrice() + dishPrice);
                    menu.setTotalCalories(menu.getTotalCalories() + dishCalories);

                    List<String> images = menu.getImages();
                    if (images.size() < 4) {
                        images.add(dishImage);
                    }
                }

                menus.addAll(menuMap.values());
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
        return menus;
    }

    public ArrayList<Menu> getMenusFilter(String type, String period, String weeklyMenu) {
        ArrayList<Menu> menus = new ArrayList<>();
        Menu menu = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                // Start building the SQL query
                StringBuilder sql = new StringBuilder("SELECT\n"
                        + "    m.id AS menu_id,\n"
                        + "    m.name AS menu_name,\n"
                        + "    m.status AS menu_status,\n"
                        + "    m.description AS menu_description, \n"
                        + "    m.category AS menu_category,\n"
                        + "    m.period AS menu_period,\n"
                        + "    m.day_of_week AS menu_weekly,\n"
                        + "    d.price AS dish_price,\n"
                        + "    d.calories AS dish_calories,\n"
                        + "    d.image AS dish_image\n"
                        + "FROM \n"
                        + "    Menu m\n"
                        + "JOIN \n"
                        + "    Menu_Dishes md ON m.id = md.menu_id\n"
                        + "JOIN \n"
                        + "    Dishes d ON md.dish_id = d.id\n"
                        + "WHERE 1=1");  // Always true condition for easier appending

                // Dynamically append conditions based on provided parameters
                List<Object> parameters = new ArrayList<>();
                if (type != null && !type.isEmpty()) {
                    sql.append(" AND m.category = ?");
                    parameters.add(type);
                }
                if (period != null && !period.isEmpty()) {
                    sql.append(" AND m.period = ?");
                    parameters.add(Integer.valueOf(period));
                }
                if (weeklyMenu != null && !weeklyMenu.isEmpty()) {
                    sql.append(" AND m.day_of_week = ?");
                    parameters.add(Integer.valueOf(weeklyMenu));
                }

                pst = cn.prepareStatement(sql.toString());
                for (int i = 0; i < parameters.size(); i++) {
                    pst.setObject(i + 1, parameters.get(i));
                }

                rs = pst.executeQuery();

                int menuId = 0;
                String menuName = null;
                String menuDescription = null;
                int totalPrice = 0;
                int totalCalories = 0;
                List<String> dishImages = new ArrayList<>();
                String menuCategory = null;
                int menuPeriod = 0;
                int menuWeekly = 0;
                boolean menuStatus = false;

                while (rs.next()) {
                    if (menuName == null || menuId != rs.getInt("menu_id")) {  // Initialize once or when a new menu_id is encountered
                        if (menuName != null) {
                            menu = new Menu(menuId, menuName, totalPrice, menuPeriod, menuWeekly, menuStatus, menuDescription, menuCategory, totalCalories, dishImages);
                            menus.add(menu);
                            // Reset for new menu
                            totalPrice = 0;
                            totalCalories = 0;
                            dishImages = new ArrayList<>();
                        }
                        menuId = rs.getInt("menu_id");
                        menuName = rs.getString("menu_name");
                        menuDescription = rs.getString("menu_description");
                        menuCategory = rs.getString("menu_category");
                        menuPeriod = rs.getInt("menu_period");
                        menuWeekly = rs.getInt("menu_weekly");
                        menuStatus = rs.getBoolean("menu_status");
                    }

                    int dishPrice = rs.getInt("dish_price");
                    int dishCalories = rs.getInt("dish_calories");
                    String dishImage = rs.getString("dish_image");

                    dishImages.add(dishImage);
                    totalPrice += dishPrice;
                    totalCalories += dishCalories;
                }

                if (menuName != null) {
                    menu = new Menu(menuId, menuName, totalPrice, menuPeriod, menuWeekly, menuStatus, menuDescription, menuCategory, totalCalories, dishImages);
                    menus.add(menu);
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
        return menus;
    }

}
