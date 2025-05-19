package GUI.Dialog;

import DTO.NhanVienDTO;
import BUS.NhanVienBUS;
import BUS.Result;
import config.DBConnector;

import javax.swing.*;
import java.sql.Connection;

public class NhanVienDialog extends JDialog {
    private NhanVienDTO nv;
    private boolean isEdit;
    private Connection conn;

    // Các component giao diện
    private JLabel jLabelHoTen, jLabelGioiTinh, jLabelChucVu, jLabelTrangThai;
    private JTextField jTextFieldHoTen, jTextFieldChucVu;
    private JComboBox<String> jComboBoxGioiTinh, jComboBoxTrangThai;
    private JButton jButtonSave, jButtonCancel;

    public NhanVienDialog() {
        this(null);
    }

    public NhanVienDialog(Integer nhanVienId) {
        this.conn = DBConnector.getConnection();
        this.isEdit = (nhanVienId != null);

        initComponents();
        setupDialog();

        if (isEdit) {
            loadNhanVienById(nhanVienId);
        }
    }

    private void setupDialog() {
        setTitle(isEdit ? "Sửa Nhân Viên" : "Thêm Nhân Viên Mới");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
    }

    private void loadNhanVienById(int id) {
        try {
            NhanVienBUS bus = new NhanVienBUS(conn);
            nv = bus.getById(id);
            if (nv != null) {
                jTextFieldHoTen.setText(nv.getHoTen());
                jComboBoxGioiTinh.setSelectedItem(nv.getGioiTinh());
                jTextFieldChucVu.setText(nv.getChucVu());
                jComboBoxTrangThai.setSelectedIndex(nv.getTrangThai() == 1 ? 0 : 1);
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên có ID: " + id, "Lỗi", JOptionPane.ERROR_MESSAGE);
                dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu nhân viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }

    private void initComponents() {
        jLabelHoTen = new JLabel("Họ tên:");
        jLabelGioiTinh = new JLabel("Giới tính:");
        jLabelChucVu = new JLabel("Chức vụ:");
        jLabelTrangThai = new JLabel("Trạng thái:");

        jTextFieldHoTen = new JTextField();
        jTextFieldChucVu = new JTextField();

        jComboBoxGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
        jComboBoxTrangThai = new JComboBox<>(new String[]{"Đang làm việc", "Đã nghỉ"});

        jButtonSave = new JButton("Lưu");
        jButtonCancel = new JButton("Hủy");

        jButtonSave.addActionListener(evt -> jButtonSaveActionPerformed());
        jButtonCancel.addActionListener(evt -> dispose());

        // Layout đơn giản dùng GroupLayout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelHoTen)
                        .addComponent(jLabelGioiTinh)
                        .addComponent(jLabelChucVu)
                        .addComponent(jLabelTrangThai))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldHoTen)
                        .addComponent(jComboBoxGioiTinh)
                        .addComponent(jTextFieldChucVu)
                        .addComponent(jComboBoxTrangThai)))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jButtonSave, 120, 120, 120)
                    .addGap(18)
                    .addComponent(jButtonCancel, 120, 120, 120))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelHoTen)
                    .addComponent(jTextFieldHoTen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelGioiTinh)
                    .addComponent(jComboBoxGioiTinh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelChucVu)
                    .addComponent(jTextFieldChucVu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTrangThai)
                    .addComponent(jComboBoxTrangThai, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
        );
    }

    private void jButtonSaveActionPerformed() {
        String hoTen = jTextFieldHoTen.getText().trim();
        String gioiTinh = (String) jComboBoxGioiTinh.getSelectedItem();
        String chucVu = jTextFieldChucVu.getText().trim();
        int trangThai = (jComboBoxTrangThai.getSelectedIndex() == 0) ? 1 : 0;

        if (hoTen.isEmpty() || chucVu.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            NhanVienBUS bus = new NhanVienBUS(conn);
            Result result;

            if (isEdit) {
                nv.setHoTen(hoTen);
                nv.setGioiTinh(gioiTinh);
                nv.setChucVu(chucVu);
                nv.setTrangThai(trangThai);

                result = bus.update(nv);
            } else {
                NhanVienDTO newNv = new NhanVienDTO(0, hoTen, gioiTinh, chucVu, trangThai);
                result = bus.add(newNv);
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
}
