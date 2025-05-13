package DAO;

import java.sql.*;
import java.util.*;
import DTO.CTQuyenDTO;
import config.DBConnector;

public class CTQuyenDAO {
    public static CTQuyenDAO getInstance(){
        return new CTQuyenDAO();
    }
    
    public ArrayList<CTQuyenDTO> selectAll(){
        ArrayList<CTQuyenDTO> result = new ArrayList<>();
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM ctquyen";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int idQuyen = rs.getInt("idQuyen");
                int idChucNang = rs.getInt("idChucNang");
                String hanhDong = rs.getString("hanhDong");
                CTQuyenDTO cq = new CTQuyenDTO(idQuyen,idChucNang,hanhDong);
                result.add(cq);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int insert(CTQuyenDTO cq){
        int result = 0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "INSERT INTO ctquyen (idQuyen,idChucNang,hanhDong) VALUES (?,?,?)";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, cq.getIdQuyen());
            pst.setInt(2, cq.getIdChucNang());
            pst.setString(3, cq.getHanhDong());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public CTQuyenDTO selectById(String t){
        CTQuyenDTO cq = null;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM ctquyen WHERE idQuyen=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int idQuyen = rs.getInt("idQuyen");
                int idChucNang = rs.getInt("idChucNang");
                String hanhDong = rs.getString("hanhDong");
                cq = new CTQuyenDTO(idQuyen,idChucNang,hanhDong);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return cq;
    }

    public int delete(int id){
        int result = 0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "DELETE FROM ctquyen WHERE idQuyen=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, id);
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int update(CTQuyenDTO cq){
        int result = 0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE ctquyen SET idChucNang=?,hanhDong=? WHERE idQuyen=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, cq.getIdChucNang());
            pst.setString(2, cq.getHanhDong());
            pst.setInt(3, cq.getIdQuyen());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

}
