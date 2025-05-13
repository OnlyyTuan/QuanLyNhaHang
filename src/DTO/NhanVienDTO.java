package DTO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MSI
 */
public class NhanVienDTO {
    private int id;
    private String hoTen;
    private String gioiTinh;
    private String chucVu;
    private int trangThai;
    
    public NhanVienDTO(int id, String hoTen, String gioiTinh, String chucVu, int trangThai){
        this.id=id;
        this.hoTen=hoTen;
        this.gioiTinh=gioiTinh;
        this.chucVu=chucVu;
        this.trangThai=trangThai;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id=id;
    }
    
    public String getHoTen(){
        return hoTen;
    }
    
    public void setHoTen(String hoTen){
        this.hoTen=hoTen;
    }
    
    public String getGioiTinh(){
        return gioiTinh;
    }
  
    public void setGioiTinh(String gioiTinh){
        this.gioiTinh=gioiTinh;
    }
    
    public String getChucVu(){
        return chucVu;
    }
    
    public void setChucVu(String chucVu){
        this.chucVu=chucVu;
    }
    
    public int getTrangThai(){
        return trangThai;
    }
    
    public void setTrangThai(int trangThai){
        this.trangThai=trangThai;
    }
    
    @Override
    public String toString(){
        return "NhanVienDTO{" + "id=" + id + ", hoTen=" + hoTen + ", gioiTinh=" + gioiTinh + ", chucVu=" + chucVu + ", trangThai=" + trangThai + '}';
    }
}
