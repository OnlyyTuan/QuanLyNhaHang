package BUS;

import DAO.CTQuyenDAO;
import DTO.CTQuyenDTO;
import java.util.ArrayList;

public class CTQuyenBUS {
    private ArrayList<CTQuyenDTO> ctqList;
    private CTQuyenDAO ctqDAO;

    public CTQuyenBUS(){
        ctqDAO = new CTQuyenDAO();
        ctqList = ctqDAO.selectAll();
    }

    public ArrayList<CTQuyenDTO> getAll(){
        return this.ctqList;
    }

    public ArrayList<CTQuyenDTO> getAllByQuyenId(int idQuyen){
        ArrayList<CTQuyenDTO> result = new ArrayList<>();
        for(CTQuyenDTO i : this.ctqList){
            if(i.getIdQuyen() == idQuyen)
                result.add(i);
        }
        return result;
    }
}
