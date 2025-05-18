/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Panel;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import config.DBConnector;
import DTO.MonAnDTO;
import BUS.MonAnBUS;
import javax.swing.JOptionPane;
import GUI.Dialog.MAmodifyDialog;
import GUI.Dialog.MAdetailDialog;
import GUI.Dialog.MonAnDialog;
/**
 *
 * @author MSI
 */
public class MonAn extends javax.swing.JPanel {
    private int selectedTableId = -1;
    private MonAnBUS monAnBUS;
    /**
     * Creates new form MonAn
     */
    public MonAn() {
        initComponents();
        Connection conn = DBConnector.getConnection();
        monAnBUS = new MonAnBUS(conn);
        loadTableData();
        setupMonAnSelection();
    }


    private void setupMonAnSelection() {
        jTable2.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = jTable2.getSelectedRow();
                if (selectedRow != -1) {
                    selectedTableId = (int) jTable2.getValueAt(selectedRow, 0);
                } else {
                    selectedTableId = -1;
                }
            }
        });
    }

    public void openAddDialog() {
        MonAnDialog dialog = new MonAnDialog();
        dialog.setVisible(true);
        
        // Refresh table data after dialog is closed
        loadTableData();
        
        // Select the last row (newly added food)
        if (jTable2.getRowCount() > 0) {
            int lastRow = jTable2.getRowCount() - 1;
            jTable2.setRowSelectionInterval(lastRow, lastRow);
            jTable2.scrollRectToVisible(jTable2.getCellRect(lastRow, 0, true));
        }
    }

    public void openModifyDialog() {
        if (selectedTableId == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn món ăn cần chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        MonAnDTO monAn = monAnBUS.getByID(selectedTableId);
        if (monAn != null) {
            MAmodifyDialog dialog = new MAmodifyDialog((javax.swing.JFrame) this.getTopLevelAncestor(), monAn);
            dialog.setVisible(true);
            
            if (dialog.isModified()) {
                loadTableData();
                // Reselect the modified food
                for (int i = 0; i < jTable2.getRowCount(); i++) {
                    if ((int)jTable2.getValueAt(i, 0) == selectedTableId) {
                        jTable2.setRowSelectionInterval(i, i);
                        jTable2.scrollRectToVisible(jTable2.getCellRect(i, 0, true));
                        break;
                    }
                }
            }
        }
    }

    public void openDetailDialog() {
        if (selectedTableId == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn món ăn cần xem chi tiết!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        MonAnDTO monAn = monAnBUS.getByID(selectedTableId);
        if (monAn != null) {
            MAdetailDialog dialog = new MAdetailDialog((javax.swing.JFrame) this.getTopLevelAncestor(), monAn);
            dialog.setVisible(true);
        }
    }

    public int getSelectedTableId() {
        return selectedTableId;
    }

    public void loadTableData() {
        List<MonAnDTO> list = monAnBUS.getList_monAn();
    
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
    
        for (MonAnDTO monAn : list) {
            String trangThai = (monAn.getTrangThai() == 1) ? "Còn" : "Hết";
            model.addRow(new Object[]{
                monAn.getId(),
                monAn.getTen(),
                String.format("%,d VNĐ", monAn.getGia()),
                trangThai
            });
        }
    }

    

    public boolean deleteTable(int tableId) {
        if (tableId == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn món ăn cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa món ăn này?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                if (monAnBUS.delete(tableId)) {
                    JOptionPane.showMessageDialog(this, "Xóa món ăn thành công!");
                    loadTableData();
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể xóa món ăn này", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa món ăn: " + ex.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }

    public void searchMonAn(String searchText, String searchType) {
        List<MonAnDTO> searchResults;
        
        if (searchText.isEmpty()) {
            // Nếu không có từ khóa tìm kiếm, hiển thị tất cả
            searchResults = monAnBUS.getList_monAn();
        } else {
            switch (searchType) {
                case "ID":
                    try {
                        int searchId = Integer.parseInt(searchText);
                        MonAnDTO monAn = monAnBUS.getByID(searchId);
                        searchResults = new ArrayList<>();
                        if (monAn != null) {
                            searchResults.add(monAn);
                        }
                    } catch (NumberFormatException e) {
                        searchResults = new ArrayList<>();
                        JOptionPane.showMessageDialog(this, 
                            "ID phải là số nguyên!", 
                            "Lỗi", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                    
                case "Tên món ăn":
                    searchResults = monAnBUS.searchByName(searchText);
                    break;
                    
                default:
                    searchResults = monAnBUS.getList_monAn();
                    break;
            }
        }
        
        // Cập nhật bảng với kết quả tìm kiếm
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        
        for (MonAnDTO monAn : searchResults) {
            String trangThai = (monAn.getTrangThai() == 1) ? "Còn" : "Hết";
            model.addRow(new Object[]{
                monAn.getId(),
                monAn.getTen(),
                String.format("%,d VNĐ", monAn.getGia()),
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
        return jTable2;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        headerBar1 = new GUI.Component.HeaderMA(this);
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(832, 534));
        setLayout(new java.awt.BorderLayout());
        add(headerBar1, java.awt.BorderLayout.PAGE_START);
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Tên", "Giá", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
        }

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    private GUI.Component.HeaderMA headerBar1;
    // End of variables declaration//GEN-END:variables
}
