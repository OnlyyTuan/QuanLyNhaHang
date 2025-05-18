package GUI.Dialog;

import DTO.BanAnDTO;
import BUS.BanAnBUS;
import config.DBConnector;
import BUS.Result;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class BAmodifyDialog extends javax.swing.JDialog {
    private BanAnBUS banAnBUS;
    private BanAnDTO banAn;
    private boolean isModified = false;
    
    public BAmodifyDialog(javax.swing.JFrame parent, BanAnDTO banAn) {
        super(parent, true);
        this.banAn = banAn;
        initComponents();
        setTitle("Chỉnh sửa bàn ăn");
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        // Initialize BUS
        Connection conn = DBConnector.getConnection();
        banAnBUS = new BanAnBUS(conn);
        
        // Load current data
        loadCurrentData();
    }
    
    private void loadCurrentData() {
        jTextFieldTen.setText(banAn.getTen());
        jComboBoxTrangThai.setSelectedIndex(banAn.getTrangThai() == 1 ? 0 : 1);
    }
    
    public boolean isModified() {
        return isModified;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jTextFieldTen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxTrangThai = new javax.swing.JComboBox<>();
        jButtonCapNhat = new javax.swing.JButton();
        jButtonHuy = new javax.swing.JButton();

        jLabel1.setText("Tên bàn:");
        jLabel2.setText("Trạng thái:");

        jComboBoxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang sử dụng", "Trống" }));

        jButtonCapNhat.setText("Cập nhật");
        jButtonCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCapNhatActionPerformed(evt);
            }
        });

        jButtonHuy.setText("Hủy");
        jButtonHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHuyActionPerformed(evt);
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
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldTen)
                    .addComponent(jComboBoxTrangThai, 0, 250, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCapNhatActionPerformed
        try {
            // Cập nhật thông tin bàn ăn
            banAn.setTen(jTextFieldTen.getText());
            banAn.setTrangThai(jComboBoxTrangThai.getSelectedIndex() == 0 ? 1 : 0);

            Result result = banAnBUS.update(banAn);
            if (result.isSuccess()) {
                JOptionPane.showMessageDialog(this, result.getMessage());
                isModified = true;
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, result.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonCapNhatActionPerformed

    private void jButtonHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonHuyActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCapNhat;
    private javax.swing.JButton jButtonHuy;
    private javax.swing.JComboBox<String> jComboBoxTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextFieldTen;
    // End of variables declaration//GEN-END:variables
} 