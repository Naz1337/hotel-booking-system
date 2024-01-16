/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.hotelbooking.system;

/**
 *
 * @author Naz
 */
public class ReceiptPage extends javax.swing.JFrame {

    private Invoice invoice;
    /**
     * Creates new form ReceiptPage
     */
    public ReceiptPage(Invoice invoice) {
        initComponents();
        this.invoice = invoice;
        
        this.receiptLbl.setText(invoice.getReceipt());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        receiptLbl = new javax.swing.JTextArea();
        backToBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        receiptLbl.setColumns(20);
        receiptLbl.setRows(5);
        jScrollPane1.setViewportView(receiptLbl);

        backToBtn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        backToBtn.setText("Back to Menu");
        backToBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(160, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backToBtn))
                .addGap(199, 199, 199))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(backToBtn)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backToBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToBtnActionPerformed
        // TODO add your handling code here:
        CustomerMainPage customerMainPage = new CustomerMainPage(CustomerMainPage.customer);
        this.setVisible(false);
        customerMainPage.setVisible(true);
    }//GEN-LAST:event_backToBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backToBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea receiptLbl;
    // End of variables declaration//GEN-END:variables
}
