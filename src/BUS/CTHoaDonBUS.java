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

    public CTHoaDonDTO getByID(int id) {
        for(CTHoaDonDTO cthd : cthdList) {
            if(cthd.getId() == id) {
                return cthd;
            }
        }
        return null;
    }
    
    public ArrayList<CTHoaDonDTO> getByHoaDonID(int hdId) {
        ArrayList<CTHoaDonDTO> result = new ArrayList<>();
        for(CTHoaDonDTO cthd : cthdList) {
            if(cthd.getIdHoaDon() == hdId) {
                result.add(cthd);
            }
        }
        return result;
    }

    
}
