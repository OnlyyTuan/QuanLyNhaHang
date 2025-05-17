package DAO;

import java.sql.*;
import java.util.*;
import DTO.MonAnDTO;
import config.DBConnector;

public class MonAnDAO {
    public static MonAnDAO getInstance(){
      return new MonAnDAO();
}

public ArrayList<MonAnDTO> selectAll(){
    ArrayList<MonAnDTO> result = new ArrayList<>();
    try{
        Connection conn = (Connection) DBConnector.getConnection();
        String query = "SELECT * FROM monan";
        PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
        ResultSet rs = (ResultSet) pst.executeQuery();
        while(rs.next()){
            int id = rs.getInt("id");
            int danhMucId=rs.getInt("id_danhMuc");
            String ten = rs.getString("ten");
            String hinhAnh = rs.getString("hinhAnh");
            int gia = rs.getInt("gia");
            int trangThai = rs.getInt("trangThai");
            MonAnDTO ma = new MonAnDTO(id, ten, danhMucId, gia, hinhAnh, trangThai);
            result.add(ma);
        }
        DBConnector.closeConnection(conn);
    } catch (Exception e) {
        System.out.println(e);
    }
    return result;
    }

public int insert(MonAnDTO ma){
    int result = 0;
    try{
        Connection conn = (Connection) DBConnector.getConnection();
        String query = "INSERT INTO monan (id_danhMuc,ten,hinhAnh,gia,trangThai) VALUES (?,?,?,?,?)";
        PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
        pst.setInt(1, ma.getIdDanhMuc());
        pst.setString(2, ma.getTen());
        pst.setString(3, ma.getHinhAnh());
        pst.setInt(4, ma.getGia());
        pst.setInt(5, ma.getTrangThai());
        result = pst.executeUpdate();
        DBConnector.closeConnection(conn);
    } catch (Exception e) {
        System.out.println(e);
    }
    return result;
    }

public MonAnDTO selectById(String t){
    MonAnDTO ma = null;
    try{
        Connection conn = (Connection) DBConnector.getConnection();
        String query = "SELECT * FROM monan WHERE id=?";
        PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
        pst.setString(1, t);
        ResultSet rs = (ResultSet) pst.executeQuery();
        while(rs.next()){
            int id = rs.getInt("id");
            int danhMucId = rs.getInt("id_danhMuc");
            String ten = rs.getString("ten");
            String hinhAnh = rs.getString("hinhAnh");
            int gia = rs.getInt("gia");
            int trangThai = rs.getInt("trangThai");
            ma = new MonAnDTO(id,ten,danhMucId,gia,hinhAnh,trangThai);

        }
        DBConnector.closeConnection(conn);
    } catch (Exception e) {
        System.out.println(e);
    }
    return ma;
}

public int getAutoIncrement(){
    int result =-1;
    try{
        Connection conn = (Connection) DBConnector.getConnection();
        String query = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlynhahang' AND TABLE_NAME = 'monan'";
        PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
        ResultSet rs= pst.executeQuery();
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
        String query = "DELETE FROM monan WHERE id=?";
        PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
        pst.setInt(1, id);
        result = pst.executeUpdate();
        DBConnector.closeConnection(conn);
    } catch (Exception e) {
        System.out.println(e);
    }
    return result;
}

public int update(MonAnDTO ma){
    int result = 0;
    try{
        Connection conn = (Connection) DBConnector.getConnection();
        String query = "UPDATE monan SET id_danhMuc=?,ten=?,hinhAnh=?,gia=?,trangThai=? WHERE id=?";
        PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
        pst.setInt(6, ma.getId());
        pst.setString(2, ma.getTen());
        pst.setInt(1, ma.getIdDanhMuc());
        pst.setInt(4, ma.getGia());
        pst.setString(3, ma.getHinhAnh());
        pst.setInt(5, ma.getTrangThai());
        result=pst.executeUpdate();
        DBConnector.closeConnection(conn);
    } catch (Exception e) {
        System.out.println(e);
    }
    return result;
    }

    
}

