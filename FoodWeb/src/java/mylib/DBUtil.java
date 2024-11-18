/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylib;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author htduy
 */
public class DBUtil {

    public static Connection makeConnection() throws Exception {
        Connection cn = null;
        // config these value before deploy and run project 
        // IP address
        String IP = "localhost";
        // Device name
        String instanceName = "htduy";
        // Database port (default: 1433)
        String port = "1433";
        // Account ID (default: sa)
        String uid = "sa";
        // Account's password
        String pwd = "12345";
        // Database name
        String db = "DBFOODWEB";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://" + IP + "\\" + instanceName + ":" + port
                + ";databasename=" + db + ";user=" + uid + ";password=" + pwd;
        Class.forName(driver);
        cn = DriverManager.getConnection(url);
        return cn;
    }
}
