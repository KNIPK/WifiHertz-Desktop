/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.wifihertz;

import com.sun.rowset.CachedRowSetImpl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
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
public class SQLite
{

    Connection connection;
    ResultSet resultSet = null;
    Statement statement = null;
    CachedRowSet crs = null;
    public String sourcePath;
    public String userData;

    public SQLite() throws ClassNotFoundException, SQLException
    {
        Class.forName("org.sqlite.JDBC");
        crs = new CachedRowSetImpl();
    }
    String getUsers = null;

    public String getWifiImages(String name) throws SQLException
    {

        connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\vanlu_000\\Documents\\NetBeansProjects\\Desktop WifiHertz\\database\\wifihertz.sqlite");
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM wifiImages where lower(image_name)='" + name.toLowerCase() + "'");
        while (resultSet.next())
        {
            getUsers = resultSet.getString("image_name");
            System.out.println("image_name:"
                    + resultSet.getString("image_name"));
        }
        return getUsers;
    }

    public void insertImage(String name, String pathName, int user_id) throws SQLException
    {
        int image_id = 0;
        connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\vanlu_000\\Documents\\NetBeansProjects\\Desktop WifiHertz\\database\\wifihertz.sqlite");
        Statement st = connection.createStatement();
        st.executeUpdate("insert into wifiImages ('user_id_img','image_name','image_src') values(" + user_id + ", '" + name + "', '" + pathName + "');");
        connection.setAutoCommit(false);
        connection.commit();
        connection.close();
    }

    public CachedRowSet getCachedRowSetImage() throws SQLException, ClassNotFoundException
    {
        //Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\vanlu_000\\Documents\\NetBeansProjects\\Desktop WifiHertz\\database\\wifihertz.sqlite");
       // Statement st = connection.createStatement();
        //crs = new CachedRowSetImpl();
        crs.setCommand("select a2.image_name from wifiUsers a1, wifiImages a2 where a1.user_id=a2.user_id_img");
        crs.execute(connection);
        //crs.setCommand("select a2.image_name from wifiUsers a1, wifiImages a2 where a2.user_id=a1.user_id_img");
        //connection.close();
        return crs;
        //connection.close();
    }

    public String getUsersData(String login, String passwd) throws SQLException
    {
        connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\vanlu_000\\Documents\\NetBeansProjects\\Desktop WifiHertz\\database\\wifihertz.sqlite");
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM wifiUsers where lower(user_login)='" + login.toLowerCase() + "' and lower(user_passwd) = '"
                + passwd.toLowerCase() + "'");
        while (rs.next())
        {
            userData = rs.getString("user_id");
            System.out.println(userData);
        }
        return userData;
    }
    public String loadPath(String nazwa) throws SQLException
    {
        connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\vanlu_000\\Documents\\NetBeansProjects\\Desktop WifiHertz\\database\\wifihertz.sqlite");
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM wifiImages where lower(image_name)='"+nazwa.toLowerCase()+"'");
        while(rs.next())
        {
          sourcePath = rs.getString("image_src");
          System.out.println(sourcePath);
        }
        return sourcePath;
    }
    public void setValue(Object obj, Class<?> cls, int rowIndex, int columnIndex) throws FileNotFoundException, IOException, SQLException
    {
        connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\vanlu_000\\Documents\\NetBeansProjects\\Desktop WifiHertz\\database\\wifihertz.sqlite");
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
        connection.setAutoCommit(false);
        crs.acceptChanges();
        connection.close();
    }
}
