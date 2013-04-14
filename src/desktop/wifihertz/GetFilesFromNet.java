/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.wifihertz;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 *
 * @author Łukasz
 */
public class GetFilesFromNet
{

    public static void saveImage(String imageUrl, String destinationFile) throws IOException
    {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        String name = "C:\\Users\\vanlu_000\\Documents\\NetBeansProjects\\Kwiecien\\WifiHertz-Desktop\\img\\"+destinationFile;
        System.out.println("plik " + name);
        OutputStream os = new FileOutputStream(name);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1)
        {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }
}
