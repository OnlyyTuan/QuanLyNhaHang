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
        try {
            Connection conn = DBConnector.getConnection();
            String query = "SELECT * FROM chitietquyen";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int idQuyen = rs.getInt("id_quyen");
                int idChucNang = rs.getInt("id_chucNang");
                String hanhDong = rs.getString("hanhDong");
                CTQuyenDTO cq = new CTQuyenDTO(idQuyen,idChucNang,hanhDong);
                result.add(cq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int insert(CTQuyenDTO cq){
        int result = 0;
        try {
            Connection conn = DBConnector.getConnection();
            String query = "INSERT INTO chitietquyen (id_quyen,id_chucNang,hanhDong) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, cq.getIdQuyen());
            ps.setInt(2, cq.getIdChucNang());
            ps.setString(3, cq.getHanhDong());
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public CTQuyenDTO selectById(String t){
        CTQuyenDTO cq = null;
        try {
            Connection conn = DBConnector.getConnection();
            String query = "SELECT * FROM chitietquyen WHERE id_quyen=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, t);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int idQuyen = rs.getInt("id_quyen");
                int idChucNang = rs.getInt("id_chucNang");
                String hanhDong = rs.getString("hanhDong");
                cq = new CTQuyenDTO(idQuyen,idChucNang,hanhDong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cq;
    }

    public int delete(int idQuyen){
        int result = 0;
        try {
            Connection conn = DBConnector.getConnection();
            String query = "DELETE FROM chitietquyen WHERE id_quyen=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idQuyen);
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
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
