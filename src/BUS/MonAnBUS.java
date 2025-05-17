package BUS;

import DAO.MonAnDAO;
import DTO.MonAnDTO;
import java.util.ArrayList;

public class MonAnBUS {
    private ArrayList<MonAnDTO> monAnList;
    private MonAnDAO monAnDAO;

    public MonAnBUS() {
        monAnDAO = new MonAnDAO();
        loadData();
    }

    public void loadData() {
        monAnList = monAnDAO.selectAll();
    }

    public ArrayList<MonAnDTO> getAll() {
        return monAnList;
    }

    public boolean add(MonAnDTO monAn) {
        for (MonAnDTO existing : monAnList) {
            if (existing.getId() == monAn.getId()) {
                System.out.println("❌ Món ăn với ID này đã tồn tại!");
                return false;
            }
        }

        int result = monAnDAO.insert(monAn);
        if (result > 0) {
            loadData();
            System.out.println("✅ Thêm món ăn thành công!");
            return true;
        } else {
            System.out.println("❌ Thêm món ăn thất bại!");
            return false;
        }
    }

    public boolean update(MonAnDTO monAn) {
        boolean found = false;
        for (MonAnDTO existing : monAnList) {
            if (existing.getId() == monAn.getId()) {
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("❌ Không tìm thấy món ăn để cập nhật!");
            return false;
        }

        int result = monAnDAO.update(monAn);
        if (result > 0) {
            loadData();
            System.out.println("✅ Cập nhật món ăn thành công!");
            return true;
        } else {
            System.out.println("❌ Cập nhật món ăn thất bại!");
            return false;
        }
    }

    public boolean delete(int id) {
        boolean found = false;
        for (MonAnDTO existing : monAnList) {
            if (existing.getId() == id) {
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("❌ Không tìm thấy món ăn để xoá!");
            return false;
        }

        int result = monAnDAO.delete(id);
        if (result > 0) {
            loadData();
            System.out.println("✅ Xoá món ăn thành công!");
            return true;
        } else {
            System.out.println("❌ Xoá món ăn thất bại!");
            return false;
        }
    }
}
