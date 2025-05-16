package BUS;

import DAO.QuyenDAO;
import DTO.QuyenDTO;
import helper.Validator;  
import java.util.ArrayList;

public class QuyenBUS {

    private ArrayList<QuyenDTO> listQuyen;
    private QuyenDAO daoQuyen;

    public QuyenBUS() {
        daoQuyen = QuyenDAO.getInstance();
        listQuyen = daoQuyen.selectAll();
    }

    public ArrayList<QuyenDTO> getListQuyen() {
        return listQuyen;
    }

    public QuyenDTO getById(int id) {
        for (QuyenDTO q : listQuyen) {
            if (q.getId() == id) {
                return q;
            }
        }
        return null;
    }

    public Result add(QuyenDTO q) {
        if (Validator.isEmpty(q.getTen())) {
            return new Result(false, "Tên quyền không được để trống");
        }

        int newId = daoQuyen.getAutoIncrement();
        q.setId(newId);

        int res = daoQuyen.insert(q);
        if (res != 0) {
            listQuyen.add(q);
            return new Result(true, "Thêm quyền thành công");
        }
        return new Result(false, "Thêm quyền thất bại");
    }

    public Result update(QuyenDTO q) {
        QuyenDTO existing = getById(q.getId());
        if (existing == null) {
            return new Result(false, "Không tìm thấy quyền để cập nhật");
        }

        if (Validator.isEmpty(q.getTen())) {
            return new Result(false, "Tên quyền không được để trống");
        }

        int res = daoQuyen.update(q);
        if (res != 0) {
            // Cập nhật trong bộ nhớ
            for (int i = 0; i < listQuyen.size(); i++) {
                if (listQuyen.get(i).getId() == q.getId()) {
                    listQuyen.set(i, q);
                    break;
                }
            }
            return new Result(true, "Cập nhật quyền thành công");
        }
        return new Result(false, "Cập nhật quyền thất bại");
    }

    public Result delete(int id) {
        QuyenDTO existing = getById(id);
        if (existing == null) {
            return new Result(false, "Không tìm thấy quyền để xóa");
        }

        int res = daoQuyen.delete(id);
        if (res != 0) {
            listQuyen.removeIf(q -> q.getId() == id);
            return new Result(true, "Xóa quyền thành công");
        }
        return new Result(false, "Xóa quyền thất bại");
    }

    public ArrayList<QuyenDTO> searchByName(String keyword) {
        ArrayList<QuyenDTO> result = new ArrayList<>();
        String key = keyword.toLowerCase().trim();
        for (QuyenDTO q : listQuyen) {
            if (q.getTen().toLowerCase().contains(key)) {
                result.add(q);
            }
        }
        return result;
    }
}
