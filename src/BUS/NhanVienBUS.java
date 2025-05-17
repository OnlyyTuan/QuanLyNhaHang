package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import helper.Validator;
import java.util.ArrayList;

public class NhanVienBUS {

    private ArrayList<NhanVienDTO> listNhanVien;
    private NhanVienDAO daoNhanVien;

    public NhanVienBUS() {
        daoNhanVien = NhanVienDAO.getInstance();
        listNhanVien = daoNhanVien.selectAll();
    }

    public ArrayList<NhanVienDTO> getListNhanVien() {
        return listNhanVien;
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

        int newId = daoNhanVien.getAutoIncrement();
        nv.setId(newId);

        int res = daoNhanVien.insert(nv);
        if (res != 0) {
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

    public Result delete(int id) {
        NhanVienDTO existing = getById(id);
        if (existing == null) {
            return new Result(false, "Không tìm thấy nhân viên để xóa");
        }

        int res = daoNhanVien.delete(id);
        if (res != 0) {
            listNhanVien.removeIf(nv -> nv.getId() == id);
            return new Result(true, "Xóa nhân viên thành công");
        }
        return new Result(false, "Xóa nhân viên thất bại");
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
