package GUI.Dialog;

import DTO.MonAnDTO;
import DTO.DanhMucMonAnDTO;
import BUS.MonAnBUS;
import DAO.DanhMucMonAnDAO;
import config.DBConnector;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Image;
import java.io.File;

public class MAdetailDialog extends javax.swing.JDialog {
    private MonAnDTO monAn;
    private MonAnBUS monAnBUS;
    private DanhMucMonAnDAO danhMucDAO;
    
    public MAdetailDialog(java.awt.Frame parent, MonAnDTO monAn) {
        super(parent, true);
        this.monAn = monAn;
        
        initComponents();
        setTitle("Chi tiết món ăn");
        setSize(500, 600);
        setLocationRelativeTo(parent);
        
        // Initialize DAO and BUS
        Connection conn = DBConnector.getConnection();
        monAnBUS = new MonAnBUS(conn);
        danhMucDAO = DanhMucMonAnDAO.getInstance();
        
        // Load data
        loadData();
    }

    private void loadData() {
        try {
            // Set basic information
            jTextFieldID.setText(String.valueOf(monAn.getId()));
            jTextFieldTen.setText(monAn.getTen());
            jTextFieldGia.setText(String.format("%,d VNĐ", monAn.getGia()));
            jTextFieldTrangThai.setText(monAn.getTrangThai() == 1 ? "Còn" : "Hết");
            
            // Set category
            DanhMucMonAnDTO danhMuc = danhMucDAO.selectById(String.valueOf(monAn.getIdDanhMuc()));
            if (danhMuc != null) {
                jTextFieldDanhMuc.setText(danhMuc.getTen());
            }
            
            // Load image
            String imagePath = monAn.getHinhAnh();
            if (imagePath != null && !imagePath.isEmpty()) {
                File imageFile = new File(imagePath);
                if (imageFile.exists()) {
                    ImageIcon icon = new ImageIcon(imagePath);
                    Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    jLabelHinhAnh.setIcon(new ImageIcon(image));
                } else {
                    jLabelHinhAnh.setText("Không tìm thấy hình ảnh");
                }
            } else {
                jLabelHinhAnh.setText("Không có hình ảnh");
            }
            
            // Set all fields to read-only
            jTextFieldID.setEditable(false);
            jTextFieldTen.setEditable(false);
            jTextFieldGia.setEditable(false);
            jTextFieldTrangThai.setEditable(false);
            jTextFieldDanhMuc.setEditable(false);
            
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
        jTextFieldTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldDanhMuc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldGia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldTrangThai = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabelHinhAnh = new javax.swing.JLabel();
        jButtonDong = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ID:");
        jLabel2.setText("Tên món ăn:");
        jLabel3.setText("Danh mục:");
        jLabel4.setText("Giá:");
        jLabel5.setText("Trạng thái:");
        jLabel6.setText("Hình ảnh:");

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
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTen, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldGia, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jTextFieldTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabelHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelHinhAnh;
    private javax.swing.JTextField jTextFieldDanhMuc;
    private javax.swing.JTextField jTextFieldGia;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldTen;
    private javax.swing.JTextField jTextFieldTrangThai;
    // End of variables declaration//GEN-END:variables
} 