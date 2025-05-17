package BUS;

import DTO.HoaDonDTO;
import DAO.HoaDonDAO;
import helper.Validator;
import BUS.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoaDonBUS {
    private Connection conn;
    private ArrayList<HoaDonDTO> listHoaDon;
    private HoaDonDAO daoHoaDon;

    public HoaDonBUS(Connection conn) {
        this.conn = conn;
        daoHoaDon = new HoaDonDAO();
        listHoaDon = daoHoaDon.selectAll();
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

    public Result add(HoaDonDTO hd) {
        if (Validator.isEmpty(hd.getTenKhach())) {
            return new Result(false, "Tên khách hàng không được để trống");
        }

        if (hd.getTongTien() <= 0) {
            return new Result(false, "Tổng tiền phải lớn hơn 0");
        }

        int res = daoHoaDon.insert(hd);
        if (res != 0) {
            listHoaDon.add(hd);
            return new Result(true, "Thêm hóa đơn thành công");
        }
        return new Result(false, "Thêm hóa đơn thất bại");
    }

    public boolean delete(int id) {
        try {
            return daoHoaDon.delete(id) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
} 