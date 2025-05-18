package GUI.Dialog;

import DTO.TaiKhoanDTO;
import DTO.NhanVienDTO;
import DTO.QuyenDTO;
import BUS.TaiKhoanBUS;
import BUS.NhanVienBUS;
import BUS.QuyenBUS;
import config.DBConnector;
import java.sql.Connection;
import javax.swing.*;
import java.awt.*;

public class TKdetailDialog extends javax.swing.JDialog {
    private TaiKhoanDTO taiKhoan;
    private NhanVienBUS nhanVienBUS;
    private QuyenBUS quyenBUS;
    
    public TKdetailDialog(javax.swing.JFrame parent, TaiKhoanDTO taiKhoan) {
        super(parent, true);
        this.taiKhoan = taiKhoan;
        initComponents();
        setupDialog();
        loadData();
    }
    
    private void setupDialog() {
        setTitle("Chi tiết tài khoản");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
    }
    
    private void loadData() {
        try {
            Connection conn = DBConnector.getConnection();
            nhanVienBUS = new NhanVienBUS(conn);
            quyenBUS = new QuyenBUS(conn);
            
            // Load thông tin cơ bản
            jTextFieldTaiKhoan.setText(taiKhoan.getTenTaiKhoan());
            jTextFieldMatKhau.setText(taiKhoan.getMatKhau());
            
            // Load thông tin nhân viên
            NhanVienDTO nhanVien = nhanVienBUS.getById(taiKhoan.getIdNhanVien());
            if (nhanVien != null) {
                jTextFieldHoTen.setText(nhanVien.getHoTen());
                jTextFieldGioiTinh.setText(nhanVien.getGioiTinh());
                jTextFieldChucVu.setText(nhanVien.getChucVu());
            }
            
            // Load thông tin quyền
            QuyenDTO quyen = quyenBUS.getById(taiKhoan.getIdQuyen());
            if (quyen != null) {
                jTextFieldQuyen.setText(quyen.getTen());
            }
            
            // Set trạng thái
            jTextFieldTrangThai.setText(taiKhoan.getTrangThai() == 1 ? "Hoạt động" : "Khóa");
            
            // Set tất cả các trường là read-only
            jTextFieldTaiKhoan.setEditable(false);
            jTextFieldMatKhau.setEditable(false);
            jTextFieldHoTen.setEditable(false);
            jTextFieldGioiTinh.setEditable(false);
            jTextFieldChucVu.setEditable(false);
            jTextFieldQuyen.setEditable(false);
            jTextFieldTrangThai.setEditable(false);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Lỗi khi tải thông tin tài khoản: " + e.getMessage(),
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldTaiKhoan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldMatKhau = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldHoTen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldGioiTinh = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldChucVu = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldQuyen = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldTrangThai = new javax.swing.JTextField();
        jButtonDong = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Chi tiết tài khoản");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        // Tạo panel chứa các trường thông tin
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Thêm các trường thông tin
        String[] labels = {"Tên tài khoản:", "Mật khẩu:", "Họ tên:", "Giới tính:", "Chức vụ:", "Quyền:", "Trạng thái:"};
        JTextField[] fields = {jTextFieldTaiKhoan, jTextFieldMatKhau, jTextFieldHoTen, 
                             jTextFieldGioiTinh, jTextFieldChucVu, jTextFieldQuyen, jTextFieldTrangThai};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            infoPanel.add(label, gbc);

            gbc.gridx = 1;
            fields[i].setFont(new Font("Segoe UI", Font.PLAIN, 14));
            fields[i].setPreferredSize(new Dimension(300, 30));
            infoPanel.add(fields[i], gbc);
        }

        // Thêm nút đóng
        jButtonDong.setBackground(new java.awt.Color(255, 107, 107));
        jButtonDong.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jButtonDong.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDong.setText("Đóng");
        jButtonDong.setBorder(null);
        jButtonDong.setPreferredSize(new java.awt.Dimension(120, 40));
        jButtonDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dispose();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        infoPanel.add(jButtonDong, gbc);

        getContentPane().add(infoPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldTaiKhoan;
    private javax.swing.JTextField jTextFieldMatKhau;
    private javax.swing.JTextField jTextFieldHoTen;
    private javax.swing.JTextField jTextFieldGioiTinh;
    private javax.swing.JTextField jTextFieldChucVu;
    private javax.swing.JTextField jTextFieldQuyen;
    private javax.swing.JTextField jTextFieldTrangThai;
    // End of variables declaration//GEN-END:variables
} 