package DAO;

import java.sql.*;
import java.util.*;
import DTO.HoaDonDTO;
import config.DBConnector;

public class HoaDonDAO {
    public static HoaDonDAO getInstance(){
        return new HoaDonDAO();
    }
    

    public ArrayList<HoaDonDTO> selectAll(){
        ArrayList<HoaDonDTO> result = new ArrayList<>();
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM hoadon";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int idBanAn = rs.getInt("id_banAn");
                int nhanVienId = rs.getInt("id_nhanVien");
                String tenKhach = rs.getString("tenKhach");
                int tongTien = rs.getInt("tongTien");
                Timestamp thoiGian = rs.getTimestamp("thoigian");
                String ghiChu = rs.getString("ghiChu");
                HoaDonDTO hd = new HoaDonDTO(id, idBanAn, nhanVienId, tenKhach, tongTien, thoiGian, ghiChu);
                result.add(hd);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int insert(HoaDonDTO hd){
        int result=0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "INSERT INTO hoadon (id_banAn, id_nhanVien, tenKhach, tongTien, thoigian, ghiChu) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, hd.getIdBanAn());
            pst.setInt(2, hd.getIdNhanVien());
            pst.setString(3, hd.getTenKhach());
            pst.setInt(4, hd.getTongTien());
            pst.setTimestamp(5, hd.getThoiGian());
            pst.setString(6, hd.getGhiChu());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public HoaDonDTO selectById(String t){
        HoaDonDTO hd = null;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM hoadon WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int idBanAn = rs.getInt("id_banAn");
                int nhanVienId = rs.getInt("id_nhanVien");
                String tenKhach = rs.getString("tenKhach");
                int tongTien = rs.getInt("tongTien");
                Timestamp thoiGian = rs.getTimestamp("thoigian");
                String ghiChu = rs.getString("ghiChu");
                hd = new HoaDonDTO(id, idBanAn, nhanVienId, tenKhach, tongTien, thoiGian, ghiChu);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return hd;
    }

    public int getAutoIncrement(){
        int result = -1;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'cuahangdienthoai' AND TABLE_NAME = 'hoadon'";
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
            String query = "DELETE FROM hoadon WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, id);
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int update(HoaDonDTO hd){
        int result = 0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE hoadon SET id_banAn=?, id_nhanVien=?, tenKhach=?, tongTien=?, thoigian=?, ghiChu=? WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, hd.getIdBanAn());
            pst.setInt(2, hd.getIdNhanVien());
            pst.setString(3, hd.getTenKhach());
            pst.setInt(4, hd.getTongTien());
            pst.setTimestamp(5, hd.getThoiGian());
            pst.setString(6, hd.getGhiChu());
            pst.setInt(7, hd.getId());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    
}
