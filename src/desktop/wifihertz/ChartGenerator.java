/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.wifihertz;

import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author mj
 */
public class ChartGenerator {
     static ChartPanel example(){
    
        // This will create the dataset 
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Linux", 29);
        result.setValue("Mac", 20);
        result.setValue("Windows", 51);
        PieDataset dataset = result;
        
        
        JFreeChart chart = org.jfree.chart.ChartFactory.createPieChart3D("Tytul",          // chart title
            dataset,                // data
            true,                   // include legend
            true,
            false);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        
        JFreeChart toDraw = chart;
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(toDraw);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        
        return chartPanel;
    }
}
