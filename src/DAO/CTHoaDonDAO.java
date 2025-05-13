package DAO;

import java.sql.*;
import java.util.*;
import DTO.CTHoaDonDTO;
import config.DBConnector;

public class CTHoaDonDAO {
    public static CTHoaDonDAO getInstance(){
        return new CTHoaDonDAO();
    }

    public ArrayList<CTHoaDonDTO> selectAll(){
        ArrayList<CTHoaDonDTO> result = new ArrayList<>();
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM cthoadon";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int idHoaDon = rs.getInt("hoadon_id");
                int idMonAn = rs.getInt("monan_id");
                int soLuong = rs.getInt("soLuong");
                int tongGia = rs.getInt("tongGia");
                CTHoaDonDTO cthd = new CTHoaDonDTO(id,idHoaDon,idMonAn,soLuong,tongGia);
                result.add(cthd);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int insert(CTHoaDonDTO cthd){
        int result = 0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "INSERT INTO cthoadon (id,hoadon_id,monan_id,soLuong,tongGia) VALUES (?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, cthd.getId());
            pst.setInt(2, cthd.getIdHoaDon());
            pst.setInt(3, cthd.getIdMonAn());
            pst.setInt(4, cthd.getSoLuong());
            pst.setInt(5, cthd.getTongGia());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public CTHoaDonDTO selectById(String t){
        CTHoaDonDTO cthd = null;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM cthoadon WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int idHoaDon = rs.getInt("hoadon_id");
                int idMonAn = rs.getInt("monan_id");
                int soLuong = rs.getInt("soLuong");
                int tongGia = rs.getInt("tongGia");
                cthd = new CTHoaDonDTO(id,idHoaDon,idMonAn,soLuong,tongGia);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return cthd;
    }

    public int delete(int id){
        int result = 0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "DELETE FROM cthoadon WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, id);
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int update(CTHoaDonDTO cthd){
        int result = 0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE cthoadon SET hoadon_id=?,monan_id=?,soLuong=?,tongGia=? WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, cthd.getId());
            pst.setInt(2, cthd.getIdHoaDon());
            pst.setInt(3, cthd.getIdMonAn());
            pst.setInt(4, cthd.getSoLuong());
            pst.setInt(5, cthd.getTongGia());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
}
