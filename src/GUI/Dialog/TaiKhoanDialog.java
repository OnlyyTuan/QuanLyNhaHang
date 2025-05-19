package GUI.Dialog;

import DTO.TaiKhoanDTO;
import DTO.NhanVienDTO;
import DTO.QuyenDTO;
import BUS.TaiKhoanBUS;
import BUS.NhanVienBUS;
import BUS.QuyenBUS;
import BUS.Result;
import config.DBConnector;
import java.sql.Connection;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class TaiKhoanDialog extends JDialog {
    private TaiKhoanDTO taiKhoan;
    private boolean isEdit;
    private javax.swing.JComboBox<NhanVienDTO> jComboBoxNhanVien;
    private javax.swing.JComboBox<QuyenDTO> jComboBoxQuyen;
    private javax.swing.JComboBox<String> jComboBoxTrangThai;

    public TaiKhoanDialog() {
        this(null);
    }

    public TaiKhoanDialog(TaiKhoanDTO taiKhoan) {
        this.taiKhoan = taiKhoan;
        this.isEdit = (taiKhoan != null);
        initComponents();
        setupDialog();
        setupComboBoxes();
        if (isEdit) {
            loadData();
        }
    }

    private void setupDialog() {
        setTitle(isEdit ? "Sửa Tài Khoản" : "Thêm Tài Khoản Mới");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
    }

    private void setupComboBoxes() {
        try {
            Connection conn = DBConnector.getConnection();

            // Setup NhanVien ComboBox
            NhanVienBUS nhanVienBUS = new NhanVienBUS(conn);
            List<NhanVienDTO> nhanVienList = nhanVienBUS.getListNhanVien();
            jComboBoxNhanVien.removeAllItems();
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
                        setText(((NhanVienDTO) value).getHoTen());
                    }
                    return this;
                }
            });

            // Setup Quyen ComboBox
            QuyenBUS quyenBUS = new QuyenBUS(conn);
            List<QuyenDTO> quyenList = quyenBUS.getListQuyen();
            jComboBoxQuyen.removeAllItems();
            for (QuyenDTO q : quyenList) {
                jComboBoxQuyen.addItem(q);
            }
            jComboBoxQuyen.setRenderer(new javax.swing.DefaultListCellRenderer() {
                @Override
                public java.awt.Component getListCellRendererComponent(
                        javax.swing.JList<?> list, Object value, int index,
                        boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof QuyenDTO) {
                        setText(((QuyenDTO) value).getTen());
                    }
                    return this;
                }
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }

    private void loadData() {
        jTextFieldTaiKhoan.setText(taiKhoan.getTenTaiKhoan());
        jTextFieldMatKhau.setText(taiKhoan.getMatKhau());

        // Set selected employee
        for (int i = 0; i < jComboBoxNhanVien.getItemCount(); i++) {
            NhanVienDTO nv = jComboBoxNhanVien.getItemAt(i);
            if (nv.getId() == taiKhoan.getIdNhanVien()) {
                jComboBoxNhanVien.setSelectedIndex(i);
                break;
            }
        }

        // Set selected role
        for (int i = 0; i < jComboBoxQuyen.getItemCount(); i++) {
            QuyenDTO q = jComboBoxQuyen.getItemAt(i);
            if (q.getId() == taiKhoan.getIdQuyen()) {
                jComboBoxQuyen.setSelectedIndex(i);
                break;
            }
        }

        // Set status
        jComboBoxTrangThai.setSelectedIndex(taiKhoan.getTrangThai() == 1 ? 0 : 1);
    }

    private void initComponents() {
        // Labels
        jLabel1 = new javax.swing.JLabel("Tên tài khoản:");
        jLabel2 = new javax.swing.JLabel("Mật khẩu:");
        jLabel3 = new javax.swing.JLabel("Nhân viên:");
        jLabel4 = new javax.swing.JLabel("Quyền:");
        jLabel5 = new javax.swing.JLabel("Trạng thái:");

        // Text fields
        jTextFieldTaiKhoan = new javax.swing.JTextField();
        jTextFieldMatKhau = new javax.swing.JTextField();

        // ComboBoxes
        jComboBoxNhanVien = new javax.swing.JComboBox<>();
        jComboBoxQuyen = new javax.swing.JComboBox<>();
        jComboBoxTrangThai = new javax.swing.JComboBox<>(new String[]{"Hoạt động", "Khóa"});

        // Buttons
        jButtonSave = new javax.swing.JButton("Lưu");
        jButtonCancel = new javax.swing.JButton("Hủy");

        jButtonSave.addActionListener(evt -> jButtonSaveActionPerformed(evt));
        jButtonCancel.addActionListener(evt -> dispose());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18)
                                                .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldTaiKhoan)
                                        .addComponent(jTextFieldMatKhau)
                                        .addComponent(jComboBoxNhanVien, 0, 250, Short.MAX_VALUE)
                                        .addComponent(jComboBoxQuyen, 0, 250, Short.MAX_VALUE)
                                        .addComponent(jComboBoxTrangThai, 0, 250, Short.MAX_VALUE))
                                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jTextFieldTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jTextFieldMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(jComboBoxNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jComboBoxQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(jComboBoxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(30, Short.MAX_VALUE))
        );
    }

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String taiKhoanStr = jTextFieldTaiKhoan.getText().trim();
            String matKhauStr = jTextFieldMatKhau.getText().trim();

            if (taiKhoanStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên tài khoản", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (matKhauStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            NhanVienDTO selectedNV = (NhanVienDTO) jComboBoxNhanVien.getSelectedItem();
            QuyenDTO selectedQuyen = (QuyenDTO) jComboBoxQuyen.getSelectedItem();
            int trangThai = jComboBoxTrangThai.getSelectedIndex() == 0 ? 1 : 0;

            TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS(DBConnector.getConnection());
            Result result;

            if (isEdit) {
                this.taiKhoan.setTenTaiKhoan(taiKhoanStr);
                this.taiKhoan.setMatKhau(matKhauStr);
                this.taiKhoan.setIdNhanVien(selectedNV.getId());
                this.taiKhoan.setIdQuyen(selectedQuyen.getId());
                this.taiKhoan.setTrangThai(trangThai);
                result = taiKhoanBUS.update(this.taiKhoan);
            } else {
                TaiKhoanDTO newTK = new TaiKhoanDTO(0, selectedNV.getId(), selectedQuyen.getId(),
                        taiKhoanStr, matKhauStr, trangThai);
                result = taiKhoanBUS.add(newTK);
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
    }

    // Variables declaration
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextFieldTaiKhoan;
    private javax.swing.JTextField jTextFieldMatKhau;
}
