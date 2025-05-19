/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class DBConnector {
    private static String url = "jdbc:mysql://localhost:3306/quanlynhahang?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static String userName = "root";
    private static String passWord = "123456";
    
    public static boolean checkConnection() {
        Connection conn = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            conn = DriverManager.getConnection(url, userName, passWord);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu!", "", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            conn = DriverManager.getConnection(url, userName, passWord);
        } catch (Exception e) {
            
        }
        return conn;
    }
    
    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
