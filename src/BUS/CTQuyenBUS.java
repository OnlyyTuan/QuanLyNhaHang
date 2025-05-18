package BUS;

import DAO.CTQuyenDAO;
import DTO.CTQuyenDTO;
import java.util.ArrayList;

public class CTQuyenBUS {
    private ArrayList<CTQuyenDTO> ctqList;
    private CTQuyenDAO ctqDAO;

    public CTQuyenBUS(){
        ctqDAO = CTQuyenDAO.getInstance();
        ctqList = ctqDAO.selectAll();
    }

    public ArrayList<CTQuyenDTO> getAll(){
        return this.ctqList;
    }

    public ArrayList<CTQuyenDTO> getAllByQuyenId(int idQuyen){
        ArrayList<CTQuyenDTO> result = new ArrayList<>();
        // Refresh the list from database
        ctqList = ctqDAO.selectAll();
        for(CTQuyenDTO i : this.ctqList){
            if(i.getIdQuyen() == idQuyen)
                result.add(i);
        }
        return result;
    }

    public boolean add(CTQuyenDTO ctq) {
        int result = ctqDAO.insert(ctq);
        if (result > 0) {
            // Refresh the list from database
            ctqList = ctqDAO.selectAll();
            return true;
        }
        return false;
    }

    public boolean delete(int idQuyen) {
        int result = ctqDAO.delete(idQuyen);
        if (result > 0) {
            // Refresh the list from database
            ctqList = ctqDAO.selectAll();
            return true;
        }
        return false;
    }
}
