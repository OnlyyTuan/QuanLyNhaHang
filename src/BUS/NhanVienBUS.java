package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import helper.Validator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienBUS {

    private ArrayList<NhanVienDTO> listNhanVien;
    private NhanVienDAO daoNhanVien;
    private Connection conn;

    public NhanVienBUS(Connection conn) {
        this.conn = conn;
        daoNhanVien = new NhanVienDAO();
        listNhanVien = daoNhanVien.selectAll();
    }

    public List<NhanVienDTO> getListNhanVien() {
        List<NhanVienDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public NhanVienDTO getById(int id) {
        for (NhanVienDTO nv : listNhanVien) {
            if (nv.getId() == id) {
                return nv;
            }
        }
        return null;
    }

    public Result add(NhanVienDTO nv) {
        if (Validator.isEmpty(nv.getHoTen())) {
            return new Result(false, "Tên nhân viên không được để trống");
        }

        if (nv.getTrangThai() < 0 || nv.getTrangThai() > 1) {
            return new Result(false, "Trạng thái nhân viên không hợp lệ");
        }

        int res = daoNhanVien.insert(nv);
        if (res != 0) {
            // Get the newly inserted ID
            int newId = daoNhanVien.getAutoIncrement() - 1;
            nv.setId(newId);
            listNhanVien.add(nv);
            return new Result(true, "Thêm nhân viên thành công");
        }

        return new Result(false, "Thêm nhân viên thất bại");
    }

    public Result update(NhanVienDTO nv) {
        NhanVienDTO existing = getById(nv.getId());
        if (existing == null) {
            return new Result(false, "Không tìm thấy nhân viên để cập nhật");
        }

        if (Validator.isEmpty(nv.getHoTen())) {
            return new Result(false, "Tên nhân viên không được để trống");
        }

        if (nv.getTrangThai() < 0 || nv.getTrangThai() > 1) {
            return new Result(false, "Trạng thái nhân viên không hợp lệ");
        }

        int res = daoNhanVien.update(nv);
        if (res != 0) {
            // Cập nhật trong bộ nhớ
            for (int i = 0; i < listNhanVien.size(); i++) {
                if (listNhanVien.get(i).getId() == nv.getId()) {
                    listNhanVien.set(i, nv);
                    break;
                }
            }
            return new Result(true, "Cập nhật nhân viên thành công");
        }
        return new Result(false, "Cập nhật nhân viên thất bại");
    }

    public boolean delete(int id) {
        try {
            // First delete associated accounts
            String sql = "DELETE FROM taikhoan WHERE id_nhanVien = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
            
            // Then delete the staff member
            return daoNhanVien.delete(id) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<NhanVienDTO> searchByName(String keyword) {
        ArrayList<NhanVienDTO> result = new ArrayList<>();
        String key = keyword.toLowerCase().trim();
        for (NhanVienDTO nv : listNhanVien) {
            if (nv.getHoTen().toLowerCase().contains(key)) {
                result.add(nv);
            }
        }
        return result;
    }
}
