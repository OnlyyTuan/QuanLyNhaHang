package DAO;

import java.sql.*;
import java.util.*;
import DTO.BanAnDTO;
import config.DBConnector;

public class BanAnDAO {
    public static BanAnDAO getInstance() {
        return new BanAnDAO();
    }

    public ArrayList<BanAnDTO> selectAll() {
        ArrayList<BanAnDTO> result = new ArrayList<>();
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM banan";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs= (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                int trangThai = rs.getInt("trangThai");
                BanAnDTO ba = new BanAnDTO(id, ten, trangThai);
                result.add(ba);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public BanAnDTO selectById(String t){
        BanAnDTO ba = null;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM banan WHERE id = ?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                int trangThai = rs.getInt("trangThai");
                ba = new BanAnDTO(id, ten, trangThai);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return ba;
    }

    public int getAutoIncrement(){
        int result = -1;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'cuahangdienthoai' AND TABLE_NAME = 'banan'";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if(!rs.isBeforeFirst()){
                System.out.println("No data");
            } else {
                while (rs.next()){
                    result = rs.getInt("AUTO_INCREMENT");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int insert(BanAnDTO ba){
        int result =0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "INSERT INTO banan (ten, trangThai) VALUES (?, ?)";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, ba.getTen());
            pst.setInt(2, ba.getTrangThai());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int update (BanAnDTO ba){
        int result =0;
        try{
            Connection conn=(Connection) DBConnector.getConnection();
            String query = "UPDATE banan SET ten=?, trangThai=? WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, ba.getTen());
            pst.setInt(2, ba.getTrangThai());
            pst.setInt(3, ba.getId());
            result=pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int delete(int id){
        int result =0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE banan SET trangThai=0 WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, id);
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }


}



