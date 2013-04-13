/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.wifihertz;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class TestingFunctions {

    public static ArrayList<WifiData> getDataFromLocalDatabase() throws ClassNotFoundException, SQLException {
        ArrayList<WifiData> wynik = new ArrayList<>();
        for (int i = 0; i < 100; i+=2) {
            for (int j = 0; j < 100; j+=2) {
                wynik.add(new WifiData(0, i, "test", 0, "tstSSID", (int)(-50*Math.sin(i)), 5*i, 5*j));
            }
        }
        return wynik;
    }
}
