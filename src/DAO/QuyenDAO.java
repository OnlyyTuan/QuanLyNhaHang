package DAO;

import java.sql.*;
import java.util.*;
import DTO.QuyenDTO;
import config.DBConnector;

public class QuyenDAO {
    public static QuyenDAO getInstance(){
        return new QuyenDAO();
    }
    
    public ArrayList<QuyenDTO> selectAll(){
        ArrayList<QuyenDTO> result = new ArrayList<>();
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM quyen";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                QuyenDTO q = new QuyenDTO(id, ten);
                result.add(q);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int insert(QuyenDTO q){
        int result = 0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "INSERT INTO quyen (id,ten) VALUES (?,?)";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, q.getId());
            pst.setString(2, q.getTen());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public QuyenDTO selectById(String t){
        QuyenDTO q = null;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM quyen WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                q = new QuyenDTO(id, ten);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return q;
    }

    public int getAutoIncrement(){
        int result = -1;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlynhahang' AND TABLE_NAME = 'quyen'";
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
            String query = "DELETE FROM quyen WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, id);
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int update(QuyenDTO q){
        int result = 0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE quyen SET ten=? WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(2, q.getId());
            pst.setString(1, q.getTen());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
}
