package DTO;

public class MonAnDTO {
    private int id;
    private String ten;
    private int idDanhMuc;
    private int gia;
    private String hinhAnh;
    private int trangThai;

    public MonAnDTO() {
    }
    
    public MonAnDTO(int id, String ten, int idDanhMuc, int gia, String hinhAnh, int trangThai) {
        this.id = id;
        this.ten = ten;
        this.idDanhMuc = idDanhMuc;
        this.gia = gia;
        this.hinhAnh = hinhAnh;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getIdDanhMuc() {
        return idDanhMuc;
    }

    public void setIdDanhMuc(int idDanhMuc) {
        this.idDanhMuc = idDanhMuc;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
}
