/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WiFiHertzPackage;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Łukasz
 */
@Entity
@Table(name = "images", catalog = "wifihertz", schema = "")
@NamedQueries({
    @NamedQuery(name = "Images.findAll", query = "SELECT i FROM Images i"),
    @NamedQuery(name = "Images.findByImgId", query = "SELECT i FROM Images i WHERE i.imgId = :imgId"),
    @NamedQuery(name = "Images.findByUserIdImg", query = "SELECT i FROM Images i WHERE i.userIdImg = :userIdImg"),
    @NamedQuery(name = "Images.findByImgName", query = "SELECT i FROM Images i WHERE i.imgName = :imgName"),
    @NamedQuery(name = "Images.findByImgScr", query = "SELECT i FROM Images i WHERE i.imgScr = :imgScr")})
public class Images implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "img_id")
    private Integer imgId;
    @Column(name = "user_id_img")
    private Integer userIdImg;
    @Column(name = "img_name")
    private String imgName;
    @Column(name = "img_scr")
    private String imgScr;

    public Images() {
    }

    public Images(Integer imgId) {
        this.imgId = imgId;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        Integer oldImgId = this.imgId;
        this.imgId = imgId;
        changeSupport.firePropertyChange("imgId", oldImgId, imgId);
    }

    public Integer getUserIdImg() {
        return userIdImg;
    }

    public void setUserIdImg(Integer userIdImg) {
        Integer oldUserIdImg = this.userIdImg;
        this.userIdImg = userIdImg;
        changeSupport.firePropertyChange("userIdImg", oldUserIdImg, userIdImg);
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        String oldImgName = this.imgName;
        this.imgName = imgName;
        changeSupport.firePropertyChange("imgName", oldImgName, imgName);
    }

    public String getImgScr() {
        return imgScr;
    }

    public void setImgScr(String imgScr) {
        String oldImgScr = this.imgScr;
        this.imgScr = imgScr;
        changeSupport.firePropertyChange("imgScr", oldImgScr, imgScr);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imgId != null ? imgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Images)) {
            return false;
        }
        Images other = (Images) object;
        if ((this.imgId == null && other.imgId != null) || (this.imgId != null && !this.imgId.equals(other.imgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WiFiHertzPackage.Images[ imgId=" + imgId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
