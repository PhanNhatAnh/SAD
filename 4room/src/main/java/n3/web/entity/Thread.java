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
@Table(name = "thread")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Thread.findAll", query = "SELECT t FROM Thread t"),
    @NamedQuery(name = "Thread.findByThreadID", query = "SELECT t FROM Thread t WHERE t.threadID = :threadID"),
    @NamedQuery(name = "Thread.findByName", query = "SELECT t FROM Thread t WHERE t.name = :name"),
    @NamedQuery(name = "Thread.findByIsLock", query = "SELECT t FROM Thread t WHERE t.isLock = :isLock")})
public class Thread implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "threadID")
    private Integer threadID;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "isLock")
    private Boolean isLock;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "threadID")
    private List<Comment> commentList;

    public Thread() {
    }

    public Thread(Integer threadID) {
        this.threadID = threadID;
    }

    public Thread(Integer threadID, String name) {
        this.threadID = threadID;
        this.name = name;
    }

    public Integer getThreadID() {
        return threadID;
    }

    public void setThreadID(Integer threadID) {
        this.threadID = threadID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(Boolean isLock) {
        this.isLock = isLock;
    }

    @XmlTransient
    @JsonIgnore
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (threadID != null ? threadID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Thread)) {
            return false;
        }
        Thread other = (Thread) object;
        if ((this.threadID == null && other.threadID != null) || (this.threadID != null && !this.threadID.equals(other.threadID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "n3.web.entity.Thread[ threadID=" + threadID + " ]";
    }
    
}
