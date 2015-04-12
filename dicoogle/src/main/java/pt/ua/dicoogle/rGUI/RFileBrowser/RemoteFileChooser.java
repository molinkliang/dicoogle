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
package pt.ua.dicoogle.rGUI.RFileBrowser;

import java.awt.Image;
import java.awt.Toolkit;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.plaf.metal.MetalIconFactory;

/**
 * Remote File Chooser window
 *
 * @author Samuel Campos <samuelcampos@ua.pt>
 */
@Deprecated
public class RemoteFileChooser extends javax.swing.JFrame {

    public static final int APPROVED = 1;
    public static final int CANCELED = 2;
    public static final int DIRECTORIES_ONLY = 3;
    
    private IRemoteFileSystem rFS;
    private String actualPath;
    private int mode;
    private FileAction act;
    private boolean created = false;

    /** Creates new form FileChooser */
    public RemoteFileChooser(IRemoteFileSystem rFS, String homePath, FileAction action) {
        initComponents();

        Image image = Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("trayicon.gif"));
        this.setIconImage(image);

        jButtonUP.setIcon(MetalIconFactory.getFileChooserUpFolderIcon());
        jButtonHome.setIcon(MetalIconFactory.getFileChooserHomeFolderIcon());

        jListFiles.setCellRenderer(new IconListRenderer());
        jComboBox.setRenderer(new IconListRenderer());

        if (homePath == null) {
            homePath = ".";
        }

        this.rFS = rFS;
        actualPath = homePath;

        mode = 0;

        loadFiles();

        created = true;

        act = action;
    }

    /**
     * Load Files to JListFiles
     */
    private void loadFiles() {
        try {
            refreshComboBox();

            RemoteFile[] files = rFS.getFiles(actualPath, false);
            DefaultListModel list = new DefaultListModel();

            for (int i = 0; i < files.length; i++) {
                if (mode == 0 || (mode == DIRECTORIES_ONLY && files[i].isDirectory())) {
                    list.addElement(files[i]);
                }
            }

            // (files.length + 1) / 2 - to round up
            jListFiles.setVisibleRowCount((files.length + 1) / 2);

            jListFiles.setModel(list);

            jTextFieldFilePath.setText(actualPath);
        } catch (RemoteException ex) {
            Logger.getLogger(RemoteFileChooser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refreshComboBox() {
        try {
            jComboBox.removeAllItems();
            RemoteFile[] rFiles = rFS.getFilePath(actualPath);
            RemoteFile tmp;
            int i;

            // invert the list order
            for (i = 0; i < rFiles.length / 2; i++) {
                tmp = rFiles[i];
                rFiles[i] = rFiles[rFiles.length - 1 - i];
                rFiles[rFiles.length - 1 - i] = tmp;
            }

            for (i = 0; i < rFiles.length; i++) {
                jComboBox.insertItemAt(rFiles[i], i);
            }

            jComboBox.setSelectedIndex(rFiles.length - 1);
        } catch (RemoteException ex) {
            Logger.getLogger(RemoteFileChooser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setFileSelectionMode(int mode) {
        if (mode == DIRECTORIES_ONLY) {
            this.mode = mode;
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

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldFilePath = new javax.swing.JTextField();
        jButtonOpen = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListFiles = new javax.swing.JList();
        jButtonCancel = new javax.swing.JButton();
        jButtonUP = new javax.swing.JButton();
        jButtonHome = new javax.swing.JButton();

        jLabel2.setText("Look in:");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(571, 320));

        jLabel1.setText("Look in:");

        jComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel3.setText("File Name:");

        jButtonOpen.setText("Open");
        jButtonOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenActionPerformed(evt);
            }
        });

        jListFiles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListFiles.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jListFiles.setDoubleBuffered(true);
        jListFiles.setLayoutOrientation(javax.swing.JList.VERTICAL_WRAP);
        jListFiles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListFilesMouseClicked(evt);
            }
        });
        jListFiles.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListFilesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListFiles);

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonUP.setText("UP");
        jButtonUP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUPActionPerformed(evt);
            }
        });

        jButtonHome.setText("Home");
        jButtonHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHomeActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .add(34, 34, 34)
                .add(jComboBox, 0, 274, Short.MAX_VALUE)
                .add(192, 192, 192))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTextFieldFilePath, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButtonOpen)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButtonCancel)
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .add(12, 12, 12)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(layout.createSequentialGroup()
                                .add(jButtonUP)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jButtonHome))
                            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE))
                        .add(20, 20, 20))))
            .add(layout.createSequentialGroup()
                .add(36, 36, 36)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                .add(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(17, 17, 17)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButtonHome)
                    .add(jButtonUP))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jTextFieldFilePath, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButtonOpen)
                    .add(jButtonCancel))
                .add(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jListFilesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListFilesValueChanged
        if (!jListFiles.isSelectionEmpty()) {
            RemoteFile file = (RemoteFile) jListFiles.getSelectedValue();
            jTextFieldFilePath.setText(file.getPath());
        }
    }//GEN-LAST:event_jListFilesValueChanged

    private void jButtonUPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUPActionPerformed
        try {
            if (!rFS.isFileSystemRoot(actualPath)) {
                RemoteFile file = rFS.getParentDirectory(actualPath);

                if(file != null){
                    actualPath = file.getPath();

                    loadFiles();
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(RemoteFileChooser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonUPActionPerformed

    private void jListFilesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListFilesMouseClicked
        if (evt.getClickCount() == 2) {
            RemoteFile file = (RemoteFile) jListFiles.getSelectedValue();
            if (file.isDirectory()) {
                actualPath = file.getPath();
                loadFiles();
            }
        }
    }//GEN-LAST:event_jListFilesMouseClicked

    private void jButtonOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenActionPerformed
        if (act != null && jTextFieldFilePath.getText() != null) {
            act.setFileChoosed(jTextFieldFilePath.getText());
        }

        this.dispose();
    }//GEN-LAST:event_jButtonOpenActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxPopupMenuWillBecomeInvisible
        try {
            int index = jComboBox.getSelectedIndex();
            RemoteFile[] rFiles = rFS.getFilePath(actualPath);
            actualPath = rFiles[rFiles.length - 1 - index].getPath();

            loadFiles();
        } catch (RemoteException ex) {
            Logger.getLogger(RemoteFileChooser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBoxPopupMenuWillBecomeInvisible

    private void jButtonHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHomeActionPerformed
        try {
            if (!rFS.isFileSystemRoot(actualPath)) {
                RemoteFile file = rFS.getHomeDirectory();
                actualPath = file.getPath();

                loadFiles();
            }
        } catch (RemoteException ex) {
            Logger.getLogger(RemoteFileChooser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonHomeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonHome;
    private javax.swing.JButton jButtonOpen;
    private javax.swing.JButton jButtonUP;
    private javax.swing.JComboBox jComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jListFiles;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextFieldFilePath;
    // End of variables declaration//GEN-END:variables
}
