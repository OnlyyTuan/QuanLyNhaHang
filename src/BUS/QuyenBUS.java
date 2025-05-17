package BUS;

import DAO.QuyenDAO;
import DTO.QuyenDTO;
import helper.Validator;  
import java.util.ArrayList; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuyenBUS {

    private Connection conn;
    private ArrayList<QuyenDTO> listQuyen;
    private QuyenDAO daoQuyen;

    public QuyenBUS(Connection conn) {
        this.conn = conn;
        daoQuyen = QuyenDAO.getInstance();
        listQuyen = daoQuyen.selectAll();
    }

    public List<QuyenDTO> getListQuyen() {
        List<QuyenDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM quyen";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new QuyenDTO(
                    rs.getInt("id"),
                    rs.getString("ten")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
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

        int res = daoQuyen.insert(q);
        if (res != 0) {
            // Get the newly inserted role with its auto-generated ID
            QuyenDTO newQuyen = daoQuyen.selectById(String.valueOf(daoQuyen.getAutoIncrement() - 1));
            if (newQuyen != null) {
                listQuyen.add(newQuyen);
                return new Result(true, "Thêm quyền thành công");
            }
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

    public boolean delete(int id) {
        try {
            return daoQuyen.delete(id) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
