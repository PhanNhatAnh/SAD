/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n3.web.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Aking
 */
@Entity
@Table(name = "accounticon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accounticon.findAll", query = "SELECT a FROM Accounticon a"),
    @NamedQuery(name = "Accounticon.findByAccIconID", query = "SELECT a FROM Accounticon a WHERE a.accIconID = :accIconID"),
    @NamedQuery(name = "Accounticon.findByIcon", query = "SELECT a FROM Accounticon a WHERE a.icon = :icon")})
public class Accounticon implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "accIconID")
    private Integer accIconID;
    @Basic(optional = false)
    @Column(name = "icon")
    private String icon;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accIconID")
    private List<Account> accountList;

    public Accounticon() {
    }

    public Accounticon(Integer accIconID) {
        this.accIconID = accIconID;
    }

    public Accounticon(Integer accIconID, String icon) {
        this.accIconID = accIconID;
        this.icon = icon;
    }

    public Integer getAccIconID() {
        return accIconID;
    }

    public void setAccIconID(Integer accIconID) {
        this.accIconID = accIconID;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @XmlTransient
    @JsonIgnore
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accIconID != null ? accIconID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accounticon)) {
            return false;
        }
        Accounticon other = (Accounticon) object;
        if ((this.accIconID == null && other.accIconID != null) || (this.accIconID != null && !this.accIconID.equals(other.accIconID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "n3.web.entity.Accounticon[ accIconID=" + accIconID + " ]";
    }
    
}
