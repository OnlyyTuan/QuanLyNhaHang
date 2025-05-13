package DTO;

import java.sql.Timestamp;


public class HoaDonDTO {
    private int id;
    private int idBanAn;
    private int idNhanVien;
    private String tenKhach;
    private int tongTien;
    private Timestamp thoiGian;
    private String ghiChu;

    public HoaDonDTO(int id, int idBanAn, int idNhanVien, String tenKhach, int tongTien, Timestamp thoiGian, String ghiChu) {
        this.id = id;
        this.idBanAn = idBanAn;
        this.idNhanVien = idNhanVien;
        this.tenKhach = tenKhach;
        this.tongTien = tongTien;
        this.thoiGian = thoiGian;
        this.ghiChu = ghiChu;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBanAn() {
        return idBanAn;
    }

    public void setIdBanAn(int idBanAn) {
        this.idBanAn = idBanAn;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getTenKhach() {
        return tenKhach;
    }

    public void setTenKhach(String tenKhach) {
        this.tenKhach = tenKhach;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public Timestamp getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Timestamp thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "id=" + id + ", idBanAn=" + idBanAn + ", idNhanVien=" + idNhanVien + ", tenKhach=" + tenKhach + ", tongTien=" + tongTien + ", thoiGian=" + thoiGian + ", ghiChu=" + ghiChu + '}';
    }
}
