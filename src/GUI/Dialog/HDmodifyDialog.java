package GUI.Dialog;

import DTO.HoaDonDTO;
import DTO.BanAnDTO;
import DTO.NhanVienDTO;
import BUS.HoaDonBUS;
import BUS.BanAnBUS;
import BUS.NhanVienBUS;
import BUS.Result;
import config.DBConnector;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class HDmodifyDialog extends javax.swing.JDialog {
    private HoaDonBUS hoaDonBUS;
    private BanAnBUS banAnBUS;
    private NhanVienBUS nhanVienBUS;
    private HoaDonDTO hoaDon;
    private boolean isModified = false;
    private JComboBox<BanAnDTO> jComboBoxBanAn;
    private JComboBox<NhanVienDTO> jComboBoxNhanVien;
    
    public HDmodifyDialog(javax.swing.JFrame parent, HoaDonDTO hoaDon) {
        super(parent, true);
        this.hoaDon = hoaDon;
        initComponents();
        setTitle("Chỉnh sửa hóa đơn");
        setSize(500, 600);
        setLocationRelativeTo(null);
        
        // Initialize BUS
        Connection conn = DBConnector.getConnection();
        hoaDonBUS = new HoaDonBUS(conn);
        banAnBUS = new BanAnBUS(conn);
        nhanVienBUS = new NhanVienBUS(conn);
        
        // Initialize comboboxes
        setupComboBoxes();
        
        // Load current data
        loadCurrentData();
    }
    
    private void setupComboBoxes() {
        // Setup Ban An combobox
        List<BanAnDTO> banAnList = banAnBUS.getList_banAn();
        for (BanAnDTO ban : banAnList) {
            jComboBoxBanAn.addItem(ban);
        }
        jComboBoxBanAn.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                javax.swing.JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof BanAnDTO) {
                    BanAnDTO ban = (BanAnDTO) value;
                    setText(String.format("ID: %d - %s (%s)", 
                        ban.getId(), 
                        ban.getTen(),
                        ban.getTrangThai() == 1 ? "Đang sử dụng" : "Trống"));
                }
                return this;
            }
        });
        
        // Setup Nhan Vien combobox
        List<NhanVienDTO> nhanVienList = nhanVienBUS.getList_nhanVien();
        for (NhanVienDTO nv : nhanVienList) {
            jComboBoxNhanVien.addItem(nv);
        }
        jComboBoxNhanVien.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                javax.swing.JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof NhanVienDTO) {
                    NhanVienDTO nv = (NhanVienDTO) value;
                    setText(String.format("ID: %d - %s", nv.getId(), nv.getHoTen()));
                }
                return this;
            }
        });
    }
    
    private void loadCurrentData() {
        jTextFieldTenKH.setText(hoaDon.getTenKhach());
        jTextFieldTongTien.setText(String.valueOf(hoaDon.getTongTien()));
        jTextFieldGhiChu.setText(hoaDon.getGhiChu());
        
        // Set date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        jTextFieldThoiGian.setText(dateFormat.format(hoaDon.getThoiGian()));
        
        // Set selected items in comboboxes
        for (int i = 0; i < jComboBoxBanAn.getItemCount(); i++) {
            BanAnDTO ban = jComboBoxBanAn.getItemAt(i);
            if (ban.getId() == hoaDon.getIdBanAn()) {
                jComboBoxBanAn.setSelectedIndex(i);
                break;
            }
        }
        
        for (int i = 0; i < jComboBoxNhanVien.getItemCount(); i++) {
            NhanVienDTO nv = jComboBoxNhanVien.getItemAt(i);
            if (nv.getId() == hoaDon.getIdNhanVien()) {
                jComboBoxNhanVien.setSelectedIndex(i);
                break;
            }
        }
    }
    
    public boolean isModified() {
        return isModified;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jTextFieldTenKH = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldTongTien = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldThoiGian = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldGhiChu = new javax.swing.JTextField();
        jButtonCapNhat = new javax.swing.JButton();
        jButtonHuy = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxBanAn = new javax.swing.JComboBox<>();
        jComboBoxNhanVien = new javax.swing.JComboBox<>();

        jLabel1.setText("Tên khách hàng:");
        jLabel2.setText("Tổng tiền:");
        jLabel3.setText("Thời gian:");
        jLabel4.setText("Ghi chú:");
        jLabel5.setText("Bàn ăn:");
        jLabel6.setText("Nhân viên:");

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
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldTenKH)
                    .addComponent(jTextFieldTongTien)
                    .addComponent(jTextFieldThoiGian)
                    .addComponent(jTextFieldGhiChu)
                    .addComponent(jComboBoxBanAn, 0, 250, Short.MAX_VALUE)
                    .addComponent(jComboBoxNhanVien, 0, 250, Short.MAX_VALUE))
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
                    .addComponent(jTextFieldTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxBanAn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCapNhatActionPerformed
        try {
            // Validate input
            if (jTextFieldTenKH.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Parse total amount
            int tongTien;
            try {
                tongTien = Integer.parseInt(jTextFieldTongTien.getText().trim());
                if (tongTien <= 0) {
                    JOptionPane.showMessageDialog(this, "Tổng tiền phải lớn hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Tổng tiền phải là số", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Parse date and time
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Timestamp thoiGian;
            try {
                thoiGian = new Timestamp(dateFormat.parse(jTextFieldThoiGian.getText().trim()).getTime());
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Định dạng thời gian không hợp lệ (dd/MM/yyyy HH:mm)", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Get selected items
            BanAnDTO selectedBan = (BanAnDTO) jComboBoxBanAn.getSelectedItem();
            NhanVienDTO selectedNV = (NhanVienDTO) jComboBoxNhanVien.getSelectedItem();

            if (selectedBan == null || selectedNV == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn bàn và nhân viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update hoa don
            hoaDon.setTenKhach(jTextFieldTenKH.getText().trim());
            hoaDon.setTongTien(tongTien);
            hoaDon.setThoiGian(thoiGian);
            hoaDon.setGhiChu(jTextFieldGhiChu.getText().trim());
            hoaDon.setIdBanAn(selectedBan.getId());
            hoaDon.setIdNhanVien(selectedNV.getId());

            Result result = hoaDonBUS.update(hoaDon);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldGhiChu;
    private javax.swing.JTextField jTextFieldTenKH;
    private javax.swing.JTextField jTextFieldThoiGian;
    private javax.swing.JTextField jTextFieldTongTien;
    // End of variables declaration//GEN-END:variables
} 