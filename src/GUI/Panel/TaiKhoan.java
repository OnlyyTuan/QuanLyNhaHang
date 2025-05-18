/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Panel;

import java.sql.Connection;
import java.util.List;
import GUI.Dialog.TKdetailDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import config.DBConnector;
import DTO.TaiKhoanDTO;
import BUS.TaiKhoanBUS;
import GUI.Dialog.TaiKhoanDialog;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class TaiKhoan extends javax.swing.JPanel {
    private int selectedTableId = -1;
    private TaiKhoanBUS taiKhoanBUS;

    /**
     * Creates new form KhacHang
     */
    public TaiKhoan() {
        initComponents();
        Connection conn = DBConnector.getConnection();
        taiKhoanBUS = new TaiKhoanBUS(conn);
        loadTableData();
        setupTaiKhoanSelection();
    }

    private void setupTaiKhoanSelection() {
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
        List<TaiKhoanDTO> list = taiKhoanBUS.getListTaiKhoan();
    
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
    
        for (TaiKhoanDTO tk : list) {
            String trangThai = (tk.getTrangThai() == 1) ? "Hoạt động" : "Khóa";
            model.addRow(new Object[]{
                tk.getId(),
                tk.getTenTaiKhoan(),
                tk.getMatKhau(),
                trangThai
            });
        }
    }

    public void openAddDialog() {
        TaiKhoanDialog dialog = new TaiKhoanDialog();
        dialog.setVisible(true);
        
        // Refresh table data after dialog is closed
        loadTableData();
        
        // Select the last row (newly added account)
        if (jTable1.getRowCount() > 0) {
            int lastRow = jTable1.getRowCount() - 1;
            jTable1.setRowSelectionInterval(lastRow, lastRow);
            jTable1.scrollRectToVisible(jTable1.getCellRect(lastRow, 0, true));
        }
    }

    public void openDetailDialog() {
        if (selectedTableId == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần xem chi tiết!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        TaiKhoanDTO taiKhoan = taiKhoanBUS.getById(selectedTableId);
        if (taiKhoan != null) {
            TKdetailDialog dialog = new TKdetailDialog((javax.swing.JFrame) this.getTopLevelAncestor(), taiKhoan);
            dialog.setVisible(true);
        }
    }

    public void openModifyDialog() {
        if (selectedTableId == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        TaiKhoanDTO taiKhoan = taiKhoanBUS.getById(selectedTableId);
        if (taiKhoan != null) {
            TaiKhoanDialog dialog = new TaiKhoanDialog(taiKhoan);
            dialog.setVisible(true);
            
            if (dialog.isModified()) {
                loadTableData();
                // Reselect the modified account
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

    public boolean deleteTable(int tableId) {
        if (tableId == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa tài khoản này?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                if (taiKhoanBUS.delete(tableId)) {
                    JOptionPane.showMessageDialog(this, "Xóa tài khoản thành công!");
                    loadTableData();
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể xóa tài khoản này", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }

    public void searchTaiKhoan(String searchText, String searchType) {
        List<TaiKhoanDTO> searchResults;
        
        if (searchText.isEmpty()) {
            // Nếu không có từ khóa tìm kiếm, hiển thị tất cả
            searchResults = taiKhoanBUS.getListTaiKhoan();
        } else {
            switch (searchType) {
                case "ID":
                    try {
                        int searchId = Integer.parseInt(searchText);
                        TaiKhoanDTO taiKhoan = taiKhoanBUS.getById(searchId);
                        searchResults = new ArrayList<>();
                        if (taiKhoan != null) {
                            searchResults.add(taiKhoan);
                        }
                    } catch (NumberFormatException e) {
                        searchResults = new ArrayList<>();
                        JOptionPane.showMessageDialog(this, 
                            "ID phải là số nguyên!", 
                            "Lỗi", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                    
                case "Tài khoản":
                    searchResults = taiKhoanBUS.searchByUsername(searchText);
                    break;
                    
                default:
                    searchResults = taiKhoanBUS.getListTaiKhoan();
                    break;
            }
        }
        
        // Cập nhật bảng với kết quả tìm kiếm
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        for (TaiKhoanDTO tk : searchResults) {
            String trangThai = (tk.getTrangThai() == 1) ? "Hoạt động" : "Khóa";
            model.addRow(new Object[]{
                tk.getId(),
                tk.getTenTaiKhoan(),
                tk.getMatKhau(),
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

        headerBar1 = new GUI.Component.HeaderBarTK(this);
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(832, 534));
        setLayout(new java.awt.BorderLayout());
        add(headerBar1, java.awt.BorderLayout.PAGE_START);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Tài khoản", "Mật khẩu", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        }

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUI.Component.HeaderBarTK headerBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
