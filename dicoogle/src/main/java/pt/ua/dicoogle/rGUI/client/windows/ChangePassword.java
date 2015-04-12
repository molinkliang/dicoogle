/**
 * Copyright (C) 2014  Universidade de Aveiro, DETI/IEETA, Bioinformatics Group - http://bioinformatics.ua.pt/
 *
 * This file is part of Dicoogle/dicoogle.
 *
 * Dicoogle/dicoogle is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Dicoogle/dicoogle is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Dicoogle.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * ChangePassword.java
 *
 * Created on 18/Abr/2010, 16:33:42
 */
package pt.ua.dicoogle.rGUI.client.windows;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pt.ua.dicoogle.rGUI.client.ClientCore;
import pt.ua.dicoogle.rGUI.interfaces.IUser;
import pt.ua.dicoogle.server.users.HashService;

/**
 *
 * @author samuelcampos
 */
@Deprecated
public class ChangePassword extends javax.swing.JFrame {

    private static Semaphore sem = new Semaphore(1, true);
    private static ChangePassword instance = null;

    private IUser user;

    public static synchronized ChangePassword getInstance() {
        try {
            sem.acquire();
            if (instance == null) {
                instance = new ChangePassword();
            }
            sem.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        return instance;
    }

    /** Creates new form ChangePassword */
    private ChangePassword() {
        initComponents();

        Image image = Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("trayicon.gif"));
        this.setIconImage(image);

        this.setTitle("Change User Password");
        
        user = ClientCore.getInstance().getUser();
        
        try {
            jLabelUsername.setText(user.getUsername());
        } catch (RemoteException ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelUsername = new javax.swing.JLabel();
        jPasswordFieldOld = new javax.swing.JPasswordField();
        jPasswordFieldNew = new javax.swing.JPasswordField();
        jPasswordFieldConfirm = new javax.swing.JPasswordField();
        jButtonCancel = new javax.swing.JButton();
        jButtonChangePass = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(370, 213));
        setPreferredSize(new java.awt.Dimension(370, 213));
        setResizable(false);
        setSize(new java.awt.Dimension(370, 213));

        jLabel1.setText("Username:");

        jLabel2.setText("Old Password:");

        jLabel3.setText("New Password:");

        jLabel4.setText("Confirm Password:");

        jLabelUsername.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelUsername.setText("Username");

        jPasswordFieldOld.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordFieldOldKeyPressed(evt);
            }
        });

        jPasswordFieldNew.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordFieldNewKeyPressed(evt);
            }
        });

        jPasswordFieldConfirm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordFieldConfirmKeyPressed(evt);
            }
        });

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonChangePass.setText("Change Password");
        jButtonChangePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangePassActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(43, 43, 43)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jButtonCancel)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel3)
                            .add(jLabel1)
                            .add(jLabel2)
                            .add(jLabel4))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(18, 18, 18)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(jPasswordFieldNew)
                                    .add(jPasswordFieldOld)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jButtonChangePass, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPasswordFieldConfirm)))
                            .add(layout.createSequentialGroup()
                                .add(26, 26, 26)
                                .add(jLabelUsername)))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jLabelUsername)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jPasswordFieldOld, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel2))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jPasswordFieldNew, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel3))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jPasswordFieldConfirm, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel4)))
                    .add(jLabel1))
                .add(17, 17, 17)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButtonCancel)
                    .add(jButtonChangePass))
                .add(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonChangePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangePassActionPerformed
        String oldPass = new String(jPasswordFieldOld.getPassword());
        String newPass = new String(jPasswordFieldNew.getPassword());
        String confirmPass = new String(jPasswordFieldConfirm.getPassword());

        if (oldPass.length() == 0 || newPass.length() == 0 || confirmPass.length() == 0) {
            JOptionPane.showMessageDialog(this, "Password must have characters!",
                    "Password Empty", JOptionPane.ERROR_MESSAGE);

            return;
        }
         if (!newPass.equals(confirmPass)) {
            JOptionPane.showMessageDialog(this, "Passwords must be equal!",
                    "Diferent Passwords", JOptionPane.ERROR_MESSAGE);

            return;
        }

        String oldPassHash = HashService.getSHA1Hash(oldPass);
        String newPassHash = HashService.getSHA1Hash(newPass);

        try {
            if (!user.changePassword(oldPassHash, newPassHash)) {
                JOptionPane.showMessageDialog(this, "Old Password is wrong!",
                    "Wrong Password", JOptionPane.ERROR_MESSAGE);

            return;
            }
            else{
                JOptionPane.showMessageDialog(this, "Password successfully changed!",
                    "Password Changed", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(this, "Its impossible to change the password at this time!",
                    "Connectivity error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonChangePassActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jPasswordFieldOldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldOldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            jButtonChangePass.doClick();
    }//GEN-LAST:event_jPasswordFieldOldKeyPressed

    private void jPasswordFieldNewKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldNewKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            jButtonChangePass.doClick();
    }//GEN-LAST:event_jPasswordFieldNewKeyPressed

    private void jPasswordFieldConfirmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldConfirmKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            jButtonChangePass.doClick();
    }//GEN-LAST:event_jPasswordFieldConfirmKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonChangePass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JPasswordField jPasswordFieldConfirm;
    private javax.swing.JPasswordField jPasswordFieldNew;
    private javax.swing.JPasswordField jPasswordFieldOld;
    // End of variables declaration//GEN-END:variables
}
