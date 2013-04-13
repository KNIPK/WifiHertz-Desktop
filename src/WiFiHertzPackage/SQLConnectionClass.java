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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public static String sourcePath;
    public static String userData;
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
    static String url1 = "jdbc:mysql://127.0.0.1:3306/";
    static String baza1 = "wifihertz";
    static String login1 = "root";          // <---- podaj swój login
    static String password1 = "";   // <---- podaj swoje hasło
    public static String loadPath(String nazwa) throws SQLException
    {
       Connection conn = (Connection) DriverManager.getConnection(url1+baza1 , login1 , password1);
       Statement st = conn.createStatement();
  
       ResultSet rs = st.executeQuery("SELECT * FROM wifihertz.images where img_name='"+nazwa+"'");
       while(rs.next())
       {
          sourcePath = rs.getString("img_scr");
          System.out.println(sourcePath);
       }
       return sourcePath;
    }
    public static String getUsersData(String log, String pass) throws SQLException
    {
       Connection conn = (Connection) DriverManager.getConnection(url1+baza1 , login1 , password1);
       Statement st = conn.createStatement();
  
       ResultSet rs = st.executeQuery("SELECT * FROM wifihertz.users where lower(login)='"+log.toLowerCase()+"' and lower(passwd) = '"
               + pass.toLowerCase() + "'");
       while(rs.next())
       {
          userData = rs.getString("user_id");
          System.out.println(userData);
       }
       return userData;
    }
    public static void insertImage(String name, String pathName, int user_id) throws SQLException
    {
        String tableName = "IMAGES";
        int image_id = 0;
        Connection conn = (Connection) DriverManager.getConnection(url1 + baza1, login1, password1);
        Statement st = conn.createStatement();
        st.executeUpdate("insert into wifihertz.images values(0,"+ user_id+", '" + pathName + "', '"+ name + "');");
        conn.setAutoCommit(false);
        conn.commit();
        conn.close(); 
        
        //TableJFrame.jTable1.repaint();
    }
    public CachedRowSet getCachedRowSetImage() throws SQLException
    {
       
        crs.setCommand("select a1.img_name from wifihertz.images a1, wifihertz.users a2 where a2.user_id=a1.user_id_img");
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

