/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.hotelbooking.system;

import java.time.LocalDate;

/**
 *
 * @author Naz
 */
public class LunchSelectPage extends javax.swing.JFrame {
    
    public static Invoice invoice;
    public static String isLunch;
    private Booking booking;
    public static int noPax;

    /**
     * Creates new form LunchSelectPage
     */
    public LunchSelectPage(Booking booking) {
        this.booking = booking;
        initComponents();
        
        lunchLbl.setVisible(false);
        inputLbl.setVisible(false);
        lunchInput.setVisible(false);
        confirmBtn.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        instructionLbl = new javax.swing.JLabel();
        yesBtn = new javax.swing.JButton();
        noBtn = new javax.swing.JButton();
        lunchLbl = new javax.swing.JLabel();
        inputLbl = new javax.swing.JLabel();
        lunchInput = new javax.swing.JTextField();
        confirmBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        instructionLbl.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        instructionLbl.setText("Do you want lunch with your bookings?");

        yesBtn.setText("Yes");
        yesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesBtnActionPerformed(evt);
            }
        });

        noBtn.setText("No");
        noBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noBtnActionPerformed(evt);
            }
        });

        lunchLbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lunchLbl.setText("How many pax you want to buy lunch for? (RM14.00/pax)");

        inputLbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inputLbl.setText("Input:");

        lunchInput.setText("0");
        lunchInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lunchInputActionPerformed(evt);
            }
        });

        confirmBtn.setText("Confirm");
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lunchLbl)
                .addGap(201, 201, 201))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(yesBtn)
                        .addGap(179, 179, 179)
                        .addComponent(noBtn))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(inputLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(confirmBtn)
                            .addComponent(lunchInput, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(170, Short.MAX_VALUE)
                .addComponent(instructionLbl)
                .addGap(141, 141, 141))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(instructionLbl)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yesBtn)
                    .addComponent(noBtn))
                .addGap(74, 74, 74)
                .addComponent(lunchLbl)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputLbl)
                    .addComponent(lunchInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(confirmBtn)
                .addContainerGap(152, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void noBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noBtnActionPerformed
        // TODO add your handling code here:
        isLunch = "No";
        invoice = new Invoice(LocalDate.now(), CustomerMainPage.customer);
        invoice.addInvoiceLine(booking.getInvoiceLine());
        InvoicePage ip = new InvoicePage(invoice);
        this.setVisible(false);
        ip.setVisible(true);
    }//GEN-LAST:event_noBtnActionPerformed

    private void yesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesBtnActionPerformed
        // TODO add your handling code here:
        isLunch = "Yes";
        noBtn.setEnabled(false);
        lunchLbl.setVisible(true);
        inputLbl.setVisible(true);
        lunchInput.setVisible(true);
        confirmBtn.setVisible(true);
    }//GEN-LAST:event_yesBtnActionPerformed

    private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBtnActionPerformed
        // TODO add your handling code here:
        
        
        noPax = Integer.valueOf(lunchInput.getText());
        
        Lunch lunch = (Lunch)Hotel.getInstance().provideService("lunch", CustomerMainPage.customer, noPax);
        invoice = new Invoice(LocalDate.now(), CustomerMainPage.customer);
        invoice.addInvoiceLine(booking.getInvoiceLine());
        invoice.addInvoiceLine(lunch.getLunchInvoiceLine());
        
        InvoicePage ip = new InvoicePage(invoice);
        this.setVisible(false);
        ip.setVisible(true);
    }//GEN-LAST:event_confirmBtnActionPerformed

    private void lunchInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lunchInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lunchInputActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirmBtn;
    private javax.swing.JLabel inputLbl;
    private javax.swing.JLabel instructionLbl;
    private javax.swing.JTextField lunchInput;
    private javax.swing.JLabel lunchLbl;
    private javax.swing.JButton noBtn;
    private javax.swing.JButton yesBtn;
    // End of variables declaration//GEN-END:variables
}
