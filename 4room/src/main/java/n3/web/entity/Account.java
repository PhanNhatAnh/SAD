/*
 * To change this template, choose Tools | Templates
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByAccountID", query = "SELECT a FROM Account a WHERE a.accountID = :accountID"),
    @NamedQuery(name = "Account.findByUsername", query = "SELECT a FROM Account a WHERE a.username = :username"),
    @NamedQuery(name = "Account.findByPasswords", query = "SELECT a FROM Account a WHERE a.passwords = :passwords"),
    @NamedQuery(name = "Account.findByRole", query = "SELECT a FROM Account a WHERE a.role = :role"),
    @NamedQuery(name = "Account.findByAvatarImg", query = "SELECT a FROM Account a WHERE a.avatarImg = :avatarImg")})
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "accountID")
    private Integer accountID;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "passwords")
    private String passwords;
    @Basic(optional = false)
    @Column(name = "role")
    private String role;
    @Basic(optional = false)
    @Column(name = "avatarImg")
    private String avatarImg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastUpdateBy")
    private List<Thread> threadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountID")
    private List<Thread> threadList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountID2")
    private List<Friendship> friendshipList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountID1")
    private List<Friendship> friendshipList1;
    @JoinColumn(name = "accIconID", referencedColumnName = "accIconID")
    @ManyToOne(optional = false)
    private Accounticon accIconID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountID")
    private List<Comment> commentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastEditBy")
    private List<Comment> commentList1;

    public Account() {
    }

    public Account(Integer accountID) {
        this.accountID = accountID;
    }

    public Account(Integer accountID, String username, String passwords, String role, String avatarImg) {
        this.accountID = accountID;
        this.username = username;
        this.passwords = passwords;
        this.role = role;
        this.avatarImg = avatarImg;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatarImg() {
        return avatarImg;
    }

    public void setAvatarImg(String avatarImg) {
        this.avatarImg = avatarImg;
    }

    @XmlTransient
    @JsonIgnore
    public List<Thread> getThreadList() {
        return threadList;
    }

    public void setThreadList(List<Thread> threadList) {
        this.threadList = threadList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Thread> getThreadList1() {
        return threadList1;
    }

    public void setThreadList1(List<Thread> threadList1) {
        this.threadList1 = threadList1;
    }

    @XmlTransient
    @JsonIgnore
    public List<Friendship> getFriendshipList() {
        return friendshipList;
    }

    public void setFriendshipList(List<Friendship> friendshipList) {
        this.friendshipList = friendshipList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Friendship> getFriendshipList1() {
        return friendshipList1;
    }

    public void setFriendshipList1(List<Friendship> friendshipList1) {
        this.friendshipList1 = friendshipList1;
    }

    public Accounticon getAccIconID() {
        return accIconID;
    }

    public void setAccIconID(Accounticon accIconID) {
        this.accIconID = accIconID;
    }

    @XmlTransient
    @JsonIgnore
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Comment> getCommentList1() {
        return commentList1;
    }

    public void setCommentList1(List<Comment> commentList1) {
        this.commentList1 = commentList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountID != null ? accountID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accountID == null && other.accountID != null) || (this.accountID != null && !this.accountID.equals(other.accountID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "n3.web.entity.Account[ accountID=" + accountID + " ]";
    }
    
}
