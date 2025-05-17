/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public class AppData {
    private Connection conn;
    private BanAnBUS banAnBUS;
    private TaiKhoanBUS taiKhoanBUS;
    
    public AppData() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlynhahang", "root", "1234");
            banAnBUS = new BanAnBUS(conn);
            taiKhoanBUS = new TaiKhoanBUS(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public BanAnBUS getBanAnBUS() {
        return banAnBUS;
    }
    
    public TaiKhoanBUS getTaiKhoanBUS() {
        return taiKhoanBUS;
    }
}
