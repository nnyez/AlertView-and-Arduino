/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.panels;

import javax.swing.JTextField;

/**
 *
 * @author Fr4nk
 */
public class FiltreDatePanel extends javax.swing.JPanel {

    /**
     * Creates new form FiltreDate
     */
    public FiltreDatePanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbFechaInicial = new javax.swing.JLabel();
        lbFechaFinal = new javax.swing.JLabel();
        txtDateStart = new javax.swing.JTextField();
        txtDateEnd = new javax.swing.JTextField();

        lbFechaInicial.setText("Fecha Inicial:");

        lbFechaFinal.setText("Fecha Final:");

        txtDateStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDateStartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbFechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbFechaInicial)
                    .addComponent(txtDateStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFechaFinal)
                    .addComponent(txtDateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtDateStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDateStartActionPerformed
    }//GEN-LAST:event_txtDateStartActionPerformed

    public JTextField getTxtDateEnd() {
        return txtDateEnd;
    }

    public JTextField getTxtDateStart() {
        return txtDateStart;
    }

    

  

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbFechaFinal;
    private javax.swing.JLabel lbFechaInicial;
    private javax.swing.JTextField txtDateEnd;
    private javax.swing.JTextField txtDateStart;
    // End of variables declaration//GEN-END:variables
}
