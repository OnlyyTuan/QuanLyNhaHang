/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.BanAnDAO;
import DTO.BanAnDTO;
import helper.Validator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class BanAnBUS {
    
    private Connection conn;
    private ArrayList<BanAnDTO> list_banAn;
    private BanAnDAO dao_banAn;

    public BanAnBUS() {}
    
    
    public BanAnBUS(Connection conn) {
        this.conn = conn;
        dao_banAn = new BanAnDAO();
        list_banAn = dao_banAn.selectAll();
    }
    
    public List<BanAnDTO> getList_banAn() {
        // Refresh the list from database
        list_banAn = dao_banAn.selectAll();
        return list_banAn;
    }

    public BanAnDAO getDao_banAn() {
        return dao_banAn;
    }

    public Result add(BanAnDTO newBanAn) {
        //Xet dieu kien ten
        if(Validator.isEmpty(newBanAn.getTen())) {
            return new Result(false, "Tên bàn ăn không được để trống");
        }
        
        for(BanAnDTO i : list_banAn) {
            if(i.getTen().equals(newBanAn.getTen()))
                return new Result(false, "Tên bàn ăn đã tồn tại");
        }

        if (newBanAn.getTrangThai() < 0 || newBanAn.getTrangThai() >1) {
            return new Result(false, "Trạng thái bàn ăn không hợp lệ");
        }
        
        if (dao_banAn.insert(newBanAn) != 0) {
            // Refresh the list after adding
            list_banAn = dao_banAn.selectAll();
            return new Result(true, "Thêm bàn ăn mới thành công");
        }
        
        return null;
    }

    public int getIndexById(int id) {
        for(int i=0; i<this.list_banAn.size(); i++) {
            if(this.list_banAn.get(i).getId() == id)
                return i;
        }
        return -1;
    }
    
    public Result update(BanAnDTO banAn) {
        if(dao_banAn.update(banAn) != 0) {
            // Refresh the list after updating
            list_banAn = dao_banAn.selectAll();
            return new Result(true, "Cập nhật bàn ăn thành công");
        }
        return new Result(false, "Cập nhật bàn ăn thất bại");
    }

    public BanAnDTO getByID(int id) {
        // Refresh the list before getting by ID
        list_banAn = dao_banAn.selectAll();
        for(BanAnDTO i : list_banAn) {
            if(i.getId() == id)
                return i;
        }
        return null;
    }

    //tim kiem theo ten
    public ArrayList<BanAnDTO> searchByName(String t) {
        ArrayList<BanAnDTO> result = new ArrayList<>();
        String searchKey = t.toLowerCase().trim();
        
        for (BanAnDTO banAn : list_banAn) {
            if (banAn.getTen().toLowerCase().contains(searchKey)) {
                result.add(banAn);
            }
        }
        return result;
    }

    public ArrayList<BanAnDTO> AvailableTable(){
        ArrayList<BanAnDTO> result = new ArrayList<>();
        for(BanAnDTO i : list_banAn){
            if(i.getTrangThai() == 0)
                result.add(i);
        }
        return result;
    }

    public ArrayList<BanAnDTO> busyTable(){
        ArrayList<BanAnDTO> result = new ArrayList<>();
        for(BanAnDTO i : list_banAn){
            if(i.getTrangThai() == 1)
                result.add(i);
        }
        return result;
    }

    public Result delete(BanAnDTO banAn){
        if(dao_banAn.delete(banAn.getId()) != 0){
        list_banAn.remove(banAn);
        return new Result(true,"Đã xóa thành công");   
    }
    return new Result(false,"Xóa thất bại");
    
    }

    public boolean delete(int id) {
        try {
            // Tìm bàn cần xóa
            BanAnDTO banAn = list_banAn.stream()
                .filter(ban -> ban.getId() == id)
                .findFirst()
                .orElse(null);

            if (banAn == null) {
                return false;
            }

            // Thực hiện xóa
            if (dao_banAn.delete(banAn.getId()) > 0) {
                list_banAn.remove(banAn);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}