package BUS;

import DAO.DanhMucMonAnDAO;
import DTO.DanhMucMonAnDTO;
import java.util.ArrayList;

public class DanhMucMonAnBUS {
    private ArrayList<DanhMucMonAnDTO> danhMucList;
    private DanhMucMonAnDAO danhMucDAO;

    public DanhMucMonAnBUS() {
        danhMucDAO = new DanhMucMonAnDAO();
        loadData();
    }

    public void loadData() {
        danhMucList = danhMucDAO.selectAll();
    }

    public ArrayList<DanhMucMonAnDTO> getAll() {
        return danhMucList;
    }

    public boolean add(DanhMucMonAnDTO dm) {
        for (DanhMucMonAnDTO existing : danhMucList) {
            if (existing.getId() == dm.getId()) {
                System.out.println("❌ Mã danh mục đã tồn tại!");
                return false;
            }
        }

        int result = danhMucDAO.insert(dm);
        if (result > 0) {
            loadData();
            System.out.println("✅ Thêm danh mục thành công!");
            return true;
        } else {
            System.out.println("❌ Thêm danh mục thất bại!");
            return false;
        }
    }

    public boolean update(DanhMucMonAnDTO dm) {
        boolean found = false;
        for (DanhMucMonAnDTO existing : danhMucList) {
            if (existing.getId() == dm.getId()) {
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("❌ Không tìm thấy danh mục để cập nhật!");
            return false;
        }

        int result = danhMucDAO.update(dm);
        if (result > 0) {
            loadData();
            System.out.println("✅ Cập nhật danh mục thành công!");
            return true;
        } else {
            System.out.println("❌ Cập nhật danh mục thất bại!");
            return false;
        }
    }

    public boolean delete(int id) {
        boolean found = false;
        for (DanhMucMonAnDTO existing : danhMucList) {
            if (existing.getId() == id) {
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("❌ Không tìm thấy danh mục để xoá!");
            return false;
        }

        int result = danhMucDAO.delete(id);
        if (result > 0) {
            loadData();
            System.out.println("✅ Xoá danh mục thành công!");
            return true;
        } else {
            System.out.println("❌ Xoá danh mục thất bại!");
            return false;
        }
    }
}
