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
import java.sql.Connection;

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
        List<TaiKhoanDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM taikhoan";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new TaiKhoanDTO(
                    rs.getInt("id"),
                    rs.getInt("id_nhanVien"),
                    rs.getInt("id_quyen"),
                    rs.getString("taiKhoan"),
                    rs.getString("matKhau"),
                    rs.getInt("trangThai")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
        
    }

    public TaiKhoanDTO getById(int id) {
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
            listTaiKhoan.add(tk);
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
            // Cập nhật trong bộ nhớ
            for (int i = 0; i < listTaiKhoan.size(); i++) {
                if (listTaiKhoan.get(i).getId() == tk.getId()) {
                    listTaiKhoan.set(i, tk);
                    break;
                }
            }
            return new Result(true, "Cập nhật tài khoản thành công");
        }
        return new Result(false, "Cập nhật tài khoản thất bại");
    }

    public boolean delete(int id) {
        try {
            return daoTaiKhoan.delete(id) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<TaiKhoanDTO> searchByUsername(String keyword) {
        ArrayList<TaiKhoanDTO> result = new ArrayList<>();
        String key = keyword.toLowerCase().trim();
        for (TaiKhoanDTO tk : listTaiKhoan) {
            if (tk.getTenTaiKhoan().toLowerCase().contains(key)) {
                result.add(tk);
            }
        }
        return result;
    }

    public TaiKhoanDTO dangNhap(String taiKhoan, String matKhau) {
        String sql = "SELECT * FROM taikhoan WHERE taiKhoan = ? AND matKhau = ? AND trangThai = 1";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, taiKhoan);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new TaiKhoanDTO(
                    rs.getInt("id"),
                    rs.getInt("id_nhanVien"),
                    rs.getInt("id_quyen"),
                    rs.getString("taiKhoan"),
                    rs.getString("matKhau"),
                    rs.getInt("trangThai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}