package BUS;

import DTO.MonAnDTO;
import DAO.MonAnDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import helper.Validator;

public class MonAnBUS {
    private Connection conn;
    private ArrayList<MonAnDTO> list_monAn;
    private MonAnDAO dao_monAn;

    public MonAnBUS(Connection conn) {
        this.conn = conn;
        dao_monAn = new MonAnDAO();
        list_monAn = dao_monAn.selectAll();
    }

    public List<MonAnDTO> getList_monAn() {
        list_monAn = dao_monAn.selectAll();
        return list_monAn;
    }

    public MonAnDAO getDao_monAn() {
        return dao_monAn;
    }

    public Result add(MonAnDTO newMonAn) {
        if(Validator.isEmpty(newMonAn.getTen())) {
            return new Result(false, "Tên món ăn không được để trống");
        }

        for(MonAnDTO i : list_monAn) {
            if(i.getTen().equals(newMonAn.getTen())) {
                return new Result(false, "Tên món ăn đã tồn tại");
            }
        }

        if(newMonAn.getGia() <= 0) {
            return new Result(false, "Giá món ăn phải lớn hơn 0");
        }

        if(newMonAn.getTrangThai() < 0 || newMonAn.getTrangThai() > 1) {
            return new Result(false, "Trạng thái món ăn không hợp lệ");
        }

        if(dao_monAn.insert(newMonAn) != 0) {
            list_monAn = dao_monAn.selectAll();
            return new Result(true, "Thêm món ăn mới thành công");
        }

        return null;
    }

    public int getIndexById(int id) {
        for(int i=0; i<this.list_monAn.size(); i++) {
            if(this.list_monAn.get(i).getId() == id)
                return i;
        }
        return -1;
    }

    public Result update(MonAnDTO monAn) {
        if(dao_monAn.update(monAn) != 0) {
            list_monAn = dao_monAn.selectAll();
            return new Result(true, "Cập nhật món ăn thành công");
        }
        return new Result(false, "Cập nhật món ăn thất bại");
    }

    public MonAnDTO getByID(int id) {
        list_monAn = dao_monAn.selectAll();
        for(MonAnDTO i : list_monAn) {
            if(i.getId() == id)
                return i;
        }
        return null;
    }

    public ArrayList<MonAnDTO> searchByName(String t) {
        ArrayList<MonAnDTO> result = new ArrayList<>();
        String searchKey = t.toLowerCase().trim();
        
        for(MonAnDTO i : list_monAn) {
            if(i.getTen().toLowerCase().contains(searchKey)) {
                result.add(i);
            }
        }
        return result;
    }

    public boolean delete(int id) {
        try {
            boolean result = dao_monAn.delete(id) > 0;
            if (result) {
                list_monAn = dao_monAn.selectAll();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
    
    
    
    
    
