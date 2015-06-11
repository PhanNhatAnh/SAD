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
import n3.web.entity.Accounticon;
import n3.web.entity.Thread;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import n3.web.entity.Account;
import n3.web.entity.Friendship;
import n3.web.entity.Comment;
import n3.web.entityController.exceptions.IllegalOrphanException;
import n3.web.entityController.exceptions.NonexistentEntityException;

/**
 *
 * @author Aking
 */
public class AccountJpaController implements Serializable {

    public AccountJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Account account) {
        if (account.getThreadList() == null) {
            account.setThreadList(new ArrayList<Thread>());
        }
        if (account.getFriendshipList() == null) {
            account.setFriendshipList(new ArrayList<Friendship>());
        }
        if (account.getCommentList() == null) {
            account.setCommentList(new ArrayList<Comment>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Accounticon accIconID = account.getAccIconID();
            if (accIconID != null) {
                accIconID = em.getReference(accIconID.getClass(), accIconID.getAccIconID());
                account.setAccIconID(accIconID);
            }
            List<Thread> attachedThreadList = new ArrayList<Thread>();
            for (Thread threadListThreadToAttach : account.getThreadList()) {
                threadListThreadToAttach = em.getReference(threadListThreadToAttach.getClass(), threadListThreadToAttach.getThreadID());
                attachedThreadList.add(threadListThreadToAttach);
            }
            account.setThreadList(attachedThreadList);
            List<Friendship> attachedFriendshipList = new ArrayList<Friendship>();
            for (Friendship friendshipListFriendshipToAttach : account.getFriendshipList()) {
                friendshipListFriendshipToAttach = em.getReference(friendshipListFriendshipToAttach.getClass(), friendshipListFriendshipToAttach.getFriendShipID());
                attachedFriendshipList.add(friendshipListFriendshipToAttach);
            }
            account.setFriendshipList(attachedFriendshipList);
            List<Comment> attachedCommentList = new ArrayList<Comment>();
            for (Comment commentListCommentToAttach : account.getCommentList()) {
                commentListCommentToAttach = em.getReference(commentListCommentToAttach.getClass(), commentListCommentToAttach.getCommentID());
                attachedCommentList.add(commentListCommentToAttach);
            }
            account.setCommentList(attachedCommentList);
            em.persist(account);
            if (accIconID != null) {
                accIconID.getAccountList().add(account);
                accIconID = em.merge(accIconID);
            }
            for (Thread threadListThread : account.getThreadList()) {
                Account oldAccountIDOfThreadListThread = threadListThread.getAccountID();
                threadListThread.setAccountID(account);
                threadListThread = em.merge(threadListThread);
                if (oldAccountIDOfThreadListThread != null) {
                    oldAccountIDOfThreadListThread.getThreadList().remove(threadListThread);
                    oldAccountIDOfThreadListThread = em.merge(oldAccountIDOfThreadListThread);
                }
            }
            for (Friendship friendshipListFriendship : account.getFriendshipList()) {
                Account oldAccountIDOfFriendshipListFriendship = friendshipListFriendship.getAccountID();
                friendshipListFriendship.setAccountID(account);
                friendshipListFriendship = em.merge(friendshipListFriendship);
                if (oldAccountIDOfFriendshipListFriendship != null) {
                    oldAccountIDOfFriendshipListFriendship.getFriendshipList().remove(friendshipListFriendship);
                    oldAccountIDOfFriendshipListFriendship = em.merge(oldAccountIDOfFriendshipListFriendship);
                }
            }
            for (Comment commentListComment : account.getCommentList()) {
                Account oldAccountIDOfCommentListComment = commentListComment.getAccountID();
                commentListComment.setAccountID(account);
                commentListComment = em.merge(commentListComment);
                if (oldAccountIDOfCommentListComment != null) {
                    oldAccountIDOfCommentListComment.getCommentList().remove(commentListComment);
                    oldAccountIDOfCommentListComment = em.merge(oldAccountIDOfCommentListComment);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Account account) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Account persistentAccount = em.find(Account.class, account.getAccountID());
            Accounticon accIconIDOld = persistentAccount.getAccIconID();
            Accounticon accIconIDNew = account.getAccIconID();
            List<Thread> threadListOld = persistentAccount.getThreadList();
            List<Thread> threadListNew = account.getThreadList();
            List<Friendship> friendshipListOld = persistentAccount.getFriendshipList();
            List<Friendship> friendshipListNew = account.getFriendshipList();
            List<Comment> commentListOld = persistentAccount.getCommentList();
            List<Comment> commentListNew = account.getCommentList();
            List<String> illegalOrphanMessages = null;
            for (Thread threadListOldThread : threadListOld) {
                if (!threadListNew.contains(threadListOldThread)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Thread " + threadListOldThread + " since its accountID field is not nullable.");
                }
            }
            for (Friendship friendshipListOldFriendship : friendshipListOld) {
                if (!friendshipListNew.contains(friendshipListOldFriendship)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Friendship " + friendshipListOldFriendship + " since its accountID field is not nullable.");
                }
            }
            for (Comment commentListOldComment : commentListOld) {
                if (!commentListNew.contains(commentListOldComment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comment " + commentListOldComment + " since its accountID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (accIconIDNew != null) {
                accIconIDNew = em.getReference(accIconIDNew.getClass(), accIconIDNew.getAccIconID());
                account.setAccIconID(accIconIDNew);
            }
            List<Thread> attachedThreadListNew = new ArrayList<Thread>();
            for (Thread threadListNewThreadToAttach : threadListNew) {
                threadListNewThreadToAttach = em.getReference(threadListNewThreadToAttach.getClass(), threadListNewThreadToAttach.getThreadID());
                attachedThreadListNew.add(threadListNewThreadToAttach);
            }
            threadListNew = attachedThreadListNew;
            account.setThreadList(threadListNew);
            List<Friendship> attachedFriendshipListNew = new ArrayList<Friendship>();
            for (Friendship friendshipListNewFriendshipToAttach : friendshipListNew) {
                friendshipListNewFriendshipToAttach = em.getReference(friendshipListNewFriendshipToAttach.getClass(), friendshipListNewFriendshipToAttach.getFriendShipID());
                attachedFriendshipListNew.add(friendshipListNewFriendshipToAttach);
            }
            friendshipListNew = attachedFriendshipListNew;
            account.setFriendshipList(friendshipListNew);
            List<Comment> attachedCommentListNew = new ArrayList<Comment>();
            for (Comment commentListNewCommentToAttach : commentListNew) {
                commentListNewCommentToAttach = em.getReference(commentListNewCommentToAttach.getClass(), commentListNewCommentToAttach.getCommentID());
                attachedCommentListNew.add(commentListNewCommentToAttach);
            }
            commentListNew = attachedCommentListNew;
            account.setCommentList(commentListNew);
            account = em.merge(account);
            if (accIconIDOld != null && !accIconIDOld.equals(accIconIDNew)) {
                accIconIDOld.getAccountList().remove(account);
                accIconIDOld = em.merge(accIconIDOld);
            }
            if (accIconIDNew != null && !accIconIDNew.equals(accIconIDOld)) {
                accIconIDNew.getAccountList().add(account);
                accIconIDNew = em.merge(accIconIDNew);
            }
            for (Thread threadListNewThread : threadListNew) {
                if (!threadListOld.contains(threadListNewThread)) {
                    Account oldAccountIDOfThreadListNewThread = threadListNewThread.getAccountID();
                    threadListNewThread.setAccountID(account);
                    threadListNewThread = em.merge(threadListNewThread);
                    if (oldAccountIDOfThreadListNewThread != null && !oldAccountIDOfThreadListNewThread.equals(account)) {
                        oldAccountIDOfThreadListNewThread.getThreadList().remove(threadListNewThread);
                        oldAccountIDOfThreadListNewThread = em.merge(oldAccountIDOfThreadListNewThread);
                    }
                }
            }
            for (Friendship friendshipListNewFriendship : friendshipListNew) {
                if (!friendshipListOld.contains(friendshipListNewFriendship)) {
                    Account oldAccountIDOfFriendshipListNewFriendship = friendshipListNewFriendship.getAccountID();
                    friendshipListNewFriendship.setAccountID(account);
                    friendshipListNewFriendship = em.merge(friendshipListNewFriendship);
                    if (oldAccountIDOfFriendshipListNewFriendship != null && !oldAccountIDOfFriendshipListNewFriendship.equals(account)) {
                        oldAccountIDOfFriendshipListNewFriendship.getFriendshipList().remove(friendshipListNewFriendship);
                        oldAccountIDOfFriendshipListNewFriendship = em.merge(oldAccountIDOfFriendshipListNewFriendship);
                    }
                }
            }
            for (Comment commentListNewComment : commentListNew) {
                if (!commentListOld.contains(commentListNewComment)) {
                    Account oldAccountIDOfCommentListNewComment = commentListNewComment.getAccountID();
                    commentListNewComment.setAccountID(account);
                    commentListNewComment = em.merge(commentListNewComment);
                    if (oldAccountIDOfCommentListNewComment != null && !oldAccountIDOfCommentListNewComment.equals(account)) {
                        oldAccountIDOfCommentListNewComment.getCommentList().remove(commentListNewComment);
                        oldAccountIDOfCommentListNewComment = em.merge(oldAccountIDOfCommentListNewComment);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = account.getAccountID();
                if (findAccount(id) == null) {
                    throw new NonexistentEntityException("The account with id " + id + " no longer exists.");
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
            Account account;
            try {
                account = em.getReference(Account.class, id);
                account.getAccountID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The account with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Thread> threadListOrphanCheck = account.getThreadList();
            for (Thread threadListOrphanCheckThread : threadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Account (" + account + ") cannot be destroyed since the Thread " + threadListOrphanCheckThread + " in its threadList field has a non-nullable accountID field.");
            }
            List<Friendship> friendshipListOrphanCheck = account.getFriendshipList();
            for (Friendship friendshipListOrphanCheckFriendship : friendshipListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Account (" + account + ") cannot be destroyed since the Friendship " + friendshipListOrphanCheckFriendship + " in its friendshipList field has a non-nullable accountID field.");
            }
            List<Comment> commentListOrphanCheck = account.getCommentList();
            for (Comment commentListOrphanCheckComment : commentListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Account (" + account + ") cannot be destroyed since the Comment " + commentListOrphanCheckComment + " in its commentList field has a non-nullable accountID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Accounticon accIconID = account.getAccIconID();
            if (accIconID != null) {
                accIconID.getAccountList().remove(account);
                accIconID = em.merge(accIconID);
            }
            em.remove(account);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Account> findAccountEntities() {
        return findAccountEntities(true, -1, -1);
    }

    public List<Account> findAccountEntities(int maxResults, int firstResult) {
        return findAccountEntities(false, maxResults, firstResult);
    }

    private List<Account> findAccountEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Account.class));
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

    public Account findAccount(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Account.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccountCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Account> rt = cq.from(Account.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
