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
        if (account.getThreadList1() == null) {
            account.setThreadList1(new ArrayList<Thread>());
        }
        if (account.getFriendshipList() == null) {
            account.setFriendshipList(new ArrayList<Friendship>());
        }
        if (account.getFriendshipList1() == null) {
            account.setFriendshipList1(new ArrayList<Friendship>());
        }
        if (account.getCommentList() == null) {
            account.setCommentList(new ArrayList<Comment>());
        }
        if (account.getCommentList1() == null) {
            account.setCommentList1(new ArrayList<Comment>());
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
            List<Thread> attachedThreadList1 = new ArrayList<Thread>();
            for (Thread threadList1ThreadToAttach : account.getThreadList1()) {
                threadList1ThreadToAttach = em.getReference(threadList1ThreadToAttach.getClass(), threadList1ThreadToAttach.getThreadID());
                attachedThreadList1.add(threadList1ThreadToAttach);
            }
            account.setThreadList1(attachedThreadList1);
            List<Friendship> attachedFriendshipList = new ArrayList<Friendship>();
            for (Friendship friendshipListFriendshipToAttach : account.getFriendshipList()) {
                friendshipListFriendshipToAttach = em.getReference(friendshipListFriendshipToAttach.getClass(), friendshipListFriendshipToAttach.getFriendShipID());
                attachedFriendshipList.add(friendshipListFriendshipToAttach);
            }
            account.setFriendshipList(attachedFriendshipList);
            List<Friendship> attachedFriendshipList1 = new ArrayList<Friendship>();
            for (Friendship friendshipList1FriendshipToAttach : account.getFriendshipList1()) {
                friendshipList1FriendshipToAttach = em.getReference(friendshipList1FriendshipToAttach.getClass(), friendshipList1FriendshipToAttach.getFriendShipID());
                attachedFriendshipList1.add(friendshipList1FriendshipToAttach);
            }
            account.setFriendshipList1(attachedFriendshipList1);
            List<Comment> attachedCommentList = new ArrayList<Comment>();
            for (Comment commentListCommentToAttach : account.getCommentList()) {
                commentListCommentToAttach = em.getReference(commentListCommentToAttach.getClass(), commentListCommentToAttach.getCommentID());
                attachedCommentList.add(commentListCommentToAttach);
            }
            account.setCommentList(attachedCommentList);
            List<Comment> attachedCommentList1 = new ArrayList<Comment>();
            for (Comment commentList1CommentToAttach : account.getCommentList1()) {
                commentList1CommentToAttach = em.getReference(commentList1CommentToAttach.getClass(), commentList1CommentToAttach.getCommentID());
                attachedCommentList1.add(commentList1CommentToAttach);
            }
            account.setCommentList1(attachedCommentList1);
            em.persist(account);
            if (accIconID != null) {
                accIconID.getAccountList().add(account);
                accIconID = em.merge(accIconID);
            }
            for (Thread threadListThread : account.getThreadList()) {
                Account oldLastUpdateByOfThreadListThread = threadListThread.getLastUpdateBy();
                threadListThread.setLastUpdateBy(account);
                threadListThread = em.merge(threadListThread);
                if (oldLastUpdateByOfThreadListThread != null) {
                    oldLastUpdateByOfThreadListThread.getThreadList().remove(threadListThread);
                    oldLastUpdateByOfThreadListThread = em.merge(oldLastUpdateByOfThreadListThread);
                }
            }
            for (Thread threadList1Thread : account.getThreadList1()) {
                Account oldAccountIDOfThreadList1Thread = threadList1Thread.getAccountID();
                threadList1Thread.setAccountID(account);
                threadList1Thread = em.merge(threadList1Thread);
                if (oldAccountIDOfThreadList1Thread != null) {
                    oldAccountIDOfThreadList1Thread.getThreadList1().remove(threadList1Thread);
                    oldAccountIDOfThreadList1Thread = em.merge(oldAccountIDOfThreadList1Thread);
                }
            }
            for (Friendship friendshipListFriendship : account.getFriendshipList()) {
                Account oldAccountID2OfFriendshipListFriendship = friendshipListFriendship.getAccountID2();
                friendshipListFriendship.setAccountID2(account);
                friendshipListFriendship = em.merge(friendshipListFriendship);
                if (oldAccountID2OfFriendshipListFriendship != null) {
                    oldAccountID2OfFriendshipListFriendship.getFriendshipList().remove(friendshipListFriendship);
                    oldAccountID2OfFriendshipListFriendship = em.merge(oldAccountID2OfFriendshipListFriendship);
                }
            }
            for (Friendship friendshipList1Friendship : account.getFriendshipList1()) {
                Account oldAccountID1OfFriendshipList1Friendship = friendshipList1Friendship.getAccountID1();
                friendshipList1Friendship.setAccountID1(account);
                friendshipList1Friendship = em.merge(friendshipList1Friendship);
                if (oldAccountID1OfFriendshipList1Friendship != null) {
                    oldAccountID1OfFriendshipList1Friendship.getFriendshipList1().remove(friendshipList1Friendship);
                    oldAccountID1OfFriendshipList1Friendship = em.merge(oldAccountID1OfFriendshipList1Friendship);
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
            for (Comment commentList1Comment : account.getCommentList1()) {
                Account oldLastEditByOfCommentList1Comment = commentList1Comment.getLastEditBy();
                commentList1Comment.setLastEditBy(account);
                commentList1Comment = em.merge(commentList1Comment);
                if (oldLastEditByOfCommentList1Comment != null) {
                    oldLastEditByOfCommentList1Comment.getCommentList1().remove(commentList1Comment);
                    oldLastEditByOfCommentList1Comment = em.merge(oldLastEditByOfCommentList1Comment);
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
            List<Thread> threadList1Old = persistentAccount.getThreadList1();
            List<Thread> threadList1New = account.getThreadList1();
            List<Friendship> friendshipListOld = persistentAccount.getFriendshipList();
            List<Friendship> friendshipListNew = account.getFriendshipList();
            List<Friendship> friendshipList1Old = persistentAccount.getFriendshipList1();
            List<Friendship> friendshipList1New = account.getFriendshipList1();
            List<Comment> commentListOld = persistentAccount.getCommentList();
            List<Comment> commentListNew = account.getCommentList();
            List<Comment> commentList1Old = persistentAccount.getCommentList1();
            List<Comment> commentList1New = account.getCommentList1();
            List<String> illegalOrphanMessages = null;
            for (Thread threadListOldThread : threadListOld) {
                if (!threadListNew.contains(threadListOldThread)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Thread " + threadListOldThread + " since its lastUpdateBy field is not nullable.");
                }
            }
            for (Thread threadList1OldThread : threadList1Old) {
                if (!threadList1New.contains(threadList1OldThread)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Thread " + threadList1OldThread + " since its accountID field is not nullable.");
                }
            }
            for (Friendship friendshipListOldFriendship : friendshipListOld) {
                if (!friendshipListNew.contains(friendshipListOldFriendship)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Friendship " + friendshipListOldFriendship + " since its accountID2 field is not nullable.");
                }
            }
            for (Friendship friendshipList1OldFriendship : friendshipList1Old) {
                if (!friendshipList1New.contains(friendshipList1OldFriendship)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Friendship " + friendshipList1OldFriendship + " since its accountID1 field is not nullable.");
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
            for (Comment commentList1OldComment : commentList1Old) {
                if (!commentList1New.contains(commentList1OldComment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comment " + commentList1OldComment + " since its lastEditBy field is not nullable.");
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
            List<Thread> attachedThreadList1New = new ArrayList<Thread>();
            for (Thread threadList1NewThreadToAttach : threadList1New) {
                threadList1NewThreadToAttach = em.getReference(threadList1NewThreadToAttach.getClass(), threadList1NewThreadToAttach.getThreadID());
                attachedThreadList1New.add(threadList1NewThreadToAttach);
            }
            threadList1New = attachedThreadList1New;
            account.setThreadList1(threadList1New);
            List<Friendship> attachedFriendshipListNew = new ArrayList<Friendship>();
            for (Friendship friendshipListNewFriendshipToAttach : friendshipListNew) {
                friendshipListNewFriendshipToAttach = em.getReference(friendshipListNewFriendshipToAttach.getClass(), friendshipListNewFriendshipToAttach.getFriendShipID());
                attachedFriendshipListNew.add(friendshipListNewFriendshipToAttach);
            }
            friendshipListNew = attachedFriendshipListNew;
            account.setFriendshipList(friendshipListNew);
            List<Friendship> attachedFriendshipList1New = new ArrayList<Friendship>();
            for (Friendship friendshipList1NewFriendshipToAttach : friendshipList1New) {
                friendshipList1NewFriendshipToAttach = em.getReference(friendshipList1NewFriendshipToAttach.getClass(), friendshipList1NewFriendshipToAttach.getFriendShipID());
                attachedFriendshipList1New.add(friendshipList1NewFriendshipToAttach);
            }
            friendshipList1New = attachedFriendshipList1New;
            account.setFriendshipList1(friendshipList1New);
            List<Comment> attachedCommentListNew = new ArrayList<Comment>();
            for (Comment commentListNewCommentToAttach : commentListNew) {
                commentListNewCommentToAttach = em.getReference(commentListNewCommentToAttach.getClass(), commentListNewCommentToAttach.getCommentID());
                attachedCommentListNew.add(commentListNewCommentToAttach);
            }
            commentListNew = attachedCommentListNew;
            account.setCommentList(commentListNew);
            List<Comment> attachedCommentList1New = new ArrayList<Comment>();
            for (Comment commentList1NewCommentToAttach : commentList1New) {
                commentList1NewCommentToAttach = em.getReference(commentList1NewCommentToAttach.getClass(), commentList1NewCommentToAttach.getCommentID());
                attachedCommentList1New.add(commentList1NewCommentToAttach);
            }
            commentList1New = attachedCommentList1New;
            account.setCommentList1(commentList1New);
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
                    Account oldLastUpdateByOfThreadListNewThread = threadListNewThread.getLastUpdateBy();
                    threadListNewThread.setLastUpdateBy(account);
                    threadListNewThread = em.merge(threadListNewThread);
                    if (oldLastUpdateByOfThreadListNewThread != null && !oldLastUpdateByOfThreadListNewThread.equals(account)) {
                        oldLastUpdateByOfThreadListNewThread.getThreadList().remove(threadListNewThread);
                        oldLastUpdateByOfThreadListNewThread = em.merge(oldLastUpdateByOfThreadListNewThread);
                    }
                }
            }
            for (Thread threadList1NewThread : threadList1New) {
                if (!threadList1Old.contains(threadList1NewThread)) {
                    Account oldAccountIDOfThreadList1NewThread = threadList1NewThread.getAccountID();
                    threadList1NewThread.setAccountID(account);
                    threadList1NewThread = em.merge(threadList1NewThread);
                    if (oldAccountIDOfThreadList1NewThread != null && !oldAccountIDOfThreadList1NewThread.equals(account)) {
                        oldAccountIDOfThreadList1NewThread.getThreadList1().remove(threadList1NewThread);
                        oldAccountIDOfThreadList1NewThread = em.merge(oldAccountIDOfThreadList1NewThread);
                    }
                }
            }
            for (Friendship friendshipListNewFriendship : friendshipListNew) {
                if (!friendshipListOld.contains(friendshipListNewFriendship)) {
                    Account oldAccountID2OfFriendshipListNewFriendship = friendshipListNewFriendship.getAccountID2();
                    friendshipListNewFriendship.setAccountID2(account);
                    friendshipListNewFriendship = em.merge(friendshipListNewFriendship);
                    if (oldAccountID2OfFriendshipListNewFriendship != null && !oldAccountID2OfFriendshipListNewFriendship.equals(account)) {
                        oldAccountID2OfFriendshipListNewFriendship.getFriendshipList().remove(friendshipListNewFriendship);
                        oldAccountID2OfFriendshipListNewFriendship = em.merge(oldAccountID2OfFriendshipListNewFriendship);
                    }
                }
            }
            for (Friendship friendshipList1NewFriendship : friendshipList1New) {
                if (!friendshipList1Old.contains(friendshipList1NewFriendship)) {
                    Account oldAccountID1OfFriendshipList1NewFriendship = friendshipList1NewFriendship.getAccountID1();
                    friendshipList1NewFriendship.setAccountID1(account);
                    friendshipList1NewFriendship = em.merge(friendshipList1NewFriendship);
                    if (oldAccountID1OfFriendshipList1NewFriendship != null && !oldAccountID1OfFriendshipList1NewFriendship.equals(account)) {
                        oldAccountID1OfFriendshipList1NewFriendship.getFriendshipList1().remove(friendshipList1NewFriendship);
                        oldAccountID1OfFriendshipList1NewFriendship = em.merge(oldAccountID1OfFriendshipList1NewFriendship);
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
            for (Comment commentList1NewComment : commentList1New) {
                if (!commentList1Old.contains(commentList1NewComment)) {
                    Account oldLastEditByOfCommentList1NewComment = commentList1NewComment.getLastEditBy();
                    commentList1NewComment.setLastEditBy(account);
                    commentList1NewComment = em.merge(commentList1NewComment);
                    if (oldLastEditByOfCommentList1NewComment != null && !oldLastEditByOfCommentList1NewComment.equals(account)) {
                        oldLastEditByOfCommentList1NewComment.getCommentList1().remove(commentList1NewComment);
                        oldLastEditByOfCommentList1NewComment = em.merge(oldLastEditByOfCommentList1NewComment);
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
                illegalOrphanMessages.add("This Account (" + account + ") cannot be destroyed since the Thread " + threadListOrphanCheckThread + " in its threadList field has a non-nullable lastUpdateBy field.");
            }
            List<Thread> threadList1OrphanCheck = account.getThreadList1();
            for (Thread threadList1OrphanCheckThread : threadList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Account (" + account + ") cannot be destroyed since the Thread " + threadList1OrphanCheckThread + " in its threadList1 field has a non-nullable accountID field.");
            }
            List<Friendship> friendshipListOrphanCheck = account.getFriendshipList();
            for (Friendship friendshipListOrphanCheckFriendship : friendshipListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Account (" + account + ") cannot be destroyed since the Friendship " + friendshipListOrphanCheckFriendship + " in its friendshipList field has a non-nullable accountID2 field.");
            }
            List<Friendship> friendshipList1OrphanCheck = account.getFriendshipList1();
            for (Friendship friendshipList1OrphanCheckFriendship : friendshipList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Account (" + account + ") cannot be destroyed since the Friendship " + friendshipList1OrphanCheckFriendship + " in its friendshipList1 field has a non-nullable accountID1 field.");
            }
            List<Comment> commentListOrphanCheck = account.getCommentList();
            for (Comment commentListOrphanCheckComment : commentListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Account (" + account + ") cannot be destroyed since the Comment " + commentListOrphanCheckComment + " in its commentList field has a non-nullable accountID field.");
            }
            List<Comment> commentList1OrphanCheck = account.getCommentList1();
            for (Comment commentList1OrphanCheckComment : commentList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Account (" + account + ") cannot be destroyed since the Comment " + commentList1OrphanCheckComment + " in its commentList1 field has a non-nullable lastEditBy field.");
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
