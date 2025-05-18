package GUI.Dialog;

import DTO.QuyenDTO;
import BUS.QuyenBUS;
import BUS.Result;
import BUS.CTQuyenBUS;
import DTO.CTQuyenDTO;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import config.DBConnector;
import java.util.ArrayList;

public class QuyenDialog extends javax.swing.JDialog {
    private QuyenDTO quyen;
    private boolean isEdit;
    private boolean modified = false;
    private Connection conn;
    private JCheckBox[][] checkBoxList;
    private String[] hanhDong = {"Xem", "Thêm", "Sửa", "Xóa"};
    private String[] chucNang = {"Món ăn", "Bàn ăn", "Hóa đơn", "Nhân viên", "Phân quyền", "Tài khoản"};
    private int sizeCN, sizeHD;

    public QuyenDialog() {
        this(null);
    }

    public QuyenDialog(QuyenDTO quyen) {
        this.quyen = quyen;
        this.isEdit = (quyen != null);
        this.conn = DBConnector.getConnection();
        this.sizeCN = chucNang.length;
        this.sizeHD = hanhDong.length;
        this.checkBoxList = new JCheckBox[sizeCN][sizeHD];
        initComponents();
        setupDialog();
        if (isEdit) {
            loadData();
        }
    }

    public boolean isModified() {
        return modified;
    }

    private void setupDialog() {
        setTitle(isEdit ? "Sửa Quyền" : "Thêm Quyền Mới");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
    }

    private void loadData() {
        txtTenQuyen.setText(quyen.getTen());
        if (isEdit) {
            CTQuyenBUS ctQuyenBUS = new CTQuyenBUS();
            ArrayList<CTQuyenDTO> ctQuyenList = ctQuyenBUS.getAllByQuyenId(quyen.getId());
            for (CTQuyenDTO ctq : ctQuyenList) {
                for (int i = 0; i < sizeCN; i++) {
                    for (int j = 0; j < sizeHD; j++) {
                        if (ctq.getIdChucNang() == i + 1 && ctq.getHanhDong().equals(hanhDong[j])) {
                            checkBoxList[i][j].setSelected(true);
                        }
                    }
                }
            }
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
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tên quyền");

        txtTenQuyen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenQuyen.setPreferredSize(new java.awt.Dimension(300, 35));

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
                checkBoxList[i][j] = new JCheckBox();
                checkBoxList[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                pnl_checkbox.add(checkBoxList[i][j]);
            }
        }
        
        pnl_center.add(pnl_checkbox, java.awt.BorderLayout.CENTER);

        jPanel5.add(pnl_center, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(900, 50));

        btnLuu.setBackground(new java.awt.Color(102, 204, 255));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setText("Lưu");
        btnLuu.setBorder(null);
        btnLuu.setPreferredSize(new java.awt.Dimension(120, 40));
        btnLuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLuuMousePressed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(255, 107, 107));
        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("Hủy");
        btnHuy.setBorder(null);
        btnHuy.setPreferredSize(new java.awt.Dimension(120, 40));
        btnHuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHuyMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(290, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        jPanel3.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuMousePressed
        try {
            String ten = txtTenQuyen.getText().trim();
            if (ten.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên quyền", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            QuyenBUS quyenBUS = new QuyenBUS(conn);
            Result result;

            if (isEdit) {
                quyen.setTen(ten);
                result = quyenBUS.update(quyen);
                if (result.isSuccess()) {
                    savePermissions(quyen.getId());
                    modified = true;
                }
            } else {
                QuyenDTO newQuyen = new QuyenDTO(0, ten);
                result = quyenBUS.add(newQuyen);
                if (result.isSuccess()) {
                    quyen = quyenBUS.getById(result.getId());
                    if (quyen != null) {
                        savePermissions(quyen.getId());
                        modified = true;
                    } else {
                        JOptionPane.showMessageDialog(this, "Không thể lấy thông tin quyền mới tạo", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            if (result.isSuccess()) {
                JOptionPane.showMessageDialog(this, result.getMessage());
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, result.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnLuuMousePressed

    private void btnHuyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyMousePressed
        dispose();
    }//GEN-LAST:event_btnHuyMousePressed

    private void savePermissions(int quyenId) {
        try {
            CTQuyenBUS ctQuyenBUS = new CTQuyenBUS();
            if (isEdit) {
                ctQuyenBUS.delete(quyenId);
            }
            
            for (int i = 0; i < sizeCN; i++) {
                for (int j = 0; j < sizeHD; j++) {
                    if (checkBoxList[i][j].isSelected()) {
                        CTQuyenDTO ctq = new CTQuyenDTO(quyenId, i + 1, hanhDong[j]);
                        boolean success = ctQuyenBUS.add(ctq);
                        if (!success) {
                            throw new Exception("Không thể lưu quyền cho chức năng: " + chucNang[i] + " - " + hanhDong[j]);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lưu quyền: " + e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
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