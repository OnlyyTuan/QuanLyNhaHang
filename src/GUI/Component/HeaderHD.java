/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Component;

import javax.swing.JPanel;
import GUI.Dialog.HoaDonDialog;
import GUI.Panel.HoaDon;
import helper.JTableExporter;
import javax.swing.JOptionPane;
/**
 *
 * @author MSI
 */
public class HeaderHD extends javax.swing.JPanel {
    private HoaDon hoaDonPanel;
    /**
     * Creates new form HeaderBar
     */
    public HeaderHD(HoaDon hoaDonPanel) {
        initComponents();
        this.hoaDonPanel = hoaDonPanel;
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldSearchBar = new javax.swing.JTextField();
        jComboBoxSearchType = new javax.swing.JComboBox<>();
        jButtonSearch = new javax.swing.JButton();
        jButtonRefresh = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButtonAdd = new javax.swing.JButton();
        jButtonModify = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonDetail3 = new javax.swing.JButton();
        jButtonExport = new javax.swing.JButton();
        jButtonImport = new javax.swing.JButton();

        jTextFieldSearchBar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldSearchBar.setText("Tìm kiếm");
        jTextFieldSearchBar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (jTextFieldSearchBar.getText().equals("Tìm kiếm")) {
                    jTextFieldSearchBar.setText("");
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (jTextFieldSearchBar.getText().isEmpty()) {
                    jTextFieldSearchBar.setText("Tìm kiếm");
                }
            }
        });

        jComboBoxSearchType.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBoxSearchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { 
            "ID", 
            "Tên khách hàng",
            "ID Bàn ăn"
        }));
        jComboBoxSearchType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSearchTypeActionPerformed(evt);
            }
        });

        jButtonSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonSearch.setText("Tìm");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        jButtonRefresh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonRefresh.setText("Làm mới");
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });

        jLabel1.setText("Sắp xếp theo:");

        jButtonAdd.setBackground(new java.awt.Color(0, 255, 0));
        jButtonAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonAdd.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAdd.setText("Thêm");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonModify.setBackground(new java.awt.Color(255, 255, 0));
        jButtonModify.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonModify.setForeground(new java.awt.Color(255, 255, 255));
        jButtonModify.setText("Sửa");
        jButtonModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifyActionPerformed(evt);
            }
        });

        jButtonDelete.setBackground(new java.awt.Color(255, 102, 102));
        jButtonDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonDelete.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelete.setText("Xóa");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonDetail3.setText("Chi tiết");
        jButtonDetail3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDetail3ActionPerformed(evt);
            }
        });

        jButtonExport.setText("Export");
        jButtonExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (hoaDonPanel != null) {
                    try {
                        JTableExporter.exportJTableToExcel(hoaDonPanel.getTable());
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, 
                            "Lỗi khi xuất file Excel: " + e.getMessage(), 
                            "Lỗi", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        jButtonImport.setText("Import");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jTextFieldSearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBoxSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonModify)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDetail3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonExport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonImport))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonModify, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDetail3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonExport, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonImport, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxSearchTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSearchTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSearchTypeActionPerformed

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        if (hoaDonPanel != null) {
            String searchText = jTextFieldSearchBar.getText().trim();
            if (searchText.equals("Tìm kiếm")) {
                searchText = "";
            }
            
            String searchType = jComboBoxSearchType.getSelectedItem().toString();
            hoaDonPanel.searchHoaDon(searchText, searchType);
        }
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        // TODO add your handling code here:
        if (hoaDonPanel != null) {
            hoaDonPanel.loadTableData();
        }
    }//GEN-LAST:event_jButtonRefreshActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        // TODO add your handling code here:

        HoaDonDialog hoaDonDialog = new HoaDonDialog();    
        hoaDonDialog.setVisible(true);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifyActionPerformed
        // TODO add your handling code here:
        if (hoaDonPanel != null) {
            hoaDonPanel.openModifyDialog();
        }
    }//GEN-LAST:event_jButtonModifyActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
        if (hoaDonPanel != null) {
            hoaDonPanel.deleteTable(hoaDonPanel.getSelectedTableId());
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonDetail3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDetail3ActionPerformed
        // TODO add your handling code here:
        if (hoaDonPanel != null) {
            hoaDonPanel.openDetailDialog();
        }
    }//GEN-LAST:event_jButtonDetail3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonDetail3;
    private javax.swing.JButton jButtonExport;
    private javax.swing.JButton jButtonImport;
    private javax.swing.JButton jButtonModify;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JComboBox<String> jComboBoxSearchType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextFieldSearchBar;
    // End of variables declaration//GEN-END:variables
}
