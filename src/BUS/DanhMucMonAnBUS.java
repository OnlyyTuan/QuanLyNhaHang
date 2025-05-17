package BUS;

import DAO.DanhMucMonAnDAO;
import DTO.DanhMucMonAnDTO;
import java.util.ArrayList;

public class DanhMucMonAnBUS {

    private ArrayList<DanhMucMonAnDTO> danhMucMonAnList;
    private DanhMucMonAnDAO danhMucMonAnDAO;

    public DanhMucMonAnBUS() {
        danhMucMonAnDAO = new DanhMucMonAnDAO();
        danhMucMonAnList = danhMucMonAnDAO.getAllDanhMuc();
    }

    // Lấy tất cả danh mục (dữ liệu lấy từ ArrayList đã load)
    public ArrayList<DanhMucMonAnDTO> getDanhMucMonAnList() {
        return danhMucMonAnList;
    }

    // Thêm danh mục mới
    public boolean addDanhMuc(DanhMucMonAnDTO dm) {
        if (danhMucMonAnDAO.insertDanhMuc(dm)) {
            danhMucMonAnList.add(dm);
            return true;
        }
        return false;
    }

    // Xóa danh mục
    public boolean deleteDanhMuc(String maDanhMuc) {
        if (danhMucMonAnDAO.deleteDanhMuc(maDanhMuc)) {
            danhMucMonAnList.removeIf(dm -> dm.getMaDanhMuc().equals(maDanhMuc));
            return true;
        }
        return false;
    }

    // Cập nhật danh mục
    public boolean updateDanhMuc(DanhMucMonAnDTO dm) {
        if (danhMucMonAnDAO.updateDanhMuc(dm)) {
            for (int i = 0; i < danhMucMonAnList.size(); i++) {
                if (danhMucMonAnList.get(i).getMaDanhMuc().equals(dm.getMaDanhMuc())) {
                    danhMucMonAnList.set(i, dm);
                    break;
                }
            }
            return true;
        }
        return false;
    }

    // Tìm danh mục theo mã
    public DanhMucMonAnDTO getDanhMucByMa(String maDanhMuc) {
        for (DanhMucMonAnDTO dm : danhMucMonAnList) {
            if (dm.getMaDanhMuc().equals(maDanhMuc)) {
                return dm;
            }
        }
        return null;
    }

    // Làm mới dữ liệu từ DB (nếu muốn cập nhật danh sách mới nhất từ DB)
    public void refresh() {
        danhMucMonAnList = danhMucMonAnDAO.getAllDanhMuc();
    }
}
