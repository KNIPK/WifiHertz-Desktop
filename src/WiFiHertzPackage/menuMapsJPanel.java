/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WiFiHertzPackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Łukasz
 */
public class menuMapsJPanel extends javax.swing.JPanel implements ListCellRenderer, ListSelectionListener {

    /**
     * Creates new form menuMapsJPanel
     */
    public File file;
    public static int i = 0;

    public menuMapsJPanel() {
        initComponents();
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
        jList = new javax.swing.JList();
        yourMapsjLabel = new javax.swing.JLabel();
        manageYourMapsLabel = new javax.swing.JLabel();
        jButtonAddMap = new javax.swing.JButton();
        jButtonReturn = new javax.swing.JButton();

        jList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList);

        yourMapsjLabel.setText("Your Maps");

        manageYourMapsLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        manageYourMapsLabel.setText("Zarządzaj swoimi Mapami");

        jButtonAddMap.setText("Add map");
        jButtonAddMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddMapActionPerformed(evt);
            }
        });

        jButtonReturn.setText("Back");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(manageYourMapsLabel)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(294, 294, 294))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonAddMap)
                            .addComponent(yourMapsjLabel))
                        .addGap(265, 265, 265))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonReturn)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(manageYourMapsLabel)
                .addGap(44, 44, 44)
                .addComponent(yourMapsjLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addComponent(jButtonAddMap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonReturn)
                .addGap(13, 13, 13))
        );
    }// </editor-fold>//GEN-END:initComponents
    public static ArrayList<String> listMap = new ArrayList<String>();
    private void jButtonAddMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddMapActionPerformed
        // TODO add your handling code here:
        listModel = new DefaultListModel();
      
        //MapChoiserJFrame.choiserJPanel1 = new choiserJPanel();
        JFileChooser jfc = new JFileChooser();
        choiserJPanel preview = new choiserJPanel(jfc);
        jfc.addPropertyChangeListener(preview);
        jfc.setAccessory(preview);
        jfc.showOpenDialog(null);
        
        file = jfc.getSelectedFile();
        listMap.add(file.getName());
        System.out.println(menuMapsJPanel.listMap.get(0));
       // listJPanel1 = new listJPanel();
        //listJPanel1.setVisible(true);
        //JFrame frame = new JFrame("List Example");
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // frame.setContentPane(new listJPanel(a));
       // frame.pack();
        //frame.setVisible(true);
       
        
    }//GEN-LAST:event_jButtonAddMapActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddMap;
    private javax.swing.JButton jButtonReturn;
    private javax.swing.JList jList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel manageYourMapsLabel;
    private javax.swing.JLabel yourMapsjLabel;
    // End of variables declaration//GEN-END:variables
    private DefaultListModel listModel;
  
    //DefaultListModel lm1  = (DefaultListModel) listaRosa.getModel();

    @Override
    public Component getListCellRendererComponent(JList jlist, Object e, int i, boolean bln, boolean bln1) {
        if (e instanceof JPanel) {
            Component component = (Component) e;
            component.setForeground(Color.white);
            component.setBackground(bln ? UIManager.getColor("Table.focusCellForeground") : Color.white);
            return component;
        } else {
            // TODO - I get one String here when the JList is first rendered; proper way to deal with this?
            //System.out.println("Got something besides a JPanel: " + value.getClass().getCanonicalName());
            return new JLabel("???");
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (jList.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                jButtonAddMap.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                jButtonAddMap.setEnabled(true);
            }

        }
    }
}
