package GUI.Dialog;

import DTO.QuyenDTO;
import BUS.QuyenBUS;
import BUS.Result;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import java.sql.Connection;
import config.DBConnector;

public class QuyenDialog extends JDialog {
    private QuyenDTO quyen;
    private boolean isEdit;
    private Connection conn;

    public QuyenDialog() {
        this(null);
    }

    public QuyenDialog(QuyenDTO quyen) {
        this.quyen = quyen;
        this.isEdit = (quyen != null);
        this.conn = DBConnector.getConnection();
        initComponents();
        setupDialog();
        if (isEdit) {
            loadData();
        }
    }

    private void setupDialog() {
        setTitle(isEdit ? "Sửa Quyền" : "Thêm Quyền Mới");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
    }

    private void loadData() {
        jTextFieldTen.setText(quyen.getTen());
    }

    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jTextFieldTen = new javax.swing.JTextField();
        jButtonSave = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        jLabel1.setText("Tên quyền:");
        jButtonSave.setText("Lưu");
        jButtonCancel.setText("Hủy");

        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jTextFieldTen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldTen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String ten = jTextFieldTen.getText().trim();
            if (ten.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên quyền", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            QuyenBUS quyenBUS = new QuyenBUS(conn);
            Result result;

            if (isEdit) {
                quyen.setTen(ten);
                result = quyenBUS.update(quyen);
            } else {
                QuyenDTO newQuyen = new QuyenDTO(0, ten);
                result = quyenBUS.add(newQuyen);
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
    private javax.swing.JTextField jTextFieldTen;
    // End of variables declaration
} 