/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.hotelbooking.system;

import java.time.Duration;
import java.time.LocalDate;

/**
 *
 * @author Naz
 */
public class LandingPage extends javax.swing.JFrame {

    /**
     * Creates new form LandingPage
     */
    public LandingPage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginBtn = new javax.swing.JButton();
        registerBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hotel Booking System");
        setName("LandingPageFrame"); // NOI18N
        setResizable(false);

        loginBtn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        registerBtn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        registerBtn.setText("Register");
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(369, 369, 369)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(registerBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loginBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(396, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(loginBtn)
                .addGap(56, 56, 56)
                .addComponent(registerBtn)
                .addContainerGap(218, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        LoginPage loginPage = new LoginPage();
        this.setVisible(false);
        loginPage.setVisible(true);
    }//GEN-LAST:event_loginBtnActionPerformed

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registerBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Hotel hotelUMP = Hotel.getInstance().setHotelName("UMP Hotel");

        hotelUMP.addCustomer(new User(0, "Admin", "1337"));
        hotelUMP.addCustomer(new User(1, "Alice Smith", "1234567890"));
        hotelUMP.addCustomer(new User(2, "Bob Johnson", "2345678901"));
        hotelUMP.addCustomer(new User(3, "Charlie Brown", "3456789012"));
        hotelUMP.addCustomer(new User(4, "Diana Prince", "4567890123"));
        hotelUMP.addCustomer(new User(5, "Edward King", "5678901234"));
        hotelUMP.addCustomer(new User(6, "Fiona Queen", "6789012345"));

        Invoice testInvoice = new Invoice(LocalDate.of(1970, 1, 1), hotelUMP.getCustomerByID(0).get());
        Booking testBook = null;
        testBook = (Booking)hotelUMP.provideService(
            "booking",
            hotelUMP.getCustomerByID(6).get(), 
            hotelUMP.getRooms()[3], 
            LocalDate.of(2023, 12, 20), 
            Duration.ofDays(1));
        testInvoice.addInvoiceLine(testBook.getInvoiceLine());

        testBook = (Booking)hotelUMP.provideService(
            "booking",
            hotelUMP.getCustomerByID(5).get(), 
            hotelUMP.getRooms()[1], 
            LocalDate.of(2023, 12, 20), 
            Duration.ofDays(1));
        testInvoice.addInvoiceLine(testBook.getInvoiceLine());
        
        testBook = (Booking)hotelUMP.provideService(
            "booking",
            hotelUMP.getCustomerByID(4).get(), 
            hotelUMP.getRooms()[7], 
            LocalDate.of(2023, 12, 20), 
            Duration.ofDays(1));
        testInvoice.addInvoiceLine(testBook.getInvoiceLine());
        
        testBook = (Booking)hotelUMP.provideService(
            "booking",
            hotelUMP.getCustomerByID(3).get(), 
            hotelUMP.getRooms()[1], 
            LocalDate.of(2023, 12, 25), 
            Duration.ofDays(1));
        testInvoice.addInvoiceLine(testBook.getInvoiceLine());
        
        testBook = (Booking)hotelUMP.provideService(
            "booking",
            hotelUMP.getCustomerByID(6).get(), 
            hotelUMP.getRooms()[0], 
            LocalDate.of(2023, 12, 25), 
            Duration.ofDays(3));
        testInvoice.addInvoiceLine(testBook.getInvoiceLine());
        testInvoice.payByCash();
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LandingPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LandingPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LandingPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LandingPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LandingPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton loginBtn;
    private javax.swing.JButton registerBtn;
    // End of variables declaration//GEN-END:variables
}
