/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Panel;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import DTO.BanAnDTO;
import BUS.BanAnBUS;
import java.sql.Connection;
import config.DBConnector;
import javax.swing.JOptionPane;
import GUI.Dialog.BAmodifyDialog;
import GUI.Dialog.BAdetailDialog;

/**
 *
 * @author MSI
 */
public class BanAn extends javax.swing.JPanel {
    private BanAnBUS banAnBUS;
    private int selectedTableId = -1;

    /**
     * Creates new form NewJPanel
     */
    public BanAn() {
        initComponents();
        Connection conn = DBConnector.getConnection();
        banAnBUS = new BanAnBUS(conn);
        loadTableData();
        setupTableSelection();
    }

    private void setupTableSelection() {
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

    public BanAnBUS getBanAnBUS() {
        return banAnBUS;
    }

    public void loadTableData() {
        List<BanAnDTO> list = banAnBUS.getList_banAn();
    
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
    
        for (BanAnDTO ban : list) {
            String trangThai = (ban.getTrangThai() == 1) ? "Đang sử dụng" : "Trống";
            model.addRow(new Object[]{
                ban.getId(),
                ban.getTen(),
                trangThai
            });
        }
    }

    public boolean deleteTable(int tableId) {
        if (tableId == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn bàn cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa bàn này?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                if (banAnBUS.delete(tableId)) {
                    JOptionPane.showMessageDialog(this, "Xóa bàn thành công!");
                    loadTableData();
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể xóa bàn này vì đang có hóa đơn liên quan!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa bàn: " + ex.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }

    public void openModifyDialog() {
        if (selectedTableId == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn bàn cần chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        BanAnDTO banAn = banAnBUS.getByID(selectedTableId);
        if (banAn != null) {
            BAmodifyDialog dialog = new BAmodifyDialog((javax.swing.JFrame) this.getTopLevelAncestor(), banAn);
            dialog.setVisible(true);
            
            if (dialog.isModified()) {
                loadTableData();
                // Reselect the modified table
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
            JOptionPane.showMessageDialog(this, "Vui lòng chọn bàn cần xem chi tiết!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        BanAnDTO banAn = banAnBUS.getByID(selectedTableId);
        if (banAn != null) {
            BAdetailDialog dialog = new BAdetailDialog((javax.swing.JFrame) this.getTopLevelAncestor(), banAn);
            dialog.setVisible(true);
        }
    }

    public void searchTable(String searchText, String searchType) {
        List<BanAnDTO> searchResults;
        
        if (searchText.isEmpty()) {
            // Nếu không có từ khóa tìm kiếm, hiển thị tất cả
            searchResults = banAnBUS.getList_banAn();
        } else {
            switch (searchType) {
                case "ID":
                    try {
                        int searchId = Integer.parseInt(searchText);
                        BanAnDTO banAn = banAnBUS.getByID(searchId);
                        searchResults = new ArrayList<>();
                        if (banAn != null) {
                            searchResults.add(banAn);
                        }
                    } catch (NumberFormatException e) {
                        searchResults = new ArrayList<>();
                        JOptionPane.showMessageDialog(this, 
                            "ID phải là số nguyên!", 
                            "Lỗi", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                    
                case "Tên bàn":
                    searchResults = banAnBUS.searchByName(searchText);
                    break;
                    
                default:
                    searchResults = banAnBUS.getList_banAn();
                    break;
            }
        }
        
        // Cập nhật bảng với kết quả tìm kiếm
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        for (BanAnDTO ban : searchResults) {
            String trangThai = (ban.getTrangThai() == 1) ? "Đang sử dụng" : "Trống";
            model.addRow(new Object[]{
                ban.getId(),
                ban.getTen(),
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

        headerBar1 = new GUI.Component.HeaderBar(this);
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(832, 534));
        setLayout(new java.awt.BorderLayout());
        add(headerBar1, java.awt.BorderLayout.PAGE_START);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên bàn", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
        }

        add(jScrollPane2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUI.Component.HeaderBar headerBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
