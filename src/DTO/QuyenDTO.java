package DTO;

public class QuyenDTO {
    private int id;
    private String ten;

    public QuyenDTO() {
    }
    public QuyenDTO(int id, String ten) {
        this.id = id;
        this.ten = ten;
        
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


    @Override
    public String toString() {
        return "QuyenDTO{" + "id=" + id + ", ten=" + ten + '}';
    }
    
}