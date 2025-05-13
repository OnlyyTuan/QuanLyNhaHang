package DTO;

public class CTHoaDonDTO {
    private int id;
    private int idHoaDon;
    private int idMonAn;
    private int soLuong;
    private int tongGia;

    public CTHoaDonDTO(int id, int idHoaDon, int idMonAn, int soLuong, int tongGia) {
        this.id = id;
        this.idHoaDon = idHoaDon;
        this.idMonAn = idMonAn;
        this.soLuong = soLuong;
        this.tongGia = tongGia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getIdMonAn() {
        return idMonAn;
    }

    public void setIdMonAn(int idMonAn) {
        this.idMonAn = idMonAn;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTongGia() {
        return tongGia;
    }

    public void setTongGia(int tongGia) {
        this.tongGia = tongGia;
    }

    @Override
    public String toString() {
        return "CTHoaDonDTO{" + "id=" + id + ", idHoaDon=" + idHoaDon + ", idMonAn=" + idMonAn + ", soLuong=" + soLuong + ", tongGia=" + tongGia + '}';
    }
}
