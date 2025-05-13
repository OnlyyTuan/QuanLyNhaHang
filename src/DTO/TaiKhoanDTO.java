package DTO;

public class TaiKhoanDTO {
    private int id;
    private int idNhanVien;
    private int idQuyen;
    private String tenTaiKhoan;
    private String matKhau;
    private int trangThai;

    public TaiKhoanDTO(int id, int idNhanVien, int idQuyen, String tenTaiKhoan, String matKhau, int trangThai) {
        this.id = id;
        this.idNhanVien = idNhanVien;
        this.idQuyen = idQuyen;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public int getIdQuyen() {
        return idQuyen;
    }

    public void setIdQuyen(int idQuyen) {
        this.idQuyen = idQuyen;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "TaiKhoanDTO{" + "id=" + id + ", idNhanVien=" + idNhanVien + ", idQuyen=" + idQuyen + ", tenTaiKhoan=" + tenTaiKhoan + ", matKhau=" + matKhau + ", trangThai=" + trangThai + '}';
    }
    
}