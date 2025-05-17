package BUS;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import helper.Validator;  
import java.util.ArrayList;

public class TaiKhoanBUS {
    private ArrayList<TaiKhoanDTO> listTaiKhoan;
    private TaiKhoanDAO daoTaiKhoan;

    public TaiKhoanBUS() {
        daoTaiKhoan = TaiKhoanDAO.getInstance();
        listTaiKhoan = daoTaiKhoan.selectAll();
    }

    public ArrayList<TaiKhoanDTO> getListTaiKhoan() {
        return listTaiKhoan;
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

        int newId = daoTaiKhoan.getAutoIncrement();
        tk.setId(newId);

        int res = daoTaiKhoan.insert(tk);
        if (res != 0) {
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

    public Result delete(int id) {
        TaiKhoanDTO existing = getById(id);
        if (existing == null) {
            return new Result(false, "Không tìm thấy tài khoản để xóa");
        }

        int res = daoTaiKhoan.delete(id);
        if (res != 0) {
            listTaiKhoan.removeIf(tk -> tk.getId() == id);
            return new Result(true, "Xóa tài khoản thành công");
        }
        return new Result(false, "Xóa tài khoản thất bại");
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
}
