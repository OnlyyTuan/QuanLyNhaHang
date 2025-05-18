package DAO;

import java.sql.*;
import java.util.*;
import DTO.HoaDonDTO;
import config.DBConnector;

public class HoaDonDAO {
    private Connection conn;

    public HoaDonDAO(Connection conn) {
        this.conn = conn;
    }

    public static HoaDonDAO getInstance(){
        return new HoaDonDAO(DBConnector.getConnection());
    }
    

    public ArrayList<HoaDonDTO> selectAll(){
        ArrayList<HoaDonDTO> result = new ArrayList<>();
        try{
            String query = "SELECT * FROM hoadon";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
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
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public HoaDonDTO getHoaDonByBanAn(int idBanAn) {
        HoaDonDTO hd = null;
        try {
            String query = "SELECT * FROM hoadon WHERE id_banAn = ? AND thoigian >= CURDATE() ORDER BY thoigian DESC LIMIT 1";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, idBanAn);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                int nhanVienId = rs.getInt("id_nhanVien");
                String tenKhach = rs.getString("tenKhach");
                int tongTien = rs.getInt("tongTien");
                Timestamp thoiGian = rs.getTimestamp("thoigian");
                String ghiChu = rs.getString("ghiChu");
                hd = new HoaDonDTO(id, idBanAn, nhanVienId, tenKhach, tongTien, thoiGian, ghiChu);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return hd;
    }

    public ArrayList<HoaDonDTO> getLichSuHoaDonByBanAn(int idBanAn) {
        ArrayList<HoaDonDTO> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM hoadon WHERE id_banAn = ? ORDER BY thoigian DESC";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, idBanAn);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int nhanVienId = rs.getInt("id_nhanVien");
                String tenKhach = rs.getString("tenKhach");
                int tongTien = rs.getInt("tongTien");
                Timestamp thoiGian = rs.getTimestamp("thoigian");
                String ghiChu = rs.getString("ghiChu");
                HoaDonDTO hd = new HoaDonDTO(id, idBanAn, nhanVienId, tenKhach, tongTien, thoiGian, ghiChu);
                result.add(hd);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int insert(HoaDonDTO hd){
        int result=0;
        try{
            String query = "INSERT INTO hoadon (id_banAn, id_nhanVien, tenKhach, tongTien, thoigian, ghiChu) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, hd.getIdBanAn());
            pst.setInt(2, hd.getIdNhanVien());
            pst.setString(3, hd.getTenKhach());
            pst.setInt(4, hd.getTongTien());
            pst.setTimestamp(5, hd.getThoiGian());
            pst.setString(6, hd.getGhiChu());
            result = pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public HoaDonDTO selectById(String t){
        HoaDonDTO hd = null;
        try{
            String query = "SELECT * FROM hoadon WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
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
        } catch (Exception e) {
            System.out.println(e);
        }
        return hd;
    }

    public int getAutoIncrement(){
        int result = -1;
        try{
            String query = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'cuahangdienthoai' AND TABLE_NAME = 'hoadon'";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if(!rs.isBeforeFirst()){
                System.out.println("No data");
            } else {
                while(rs.next()){
                    result = rs.getInt("AUTO_INCREMENT");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }


    public int delete(int id){
        int result = 0;
        try{
            String query = "DELETE FROM hoadon WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            result = pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int update(HoaDonDTO hd){
        int result = 0;
        try{
            String query = "UPDATE hoadon SET id_banAn=?, id_nhanVien=?, tenKhach=?, tongTien=?, thoigian=?, ghiChu=? WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, hd.getIdBanAn());
            pst.setInt(2, hd.getIdNhanVien());
            pst.setString(3, hd.getTenKhach());
            pst.setInt(4, hd.getTongTien());
            pst.setTimestamp(5, hd.getThoiGian());
            pst.setString(6, hd.getGhiChu());
            pst.setInt(7, hd.getId());
            result = pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public List<HoaDonDTO> getList_hoaDon() {
        List<HoaDonDTO> list = new ArrayList<>();
        String sql = "SELECT h.*, n.hoTen as tenNhanVien FROM hoadon h " +
                    "LEFT JOIN nhanvien n ON h.id_nhanVien = n.id " +
                    "ORDER BY h.thoigian DESC";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HoaDonDTO hoaDon = new HoaDonDTO(
                    rs.getInt("id"),
                    rs.getInt("id_nhanVien"),
                    rs.getInt("id_banAn"),
                    rs.getString("tenKhach"),
                    rs.getInt("tongTien"),
                    rs.getTimestamp("thoigian"),
                    rs.getString("ghiChu")
                );
                list.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public HoaDonDTO getByID(int id) {
        String sql = "SELECT h.*, n.hoTen as tenNhanVien FROM hoadon h " +
                    "LEFT JOIN nhanvien n ON h.id_nhanVien = n.id " +
                    "WHERE h.id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new HoaDonDTO(
                    rs.getInt("id"),
                    rs.getInt("id_nhanVien"),
                    rs.getInt("id_banAn"),
                    rs.getString("tenKhach"),
                    rs.getInt("tongTien"),
                    rs.getTimestamp("thoigian"),
                    rs.getString("ghiChu")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
