/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Account;
import java.sql.*;
import java.util.ArrayList;
import mylib.DBUtil;

/**
 *
 * @author Tan Phat
 */
public class AccountDAO {

    // insert role = 'User'
    public int insert(String fullName, String password, String email, String phone, String address, int wardId, int districtId, int cityId) throws Exception {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.makeConnection();
            if (conn != null) {
                String sql = "INSERT INTO Accounts (full_name, [password], email, phone, [address], ward_id, district_id, city_id, [role], [status]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'user', 1)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, fullName);
                pstmt.setString(2, password);
                pstmt.setString(3, email);
                pstmt.setString(4, phone);
                pstmt.setString(5, address);
                pstmt.setInt(6, wardId);
                pstmt.setInt(7, districtId);
                pstmt.setInt(8, cityId);
                result = pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // Re-throw the exception after logging it
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
        return result;
    }

    public boolean checkEmail(String email) {
        boolean exists = false;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            // Make connection between backend and SQL Server
            cn = DBUtil.makeConnection();
            if (cn != null) {
                // Write SQL and execute SQL
                String sql = "SELECT [email] FROM [dbo].[Accounts] \n"
                        + "WHERE [email] = ? COLLATE SQL_Latin1_General_CP1_CI_AS";
                pst = cn.prepareStatement(sql);
                // Set email input parameter
                pst.setString(1, email);
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

    public Account checkLogin(String email, String password) {
        Account rs = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet table = null;

        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "SELECT [id],[full_name],[password],[email],[phone],[address],[ward_id],[district_id],[city_id],[role],[status] \n"
                        + "  FROM [dbo].[Accounts] \n"
                        + "  WHERE [email] = ? AND [Password] = ? COLLATE SQL_Latin1_General_CP1_CI_AS ";
                pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                table = pst.executeQuery();

                if (table != null && table.next()) {
                    int id = table.getInt("id");
                    String fullName = table.getString("full_name");
                    String dbPassword = table.getString("password");
                    String dbEmail = table.getString("email");
                    String phone = table.getString("phone");
                    String address = table.getString("address");
                    int wardId = table.getInt("ward_id");
                    int districtId = table.getInt("district_id");
                    int cityId = table.getInt("city_id");
                    String role = table.getString("role");
                    boolean status = table.getBoolean("status");

                    rs = new Account(id, fullName, dbPassword, dbEmail, phone, address, wardId, districtId, cityId, role, status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (table != null) {
                    table.close();
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
        return rs;
    }

    public ArrayList<Account> getAllAccounts() {
        ArrayList<Account> accountList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBUtil.makeConnection();
            if (conn != null) {
                String sql = "SELECT id, full_name, email, phone, address, ward_id, district_id, city_id, role, status "
                        + "FROM Accounts";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String fullName = rs.getString("full_name");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    int wardId = rs.getInt("ward_id");
                    int districtId = rs.getInt("district_id");
                    int cityId = rs.getInt("city_id");
                    String role = rs.getString("role");
                    boolean status = rs.getBoolean("status");

                    // Tạo đối tượng Account từ các thông tin lấy được từ cơ sở dữ liệu
                    Account account = new Account(id, fullName, email, phone, address, wardId, districtId, cityId, role, status);
                    accountList.add(account);
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
        return accountList;
    }

    public Account getAccountById(int accountId) throws Exception {
        Connection conn = null;
        try {
            conn = DBUtil.makeConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Accounts WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, accountId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    Account account = new Account(
                            rs.getInt("id"),
                            rs.getString("full_name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            rs.getInt("ward_id"),
                            rs.getInt("district_id"),
                            rs.getInt("city_id"),
                            rs.getString("role"),
                            rs.getBoolean("status")
                    );
                    return account;
                }
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

    // admin role insert
    public void addAccount(Account account) {
        Connection conn = null;
        try {
            conn = DBUtil.makeConnection();
            if (conn != null) {
                String sql = "INSERT INTO Accounts (full_name, email, phone, address, ward_id, district_id, city_id, role, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, account.getFullName());
                pstmt.setString(2, account.getEmail());
                pstmt.setString(3, account.getPhone());
                pstmt.setString(4, account.getAddress());
                pstmt.setInt(5, account.getWardId());
                pstmt.setInt(6, account.getDistrictId());
                pstmt.setInt(7, account.getCityId());
                pstmt.setString(8, account.getRole());
                pstmt.setBoolean(9, account.isStatus());
                pstmt.executeUpdate();
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
    }

    public void updateAccount(Account account) {
        Connection conn = null;
        try {
            conn = DBUtil.makeConnection();
            if (conn != null) {
                String sql = "UPDATE Accounts SET full_name = ?, email = ?, phone = ?, address = ?, ward_id = ?, district_id = ?, city_id = ?, role = ?, status = ? WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, account.getFullName());
                pstmt.setString(2, account.getEmail());
                pstmt.setString(3, account.getPhone());
                pstmt.setString(4, account.getAddress());
                pstmt.setInt(5, account.getWardId());
                pstmt.setInt(6, account.getDistrictId());
                pstmt.setInt(7, account.getCityId());
                pstmt.setString(8, account.getRole());
                pstmt.setBoolean(9, account.isStatus());
                pstmt.setInt(10, account.getId());
                pstmt.executeUpdate();
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
    }
    
    public boolean deleteAccount(String id) {
        Connection conn = null;
        boolean isDeleted = false;
        try {
            conn = DBUtil.makeConnection();
            if (conn != null) {
                String sql = "DELETE FROM Accounts WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, id);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    isDeleted = true; // Xóa thành công
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
        return isDeleted;
    }

    
//    public void deleteAccount(String id) {
//        Connection conn = null;
//        try {
//            conn = DBUtil.makeConnection();
//            if (conn != null) {
//                String sql = "DELETE FROM Accounts WHERE id = ?";
//                PreparedStatement pstmt = conn.prepareStatement(sql);
//                pstmt.setString(1, id);
//                pstmt.executeUpdate();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void updateAccountStatus(String id, boolean status) {
        Connection conn = null;
        try {
            conn = DBUtil.makeConnection();
            if (conn != null) {
                String sql = "UPDATE [DBFOODWEB].[dbo].[Accounts]\n"
                        + "SET [status] = ?\n"
                        + "WHERE [id] = ?;";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setBoolean(1, status);
                pstmt.setString(2, id);
                pstmt.executeUpdate();
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
    }

    public ArrayList<Account> getAccountsByInfo(String info) {
        ArrayList<Account> accountList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBUtil.makeConnection();
            if (conn != null) {
                String sql = "SELECT id, full_name, email, phone, address, ward_id, district_id, city_id, role, status "
                        + "FROM Accounts"
                        + " WHERE phone LIKE ? OR email LIKE ? OR full_name LIKE ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "%" + info + "%");
                pstmt.setString(2, "%" + info + "%");
                pstmt.setString(3, "%" + info + "%");
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String fullName = rs.getString("full_name");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    int wardId = rs.getInt("ward_id");
                    int districtId = rs.getInt("district_id");
                    int cityId = rs.getInt("city_id");
                    String role = rs.getString("role");
                    boolean status = rs.getBoolean("status");

                    // Tạo đối tượng Account từ các thông tin lấy được từ cơ sở dữ liệu
                    Account account = new Account(id, fullName, email, phone, address, wardId, districtId, cityId, role, status);
                    accountList.add(account);
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
        return accountList;
    }
}
