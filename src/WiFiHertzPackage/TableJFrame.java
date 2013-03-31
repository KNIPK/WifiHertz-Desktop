/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WiFiHertzPackage;

import static WiFiHertzPackage.SQLConnectionClass.loadPath;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.swing.table.TableColumn;

/**
 *
 * @author Łukasz
 */
public class TableJFrame extends javax.swing.JFrame
{

    CachedRowSet crs;
   
    CachedRowSet crsImage = null;
    CachedRowSet crsUsers = null;
    CachedRowSet crs1, crs2;
    ArrayList<TableColumn> removedColumns;
    /**model tabeli*/
    static TableModelClass model;
    public static String nameMap;
    TableModelClass modelImages;
    TableModelClass modelUsers;
    static SQLConnectionClass bMysql;
    SQLConnectionClass bMysqlA, bMysqlP;
    ArrayList<Integer> userList;
    ArrayList<Object> imageList;
    boolean users = true;
    boolean images = false;
    /**
     * Creates new form TableJFrame
     */
    public TableJFrame() throws SQLException, FileNotFoundException, IOException
    {
         
        bMysql = new SQLConnectionClass();
        crs = bMysql.getCachedRowSetImage();
        model = new TableModelClass(crs, bMysql);
        initComponents();
        this.setPreferredSize(new Dimension(jTable1.getWidth(), jTable1.getHeight()));
        getDataFromDatabase();
       
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
        menuMapsJPanel2 = new WiFiHertzPackage.menuMapsJPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        menuMapsJPanel2.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                menuMapsJPanel2MouseClicked(evt);
            }
        });

        jLabel1.setText("Your Maps");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(menuMapsJPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(menuMapsJPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuMapsJPanel2MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_menuMapsJPanel2MouseClicked
    {//GEN-HEADEREND:event_menuMapsJPanel2MouseClicked
        
        
    }//GEN-LAST:event_menuMapsJPanel2MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTable1MouseClicked
    {//GEN-HEADEREND:event_jTable1MouseClicked
        String nnn;
        int row = jTable1.rowAtPoint(evt.getPoint());
        int col = jTable1.columnAtPoint(evt.getPoint());
        int colMap = jTable1.getSelectedRow();
        nameMap = (String) jTable1.getModel().getValueAt(row, col);
        
        System.out.println(nameMap);
        try
        {
            nnn=loadPath(nameMap);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(TableJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked
    public void mouseClicked(java.awt.event.MouseEvent event)
    {
        
    }
    private void getDataFromDatabase()
    {
        try
        {
            users = false;
            images = true;
            
            crs = bMysql.getCachedRowSetImage();        // TODO add your handling code here:
        }
        catch (SQLException ex)
        {
            Logger.getLogger(TableJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        model = new TableModelClass(crs, bMysql);
        jTable1.setModel(model);
    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(TableJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(TableJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(TableJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(TableJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    new TableJFrame().setVisible(true);
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(TableJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (FileNotFoundException ex)
                {
                    Logger.getLogger(TableJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (IOException ex)
                {
                    Logger.getLogger(TableJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private WiFiHertzPackage.menuMapsJPanel menuMapsJPanel2;
    // End of variables declaration//GEN-END:variables
}
