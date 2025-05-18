/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Panel;

import java.sql.Connection;
import java.util.List;
import GUI.Dialog.NVmodifyDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import config.DBConnector;
import DTO.NhanVienDTO;
import BUS.NhanVienBUS;
import GUI.Dialog.NVdetailDialog;
import GUI.Dialog.NhanVienDialog;
import java.util.ArrayList;
/**
 *
 * @author MSI
 */
public class NhanVien extends javax.swing.JPanel {
    private int selectedTableId = -1;
    private NhanVienBUS nhanVienBUS;
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
        List<NhanVienDTO> list = nhanVienBUS.getListNhanVien();
    
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
    
        for (NhanVienDTO nv : list) {
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

    public void openAddDialog() {
        NhanVienDialog dialog = new NhanVienDialog();
        dialog.setVisible(true);
        
        // Refresh table data after dialog is closed
        loadTableData();
        
        // Select the last row (newly added staff)
        if (jTable1.getRowCount() > 0) {
            int lastRow = jTable1.getRowCount() - 1;
            jTable1.setRowSelectionInterval(lastRow, lastRow);
            jTable1.scrollRectToVisible(jTable1.getCellRect(lastRow, 0, true));
        }
    }

    public void openModifyDialog() {
        if (selectedTableId == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        NhanVienDTO nhanVien = nhanVienBUS.getById(selectedTableId);
        if (nhanVien != null) {
            NVmodifyDialog dialog = new NVmodifyDialog((javax.swing.JFrame) this.getTopLevelAncestor(), nhanVien);
            dialog.setVisible(true);
            
            if (dialog.isModified()) {
                loadTableData();
                // Reselect the modified staff
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    if ((int)jTable1.getValueAt(i, 0) == selectedTableId) {
                        jTable1.setRowSelectionInterval(i, i);
                        jTable1.scrollRectToVisible(jTable1.getCellRect(i, 0, true));
                        break;
                    }
                }
            }
        }
    }

    public void openDetailDialog() {
        if (selectedTableId == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xem chi tiết!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        NhanVienDTO nhanVien = nhanVienBUS.getById(selectedTableId);
        if (nhanVien != null) {
            NVdetailDialog dialog = new NVdetailDialog((javax.swing.JFrame) this.getTopLevelAncestor(), nhanVien);
            dialog.setVisible(true);
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

    public NhanVienDTO getNhanVienById(int id) {
        return nhanVienBUS.getById(id);
    }

    public void searchNhanVien(String searchText, String searchType) {
        List<NhanVienDTO> searchResults;
        
        if (searchText.isEmpty()) {
            // Nếu không có từ khóa tìm kiếm, hiển thị tất cả
            searchResults = nhanVienBUS.getListNhanVien();
        } else {
            switch (searchType) {
                case "ID":
                    try {
                        int searchId = Integer.parseInt(searchText);
                        NhanVienDTO nhanVien = nhanVienBUS.getById(searchId);
                        searchResults = new ArrayList<>();
                        if (nhanVien != null) {
                            searchResults.add(nhanVien);
                        }
                    } catch (NumberFormatException e) {
                        searchResults = new ArrayList<>();
                        JOptionPane.showMessageDialog(this, 
                            "ID phải là số nguyên!", 
                            "Lỗi", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                    
                case "Tên nhân viên":
                    searchResults = nhanVienBUS.searchByName(searchText);
                    break;
                    
                case "Giới tính":
                    searchResults = nhanVienBUS.searchByGender(searchText);
                    break;
                    
                case "Chức vụ":
                    searchResults = nhanVienBUS.searchByPosition(searchText);
                    break;
                    
                default:
                    searchResults = nhanVienBUS.getListNhanVien();
                    break;
            }
        }
        
        // Cập nhật bảng với kết quả tìm kiếm
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        for (NhanVienDTO nv : searchResults) {
            String trangThai = (nv.getTrangThai() == 1) ? "Đang làm việc" : "Đã nghỉ";
            model.addRow(new Object[]{
                nv.getId(),
                nv.getHoTen(),
                nv.getGioiTinh(),
                nv.getChucVu(),
                trangThai
            });
        }
        
        // Thông báo nếu không tìm thấy kết quả
        if (searchResults.isEmpty() && !searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Không tìm thấy kết quả phù hợp!", 
                "Thông báo", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public javax.swing.JTable getTable() {
        return jTable1;
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
