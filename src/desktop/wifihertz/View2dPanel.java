/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.wifihertz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class View2dPanel extends javax.swing.JPanel {

    ArrayList<WifiData> data = null;
    private int max = Integer.MIN_VALUE;
    private int min = Integer.MAX_VALUE;

    public View2dPanel() {
        initComponents();

    }

    private void findMinMax() {
        if (data != null) {
            for (WifiData wifiData : data) {
                int signal = wifiData.getWifiRange();
                if (signal < min) {
                    min = signal;
                }
                if (signal > max) {
                    max = signal;
                }
            }
        }
    }

    void getTestingData() {
        try {
            data = TestingFunctions.getDataFromLocalDatabase();
            findMinMax();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    void printData() {
        for (WifiData wifiData : data) {
            System.out.println(wifiData);
        }
    }

    private Color rangeToColor(int min, int max, int value) {
        int rangeSize = max - min;
        double ratio = 255 / rangeSize;

        int result = (int) ((value - min) * ratio);

        return new Color(255-result, 255-result, 255-result);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(0, 0, this.getSize().width, this.getSize().height);
        g2.setBackground(Color.white);
        Color color = Color.black;
        if (data != null) {
            for (WifiData wifiData : data) {
                g2.setColor(rangeToColor(min, max, wifiData.getWifiRange()));
                g2.drawLine(wifiData.getPositionX(), wifiData.getPositionY(), wifiData.getPositionX(), wifiData.getPositionY());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
