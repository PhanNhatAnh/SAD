/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n3.web.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aking
 */
@Entity
@Table(name = "comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findByCommentID", query = "SELECT c FROM Comment c WHERE c.commentID = :commentID"),
    @NamedQuery(name = "Comment.findByHide", query = "SELECT c FROM Comment c WHERE c.hide = :hide"),
    @NamedQuery(name = "Comment.findByLastEdit", query = "SELECT c FROM Comment c WHERE c.lastEdit = :lastEdit")})
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "commentID")
    private Integer commentID;
    @Column(name = "hide")
    private Boolean hide;
    @Lob
    @Column(name = "content")
    private String content;
    @Column(name = "lastEdit")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastEdit;
    @JoinColumn(name = "accountID", referencedColumnName = "accountID")
    @ManyToOne(optional = false)
    private Account accountID;
    @JoinColumn(name = "lastEditBy", referencedColumnName = "accountID")
    @ManyToOne(optional = false)
    private Account lastEditBy;
    @JoinColumn(name = "threadID", referencedColumnName = "threadID")
    @ManyToOne(optional = false)
    private Thread threadID;

    public Comment() {
    }

    public Comment(Integer commentID) {
        this.commentID = commentID;
    }

    public Integer getCommentID() {
        return commentID;
    }

    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
    }

    public Boolean getHide() {
        return hide;
    }

    public void setHide(Boolean hide) {
        this.hide = hide;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(Date lastEdit) {
        this.lastEdit = lastEdit;
    }

    public Account getAccountID() {
        return accountID;
    }

    public void setAccountID(Account accountID) {
        this.accountID = accountID;
    }

    public Account getLastEditBy() {
        return lastEditBy;
    }

    public void setLastEditBy(Account lastEditBy) {
        this.lastEditBy = lastEditBy;
    }

    public Thread getThreadID() {
        return threadID;
    }

    public void setThreadID(Thread threadID) {
        this.threadID = threadID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commentID != null ? commentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.commentID == null && other.commentID != null) || (this.commentID != null && !this.commentID.equals(other.commentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "n3.web.entity.Comment[ commentID=" + commentID + " ]";
    }
    
}
