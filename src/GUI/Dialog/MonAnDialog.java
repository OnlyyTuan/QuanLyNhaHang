/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.Dialog;

import DTO.MonAnDTO;
import DTO.DanhMucMonAnDTO;
import BUS.MonAnBUS;
import DAO.DanhMucMonAnDAO;
import config.DBConnector;
import BUS.Result;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 *
 * @author MSI
 */
public class MonAnDialog extends javax.swing.JDialog {
    private MonAnBUS monAnBUS;
    private DanhMucMonAnDAO danhMucDAO;
    
    /**
     * Creates new form MonAnDialog
     */
    public MonAnDialog() {
        initComponents();
        setTitle("Thêm Món Ăn Mới");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        
        // Initialize DAO and BUS
        Connection conn = DBConnector.getConnection();
        monAnBUS = new MonAnBUS(conn);
        danhMucDAO = DanhMucMonAnDAO.getInstance();
        
        // Load danh mục vào ComboBox
        loadDanhMucComboBox();
    }

    private void loadDanhMucComboBox() {
        ArrayList<DanhMucMonAnDTO> danhMucList = danhMucDAO.selectAll();
        DefaultComboBoxModel<DanhMucMonAnDTO> model = new DefaultComboBoxModel<>();
        for (DanhMucMonAnDTO dm : danhMucList) {
            model.addElement(dm);
        }
        jComboBoxDanhMuc.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jTextFieldTen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxDanhMuc = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldGia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldHinhAnh = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxTrangThai = new javax.swing.JComboBox<>();
        jButtonThem = new javax.swing.JButton();
        jButtonChonHinh = new javax.swing.JButton();
        jLabelHinhAnh = new javax.swing.JLabel();

        jLabel1.setText("Tên món ăn:");
        jLabel2.setText("Danh mục:");
        jLabel3.setText("Giá:");
        jLabel4.setText("Hình ảnh:");
        jLabel5.setText("Trạng thái:");

        jComboBoxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn", "Hết" }));

        jButtonThem.setText("Thêm");
        jButtonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemActionPerformed(evt);
            }
        });

        jButtonChonHinh.setText("Chọn hình");
        jButtonChonHinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChonHinhActionPerformed(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldTen)
                    .addComponent(jComboBoxDanhMuc, 0, 250, Short.MAX_VALUE)
                    .addComponent(jTextFieldGia)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldHinhAnh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonChonHinh))
                    .addComponent(jComboBoxTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jComboBoxDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonChonHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jButtonThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemActionPerformed
        try {
            MonAnDTO monAn = new MonAnDTO();
            monAn.setTen(jTextFieldTen.getText());
            
            // Lấy ID danh mục từ ComboBox
            DanhMucMonAnDTO selectedDanhMuc = (DanhMucMonAnDTO) jComboBoxDanhMuc.getSelectedItem();
            monAn.setIdDanhMuc(selectedDanhMuc.getId());
            
            monAn.setGia(Integer.parseInt(jTextFieldGia.getText()));
            monAn.setHinhAnh(jTextFieldHinhAnh.getText());
            monAn.setTrangThai(jComboBoxTrangThai.getSelectedIndex() == 0 ? 1 : 0);

            Result result = monAnBUS.add(monAn);
            if (result.isSuccess()) {
                JOptionPane.showMessageDialog(this, result.getMessage());
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, result.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá là số", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonThemActionPerformed

    private void jButtonChonHinhActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                String extension = getExtension(f);
                if (extension != null) {
                    return extension.equals("jpg") || extension.equals("jpeg") || 
                           extension.equals("png") || extension.equals("gif");
                }
                return false;
            }
            
            public String getDescription() {
                return "Image Files (*.jpg, *.jpeg, *.png, *.gif)";
            }
            
            private String getExtension(File f) {
                String ext = null;
                String s = f.getName();
                int i = s.lastIndexOf('.');
                if (i > 0 && i < s.length() - 1) {
                    ext = s.substring(i + 1).toLowerCase();
                }
                return ext;
            }
        });
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            // Lưu hình ảnh vào thư mục images trong project
            try {
                String projectPath = System.getProperty("user.dir");
                String imagesDir = projectPath + File.separator + "images";
                File imagesFolder = new File(imagesDir);
                if (!imagesFolder.exists()) {
                    imagesFolder.mkdir();
                }
                
                // Tạo tên file mới với timestamp để tránh trùng lặp
                String newFileName = "food_" + System.currentTimeMillis() + "." + getExtension(selectedFile);
                File destFile = new File(imagesDir + File.separator + newFileName);
                
                // Copy file
                java.nio.file.Files.copy(selectedFile.toPath(), destFile.toPath(), 
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                
                // Lưu đường dẫn tương đối vào database
                jTextFieldHinhAnh.setText("images/" + newFileName);
                
                // Hiển thị preview
                ImageIcon icon = new ImageIcon(destFile.getPath());
                Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                jLabelHinhAnh.setIcon(new ImageIcon(image));
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Lỗi khi lưu hình ảnh: " + e.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonThem;
    private javax.swing.JComboBox<DanhMucMonAnDTO> jComboBoxDanhMuc;
    private javax.swing.JComboBox<String> jComboBoxTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextFieldGia;
    private javax.swing.JTextField jTextFieldHinhAnh;
    private javax.swing.JTextField jTextFieldTen;
    private javax.swing.JButton jButtonChonHinh;
    private javax.swing.JLabel jLabelHinhAnh;
    // End of variables declaration//GEN-END:variables
}
