/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WiFiHertzPackage;

/**
 *
 * @author Łukasz
 */import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MyJListClass extends JPanel {
  JList list;

  public MyJListClass(String[] label) {
    setLayout(new BorderLayout());

    list = new JList(label);
    JButton button = new JButton("Print");
    JScrollPane pane = new JScrollPane(list);

    DefaultListSelectionModel m = new DefaultListSelectionModel();
    m.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    m.setLeadAnchorNotificationEnabled(false);
    list.setSelectionModel(m);

    list.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        System.out.println(e.toString());
      }
    });
    button.addActionListener(new PrintListener());

    add(pane, BorderLayout.NORTH);
    add(button, BorderLayout.SOUTH);
  }
  static String label[] = { "Zero", "One", "Two", "Three", "Four", "Five", "Six",
      "Seven", "Eight", "Nine", "Ten", "Eleven" };
  public static void main(String s[]) {
    JFrame frame = new JFrame("List Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new MyJListClass(label));
    frame.pack();
    frame.setVisible(true);
  }

  // An inner class to respond to clicks on the Print button
  class PrintListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      int selected[] = list.getSelectedIndices();
      System.out.println("Selected Elements:  ");

      for (int i = 0; i < selected.length; i++) {
        String element = (String) list.getModel().getElementAt(
            selected[i]);
        System.out.println("  " + element);
      }
    }
  }
}
     