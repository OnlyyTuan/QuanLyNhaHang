package BUS;

import DAO.QuyenDAO;
import DTO.QuyenDTO;
import helper.Validator;  
import java.util.ArrayList; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        // Refresh the list from database
        listQuyen = daoQuyen.selectAll();
        return listQuyen;
    }

    public QuyenDTO getById(int id) {
        // Refresh the list before getting by ID
        listQuyen = daoQuyen.selectAll();
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
            int newId = daoQuyen.getAutoIncrement() - 1;
            QuyenDTO newQuyen = daoQuyen.selectById(String.valueOf(newId));
            if (newQuyen != null) {
                // Refresh the list after adding
                listQuyen = daoQuyen.selectAll();
                return new Result(true, "Thêm quyền thành công", newId);
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
            // Refresh the list after updating
            listQuyen = daoQuyen.selectAll();
            return new Result(true, "Cập nhật quyền thành công");
        }
        return new Result(false, "Cập nhật quyền thất bại");
    }

    public boolean delete(int id) {
        try {
            // Check if the role is being used by any accounts
            String sql = "SELECT COUNT(*) FROM taikhoan WHERE id_quyen = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    throw new RuntimeException("Không thể xóa quyền này vì đang được sử dụng bởi tài khoản");
                }
            }

            // Delete associated permissions from ctquyen table first
            sql = "DELETE FROM chitietquyen WHERE id_quyen = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }

            // Then delete the role
            boolean result = daoQuyen.delete(id) > 0;
            if (result) {
                // Refresh the list after deleting
                listQuyen = daoQuyen.selectAll();
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xóa quyền: " + e.getMessage());
        }
    }

    public ArrayList<QuyenDTO> searchByName(String keyword) {
        // Refresh the list before searching
        listQuyen = daoQuyen.selectAll();
        ArrayList<QuyenDTO> result = new ArrayList<>();
        String key = keyword.toLowerCase().trim();
        for (QuyenDTO q : listQuyen) {
            if (q.getTen().toLowerCase().contains(key)) {
                result.add(q);
            }
        }
        return result;
    }

    public QuyenDTO searchById(int id) {
        // Refresh the list before searching
        listQuyen = daoQuyen.selectAll();
        for (QuyenDTO q : listQuyen) {
            if (q.getId() == id) {
                return q;
            }
        }
        return null;
    }
}
