/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package n3.web.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aking
 */
@Entity
@Table(name = "friendship")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Friendship.findAll", query = "SELECT f FROM Friendship f"),
    @NamedQuery(name = "Friendship.findByFriendShipID", query = "SELECT f FROM Friendship f WHERE f.friendShipID = :friendShipID")})
public class Friendship implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "friendShipID")
    private Integer friendShipID;
    @JoinColumn(name = "accountID2", referencedColumnName = "accountID")
    @ManyToOne(optional = false)
    private Account accountID2;
    @JoinColumn(name = "accountID1", referencedColumnName = "accountID")
    @ManyToOne(optional = false)
    private Account accountID1;

    public Friendship() {
    }

    public Friendship(Integer friendShipID) {
        this.friendShipID = friendShipID;
    }

    public Integer getFriendShipID() {
        return friendShipID;
    }

    public void setFriendShipID(Integer friendShipID) {
        this.friendShipID = friendShipID;
    }

    public Account getAccountID2() {
        return accountID2;
    }

    public void setAccountID2(Account accountID2) {
        this.accountID2 = accountID2;
    }

    public Account getAccountID1() {
        return accountID1;
    }

    public void setAccountID1(Account accountID1) {
        this.accountID1 = accountID1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (friendShipID != null ? friendShipID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Friendship)) {
            return false;
        }
        Friendship other = (Friendship) object;
        if ((this.friendShipID == null && other.friendShipID != null) || (this.friendShipID != null && !this.friendShipID.equals(other.friendShipID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "n3.web.entity.Friendship[ friendShipID=" + friendShipID + " ]";
    }
    
}
