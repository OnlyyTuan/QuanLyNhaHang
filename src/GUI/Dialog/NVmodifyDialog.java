package GUI.Dialog;

import DTO.NhanVienDTO;
import BUS.NhanVienBUS;
import BUS.Result;
import config.DBConnector;
import java.sql.Connection;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

public class NVmodifyDialog extends javax.swing.JDialog {
    private NhanVienBUS nhanVienBUS;
    private NhanVienDTO nhanVien;
    private boolean isModified = false;
    
    public NVmodifyDialog(javax.swing.JFrame parent, NhanVienDTO nhanVien) {
        super(parent, true);
        this.nhanVien = nhanVien;
        initComponents();
        setTitle("Chỉnh sửa nhân viên");
        setSize(500, 400);
        setLocationRelativeTo(null);
        
        // Initialize BUS
        Connection conn = DBConnector.getConnection();
        nhanVienBUS = new NhanVienBUS(conn);
        
        // Load current data
        loadCurrentData();
    }
    
    private void loadCurrentData() {
        jTextFieldNameNV.setText(nhanVien.getHoTen());
        jComboBoxChucVu.setSelectedItem(nhanVien.getChucVu());
        jComboBoxTrangThai.setSelectedIndex(nhanVien.getTrangThai() == 1 ? 0 : 1);
        
        // Set gender radio buttons
        if (nhanVien.getGioiTinh().equals("Nam")) {
            jRadioButton1.setSelected(true);
        } else {
            jRadioButton2.setSelected(true);
        }
    }
    
    public boolean isModified() {
        return isModified;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNameNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonCapNhat = new javax.swing.JButton();
        jButtonHuy = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxChucVu = new javax.swing.JComboBox<>();
        jComboBoxTrangThai = new javax.swing.JComboBox<>(new String[] { "Đang làm việc", "Đã nghỉ" });

        jLabel2.setText("Tên");
        jLabel3.setText("Giới tính");
        jLabel4.setText("Trạng thái");
        jLabel5.setText("Chức vụ");

        // Add positions to combo box
        jComboBoxChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { 
            "Quản lý", 
            "Nhân viên" 
        }));

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

        jRadioButton1.setText("Nam");
        jRadioButton2.setText("Nữ");

        // Create a button group for gender selection
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(jRadioButton1);
        genderGroup.add(jRadioButton2);
        jRadioButton1.setSelected(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldNameNV, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jComboBoxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jComboBoxChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jButtonHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNameNV, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2))
                    .addComponent(jComboBoxChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCapNhatActionPerformed
        try {
            // Validate input
            if (jTextFieldNameNV.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhân viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lấy trạng thái từ combobox
            int trangThai = jComboBoxTrangThai.getSelectedIndex() == 0 ? 1 : 0;

            // Update nhan vien
            nhanVien.setHoTen(jTextFieldNameNV.getText().trim());
            nhanVien.setGioiTinh(jRadioButton1.isSelected() ? "Nam" : "Nữ");
            nhanVien.setChucVu(jComboBoxChucVu.getSelectedItem().toString());
            nhanVien.setTrangThai(trangThai);

            Result result = nhanVienBUS.update(nhanVien);
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField jTextFieldNameNV;
    private javax.swing.JComboBox<String> jComboBoxTrangThai;
    private javax.swing.JComboBox<String> jComboBoxChucVu;
    // End of variables declaration//GEN-END:variables
} 