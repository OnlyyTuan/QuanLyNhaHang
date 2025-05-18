package GUI.Dialog;

import DTO.QuyenDTO;
import DTO.CTQuyenDTO;
import BUS.CTQuyenBUS;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PQdetailDialog extends javax.swing.JDialog {
    private QuyenDTO quyen;
    private String[] columnNames = {"Chức năng", "Hành động"};
    private String[] chucNang = {"Món ăn", "Bàn ăn", "Hóa đơn", "Nhân viên", "Phân quyền", "Tài khoản"};
    private String[] hanhDong = {"Xem", "Thêm", "Sửa", "Xóa"};
    private int sizeCN, sizeHD;
    
    public PQdetailDialog(javax.swing.JFrame parent, QuyenDTO quyen) {
        super(parent, true);
        this.quyen = quyen;
        this.sizeCN = chucNang.length;
        this.sizeHD = hanhDong.length;
        initComponents();
        setupDialog();
        loadData();
    }
    
    private void setupDialog() {
        setTitle("Chi tiết quyền - " + quyen.getTen());
        setSize(900, 600);
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
    }
    
    private void loadData() {
        try {
            loadChiTietQuyen();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Lỗi khi tải thông tin quyền: " + e.getMessage(),
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadChiTietQuyen() {
        try {
            CTQuyenBUS ctQuyenBUS = new CTQuyenBUS();
            ArrayList<CTQuyenDTO> ctQuyenList = ctQuyenBUS.getAllByQuyenId(quyen.getId());
            
            for (CTQuyenDTO ctq : ctQuyenList) {
                int row = ctq.getIdChucNang() - 1;
                int col = -1;
                
                switch (ctq.getHanhDong().toLowerCase()) {
                    case "xem": col = 0; break;
                    case "thêm": col = 1; break;
                    case "sửa": col = 2; break;
                    case "xóa": col = 3; break;
                }
                
                if (row >= 0 && row < sizeCN && col >= 0 && col < sizeHD) {
                    JCheckBox checkBox = (JCheckBox) pnl_checkbox.getComponent(row * sizeHD + col);
                    checkBox.setSelected(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Lỗi khi tải chi tiết quyền: " + e.getMessage(),
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTenQuyen = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        pnl_chucNang = new javax.swing.JPanel();
        pnl_center = new javax.swing.JPanel();
        pnl_hanhdong = new javax.swing.JPanel();
        pnl_checkbox = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnDong = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tên quyền");

        txtTenQuyen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenQuyen.setText(quyen.getTen());
        txtTenQuyen.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(263, 263, 263)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(263, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTenQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        pnl_chucNang.setBackground(new java.awt.Color(255, 255, 255));
        pnl_chucNang.setPreferredSize(new java.awt.Dimension(120, 500));
        pnl_chucNang.setLayout(new java.awt.GridLayout(10, 1));
        
        // Thêm label "Tên chức năng"
        JLabel tenCN = new JLabel("Tên chức năng");
        tenCN.setHorizontalAlignment(SwingConstants.CENTER);
        tenCN.setFont(new Font("Segoe UI", Font.BOLD, 12));
        pnl_chucNang.add(tenCN);
        
        // Thêm tên các chức năng
        for (String cn : chucNang) {
            JLabel label = new JLabel(cn);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Segoe UI", Font.BOLD, 12));
            pnl_chucNang.add(label);
        }
        
        jPanel5.add(pnl_chucNang, java.awt.BorderLayout.LINE_START);

        pnl_center.setLayout(new java.awt.BorderLayout());

        pnl_hanhdong.setBackground(new java.awt.Color(255, 255, 255));
        pnl_hanhdong.setPreferredSize(new java.awt.Dimension(780, 50));
        pnl_hanhdong.setLayout(new java.awt.GridLayout(1, 4));
        
        // Thêm tên các hành động
        for (String hd : hanhDong) {
            JLabel label = new JLabel(hd);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Segoe UI", Font.BOLD, 12));
            pnl_hanhdong.add(label);
        }
        
        pnl_center.add(pnl_hanhdong, java.awt.BorderLayout.PAGE_START);

        pnl_checkbox.setBackground(new java.awt.Color(255, 255, 255));
        pnl_checkbox.setLayout(new java.awt.GridLayout(9, 4));
        
        // Thêm checkbox cho từng chức năng và hành động
        for (int i = 0; i < sizeCN; i++) {
            for (int j = 0; j < sizeHD; j++) {
                JCheckBox checkBox = new JCheckBox();
                checkBox.setHorizontalAlignment(SwingConstants.CENTER);
                checkBox.setEnabled(false);
                pnl_checkbox.add(checkBox);
            }
        }
        
        pnl_center.add(pnl_checkbox, java.awt.BorderLayout.CENTER);

        jPanel5.add(pnl_center, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(900, 50));

        btnDong.setBackground(new java.awt.Color(255, 107, 107));
        btnDong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDong.setForeground(new java.awt.Color(255, 255, 255));
        btnDong.setText("Đóng");
        btnDong.setBorder(null);
        btnDong.setPreferredSize(new java.awt.Dimension(120, 40));
        btnDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDongMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(390, 390, 390)
                .addComponent(btnDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(390, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        jPanel3.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDongMousePressed
        dispose();
    }//GEN-LAST:event_btnDongMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel pnl_center;
    private javax.swing.JPanel pnl_checkbox;
    private javax.swing.JPanel pnl_chucNang;
    private javax.swing.JPanel pnl_hanhdong;
    private javax.swing.JTextField txtTenQuyen;
    // End of variables declaration//GEN-END:variables
} 