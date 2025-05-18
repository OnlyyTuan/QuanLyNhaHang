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
        // Refresh the list from database
        listNhanVien = daoNhanVien.selectAll();
        return listNhanVien;
    }

    public NhanVienDTO getById(int id) {
        // Refresh the list before getting by ID
        listNhanVien = daoNhanVien.selectAll();
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
            // Refresh the list after adding
            listNhanVien = daoNhanVien.selectAll();
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
            // Refresh the list after updating
            listNhanVien = daoNhanVien.selectAll();
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
            boolean result = daoNhanVien.delete(id) > 0;
            if (result) {
                // Refresh the list after deleting
                listNhanVien = daoNhanVien.selectAll();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<NhanVienDTO> searchByName(String keyword) {
        // Refresh the list before searching
        listNhanVien = daoNhanVien.selectAll();
        ArrayList<NhanVienDTO> result = new ArrayList<>();
        String key = keyword.toLowerCase().trim();
        for (NhanVienDTO nv : listNhanVien) {
            if (nv.getHoTen().toLowerCase().contains(key)) {
                result.add(nv);
            }
        }
        return result;
    }

    public List<NhanVienDTO> getList_nhanVien() {
        // Refresh the list before getting active staff
        listNhanVien = daoNhanVien.selectAll();
        List<NhanVienDTO> list = new ArrayList<>();
        for (NhanVienDTO nv : listNhanVien) {
            if (nv.getTrangThai() == 1) {
                list.add(nv);
            }
        }
        return list;
    }

    public List<NhanVienDTO> searchByGender(String gender) {
        // Refresh the list before searching
        listNhanVien = daoNhanVien.selectAll();
        List<NhanVienDTO> results = new ArrayList<>();
        
        for (NhanVienDTO nv : listNhanVien) {
            if (nv.getGioiTinh() != null && 
                nv.getGioiTinh().equalsIgnoreCase(gender)) {
                results.add(nv);
            }
        }
        
        return results;
    }

    public List<NhanVienDTO> searchByPosition(String position) {
        // Refresh the list before searching
        listNhanVien = daoNhanVien.selectAll();
        List<NhanVienDTO> results = new ArrayList<>();
        
        for (NhanVienDTO nv : listNhanVien) {
            if (nv.getChucVu() != null && 
                nv.getChucVu().toLowerCase().contains(position.toLowerCase())) {
                results.add(nv);
            }
        }
        
        return results;
    }
}
