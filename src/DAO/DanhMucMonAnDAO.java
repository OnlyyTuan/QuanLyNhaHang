package DAO;

import java.sql.*;
import java.util.*;
import DTO.DanhMucMonAnDTO;
import config.DBConnector;

public class DanhMucMonAnDAO {
    public static DanhMucMonAnDAO getInstance(){
        return new DanhMucMonAnDAO();
    }

    public ArrayList<DanhMucMonAnDTO> selectAll(){
        ArrayList<DanhMucMonAnDTO> result = new ArrayList<>();
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM danhmucmonan";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                DanhMucMonAnDTO dmma = new DanhMucMonAnDTO(id,ten);
                result.add(dmma);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int insert(DanhMucMonAnDTO dmma){
        int result = 0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "INSERT INTO danhmucmonan (id,ten) VALUES (?,?)";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, dmma.getId());
            pst.setString(2, dmma.getTen());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);

        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public DanhMucMonAnDTO selectById(String t){
        DanhMucMonAnDTO dmma = null;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM danhmucmonan WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                dmma = new DanhMucMonAnDTO(id,ten);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return dmma;
    }

    public int getAutoIncrement(){
        int result = -1;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlynhahang' AND TABLE_NAME = 'danhmucmonan'";
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
            String query = "DELETE FROM danhmucmonan WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, id);
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int update(DanhMucMonAnDTO dmma){
        int result = 0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE danhmucmonan SET ten=? WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(2, dmma.getId());
            pst.setString(1, dmma.getTen());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) { 
            System.out.println(e);
        }
        return result;
    }

   
}
