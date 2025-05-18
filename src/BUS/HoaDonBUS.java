package BUS;

import DTO.HoaDonDTO;
import DAO.HoaDonDAO;
import helper.Validator;
import BUS.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoaDonBUS {
    private Connection conn;
    private ArrayList<HoaDonDTO> listHoaDon;
    private HoaDonDAO daoHoaDon;

    public HoaDonBUS(Connection conn) {
        this.conn = conn;
        daoHoaDon = new HoaDonDAO(conn);
        listHoaDon = daoHoaDon.selectAll();
    }

    public List<HoaDonDTO> getList_hoaDon() {
        // Refresh the list from database
        listHoaDon = daoHoaDon.selectAll();
        return listHoaDon;
    }

    public List<HoaDonDTO> searchByCustomerName(String customerName) {
        List<HoaDonDTO> allHoaDon = daoHoaDon.getList_hoaDon();
        List<HoaDonDTO> results = new ArrayList<>();
        
        for (HoaDonDTO hoaDon : allHoaDon) {
            if (hoaDon.getTenKhach() != null && 
                hoaDon.getTenKhach().toLowerCase().contains(customerName.toLowerCase())) {
                results.add(hoaDon);
            }
        }
        
        return results;
    }

    public List<HoaDonDTO> searchByTableId(int tableId) {
        List<HoaDonDTO> allHoaDon = daoHoaDon.getList_hoaDon();
        List<HoaDonDTO> results = new ArrayList<>();
        
        for (HoaDonDTO hoaDon : allHoaDon) {
            if (hoaDon.getIdBanAn() == tableId) {
                results.add(hoaDon);
            }
        }
        
        return results;
    }

    public HoaDonDTO getHoaDonByBanAn(int idBanAn) {
        return daoHoaDon.getHoaDonByBanAn(idBanAn);
    }

    public List<HoaDonDTO> getLichSuHoaDonByBanAn(int idBanAn) {
        return daoHoaDon.getLichSuHoaDonByBanAn(idBanAn);
    }

    public Result add(HoaDonDTO hd) {
        if (Validator.isEmpty(hd.getTenKhach())) {
            return new Result(false, "Tên khách hàng không được để trống");
        }

        if (hd.getTongTien() <= 0) {
            return new Result(false, "Tổng tiền phải lớn hơn 0");
        }

        int res = daoHoaDon.insert(hd);
        if (res != 0) {
            // Refresh the list after adding
            listHoaDon = daoHoaDon.selectAll();
            return new Result(true, "Thêm hóa đơn thành công");
        }
        return new Result(false, "Thêm hóa đơn thất bại");
    }

    public boolean delete(int id) {
        try {
            return daoHoaDon.delete(id) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public HoaDonDTO getByID(int id) {
        for (HoaDonDTO hd : listHoaDon) {
            if (hd.getId() == id) {
                return hd;
            }
        }
        return null;
    }

    public Result update(HoaDonDTO hoaDon) {
        if (Validator.isEmpty(hoaDon.getTenKhach())) {
            return new Result(false, "Tên khách hàng không được để trống");
        }

        if (hoaDon.getTongTien() <= 0) {
            return new Result(false, "Tổng tiền phải lớn hơn 0");
        }

        int res = daoHoaDon.update(hoaDon);
        if (res != 0) {
            // Update in memory list
            for (int i = 0; i < listHoaDon.size(); i++) {
                if (listHoaDon.get(i).getId() == hoaDon.getId()) {
                    listHoaDon.set(i, hoaDon);
                    break;
                }
            }
            return new Result(true, "Cập nhật hóa đơn thành công");
        }
        return new Result(false, "Cập nhật hóa đơn thất bại");
    }
} 