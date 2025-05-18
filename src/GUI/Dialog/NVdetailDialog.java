package GUI.Dialog;

import DTO.NhanVienDTO;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;

public class NVdetailDialog extends javax.swing.JDialog {
    private NhanVienDTO nhanVien;
    
    public NVdetailDialog(javax.swing.JFrame parent, NhanVienDTO nhanVien) {
        super(parent, true);
        this.nhanVien = nhanVien;
        initComponents();
        setTitle("Chi tiết nhân viên");
        setSize(500, 400);
        setLocationRelativeTo(null);
        
        // Load data
        loadData();
    }
    
    private void loadData() {
        try {
            // Hiển thị thông tin cơ bản
            jTextFieldID.setText(String.valueOf(nhanVien.getId()));
            jTextFieldHoTen.setText(nhanVien.getHoTen());
            jTextFieldGioiTinh.setText(nhanVien.getGioiTinh());
            jTextFieldChucVu.setText(nhanVien.getChucVu());
            
            // Hiển thị trạng thái
            String trangThai = (nhanVien.getTrangThai() == 1) ? "Đang làm việc" : "Đã nghỉ";
            jTextFieldTrangThai.setText(trangThai);
            
            // Set tất cả các trường là read-only
            jTextFieldID.setEditable(false);
            jTextFieldHoTen.setEditable(false);
            jTextFieldGioiTinh.setEditable(false);
            jTextFieldChucVu.setEditable(false);
            jTextFieldTrangThai.setEditable(false);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Lỗi khi tải thông tin nhân viên: " + e.getMessage(),
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jTextFieldID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldHoTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldGioiTinh = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldChucVu = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldTrangThai = new javax.swing.JTextField();
        jButtonDong = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jLabel1.setText("ID:");

        jTextFieldID.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jLabel2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jLabel2.setText("Họ tên:");

        jTextFieldHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jLabel3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jLabel3.setText("Giới tính:");

        jTextFieldGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jLabel4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jLabel4.setText("Chức vụ:");

        jTextFieldChucVu.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jLabel5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jLabel5.setText("Trạng thái:");

        jTextFieldTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jButtonDong.setBackground(new Color(255, 51, 51));
        jButtonDong.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jButtonDong.setForeground(new Color(255, 255, 255));
        jButtonDong.setText("Đóng");
        jButtonDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDongActionPerformed(evt);
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
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldID)
                    .addComponent(jTextFieldHoTen)
                    .addComponent(jTextFieldGioiTinh)
                    .addComponent(jTextFieldChucVu)
                    .addComponent(jTextFieldTrangThai))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonDong, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jButtonDong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDongActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonDongActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextFieldChucVu;
    private javax.swing.JTextField jTextFieldGioiTinh;
    private javax.swing.JTextField jTextFieldHoTen;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldTrangThai;
    // End of variables declaration//GEN-END:variables
} 