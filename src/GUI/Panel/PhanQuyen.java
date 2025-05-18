/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Panel;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import GUI.Dialog.PQdetailDialog;
import config.DBConnector;
import DTO.QuyenDTO;
import BUS.QuyenBUS;
import javax.swing.JOptionPane;
import GUI.Dialog.QuyenDialog;
/**
 *
 * @author MSI
 */
public class PhanQuyen extends javax.swing.JPanel {
    private int selectedTableId = -1;
    private QuyenBUS quyenBUS;
    /**
     * Creates new form PhanQuyen
     */
    public PhanQuyen() {
        initComponents();
        Connection conn = DBConnector.getConnection();
        quyenBUS = new QuyenBUS(conn);
        loadTableData();
        setupPhanQuyenSelection();
    }



    private void setupPhanQuyenSelection() {
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
        List<QuyenDTO> list = quyenBUS.getListQuyen();
    
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
    
        for (QuyenDTO quyen : list) {
            model.addRow(new Object[]{
                quyen.getId(),
                quyen.getTen()
            });
        }
    }

    public void openAddDialog() {
        QuyenDialog dialog = new QuyenDialog();
        dialog.setVisible(true);
        
        // Refresh table data after dialog is closed
        loadTableData();
        
        // Select the last row (newly added role)
        if (jTable1.getRowCount() > 0) {
            int lastRow = jTable1.getRowCount() - 1;
            jTable1.setRowSelectionInterval(lastRow, lastRow);
            jTable1.scrollRectToVisible(jTable1.getCellRect(lastRow, 0, true));
        }
    }

    public void openDetailDialog() {
        if (selectedTableId == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn quyền cần xem chi tiết!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        QuyenDTO quyen = quyenBUS.getById(selectedTableId);
        if (quyen != null) {
            PQdetailDialog dialog = new PQdetailDialog((javax.swing.JFrame) this.getTopLevelAncestor(), quyen);
            dialog.setVisible(true);
        }
    }

    public void openModifyDialog() {
        if (selectedTableId == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn quyền cần sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        QuyenDTO quyen = quyenBUS.getById(selectedTableId);
        if (quyen != null) {
            QuyenDialog dialog = new QuyenDialog(quyen);
            dialog.setVisible(true);
            
            if (dialog.isModified()) {
                loadTableData();
                // Reselect the modified role
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
            JOptionPane.showMessageDialog(this, "Vui lòng chọn quyền cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa quyền này?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                if (quyenBUS.delete(tableId)) {
                    JOptionPane.showMessageDialog(this, "Xóa quyền thành công!");
                    loadTableData();
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể xóa quyền này", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }

    public void searchQuyen(String searchText, String searchType) {
        List<QuyenDTO> searchResults;
        
        if (searchText.isEmpty()) {
            // Nếu không có từ khóa tìm kiếm, hiển thị tất cả
            searchResults = quyenBUS.getListQuyen();
        } else {
            switch (searchType) {
                case "ID":
                    try {
                        int searchId = Integer.parseInt(searchText);
                        QuyenDTO quyen = quyenBUS.searchById(searchId);
                        searchResults = new ArrayList<>();
                        if (quyen != null) {
                            searchResults.add(quyen);
                        }
                    } catch (NumberFormatException e) {
                        searchResults = new ArrayList<>();
                        JOptionPane.showMessageDialog(this, 
                            "ID phải là số nguyên!", 
                            "Lỗi", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                    
                case "Tên quyền":
                    searchResults = quyenBUS.searchByName(searchText);
                    break;
                    
                default:
                    searchResults = quyenBUS.getListQuyen();
                    break;
            }
        }
        
        // Cập nhật bảng với kết quả tìm kiếm
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        for (QuyenDTO q : searchResults) {
            model.addRow(new Object[]{
                q.getId(),
                q.getTen()
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

        headerBar1 = new GUI.Component.HeaderBarQuyen(this);
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(832, 534));
        setLayout(new java.awt.BorderLayout());
        add(headerBar1, java.awt.BorderLayout.PAGE_START);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Tên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUI.Component.HeaderBarQuyen headerBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
