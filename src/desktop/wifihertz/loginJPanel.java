/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.wifihertz;

import static desktop.wifihertz.MainJFrame.jButton1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

/**
 *
 * @author Łukasz
 */
public class loginJPanel extends javax.swing.JPanel
{

    /**
     * Creates new form loginJPanel
     */
    TableModelClass model;
    TableModelClass modelUsers;
    CachedRowSet crs;
    private static String SqLite = "org.sqlite.JDBC";

    public loginJPanel()
    {
        //loginJPanel pane = new loginJPanel();
        //jTable1.setGridColor(Color.yellow);
       
                
       

        //Dimension dimension = new Dimension(ySize, ySize);//.getScreenSize();
        //setPreferredSize(dimension);
        //jTable1.setLayout(new BoxLayo);
    }

    public void refresh() throws ClassNotFoundException, SQLException, MalformedURLException, IOException
    {
        if (MainJFrame.isLogged == true)
        {
            if (MainJFrame.isInternetAccess == true)
            {
                SQLConnection.getRemoteDataBaseAllUsersToBeCopy();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Warning! Can not connect to internet ", "Warning", JOptionPane.WARNING_MESSAGE);
            }

            try
            {
                Class.forName(SqLite);
                try
                {
                    crs = SQLConnection.getAllLocalUsers();
                    model = new TableModelClass(crs);
                    initComponents();
                    getDataFromDatabase();
                }
                catch (ClassNotFoundException ex)
                {
                    Logger.getLogger(loginJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(loginJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            catch (ClassNotFoundException ex)
            {
                Logger.getLogger(loginJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void manageTab(Boolean what) throws ClassNotFoundException, SQLException
    {
        //initComponents();
        this.setVisible(what);
    }

    private void getDataFromDatabase() throws ClassNotFoundException, SQLException
    {
        crs = SQLConnection.getAllLocalUsers();        // TODO add your handling code here:
        model = new TableModelClass(crs);
        jTable1.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setMinimumSize(new java.awt.Dimension(1000, 500));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    public static String getUserName;
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTable1MouseClicked
    {//GEN-HEADEREND:event_jTable1MouseClicked
        // TODO add your handling code here:
        int row = jTable1.rowAtPoint(evt.getPoint());
        int col = jTable1.columnAtPoint(evt.getPoint());
        getUserName = (String) jTable1.getModel().getValueAt(row, col);
        System.out.println("Wybrales usera " + getUserName);
        String toDisp = getUserName + " is logged!";
        MainJFrame.userLoginLabel.setText(toDisp);
        JOptionPane.showMessageDialog(this, "You are logged as " + getUserName, "Login!", JOptionPane.INFORMATION_MESSAGE);
        jTable1.getSelectedRow();
        try
        {
            manageTab(false);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(loginJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(loginJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        jButton1.setVisible(true);
    }//GEN-LAST:event_jTable1MouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
