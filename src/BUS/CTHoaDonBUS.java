package BUS;

import DAO.CTHoaDonDAO;
import DTO.CTHoaDonDTO;
import java.util.ArrayList;

public class CTHoaDonBUS {
    private ArrayList<CTHoaDonDTO> cthdList;
    private CTHoaDonDAO cthdDAO;

    public CTHoaDonBUS(){
        cthdDAO = new CTHoaDonDAO();
        cthdList = cthdDAO.selectAll();
    }

    public ArrayList<CTHoaDonDTO> getAll(){
        return this.cthdList;
    }

    public ArrayList<CTHoaDonDTO> getAllByID(int hdId){
        return cthdDAO.selectById(hdId);
    }
    
    
}
