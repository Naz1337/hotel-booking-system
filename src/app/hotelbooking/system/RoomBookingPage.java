/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.hotelbooking.system;

import java.time.LocalDate;
import java.time.Duration;
/**
 *
 * @author Naz
 */

public class RoomBookingPage extends javax.swing.JFrame {

    public static LocalDate strtDate;
    public static LocalDate lstDate;
    private Room[] availableRooms;
    private Duration stayDuration;
    /**
     * Creates new form RoomBookingPage
     */
    public RoomBookingPage(LocalDate strtDate, LocalDate lstDate) {
        RoomBookingPage.strtDate = strtDate;
        RoomBookingPage.lstDate = lstDate;
        this.stayDuration = Duration.between(strtDate.atStartOfDay(), lstDate.atStartOfDay());
        String chosenDateMsg = String.format("%s - %s, %d days", strtDate.toString(), lstDate.toString(), stayDuration.toDays());
        initComponents();
        this.chosenDateLbl.setText(chosenDateMsg);
        
    }
    
    public String[] listOfAvailableRooms() {
        Hotel umpHotel = Hotel.getInstance();
        this.availableRooms = umpHotel.avaliableRooms(strtDate, lstDate);
        
        int durationInDays = (int) this.stayDuration.toDays();
        
        String[] tobereturned = new String[this.availableRooms.length];
        
        for (int i = 0; i < this.availableRooms.length; i++) {
            Room room = this.availableRooms[i];
            
            tobereturned[i] = String.format("Room %s - RM%.2f", room, room.getPrice() * durationInDays);
        }
        
        return tobereturned;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loggedAsLbl = new javax.swing.JLabel();
        instructionLbl = new javax.swing.JLabel();
        chosenDateLbl = new javax.swing.JLabel();
        bckBtn = new javax.swing.JButton();
        nextBtn = new javax.swing.JButton();
        roomCmboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        loggedAsLbl.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        loggedAsLbl.setText("Ya shall not have see tis :>");

        instructionLbl.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        instructionLbl.setText("The followings are the room that you can book");

        chosenDateLbl.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        chosenDateLbl.setText("wahoo");

        bckBtn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        bckBtn.setText("Back");
        bckBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bckBtnActionPerformed(evt);
            }
        });

        nextBtn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        nextBtn.setText("Next");
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });

        roomCmboBox.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        roomCmboBox.setModel(new javax.swing.DefaultComboBoxModel<>(this.listOfAvailableRooms()));
        roomCmboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomCmboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(loggedAsLbl))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(412, 412, 412)
                        .addComponent(chosenDateLbl))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(bckBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addComponent(instructionLbl)
                .addGap(83, 83, 83))
            .addGroup(layout.createSequentialGroup()
                .addGap(327, 327, 327)
                .addComponent(roomCmboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loggedAsLbl)
                .addGap(18, 18, 18)
                .addComponent(instructionLbl)
                .addGap(18, 18, 18)
                .addComponent(chosenDateLbl)
                .addGap(76, 76, 76)
                .addComponent(roomCmboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bckBtn)
                    .addComponent(nextBtn))
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void roomCmboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomCmboBoxActionPerformed
//        // TODO add your handling code here:
//        User customer = this.umpHotel.getCustomers()[this.userCmboBox.getSelectedIndex()];
//        //        System.out.println(customer.getName());
//        String customerName = customer.getName();
//        if ((! customerName.equals("Admin")))
//        this.loginBtn.setEnabled(true);
//        else
//        this.loginBtn.setEnabled(false);
    }//GEN-LAST:event_roomCmboBoxActionPerformed

    private void bckBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bckBtnActionPerformed
        BookingDatePage datePage = new BookingDatePage();
        this.setVisible(false);
        datePage.setVisible(true);
    }//GEN-LAST:event_bckBtnActionPerformed

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        
        
    }//GEN-LAST:event_nextBtnActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(RoomBookingPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(RoomBookingPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(RoomBookingPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(RoomBookingPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new RoomBookingPage().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bckBtn;
    private javax.swing.JLabel chosenDateLbl;
    private javax.swing.JLabel instructionLbl;
    private javax.swing.JLabel loggedAsLbl;
    private javax.swing.JButton nextBtn;
    private javax.swing.JComboBox<String> roomCmboBox;
    // End of variables declaration//GEN-END:variables
}