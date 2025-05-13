package DAO;

import java.sql.*;
import java.util.*;
import DTO.TaiKhoanDTO;
import config.DBConnector;

public class TaiKhoanDAO {
    public static TaiKhoanDAO getInstance(){
        return new TaiKhoanDAO();
    }

    public ArrayList<TaiKhoanDTO> selectAll(){
        ArrayList<TaiKhoanDTO> result = new ArrayList<>();
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM taikhoan";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int idNhanVien = rs.getInt("nhanvien_id");
                int idQuyen = rs.getInt("quyen_id");
                String tenTaiKhoan = rs.getString("tenTaiKhoan");
                String matKhau = rs.getString("matKhau");
                int trangThai = rs.getInt("trangThai");
                TaiKhoanDTO tk = new TaiKhoanDTO(id,idNhanVien,idQuyen,tenTaiKhoan,matKhau,trangThai);
                result.add(tk);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int insert(TaiKhoanDTO tk){
        int result = 0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "INSERT INTO taikhoan (id,nhanvien_id,quyen_id,tenTaiKhoan,matKhau,trangThai) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, tk.getId());
            pst.setInt(2, tk.getIdNhanVien());
            pst.setInt(3, tk.getIdQuyen());
            pst.setString(4, tk.getTenTaiKhoan());
            pst.setString(5, tk.getMatKhau());
            pst.setInt(6, tk.getTrangThai());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public TaiKhoanDTO selectById(String t){
        TaiKhoanDTO tk = null;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM taikhoan WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int idNhanVien = rs.getInt("nhanvien_id");
                int idQuyen = rs.getInt("quyen_id");
                String tenTaiKhoan = rs.getString("tenTaiKhoan");
                String matKhau = rs.getString("matKhau");
                int trangThai = rs.getInt("trangThai");
                tk = new TaiKhoanDTO(id,idNhanVien,idQuyen,tenTaiKhoan,matKhau,trangThai);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return tk;
    }

    public int getAutoIncrement(){
        int result = -1;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlynhahang' AND TABLE_NAME = 'taikhoan'";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if(!rs.isBeforeFirst()){
                System.out.println("No data");
            } else {
                while(rs.next()){
                    result = rs.getInt("AUTO_INCREMENT");
                }
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int delete(int id){
        int result = 0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "DELETE FROM taikhoan WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, id);
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int update(TaiKhoanDTO tk){
        int result = 0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE taikhoan SET nhanvien_id=?,quyen_id=?,tenTaiKhoan=?,matKhau=?,trangThai=? WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(6, tk.getId());
            pst.setInt(1, tk.getIdNhanVien());
            pst.setInt(2, tk.getIdQuyen());
            pst.setString(3, tk.getTenTaiKhoan());
            pst.setString(4, tk.getMatKhau());
            pst.setInt(5, tk.getTrangThai());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    
    
}
