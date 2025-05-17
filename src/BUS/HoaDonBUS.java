package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;
import java.util.ArrayList;

public class HoaDonBUS {
    private ArrayList<HoaDonDTO> hoaDonList;
    private HoaDonDAO hoaDonDAO;

    public HoaDonBUS() {
        hoaDonDAO = new HoaDonDAO();
        loadData();
    }

    public void loadData() {
        hoaDonList = hoaDonDAO.selectAll();
    }

    public ArrayList<HoaDonDTO> getAll() {
        return hoaDonList;
    }

    public boolean add(HoaDonDTO hd) {
        for (HoaDonDTO existing : hoaDonList) {
            if (existing.getId() == hd.getId()) {
                System.out.println("❌ ID hoá đơn đã tồn tại!");
                return false;
            }
        }

        int result = hoaDonDAO.insert(hd);
        if (result > 0) {
            loadData();
            System.out.println("✅ Thêm hoá đơn thành công!");
            return true;
        } else {
            System.out.println("❌ Thêm hoá đơn thất bại!");
            return false;
        }
    }

    public boolean update(HoaDonDTO hd) {
        boolean found = false;
        for (HoaDonDTO existing : hoaDonList) {
            if (existing.getId() == hd.getId()) {
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("❌ Không tìm thấy hoá đơn để cập nhật!");
            return false;
        }

        int result = hoaDonDAO.update(hd);
        if (result > 0) {
            loadData();
            System.out.println("✅ Cập nhật hoá đơn thành công!");
            return true;
        } else {
            System.out.println("❌ Cập nhật hoá đơn thất bại!");
            return false;
        }
    }

    public boolean delete(int id) {
        boolean found = false;
        for (HoaDonDTO existing : hoaDonList) {
            if (existing.getId() == id) {
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("❌ Không tìm thấy hoá đơn để xoá!");
            return false;
        }

        int result = hoaDonDAO.delete(id);
        if (result > 0) {
            loadData();
            System.out.println("✅ Xoá hoá đơn thành công!");
            return true;
        } else {
            System.out.println("❌ Xoá hoá đơn thất bại!");
            return false;
        }
    }
}
