package WiFiHertzPackage;

import java.awt.Component;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

class ComboCellEditor extends AbstractCellEditor implements TableCellEditor {

    JComponent component = new JComboBox();
    TableModel model;

    CachedRowSet crs;
    int ids[];
    public ComboCellEditor(TableModel model, CachedRowSet crs) {
        this.model = model;
        this.crs = crs;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int rowIndex, int vColIndex) {
        try {
           
            ((JComboBox) component).removeAllItems();
            Integer selection = (Integer) value;
            int selectionId = 0;
            int colCount = model.getColumnCount();
            crs.last();
            int rowcount = crs.getRow();
            ids = new int[rowcount];
            crs.beforeFirst();
            for (int i = 0; crs.next(); i++) 
            {
                ids[i] = crs.getInt(1);
                if (ids[i] == (int) selection) 
                {
                    selectionId = i;
                }
                String name = "";
                for (int j = 1; j < colCount; j++) 
                {
                    name += crs.getString(j + 1) + " ";
                }
                ((JComboBox) component).addItem(name);
            }
            ((JComboBox) component).setSelectedIndex(selectionId);
            return component;
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(ComboCellEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @Override
    public Object getCellEditorValue()
    {
        int index = ((JComboBox) component).getSelectedIndex();
        return ids[index];
    }
}
class ComboCellRenderer implements TableCellRenderer 
{
    JComponent component = new JComboBox();
    TableModel model;
    CachedRowSet crs;
    int ids[];
    public ComboCellRenderer(TableModel model, CachedRowSet crs) 
    {
        this.model = model;
        this.crs = crs;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) 
    {
        try {
            
            ((JComboBox) component).removeAllItems();         
            Integer selection = (Integer) value;
            int selectionId = 0;
            int colCount = model.getColumnCount();
            crs.last();
            int rowcount = crs.getRow();
            ids = new int[rowcount];
            crs.beforeFirst();
            for (int i = 0; crs.next(); i++) 
            {
                ids[i] = crs.getInt(1);
                if (ids[i] == (int) selection) 
                {
                    selectionId = i;
                }
                String name = "";
                for (int j = 1; j < colCount; j++)
                {
                    name += crs.getString(j + 1) + " ";
                }
                ((JComboBox) component).addItem(name);
            }
            ((JComboBox) component).setSelectedIndex(selectionId);

            return component;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ComboCellEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}