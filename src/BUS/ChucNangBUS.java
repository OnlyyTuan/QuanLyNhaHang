package BUS;

import DAO.ChucNangDAO;
import DTO.ChucNangDTO;
import helper.*;
import java.util.ArrayList;

public class ChucNangBUS {
    private ArrayList<ChucNangDTO> cnList;
    private ChucNangDAO cnDao;
    
    public ChucNangBUS(){
        cnDao = new ChucNangDAO();
        cnList= cnDao.selectAll();
    }

    public ArrayList<ChucNangDTO> getcnList(){
        return cnList;
    }

    public ChucNangDTO getByID(int id){
        for(ChucNangDTO i: cnList){
            if(i.getId() ==id)
            return i;
        }
        return null;
    }
    
    public String getNameByID(int id) {
        for (ChucNangDTO cn : cnList) {
            if (cn.getId() == id) {
                return cn.getTen();
            }
        }
        return null;
    }
}
