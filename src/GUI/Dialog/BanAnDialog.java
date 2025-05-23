/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Dialog;

import DTO.BanAnDTO;
import BUS.BanAnBUS;
import BUS.Result;
import config.DBConnector;
import java.sql.Connection;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

/**
 *
 * @author MSI
 */
public class BanAnDialog extends JDialog {
    private BanAnDTO banAn;
    private boolean isModified = false;
    private JComboBox<String> jComboBoxTrangThai;

    /**
     * Creates new form BanAndialog
     */
    public BanAnDialog() {
        this(null);
    }

    public BanAnDialog(BanAnDTO banAn) {
        this.banAn = banAn;
        initComponents();
        setupDialog();
        if (banAn != null) {
            loadData();
        }
    }

    private void setupDialog() {
        setTitle(banAn == null ? "Thêm Bàn Ăn Mới" : "Sửa Bàn Ăn");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
    }

    private void loadData() {
        jTextFieldNameBA.setText(banAn.getTen());
        jComboBoxTrangThai.setSelectedIndex(banAn.getTrangThai() == 1 ? 0 : 1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        // Initialize all components first
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNameBA = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButtonSave = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        
        // Initialize ComboBox
        jComboBoxTrangThai = new javax.swing.JComboBox<>(new String[]{"Đang sử dụng", "Trống"});

        jLabel1.setText("Tên bàn:");
        jLabel2.setText("Trạng thái:");
        jButtonSave.setText("Thêm");
        jButtonCancel.setText("Hủy");

        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldNameBA)
                    .addComponent(jComboBoxTrangThai, 0, 250, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNameBA, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        // Set button colors
        jButtonSave.setBackground(new java.awt.Color(255, 255, 255));
        jButtonSave.setForeground(new java.awt.Color(0, 0, 0));
        jButtonCancel.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCancel.setForeground(new java.awt.Color(0, 0, 0));

        // Set fonts
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jTextFieldNameBA.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jComboBoxTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jButtonSave.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jButtonCancel.setFont(new java.awt.Font("Segoe UI", 0, 14));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String tenBan = jTextFieldNameBA.getText().trim();
            
            if (tenBan.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên bàn", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int trangThai = jComboBoxTrangThai.getSelectedIndex() == 0 ? 1 : 0;
            Connection conn = DBConnector.getConnection();
            BanAnBUS banAnBUS = new BanAnBUS(conn);
            Result result;

            if (banAn == null) {
                // Thêm mới
                BanAnDTO newBan = new BanAnDTO(0, tenBan, trangThai);
                result = banAnBUS.add(newBan);
            } else {
                // Cập nhật
                banAn.setTen(tenBan);
                banAn.setTrangThai(trangThai);
                result = banAnBUS.update(banAn);
            }

            if (result.isSuccess()) {
                JOptionPane.showMessageDialog(this, result.getMessage());
                isModified = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, result.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isModified() {
        return isModified;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextFieldNameBA;
    // End of variables declaration//GEN-END:variables
}
