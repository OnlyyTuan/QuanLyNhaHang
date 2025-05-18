package DAO;

import java.sql.*;
import java.util.*;
import DTO.NhanVienDTO;
import config.DBConnector;

public class NhanVienDAO {
    public static NhanVienDAO getInstance(){
        return new NhanVienDAO();
    }

    public ArrayList<NhanVienDTO> selectAll(){
        ArrayList<NhanVienDTO> result = new ArrayList<>();
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM nhanvien";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String hoTen = rs.getString("hoTen");
                String gioiTinh = rs.getString("gioiTinh");
                String chucVu = rs.getString("chucVu");
                int trangThai = rs.getInt("trangThai");
                NhanVienDTO nv = new NhanVienDTO(id,hoTen,gioiTinh,chucVu,trangThai);
                result.add(nv);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int insert(NhanVienDTO nv){
        int result=0;
        try{
            Connection conn=(Connection) DBConnector.getConnection();
            String query = "INSERT INTO nhanvien (hoTen,gioiTinh,chucVu,trangThai) VALUES (?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, nv.getHoTen());
            pst.setString(2, nv.getGioiTinh());
            pst.setString(3, nv.getChucVu());
            pst.setInt(4, nv.getTrangThai());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public NhanVienDTO selectById(String t){
        NhanVienDTO nv = null;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT * FROM nhanvien WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String hoTen = rs.getString("hoTen");
                String gioiTinh = rs.getString("gioiTinh");
                String chucVu = rs.getString("chucVu");
                int trangThai = rs.getInt("trangThai");
                nv = new NhanVienDTO(id,hoTen,gioiTinh,chucVu,trangThai);
            }
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return nv;
    }
    
    public int getAutoIncrement(){
        int result = -1;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlynhahang' AND TABLE_NAME = 'nhanvien'";
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
            String query = "DELETE FROM nhanvien WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, id);
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int update(NhanVienDTO nv){
        int result = 0;
        try{
            Connection conn = (Connection) DBConnector.getConnection();
            String query = "UPDATE nhanvien SET hoTen=?,gioiTinh=?,chucVu=?,trangThai=? WHERE id=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(5, nv.getId());
            pst.setString(1, nv.getHoTen());
            pst.setString(2, nv.getGioiTinh());
            pst.setString(3, nv.getChucVu());
            pst.setInt(4, nv.getTrangThai());
            result = pst.executeUpdate();
            DBConnector.closeConnection(conn);            
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public List<NhanVienDTO> searchByName(String name) {
        List<NhanVienDTO> allNhanVien = getListNhanVien();
        List<NhanVienDTO> results = new ArrayList<>();
        
        for (NhanVienDTO nv : allNhanVien) {
            if (nv.getHoTen() != null && 
                nv.getHoTen().toLowerCase().contains(name.toLowerCase())) {
                results.add(nv);
            }
        }
        
        return results;
    }

    public List<NhanVienDTO> getListNhanVien() {
        List<NhanVienDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien";
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new NhanVienDTO(
                    rs.getInt("id"),
                    rs.getString("hoTen"),
                    rs.getString("gioiTinh"),
                    rs.getString("chucVu"),
                    rs.getInt("trangThai")
                ));
            }
            DBConnector.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public NhanVienDTO getById(int id) {
        String sql = "SELECT * FROM nhanvien WHERE id = ?";
        try {
            Connection conn = (Connection) DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new NhanVienDTO(
                    rs.getInt("id"),
                    rs.getString("hoTen"),
                    rs.getString("gioiTinh"),
                    rs.getString("chucVu"),
                    rs.getInt("trangThai")
                );
            }
            DBConnector.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

