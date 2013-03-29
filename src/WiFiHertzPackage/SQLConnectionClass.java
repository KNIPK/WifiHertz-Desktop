/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WiFiHertzPackage;

import com.mysql.jdbc.Connection;
import com.sun.rowset.CachedRowSetImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.prefs.Preferences;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Łukasz
 */
public class SQLConnectionClass 
{
    Connection  conn = null;
    CachedRowSet crs = null;
    
    BazaConn database = null;
    
    String url, user, passwd;  
    public SQLConnectionClass() throws SQLException, FileNotFoundException, IOException
    {
        database = new BazaConn();
        crs = new CachedRowSetImpl();
        Properties prop = database.getProperties();
        url = prop.getProperty("url");
        user = prop.getProperty("user");
        passwd = prop.getProperty("passwd");
        crs.setUrl(url);
        crs.setUsername(user);
        crs.setPassword(passwd);
    }
    public CachedRowSet getCachedRowSetImage() throws SQLException
    {
        crs.setCommand("select a2.user_id, a1.img_name from wifihertz.images a1, wifihertz.users a2 where a2.user_id=a1.user_id_img");
        crs.execute();
        return crs;
    }
    public CachedRowSet getCachedRowSetUsers() throws SQLException
    {
        crs.setCommand("select * from wifihertz.users");
        crs.execute();
        return crs;
    }
    public void setValue(Object obj, Class<?> cls, int rowIndex, int columnIndex) throws FileNotFoundException, IOException, SQLException
    {
        conn = database.getConnection();
        crs.absolute(rowIndex+1); 
        if(cls == Integer.class)
        {
            crs.updateInt(columnIndex + 1, (Integer) obj);
        }
        if(cls == Float.class)
        {
            crs.updateFloat(columnIndex + 1, (Float) obj);
        }
        if(cls == String.class)
        {
            crs.updateString(columnIndex + 1, (String) obj);
        }
        crs.updateRow();
        conn.setAutoCommit(false);
        crs.acceptChanges();
        conn.close();
    }
    public void insertMapToImages(String[] row, int user_id) throws FileNotFoundException, FileNotFoundException, IOException, SQLException
    { 
        crs.moveToInsertRow();
        crs.updateInt("img_id", 0);
        crs.updateInt("user_id_img",user_id);
        crs.updateString("img_name", row[0]);
        crs.insertRow();
        crs.moveToCurrentRow();
        conn = database.getConnection();
        conn.setAutoCommit(false);
        crs.acceptChanges();
        conn.close();
    }
}
/**klasa odpowiedzialna za polaczenie*/
class BazaConn {

    Properties prop;
    /**pobiera dane (baza, użytkownik, hasło) o bazie danych z pliku.*/
    BazaConn() throws FileNotFoundException, IOException {
        String fileName = new PropertiesFile().getFileProperties();
        prop = new Properties();
        InputStream in = new FileInputStream(fileName);
        prop.load(in);
        in.close();
    }

    Connection getConnection()
            throws FileNotFoundException, IOException, SQLException {
        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        return (Connection) DriverManager.getConnection(
                url, user, password);
    }

    Properties getProperties() {
        return prop;
    }
    /**klasa opdowiedzialna za utworzenie pliku z parametrami polaczenia z baza
     -zawiera metody pozwalające na pobranie danych o bazie danych*/
    class PropertiesFile {

        public String getFileProperties() throws FileNotFoundException, IOException {

            StringBuilder dirP = new StringBuilder();
            Preferences node = Preferences.userNodeForPackage(this.getClass());
            Preferences root = Preferences.userRoot();
            String path = node.absolutePath();
            String[] packagename = path.split("/");
            String userDir = System.getProperty("user.home");
            dirP.append(userDir);
            dirP.append("/propertiesjava");
            dirP.append(path);
            File dirPr = new File(dirP.toString());
            if (!dirPr.exists()) {
                dirPr.mkdirs();
            }
            dirP.append("/");
            dirP.append(packagename[1]);
            dirP.append(".properties");
            String fileName = dirP.toString();
            File fileP = new File(fileName);
            if (!fileP.exists()) {
                FileOutputStream out = new FileOutputStream(fileName);
                Properties baza = new Properties();
                baza.put("url", "jdbc:mysql://localhost:3306/mysql");
                baza.put("user", "root");
                baza.put("password", "");
                baza.store(out, "Właściwości bazy danych");
                out.close();
            }
            return fileName;
        }
    }
}

