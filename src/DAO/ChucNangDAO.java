package DAO;

import java.sql.*;
import java.util.*;
import DTO.ChucNangDTO;
import config.DBConnector;

public class ChucNangDAO {
    public static ChucNangDAO getInstance(){
        return new ChucNangDAO();
    }

    public ArrayList<ChucNangDTO> selectAll(){
        ArrayList<ChucNangDTO> result = new ArrayList<>();
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM chucnang";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                ChucNangDTO cn = new ChucNangDTO(id, ten);
                result.add(cn);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int insert(ChucNangDTO cn){
        int result = 0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "INSERT INTO chucnang (id,ten) VALUES (?,?)";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, cn.getId());
            pst.setString(2, cn.getTen());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public ChucNangDTO selectById(String t){
        ChucNangDTO cn = null;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM chucnang WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                cn = new ChucNangDTO(id, ten);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return cn;
    }

    public int getAutoIncrement(){
        int result = -1;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlynhahang' AND TABLE_NAME = 'chucnang'";
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
            String query = "DELETE FROM chucnang WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, id);
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int update(ChucNangDTO cn){
        int result = 0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE chucnang SET ten=? WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, cn.getTen());
            pst.setInt(2, cn.getId());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
}
