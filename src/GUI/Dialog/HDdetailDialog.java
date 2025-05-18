package GUI.Dialog;

import DTO.HoaDonDTO;
import BUS.HoaDonBUS;
import config.DBConnector;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class HDdetailDialog extends javax.swing.JDialog {
    private HoaDonDTO hoaDon;
    private HoaDonBUS hoaDonBUS;
    
    public HDdetailDialog(java.awt.Frame parent, HoaDonDTO hoaDon) {
        super(parent, true);
        this.hoaDon = hoaDon;
        
        initComponents();
        setTitle("Chi tiết hóa đơn");
        setSize(500, 500);
        setLocationRelativeTo(parent);
        
        // Initialize BUS
        Connection conn = DBConnector.getConnection();
        hoaDonBUS = new HoaDonBUS(conn);
        
        // Load data
        loadData();
    }

    private void loadData() {
        try {
            // Set basic information
            jTextFieldID.setText(String.valueOf(hoaDon.getId()));
            jTextFieldTenKhach.setText(hoaDon.getTenKhach());
            jTextFieldBanAn.setText(String.valueOf(hoaDon.getIdBanAn()));
            jTextFieldTongTien.setText(String.format("%,d VNĐ", hoaDon.getTongTien()));
            
            // Format date
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            jTextFieldThoiGian.setText(dateFormat.format(hoaDon.getThoiGian()));
            
            // Set note if exists
            if (hoaDon.getGhiChu() != null && !hoaDon.getGhiChu().isEmpty()) {
                jTextAreaGhiChu.setText(hoaDon.getGhiChu());
            }
            
            // Set all fields to read-only
            jTextFieldID.setEditable(false);
            jTextFieldTenKhach.setEditable(false);
            jTextFieldBanAn.setEditable(false);
            jTextFieldTongTien.setEditable(false);
            jTextFieldThoiGian.setEditable(false);
            jTextAreaGhiChu.setEditable(false);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Lỗi khi tải dữ liệu: " + e.getMessage(),
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
        jTextFieldTenKhach = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldBanAn = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldTongTien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldThoiGian = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaGhiChu = new javax.swing.JTextArea();
        jButtonDong = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ID:");
        jLabel2.setText("Tên khách hàng:");
        jLabel3.setText("Bàn ăn:");
        jLabel4.setText("Tổng tiền:");
        jLabel5.setText("Thời gian:");
        jLabel6.setText("Ghi chú:");

        jTextAreaGhiChu.setColumns(20);
        jTextAreaGhiChu.setRows(5);
        jScrollPane1.setViewportView(jTextAreaGhiChu);

        jButtonDong.setBackground(new java.awt.Color(255, 51, 51));
        jButtonDong.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jButtonDong.setForeground(new java.awt.Color(255, 255, 255));
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldBanAn, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(jTextFieldTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldBanAn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaGhiChu;
    private javax.swing.JTextField jTextFieldBanAn;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldTenKhach;
    private javax.swing.JTextField jTextFieldThoiGian;
    private javax.swing.JTextField jTextFieldTongTien;
    // End of variables declaration//GEN-END:variables
} 