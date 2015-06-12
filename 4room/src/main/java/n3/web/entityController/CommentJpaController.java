/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package n3.web.entityController;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import n3.web.entity.Account;
import n3.web.entity.Comment;
import n3.web.entity.Thread;
import n3.web.entityController.exceptions.NonexistentEntityException;

/**
 *
 * @author Aking
 */
public class CommentJpaController implements Serializable {

    public CommentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comment comment) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Account accountID = comment.getAccountID();
            if (accountID != null) {
                accountID = em.getReference(accountID.getClass(), accountID.getAccountID());
                comment.setAccountID(accountID);
            }
            Account lastEditBy = comment.getLastEditBy();
            if (lastEditBy != null) {
                lastEditBy = em.getReference(lastEditBy.getClass(), lastEditBy.getAccountID());
                comment.setLastEditBy(lastEditBy);
            }
            Thread threadID = comment.getThreadID();
            if (threadID != null) {
                threadID = em.getReference(threadID.getClass(), threadID.getThreadID());
                comment.setThreadID(threadID);
            }
            em.persist(comment);
            if (accountID != null) {
                accountID.getCommentList().add(comment);
                accountID = em.merge(accountID);
            }
            if (lastEditBy != null) {
                lastEditBy.getCommentList().add(comment);
                lastEditBy = em.merge(lastEditBy);
            }
            if (threadID != null) {
                threadID.getCommentList().add(comment);
                threadID = em.merge(threadID);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comment comment) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comment persistentComment = em.find(Comment.class, comment.getCommentID());
            Account accountIDOld = persistentComment.getAccountID();
            Account accountIDNew = comment.getAccountID();
            Account lastEditByOld = persistentComment.getLastEditBy();
            Account lastEditByNew = comment.getLastEditBy();
            Thread threadIDOld = persistentComment.getThreadID();
            Thread threadIDNew = comment.getThreadID();
            if (accountIDNew != null) {
                accountIDNew = em.getReference(accountIDNew.getClass(), accountIDNew.getAccountID());
                comment.setAccountID(accountIDNew);
            }
            if (lastEditByNew != null) {
                lastEditByNew = em.getReference(lastEditByNew.getClass(), lastEditByNew.getAccountID());
                comment.setLastEditBy(lastEditByNew);
            }
            if (threadIDNew != null) {
                threadIDNew = em.getReference(threadIDNew.getClass(), threadIDNew.getThreadID());
                comment.setThreadID(threadIDNew);
            }
            comment = em.merge(comment);
            if (accountIDOld != null && !accountIDOld.equals(accountIDNew)) {
                accountIDOld.getCommentList().remove(comment);
                accountIDOld = em.merge(accountIDOld);
            }
            if (accountIDNew != null && !accountIDNew.equals(accountIDOld)) {
                accountIDNew.getCommentList().add(comment);
                accountIDNew = em.merge(accountIDNew);
            }
            if (lastEditByOld != null && !lastEditByOld.equals(lastEditByNew)) {
                lastEditByOld.getCommentList().remove(comment);
                lastEditByOld = em.merge(lastEditByOld);
            }
            if (lastEditByNew != null && !lastEditByNew.equals(lastEditByOld)) {
                lastEditByNew.getCommentList().add(comment);
                lastEditByNew = em.merge(lastEditByNew);
            }
            if (threadIDOld != null && !threadIDOld.equals(threadIDNew)) {
                threadIDOld.getCommentList().remove(comment);
                threadIDOld = em.merge(threadIDOld);
            }
            if (threadIDNew != null && !threadIDNew.equals(threadIDOld)) {
                threadIDNew.getCommentList().add(comment);
                threadIDNew = em.merge(threadIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comment.getCommentID();
                if (findComment(id) == null) {
                    throw new NonexistentEntityException("The comment with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comment comment;
            try {
                comment = em.getReference(Comment.class, id);
                comment.getCommentID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comment with id " + id + " no longer exists.", enfe);
            }
            Account accountID = comment.getAccountID();
            if (accountID != null) {
                accountID.getCommentList().remove(comment);
                accountID = em.merge(accountID);
            }
            Account lastEditBy = comment.getLastEditBy();
            if (lastEditBy != null) {
                lastEditBy.getCommentList().remove(comment);
                lastEditBy = em.merge(lastEditBy);
            }
            Thread threadID = comment.getThreadID();
            if (threadID != null) {
                threadID.getCommentList().remove(comment);
                threadID = em.merge(threadID);
            }
            em.remove(comment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comment> findCommentEntities() {
        return findCommentEntities(true, -1, -1);
    }

    public List<Comment> findCommentEntities(int maxResults, int firstResult) {
        return findCommentEntities(false, maxResults, firstResult);
    }

    private List<Comment> findCommentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comment.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Comment findComment(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comment.class, id);
        } finally {
            em.close();
        }
    }

    public int getCommentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comment> rt = cq.from(Comment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
