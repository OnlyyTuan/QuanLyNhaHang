package GUI.Panel;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import config.DBConnector;
import DTO.NhanVienDTO;
import BUS.NhanVienBUS;

/**
 *
 * @author MSI
 */
public class NhanVien extends javax.swing.JPanel {
    private int selectedTableId = -1;
    private NhanVienBUS nhanVienBUS;
    private List<NhanVienDTO> danhSachNhanVienGoc;  // Lưu danh sách gốc để tìm kiếm

    /**
     * Creates new form KhachHang
     */
    public NhanVien() {
        initComponents();
        Connection conn = DBConnector.getConnection();
        nhanVienBUS = new NhanVienBUS(conn);
        loadTableData();
        setupNhanVienSelection();
    }

    private void setupNhanVienSelection() {
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = jTable1.getSelectedRow();
                if (selectedRow != -1) {
                    selectedTableId = (int) jTable1.getValueAt(selectedRow, 0);
                } else {
                    selectedTableId = -1;
                }
            }
        });
    }

    public int getSelectedTableId() {
        return selectedTableId;
    }

    public void loadTableData() {
        Connection conn = DBConnector.getConnection();
        NhanVienBUS nhanVienBUS = new NhanVienBUS(conn);
        danhSachNhanVienGoc = nhanVienBUS.getListNhanVien();  // Lưu danh sách gốc
    
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
    
        for (NhanVienDTO nv : danhSachNhanVienGoc) {
            String trangThai = (nv.getTrangThai() == 1) ? "Đang làm việc" : "Đã nghỉ";
            model.addRow(new Object[]{
                nv.getId(),
                nv.getHoTen(),
                nv.getGioiTinh(),
                nv.getChucVu(),
                trangThai
            });
        }
    }

    public boolean deleteTable(int tableId) {
        if (tableId == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa nhân viên này?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                if (nhanVienBUS.delete(tableId)) {
                    JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!");
                    loadTableData();
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể xóa nhân viên này", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa nhân viên: " + ex.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }

    // Phương thức tìm kiếm dữ liệu trong bảng theo từ khóa và loại tìm kiếm
    public void searchTableData(String keyword, String searchType) {
        if (keyword == null || keyword.trim().isEmpty()) {
            loadTableData();  // Nếu không có từ khóa thì load lại dữ liệu đầy đủ
            return;
        }

        keyword = keyword.toLowerCase().trim();
        List<NhanVienDTO> filteredList = new ArrayList<>();

        for (NhanVienDTO nv : danhSachNhanVienGoc) {
            switch (searchType) {
                case "Tên":
                    if (nv.getHoTen().toLowerCase().contains(keyword)) {
                        filteredList.add(nv);
                    }
                    break;
                case "Chức vụ":
                    if (nv.getChucVu().toLowerCase().contains(keyword)) {
                        filteredList.add(nv);
                    }
                    break;
                case "Giới tính":
                    if (nv.getGioiTinh().toLowerCase().contains(keyword)) {
                        filteredList.add(nv);
                    }
                    break;
                case "Trạng thái":
                    String trangThai = (nv.getTrangThai() == 1) ? "đang làm việc" : "đã nghỉ";
                    if (trangThai.contains(keyword)) {
                        filteredList.add(nv);
                    }
                    break;
                default:
                    // Nếu không có loại tìm kiếm hợp lệ, tìm tất cả các trường trên
                    if (nv.getHoTen().toLowerCase().contains(keyword)
                        || nv.getChucVu().toLowerCase().contains(keyword)
                        || nv.getGioiTinh().toLowerCase().contains(keyword)
                        || ((nv.getTrangThai() == 1 ? "đang làm việc" : "đã nghỉ").contains(keyword))) {
                        filteredList.add(nv);
                    }
                    break;
            }
        }

        // Cập nhật bảng với dữ liệu lọc
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        for (NhanVienDTO nv : filteredList) {
            String trangThai = (nv.getTrangThai() == 1) ? "Đang làm việc" : "Đã nghỉ";
            model.addRow(new Object[]{
                nv.getId(),
                nv.getHoTen(),
                nv.getGioiTinh(),
                nv.getChucVu(),
                trangThai
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerBar1 = new GUI.Component.HeaderBarNV(this);
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(832, 534));
        setLayout(new java.awt.BorderLayout());
        add(headerBar1, java.awt.BorderLayout.PAGE_START);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên", "Giới tính", "Chức vụ", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUI.Component.HeaderBarNV headerBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
