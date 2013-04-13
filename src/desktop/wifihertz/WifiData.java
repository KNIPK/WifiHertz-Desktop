/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.wifihertz;

/**
 *
 * @author Łukasz
 */
public class WifiData
{
    private int imageId; private int dataId; private String wifiName;
    private long dataTime; private String wifiSsid; private int wifiRange;
    private int positionX; private int positionY;

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getImageId() {
        return imageId;
    }

    public int getDataId() {
        return dataId;
    }

    public String getWifiName() {
        return wifiName;
    }

    public long getDataTime() {
        return dataTime;
    }

    public String getWifiSsid() {
        return wifiSsid;
    }

    public int getWifiRange() {
        return wifiRange;
    }
    
    
    
    public WifiData()
    {
        this.imageId = 0; this.dataId=0; this.wifiName = null; this.dataTime = 0;
        this.wifiSsid = null; this.wifiRange = 0; this.positionX = 0; this.positionY = 0;
    }
    public WifiData(int imageId, int dataId, String wifiName, long dataTime, String wifiSsid, int wifiRange, int positionX, int positionY)
    {
        this.imageId = imageId; this.dataId = dataId; this.wifiName = wifiName ; this.dataTime = dataTime;
        this.wifiSsid = wifiSsid; this.wifiRange = wifiRange; this.positionX = positionX; this.positionY = positionY;
    }
    
    @Override
    public String toString()
    {
        return   "imageId : " + this.imageId  + " dataId " + this.dataId + " dataTime " + this.dataTime + " wifiName " +
                 this.wifiName + " wifiSsid "  + this.wifiSsid + " wifiRange " + 
                 this.wifiRange + " positionX " + this.positionX + " positionY " + this.positionY;
    }
}
