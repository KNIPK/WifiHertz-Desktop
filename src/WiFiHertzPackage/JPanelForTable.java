/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WiFiHertzPackage;

import static WiFiHertzPackage.TableJFrame.bMysql;
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
public class JPanelForTable extends javax.swing.JPanel
{
    CachedRowSet crs;
   
    CachedRowSet crsImage = null;
    CachedRowSet crsUsers = null;
    CachedRowSet crs1, crs2;
    ArrayList<TableColumn> removedColumns;
    /**model tabeli*/
    static TableModelClass model;
   
    TableModelClass modelImages;
    TableModelClass modelUsers;
    static SQLConnectionClass bMysql;
    SQLConnectionClass bMysqlA, bMysqlP;
    ArrayList<Integer> userList;
    ArrayList<Object> imageList;
    boolean users = true;
    boolean images = false;
    /**
     * Creates new form JPanelForTable
     */
    public JPanelForTable() throws SQLException, FileNotFoundException, IOException
    {
        bMysql = new SQLConnectionClass();
        crs = bMysql.getCachedRowSetImage();
        model = new TableModelClass(crs, bMysql);
        initComponents();
        this.setPreferredSize(new Dimension(jTable1.getWidth(), jTable1.getHeight()));
        getDataFromDatabase();
    }
    private void getDataFromDatabase()
    {
        try
        {
            users = false;
            images = true;
            /*tworzymy sobie nasz zbior buforowany*/
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
