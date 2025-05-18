package GUI.Dialog;

import DTO.BanAnDTO;
import DTO.HoaDonDTO;
import BUS.BanAnBUS;
import BUS.HoaDonBUS;
import config.DBConnector;
import java.sql.Connection;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;

public class BAdetailDialog extends JDialog {
    private BanAnDTO banAn;
    private BanAnBUS banAnBUS;
    private HoaDonBUS hoaDonBUS;

    public BAdetailDialog(javax.swing.JFrame parent, BanAnDTO banAn) {
        super(parent, true);
        this.banAn = banAn;
        initComponents();
        setupDialog();
        loadData();
    }

    private void setupDialog() {
        setTitle("Chi tiết bàn ăn");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
    }

    private void loadData() {
        try {
            Connection conn = DBConnector.getConnection();
            banAnBUS = new BanAnBUS(conn);
            hoaDonBUS = new HoaDonBUS(conn);

            // Load thông tin cơ bản
            jTextFieldID.setText(String.valueOf(banAn.getId()));
            jTextFieldTen.setText(banAn.getTen());
            jTextFieldTrangThai.setText(banAn.getTrangThai() == 1 ? "Đang sử dụng" : "Trống");

            // Load thông tin khách hàng và hóa đơn hiện tại
            if (banAn.getTrangThai() == 1) {
                HoaDonDTO hoaDonHienTai = hoaDonBUS.getHoaDonByBanAn(banAn.getId());
                if (hoaDonHienTai != null) {
                    jTextFieldKhachHang.setText(hoaDonHienTai.getTenKhach());
                    jTextFieldHoaDon.setText(String.valueOf(hoaDonHienTai.getId()));
                    jTextFieldTongTien.setText(String.format("%,d VNĐ", hoaDonHienTai.getTongTien()));
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    jTextFieldThoiGian.setText(dateFormat.format(hoaDonHienTai.getThoiGian()));
                }
            }

            // Load lịch sử hóa đơn
            List<HoaDonDTO> lichSuHoaDon = hoaDonBUS.getLichSuHoaDonByBanAn(banAn.getId());
            DefaultTableModel model = (DefaultTableModel) jTableLichSu.getModel();
            model.setRowCount(0);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            for (HoaDonDTO hd : lichSuHoaDon) {
                model.addRow(new Object[]{
                    hd.getId(),
                    hd.getTenKhach(),
                    String.format("%,d VNĐ", hd.getTongTien()),
                    dateFormat.format(hd.getThoiGian()),
                    hd.getGhiChu()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        // Initialize components
        jLabel1 = new javax.swing.JLabel();
        jTextFieldID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldTrangThai = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldKhachHang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldHoaDon = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldTongTien = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldThoiGian = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLichSu = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jButtonClose = new javax.swing.JButton();

        // Set labels
        jLabel1.setText("ID:");
        jLabel2.setText("Tên bàn:");
        jLabel3.setText("Trạng thái:");
        jLabel4.setText("Khách hàng:");
        jLabel5.setText("Hóa đơn hiện tại:");
        jLabel6.setText("Tổng tiền:");
        jLabel7.setText("Thời gian:");
        jLabel8.setText("Lịch sử hóa đơn:");
        jButtonClose.setText("Đóng");

        // Make text fields read-only
        jTextFieldID.setEditable(false);
        jTextFieldTen.setEditable(false);
        jTextFieldTrangThai.setEditable(false);
        jTextFieldKhachHang.setEditable(false);
        jTextFieldHoaDon.setEditable(false);
        jTextFieldTongTien.setEditable(false);
        jTextFieldThoiGian.setEditable(false);

        // Setup table
        jTableLichSu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID", "Khách hàng", "Tổng tiền", "Thời gian", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableLichSu);

        // Add button listener
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });

        // Set fonts
        java.awt.Font labelFont = new java.awt.Font("Segoe UI", 0, 14);
        java.awt.Font textFont = new java.awt.Font("Segoe UI", 0, 14);
        java.awt.Font buttonFont = new java.awt.Font("Segoe UI", 1, 14);
        java.awt.Font titleFont = new java.awt.Font("Segoe UI", 1, 16);

        jLabel1.setFont(labelFont);
        jLabel2.setFont(labelFont);
        jLabel3.setFont(labelFont);
        jLabel4.setFont(labelFont);
        jLabel5.setFont(labelFont);
        jLabel6.setFont(labelFont);
        jLabel7.setFont(labelFont);
        jLabel8.setFont(titleFont);

        jTextFieldID.setFont(textFont);
        jTextFieldTen.setFont(textFont);
        jTextFieldTrangThai.setFont(textFont);
        jTextFieldKhachHang.setFont(textFont);
        jTextFieldHoaDon.setFont(textFont);
        jTextFieldTongTien.setFont(textFont);
        jTextFieldThoiGian.setFont(textFont);

        jButtonClose.setFont(buttonFont);
        jButtonClose.setBackground(new java.awt.Color(204, 0, 0));
        jButtonClose.setForeground(new java.awt.Color(255, 255, 255));

        // Layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldID)
                            .addComponent(jTextFieldTen)
                            .addComponent(jTextFieldTrangThai)
                            .addComponent(jTextFieldKhachHang)
                            .addComponent(jTextFieldHoaDon)
                            .addComponent(jTextFieldTongTien)
                            .addComponent(jTextFieldThoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldTen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jLabel8)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }

    // Variables declaration
    private javax.swing.JButton jButtonClose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableLichSu;
    private javax.swing.JTextField jTextFieldHoaDon;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldKhachHang;
    private javax.swing.JTextField jTextFieldTen;
    private javax.swing.JTextField jTextFieldThoiGian;
    private javax.swing.JTextField jTextFieldTongTien;
    private javax.swing.JTextField jTextFieldTrangThai;
    // End of variables declaration
} 