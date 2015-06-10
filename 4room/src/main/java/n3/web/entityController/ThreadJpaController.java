/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package n3.web.entityController;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import n3.web.entity.Comment;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import n3.web.entity.Thread;
import n3.web.entityController.exceptions.IllegalOrphanException;
import n3.web.entityController.exceptions.NonexistentEntityException;

/**
 *
 * @author Aking
 */
public class ThreadJpaController implements Serializable {

    public ThreadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Thread thread) {
        if (thread.getCommentList() == null) {
            thread.setCommentList(new ArrayList<Comment>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Comment> attachedCommentList = new ArrayList<Comment>();
            for (Comment commentListCommentToAttach : thread.getCommentList()) {
                commentListCommentToAttach = em.getReference(commentListCommentToAttach.getClass(), commentListCommentToAttach.getCommentID());
                attachedCommentList.add(commentListCommentToAttach);
            }
            thread.setCommentList(attachedCommentList);
            em.persist(thread);
            for (Comment commentListComment : thread.getCommentList()) {
                Thread oldThreadIDOfCommentListComment = commentListComment.getThreadID();
                commentListComment.setThreadID(thread);
                commentListComment = em.merge(commentListComment);
                if (oldThreadIDOfCommentListComment != null) {
                    oldThreadIDOfCommentListComment.getCommentList().remove(commentListComment);
                    oldThreadIDOfCommentListComment = em.merge(oldThreadIDOfCommentListComment);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Thread thread) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Thread persistentThread = em.find(Thread.class, thread.getThreadID());
            List<Comment> commentListOld = persistentThread.getCommentList();
            List<Comment> commentListNew = thread.getCommentList();
            List<String> illegalOrphanMessages = null;
            for (Comment commentListOldComment : commentListOld) {
                if (!commentListNew.contains(commentListOldComment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comment " + commentListOldComment + " since its threadID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Comment> attachedCommentListNew = new ArrayList<Comment>();
            for (Comment commentListNewCommentToAttach : commentListNew) {
                commentListNewCommentToAttach = em.getReference(commentListNewCommentToAttach.getClass(), commentListNewCommentToAttach.getCommentID());
                attachedCommentListNew.add(commentListNewCommentToAttach);
            }
            commentListNew = attachedCommentListNew;
            thread.setCommentList(commentListNew);
            thread = em.merge(thread);
            for (Comment commentListNewComment : commentListNew) {
                if (!commentListOld.contains(commentListNewComment)) {
                    Thread oldThreadIDOfCommentListNewComment = commentListNewComment.getThreadID();
                    commentListNewComment.setThreadID(thread);
                    commentListNewComment = em.merge(commentListNewComment);
                    if (oldThreadIDOfCommentListNewComment != null && !oldThreadIDOfCommentListNewComment.equals(thread)) {
                        oldThreadIDOfCommentListNewComment.getCommentList().remove(commentListNewComment);
                        oldThreadIDOfCommentListNewComment = em.merge(oldThreadIDOfCommentListNewComment);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = thread.getThreadID();
                if (findThread(id) == null) {
                    throw new NonexistentEntityException("The thread with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Thread thread;
            try {
                thread = em.getReference(Thread.class, id);
                thread.getThreadID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The thread with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Comment> commentListOrphanCheck = thread.getCommentList();
            for (Comment commentListOrphanCheckComment : commentListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Thread (" + thread + ") cannot be destroyed since the Comment " + commentListOrphanCheckComment + " in its commentList field has a non-nullable threadID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(thread);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Thread> findThreadEntities() {
        return findThreadEntities(true, -1, -1);
    }

    public List<Thread> findThreadEntities(int maxResults, int firstResult) {
        return findThreadEntities(false, maxResults, firstResult);
    }

    private List<Thread> findThreadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Thread.class));
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

    public Thread findThread(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Thread.class, id);
        } finally {
            em.close();
        }
    }

    public int getThreadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Thread> rt = cq.from(Thread.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
