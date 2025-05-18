package GUI.Panel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

import java.sql.Connection;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import config.DBConnector;
import DTO.HoaDonDTO;
import BUS.HoaDonBUS;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import GUI.Dialog.HDmodifyDialog;
import GUI.Dialog.HDdetailDialog;
import GUI.Dialog.HoaDonDialog;
import java.util.ArrayList;
/**
 *
 * @author MSI
 */
public class HoaDon extends javax.swing.JPanel {
    private HoaDonBUS hoaDonBUS;
    private int selectedTableId = -1;
    /**
     * Creates new form HoaDon
     */
    public HoaDon() {
        initComponents();
        Connection conn = DBConnector.getConnection();
        hoaDonBUS = new HoaDonBUS(conn);
        loadTableData();
        setupHoaDonSelection();
    }

    
    private void setupHoaDonSelection() {
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
        List<HoaDonDTO> list = hoaDonBUS.getList_hoaDon();
    
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
    
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        for (HoaDonDTO hoaDon : list) {
            model.addRow(new Object[]{
                hoaDon.getId(),
                hoaDon.getTenKhach(),
                hoaDon.getIdNhanVien(), 
                String.format("%,d VNĐ", hoaDon.getTongTien()),
                dateFormat.format(hoaDon.getThoiGian()),
                hoaDon.getGhiChu()
            });
        }
    }

    public boolean deleteTable(int tableId) {
        if (tableId == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa hóa đơn này?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                if (hoaDonBUS.delete(tableId)) {
                    JOptionPane.showMessageDialog(this, "Xóa hóa đơn thành công!");
                    loadTableData();
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể xóa hóa đơn này", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa hóa đơn: " + ex.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }

    public void openModifyDialog() {
        if (selectedTableId == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        HoaDonDTO hoaDon = hoaDonBUS.getByID(selectedTableId);
        if (hoaDon != null) {
            HDmodifyDialog dialog = new HDmodifyDialog((javax.swing.JFrame) this.getTopLevelAncestor(), hoaDon);
            dialog.setVisible(true);
            
            if (dialog.isModified()) {
                loadTableData();
                // Reselect the modified invoice
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
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần xem chi tiết!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        HoaDonDTO hoaDon = hoaDonBUS.getByID(selectedTableId);
        if (hoaDon != null) {
            HDdetailDialog dialog = new HDdetailDialog((javax.swing.JFrame) this.getTopLevelAncestor(), hoaDon);
            dialog.setVisible(true);
        }
    }

    public void searchHoaDon(String searchText, String searchType) {
        List<HoaDonDTO> searchResults;
        
        if (searchText.isEmpty()) {
            // Nếu không có từ khóa tìm kiếm, hiển thị tất cả
            searchResults = hoaDonBUS.getList_hoaDon();
        } else {
            switch (searchType) {
                case "ID":
                    try {
                        int searchId = Integer.parseInt(searchText);
                        HoaDonDTO hoaDon = hoaDonBUS.getByID(searchId);
                        searchResults = new ArrayList<>();
                        if (hoaDon != null) {
                            searchResults.add(hoaDon);
                        }
                    } catch (NumberFormatException e) {
                        searchResults = new ArrayList<>();
                        JOptionPane.showMessageDialog(this, 
                            "ID phải là số nguyên!", 
                            "Lỗi", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                    
                case "Tên khách hàng":
                    searchResults = hoaDonBUS.searchByCustomerName(searchText);
                    break;
                    
                case "ID Bàn ăn":
                    try {
                        int banAnId = Integer.parseInt(searchText);
                        searchResults = hoaDonBUS.searchByTableId(banAnId);
                    } catch (NumberFormatException e) {
                        searchResults = new ArrayList<>();
                        JOptionPane.showMessageDialog(this, 
                            "ID bàn ăn phải là số nguyên!", 
                            "Lỗi", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                    
                default:
                    searchResults = hoaDonBUS.getList_hoaDon();
                    break;
            }
        }
        
        // Cập nhật bảng với kết quả tìm kiếm
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        for (HoaDonDTO hoaDon : searchResults) {
            model.addRow(new Object[]{
                hoaDon.getId(),
                hoaDon.getTenKhach(),
                hoaDon.getIdNhanVien(),
                String.format("%,d VNĐ", hoaDon.getTongTien()),
                dateFormat.format(hoaDon.getThoiGian()),
                hoaDon.getGhiChu()
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

    public void openAddDialog() {
        HoaDonDialog dialog = new HoaDonDialog();
        dialog.setOnInvoiceCreatedListener(() -> {
            loadTableData(); // Reload table data when a new invoice is created
            // Select the newly created invoice (last row)
            int lastRow = jTable1.getRowCount() - 1;
            if (lastRow >= 0) {
                jTable1.setRowSelectionInterval(lastRow, lastRow);
                jTable1.scrollRectToVisible(jTable1.getCellRect(lastRow, 0, true));
            }
        });
        dialog.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        
        headerBar1 = new GUI.Component.HeaderHD(this);
        setPreferredSize(new java.awt.Dimension(832, 534));
        setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên khách hàng", "ID Bàn ăn", "Tổng tiền", "Ngầy xuất", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }
        add(headerBar1, java.awt.BorderLayout.PAGE_START);
        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private GUI.Component.HeaderHD headerBar1;
    // End of variables declaration//GEN-END:variables
}
