/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.BanAnDAO;
import DTO.BanAnDTO;
import helper.Validator;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class BanAnBUS {
    
    private ArrayList<BanAnDTO> list_banAn;
    private BanAnDAO dao_banAn;
    
    public BanAnBUS() {
        dao_banAn = new BanAnDAO();
        list_banAn = dao_banAn.selectAll();
    }
    
    public ArrayList<BanAnDTO> getList_banAn() {
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
        
        if(dao_banAn.insert(newBanAn) != 0) {
            //Lay ID cua phan tu cuoi +1 len de co id cho phan tu moi trong bo nho
            int newID = list_banAn.getLast().getId() + 1;
            newBanAn.setId(newID);
            list_banAn.add(newBanAn);
            return new Result(true, "Thêm bàn ăn mới thành công");
        }
        
        return null;
    }
    
    public Result update(BanAnDTO banAn) {
        BanAnDTO existingTable = getByID(banAn.getId());
        if (existingTable == null) {
            return new Result(false, "Không tìm thấy bàn ăn cần cập nhật");
        }
        if (dao_banAn.update(banAn) != 0) {
            
            for (int i = 0; i < list_banAn.size(); i++) {
                if (list_banAn.get(i).getId() == banAn.getId()) {
                    list_banAn.set(i, banAn);
                    break;
                }
            }
            return new Result(true, "Cập nhật bàn ăn thành công");
        }
    
        return new Result(false, "Cập nhật bàn ăn thất bại");
    }

    public BanAnDTO getByID(int id) {
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
}