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
 * 
 * 
 * TaskList.java
 *
 * Created on Jul 20, 2010, 10:21:21 AM
 */

package pt.ua.dicoogle.rGUI.client.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import pt.ua.dicoogle.rGUI.interfaces.controllers.ITaskList;
import pt.ua.dicoogle.rGUI.interfaces.signals.ITaskListSignal;
import pt.ua.dicoogle.sdk.datastructs.Report;
import pt.ua.dicoogle.sdk.task.Task;

/**
 *
 * @author Luís A. Bastião Silva <bastiao@ua.pt>
 */
@Deprecated
public final class TaskList extends javax.swing.JPanel {

    private static ITaskList taskList;
    private static ITaskListSignal tasksSignal;

    class PairTaskProgress{
        public Task task;
        public JProgressBar bar;
    }
    private ArrayList<PairTaskProgress> tasks = new ArrayList<>();
    
    Timer timer;
    
    /** Creates new form TaskList */
    public TaskList(){
        initComponents();
        
        ActionListener updateTask = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProgress();
            }
        };
        
        timer = new Timer(1000,updateTask);
        timer.start();
        
        //registerRemoteObject();
        //getTaskList();

    }

    public void updateProgress(){
        ArrayList<PairTaskProgress> ntasks = new ArrayList<>();
        for(PairTaskProgress task : tasks){
            //otherwise updates it
           task.bar.setValue((int)(task.task.getProgress()*100));
            if(task.task.isDone()){
                remove(task.bar);
            }
            else{
                ntasks.add(task);
            }
        }
        tasks = ntasks;
        
        revalidate();
        repaint();
    }
    
    public void add(Iterable<Task<Report>> tasks){
                
        for(Task<Report> task : tasks){
            JProgressBar bar = new JProgressBar(0,100);
            bar.setString(task.getName()+" -> "+task.getProgress());
            bar.setValue((int)(task.getProgress()*100)+10);
            
            PairTaskProgress pair = new PairTaskProgress();
            pair.task = task;
            pair.bar = bar;
            //scrollPanel.add(pair.bar);
            this.tasks.add(pair);
            add(bar);

        }
        updateProgress();
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
        jSeparator1 = new javax.swing.JSeparator();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        jLabel1.setText("Task Progress");
        add(jLabel1);
        add(jSeparator1);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

}
