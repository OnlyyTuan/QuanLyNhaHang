package DTO;

public class BanAnDTO {
    private int id;
    private String ten;
    private int trangThai;

    public BanAnDTO(int id, String ten, int trangThai) {
        this.id = id;
        this.ten = ten;
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

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }   
    
    
}

