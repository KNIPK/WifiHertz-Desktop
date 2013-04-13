/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.wifihertz;

import com.sun.rowset.CachedRowSetImpl;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Łukasz
 */
public class SQLConnection
{

    private static void insertToLocalDataWifi(int imageId, int dataId, String wifiName, long dataTime, String wifiSsid, int wifiRange, int positionX, int positionY) throws SQLException, ClassNotFoundException
    {
        Class.forName(SqLite);
         System.out.println("Ładuje do bazy");
        String query = "insert into wifidata values(" + imageId + ", " + dataId + "," + dataTime + 
                ",'" + wifiName +  "', '" + wifiSsid + "' , "+wifiRange +
                "," + positionX + "," + positionY + ");";
        int image_id = 0;
        Connection connection = DriverManager.getConnection(localUrlDatabase);
        Statement st = connection.createStatement();
        st.executeUpdate(query);
        connection.setAutoCommit(false);
        connection.commit();
        connection.close();
    }
    private static void insertToLocalDatabaseUsers(int user_id, String user_login) throws SQLException, ClassNotFoundException
    {
        Class.forName(SqLite);
        String query = "insert into users (user_id, user_login) values( " + user_id + ",'" + user_login + "')";
        int image_id = 0;
        Connection connection = DriverManager.getConnection(localUrlDatabase);
        Statement st = connection.createStatement();
        st.executeUpdate(query);
        connection.setAutoCommit(false);
        connection.commit();
        connection.close();
    }
    private static ArrayList<Integer> connectToLocalDataBase() throws ClassNotFoundException, SQLException
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Class.forName(SqLite);
        String query = "SELECT * FROM users;";
        Connection connection1 = DriverManager.getConnection(localUrlDatabase);
        Statement statement1 = connection1.createStatement();
        ResultSet resultSet1 = statement1.executeQuery(query);
        while (resultSet1.next())
        {
            list.add(Integer.parseInt(resultSet1.getString("user_id")));
        }
        connection1.close();
        return list;
    }

    public static CachedRowSet getAllLocalUsers() throws ClassNotFoundException, SQLException
    {
        CachedRowSet crs = null;
        crs = new CachedRowSetImpl();
        Class.forName(SqLite);
        String query = "SELECT user_login FROM users;";
        Connection connection1 = DriverManager.getConnection(localUrlDatabase);
        Statement statement1 = connection1.createStatement();
        crs.setCommand(query);
        crs.execute(connection1);
        connection1.close();
        return crs;
    }

    public static CachedRowSet getUsers(String login) throws ClassNotFoundException, SQLException
    {
        CachedRowSet crs = null;
        crs = new CachedRowSetImpl();
        Class.forName(SqLite);
        String query = "SELECT * FROM users where lower(user_login)='" + login.toLowerCase() + "'";
        Connection connection1 = DriverManager.getConnection(localUrlDatabase);
        Statement statement1 = connection1.createStatement();
        crs.setCommand(query);
        crs.execute(connection1);
        connection1.close();
        return crs;
    }

    public static boolean isInternetReachable() throws MalformedURLException, IOException
    {
        try
        {
            URL url = new URL("http://www.google.com");
            HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
            Object objData = urlConnect.getContent();
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
            return false;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void getRemoteDataBase(String user_login, String user_passwd) throws ClassNotFoundException, SQLException
    {
        Class.forName(mySql);
        Boolean ifICan = false;
        String query = "SELECT * FROM users where lower(userLogin)='" + user_login.toLowerCase() + "' and lower(userPassword)=MD5('" + user_passwd.toLowerCase() + "')";
        String getName = null;
        String getPasswd;
        int getUserId = -1;
        Connection connection = DriverManager.getConnection(urlDatabase);
        System.out.println("blad " + connection);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next())
        {
            //getUsers = resultSet.getString("image_name");
            getName = resultSet.getString("userLogin");
            getUserId = Integer.parseInt(resultSet.getString("userId"));
            System.out.println("user_id:"
                    + resultSet.getString("userId") + " user login : " + resultSet.getString("userLogin") + " user passwd : " + resultSet.getString("userPassword"));
        }
        //sprawdzenie czy dany uzytkownik jest juz w bazie
        Boolean ifUserExist = findUserInDataBase(getUserId);
        if (!ifUserExist)
        {
            insertToLocalDatabaseUsers(getUserId, getName);
        }
        connection.close();
    }

    public static void getRemoteDataBaseAllUsersToBeCopy() throws ClassNotFoundException, SQLException
    {
        Class.forName(mySql);
        Boolean ifICan = false;
        String query = "SELECT * FROM users;";
        String getName = null;
        String getPasswd;
        int getUserId = -1;
        Connection connection = DriverManager.getConnection(urlDatabase);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next())
        {
            getName = resultSet.getString("userLogin");
            getUserId = Integer.parseInt(resultSet.getString("userId"));
            System.out.println("user_id:"
                    + resultSet.getString("userId") + " user login : " + resultSet.getString("userLogin") + " user passwd : " + resultSet.getString("userPassword"));
        }
        //sprawdzenie czy dany uzytkownik jest juz w bazie
        Boolean ifUserExist = findUserInDataBase(getUserId);
        if (!ifUserExist)
        {
            insertToLocalDatabaseUsers(getUserId, getName);
        }
        connection.close();
    }
    public static ArrayList<WifiData> getDataFromLocalDatabase() throws ClassNotFoundException, SQLException
    {
        ArrayList<WifiData> wifiData = new ArrayList<WifiData>();
        
        Class.forName(SqLite);
        String query = "SELECT * FROM wifidata;";
        Connection connection = DriverManager.getConnection(localUrlDatabase);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next())
        {
             wifiData.add(new WifiData(  Integer.parseInt(resultSet.getString("imageId")),
                                         Integer.parseInt(resultSet.getString("dataId")),
                                         resultSet.getString("wifiName"),
                                         Long.parseLong(resultSet.getString("dataTime")),
                                         resultSet.getString("wifiSsid"),
                                         Integer.parseInt(resultSet.getString("wifiRange")),
                                         Integer.parseInt(resultSet.getString("positionX")),
                                         Integer.parseInt(resultSet.getString("positionY"))
                                        ));
        }
        connection.close();
        return wifiData;
    }
    public static void inicializeWifiData() throws ClassNotFoundException, SQLException
    {
        Class.forName(mySql);
        Boolean ifICan = false;
        
        String query = "SELECT * FROM wifidata;";
        Connection connection = DriverManager.getConnection(urlDatabase);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
       
        while (resultSet.next())
        {
            insertToLocalDataWifi(Integer.parseInt(resultSet.getString("imageId")),
                                         Integer.parseInt(resultSet.getString("dataId")),
                                         resultSet.getString("wifiName"),
                                         Long.parseLong(resultSet.getString("dataTime")),
                                         resultSet.getString("wifiSsid"),
                                         Integer.parseInt(resultSet.getString("wifiRange")),
                                         Integer.parseInt(resultSet.getString("positionX")),
                                         Integer.parseInt(resultSet.getString("positionY")));
//            getImageId   = Integer.parseInt(resultSet.getString("imageId"));
//            getDataId    = Integer.parseInt(resultSet.getString("dataId"));
//            getDataTime  = Long.parseLong(resultSet.getString("dataTime"));
//            getWifiName  = resultSet.getString("wifiName");
//            getWifiSsid  = resultSet.getString("wifiSsid");
//            getWifiRange = Integer.parseInt(resultSet.getString("wifiRange"));
//            getPositionX = Integer.parseInt(resultSet.getString("positionX"));
//            getPositionY = Integer.parseInt(resultSet.getString("positionY"));
           // System.out.println("Na serwerze : " + getImageId + " Ssid " + getWifiSsid + " dataTime : " + getDataTime);
        }
        //insertToLocalDataWifi(wifiData);
        connection.close();
    }

    public static CachedRowSet getRemoteDataBaseAllUsers() throws ClassNotFoundException, SQLException
    {
        CachedRowSet crs = null;
        crs = new CachedRowSetImpl();
        Class.forName(mySql);
        Boolean ifICan = false;
        String query = "SELECT * FROM users;";
        Connection connection1 = DriverManager.getConnection(urlDatabase);
        Statement statement1 = connection1.createStatement();
        crs.setCommand(query);
        crs.execute(connection1);
        connection1.close();
        return crs;
    }

    private static Boolean findUserInDataBase(int getUserId) throws ClassNotFoundException, SQLException
    {
        ArrayList<Integer> liska = connectToLocalDataBase();
        Boolean getOut = false;
        for (Integer ip : liska)
        {
            if (ip == getUserId)
            {
                getOut = true;
            }
        }
        return getOut;
    }

    public static void setValue(Object obj, Class<?> cls, int rowIndex, int columnIndex) throws FileNotFoundException, IOException, SQLException
    {
        Connection connection = DriverManager.getConnection(SqLite);
        CachedRowSet crs = new CachedRowSetImpl();
        crs.absolute(rowIndex + 1);
        if (cls == Integer.class)
        {
            crs.updateInt(columnIndex + 1, (Integer) obj);
        }
        if (cls == Float.class)
        {
            crs.updateFloat(columnIndex + 1, (Float) obj);
        }
        if (cls == String.class)
        {
            crs.updateString(columnIndex + 1, (String) obj);
        }
        crs.updateRow();
        connection.setAutoCommit(false);
        crs.acceptChanges();
        connection.close();
    }
    private static String SqLite             = "org.sqlite.JDBC";
    private static String mySql              = "com.mysql.jdbc.Driver";
    private static String localUrlDatabase   = "jdbc:sqlite:db\\wifihertzdatabase.sqlite";
    private static String urlDatabase        = "jdbc:mysql://mysql3.hekko.net.pl/kalny_wifi?user=kalny_wifi&password=xI2oUCmk";
}
