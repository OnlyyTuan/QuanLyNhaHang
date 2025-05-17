package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;
import java.util.ArrayList;

public class HoaDonBUS {
    private ArrayList<HoaDonDTO> listHoaDon;
    private HoaDonDAO hoaDonDAO;

    public HoaDonBUS() {
        hoaDonDAO = new HoaDonDAO();
        listHoaDon = hoaDonDAO.getAllHoaDon();
    }

    // Lấy danh sách hóa đơn đã load
    public ArrayList<HoaDonDTO> getListHoaDon() {
        return listHoaDon;
    }

    // Thêm hóa đơn mới
    public boolean addHoaDon(HoaDonDTO hd) {
        if (hoaDonDAO.insertHoaDon(hd)) {
            listHoaDon.add(hd);
            return true;
        }
        return false;
    }

    // Xóa hóa đơn theo mã hóa đơn
    public boolean deleteHoaDon(String maHoaDon) {
        if (hoaDonDAO.deleteHoaDon(maHoaDon)) {
            listHoaDon.removeIf(hd -> hd.getMaHoaDon().equals(maHoaDon));
            return true;
        }
        return false;
    }

    // Cập nhật hóa đơn
    public boolean updateHoaDon(HoaDonDTO hd) {
        if (hoaDonDAO.updateHoaDon(hd)) {
            for (int i = 0; i < listHoaDon.size(); i++) {
                if (listHoaDon.get(i).getMaHoaDon().equals(hd.getMaHoaDon())) {
                    listHoaDon.set(i, hd);
                    break;
                }
            }
            return true;
        }
        return false;
    }

    // Tìm hóa đơn theo mã hóa đơn
    public HoaDonDTO getHoaDonByMa(String maHoaDon) {
        for (HoaDonDTO hd : listHoaDon) {
            if (hd.getMaHoaDon().equals(maHoaDon)) {
                return hd;
            }
        }
        return null;
    }

    // Làm mới dữ liệu từ DB
    public void refresh() {
        listHoaDon = hoaDonDAO.getAllHoaDon();
    }
}
