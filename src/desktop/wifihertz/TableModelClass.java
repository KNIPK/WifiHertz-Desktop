/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.wifihertz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Łukasz
 */
public class TableModelClass extends AbstractTableModel 
{
    CachedRowSet crs; 
    
    public TableModelClass(CachedRowSet crsA)
    {
        crs = crsA;
    }
    
    @Override
    public int getRowCount() {
        return crs.size();
    }

    @Override
    public int getColumnCount() 
    {
        int colCount = 0;
        try 
        {
            java.sql.ResultSetMetaData metaData = crs.getMetaData();
            colCount = metaData.getColumnCount();
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(TableModelClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return colCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        try
        {
            crs.absolute(rowIndex + 1);
            return crs.getObject(columnIndex+1);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(TableModelClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @Override
    public String getColumnName(int columnIndex) 
    {
        String columnName = null;
        try
        {
            
          
            java.sql.ResultSetMetaData metaData = crs.getMetaData();
            columnName = metaData.getColumnLabel(columnIndex + 1);
           
            
            //return columnsNames[columnIndex];
        }
        catch (SQLException ex)
        {
            Logger.getLogger(TableModelClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return columnName;
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) 
    {
        return true;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) 
    {
        Class crsClass = null;
        try
        {
           
            crs.absolute(1);
            crsClass = crs.getObject(getColumnName(columnIndex)).getClass();
            
            
        }
        catch (SQLException ex)
        {
            Logger.getLogger(TableModelClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return crsClass;
    }
    @Override
    public void setValueAt(Object obj, int rowIndex, int columnIndex) 
    {
        try        
        {
            SQLConnection.setValue(obj, getColumnClass(columnIndex), rowIndex, columnIndex);
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(TableModelClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(TableModelClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(TableModelClass.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
}
