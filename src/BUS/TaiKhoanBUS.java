package BUS;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import helper.Validator;  
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiKhoanBUS {

    private ArrayList<TaiKhoanDTO> listTaiKhoan;
    private TaiKhoanDAO daoTaiKhoan;
    private Connection conn;

    public TaiKhoanBUS(Connection conn) {
        this.conn = conn;
        daoTaiKhoan = new TaiKhoanDAO();
        listTaiKhoan = daoTaiKhoan.selectAll();
    }

    public List<TaiKhoanDTO> getListTaiKhoan() {
        // Refresh the list from database
        listTaiKhoan = daoTaiKhoan.selectAll();
        return listTaiKhoan;
    }

    public TaiKhoanDTO getById(int id) {
        // Refresh the list before getting by ID
        listTaiKhoan = daoTaiKhoan.selectAll();
        for (TaiKhoanDTO tk : listTaiKhoan) {
            if (tk.getId() == id) {
                return tk;
            }
        }
        return null;
    }

    public Result add(TaiKhoanDTO tk) {
        if (Validator.isEmpty(tk.getTenTaiKhoan())) {
            return new Result(false, "Tên tài khoản không được để trống");
        }
        if (Validator.isEmpty(tk.getMatKhau())) {
            return new Result(false, "Mật khẩu không được để trống");
        }

        int newId = daoTaiKhoan.insert(tk);
        if (newId > 0) {
            tk.setId(newId);
            // Refresh the list after adding
            listTaiKhoan = daoTaiKhoan.selectAll();
            return new Result(true, "Thêm tài khoản thành công");
        }
        return new Result(false, "Thêm tài khoản thất bại");
    }

    public Result update(TaiKhoanDTO tk) {
        TaiKhoanDTO existing = getById(tk.getId());
        if (existing == null) {
            return new Result(false, "Không tìm thấy tài khoản để cập nhật");
        }

        if (Validator.isEmpty(tk.getTenTaiKhoan())) {
            return new Result(false, "Tên tài khoản không được để trống");
        }
        if (Validator.isEmpty(tk.getMatKhau())) {
            return new Result(false, "Mật khẩu không được để trống");
        }

        int res = daoTaiKhoan.update(tk);
        if (res != 0) {
            // Refresh the list after updating
            listTaiKhoan = daoTaiKhoan.selectAll();
            return new Result(true, "Cập nhật tài khoản thành công");
        }
        return new Result(false, "Cập nhật tài khoản thất bại");
    }

    public boolean delete(int id) {
        try {
            boolean result = daoTaiKhoan.delete(id) > 0;
            if (result) {
                // Refresh the list after deleting
                listTaiKhoan = daoTaiKhoan.selectAll();
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xóa tài khoản: " + e.getMessage());
        }
    }

    public ArrayList<TaiKhoanDTO> searchByUsername(String keyword) {
        // Refresh the list before searching
        listTaiKhoan = daoTaiKhoan.selectAll();
        ArrayList<TaiKhoanDTO> result = new ArrayList<>();
        String key = keyword.toLowerCase().trim();
        for (TaiKhoanDTO tk : listTaiKhoan) {
            if (tk.getTenTaiKhoan().toLowerCase().contains(key)) {
                result.add(tk);
            }
        }
        return result;
    }

    public class LoginResult {
        public final TaiKhoanDTO taiKhoan;
        public final DTO.NhanVienDTO nhanVien;
        
        public LoginResult(TaiKhoanDTO taiKhoan, DTO.NhanVienDTO nhanVien) {
            this.taiKhoan = taiKhoan;
            this.nhanVien = nhanVien;
        }
    }

    public LoginResult dangNhap(String taiKhoan, String matKhau) {
        TaiKhoanDTO tk = null;
        DTO.NhanVienDTO nv = null;
        
        String sql = "SELECT * FROM taikhoan WHERE taiKhoan = ? AND matKhau = ? AND trangThai = 1";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, taiKhoan);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tk = new TaiKhoanDTO(
                    rs.getInt("id"),
                    rs.getInt("id_nhanVien"),
                    rs.getInt("id_quyen"),
                    rs.getString("taiKhoan"),
                    rs.getString("matKhau"),
                    rs.getInt("trangThai")
                );
                nv = getNhanVienFromLogin(taiKhoan, matKhau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new LoginResult(tk, nv);
    }

    public DTO.NhanVienDTO getNhanVienFromLogin(String taiKhoan, String matKhau) {
        String sql = "SELECT nv.* FROM nhanvien nv " +
                    "JOIN taikhoan tk ON nv.id = tk.id_nhanVien " +
                    "WHERE tk.taiKhoan = ? AND tk.matKhau = ? AND tk.trangThai = 1";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, taiKhoan);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new DTO.NhanVienDTO(
                    rs.getInt("id"),
                    rs.getString("hoTen"),
                    rs.getString("gioiTinh"),
                    rs.getString("chucVu"),
                    rs.getInt("trangThai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}