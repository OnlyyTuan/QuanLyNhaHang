package GUI;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.awt.BorderLayout;
import java.awt.CardLayout;
import BUS.AppData;
import BUS.BanAnBUS;
import DTO.TaiKhoanDTO;
import GUI.Panel.SideMenu;
import javax.swing.JFrame;
import javax.swing.JPanel;
import GUI.Panel.*;
/**
 *
 * @author MSI
 */
public class MainFrame extends JFrame {

    private AppData appData;
    private BanAnBUS banAnBUS;
    private TaiKhoanDTO taiKhoan;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private SideMenu sideMenu;
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame(TaiKhoanDTO taiKhoan) {
        this.taiKhoan = taiKhoan;
        
        initComponents();
        setTitle("Quản Lý Nhà Hàng");
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(1200, 800);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        sideMenu = new SideMenu();
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);
        // Add content panels
        contentPanel.add(new BanAn(), "BanAn");
        contentPanel.add(new MonAn(),"MonAn");
        contentPanel.add(new NhanVien(), "NhanVien");
        contentPanel.add(new PhanQuyen(), "PhanQuyen");
        contentPanel.add(new HoaDon(), "HoaDon");
        contentPanel.add(new TaiKhoan(), "TaiKhoan");

        sideMenu.setContentPanel(contentPanel, cardLayout);

        add(sideMenu, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);


    }

    public SideMenu getSideMenu() {
        return sideMenu;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        
        
        SideMenu sideMenu = new SideMenu();
        add(sideMenu, BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
