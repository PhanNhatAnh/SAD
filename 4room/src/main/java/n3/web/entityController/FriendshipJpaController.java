/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import n3.web.entity.Friendship;
import n3.web.entityController.exceptions.NonexistentEntityException;

/**
 *
 * @author Aking
 */
public class FriendshipJpaController implements Serializable {

    public FriendshipJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Friendship friendship) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Account accountID2 = friendship.getAccountID2();
            if (accountID2 != null) {
                accountID2 = em.getReference(accountID2.getClass(), accountID2.getAccountID());
                friendship.setAccountID2(accountID2);
            }
            Account accountID1 = friendship.getAccountID1();
            if (accountID1 != null) {
                accountID1 = em.getReference(accountID1.getClass(), accountID1.getAccountID());
                friendship.setAccountID1(accountID1);
            }
            em.persist(friendship);
            if (accountID2 != null) {
                accountID2.getFriendshipList().add(friendship);
                accountID2 = em.merge(accountID2);
            }
            if (accountID1 != null) {
                accountID1.getFriendshipList().add(friendship);
                accountID1 = em.merge(accountID1);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Friendship friendship) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Friendship persistentFriendship = em.find(Friendship.class, friendship.getFriendShipID());
            Account accountID2Old = persistentFriendship.getAccountID2();
            Account accountID2New = friendship.getAccountID2();
            Account accountID1Old = persistentFriendship.getAccountID1();
            Account accountID1New = friendship.getAccountID1();
            if (accountID2New != null) {
                accountID2New = em.getReference(accountID2New.getClass(), accountID2New.getAccountID());
                friendship.setAccountID2(accountID2New);
            }
            if (accountID1New != null) {
                accountID1New = em.getReference(accountID1New.getClass(), accountID1New.getAccountID());
                friendship.setAccountID1(accountID1New);
            }
            friendship = em.merge(friendship);
            if (accountID2Old != null && !accountID2Old.equals(accountID2New)) {
                accountID2Old.getFriendshipList().remove(friendship);
                accountID2Old = em.merge(accountID2Old);
            }
            if (accountID2New != null && !accountID2New.equals(accountID2Old)) {
                accountID2New.getFriendshipList().add(friendship);
                accountID2New = em.merge(accountID2New);
            }
            if (accountID1Old != null && !accountID1Old.equals(accountID1New)) {
                accountID1Old.getFriendshipList().remove(friendship);
                accountID1Old = em.merge(accountID1Old);
            }
            if (accountID1New != null && !accountID1New.equals(accountID1Old)) {
                accountID1New.getFriendshipList().add(friendship);
                accountID1New = em.merge(accountID1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = friendship.getFriendShipID();
                if (findFriendship(id) == null) {
                    throw new NonexistentEntityException("The friendship with id " + id + " no longer exists.");
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
            Friendship friendship;
            try {
                friendship = em.getReference(Friendship.class, id);
                friendship.getFriendShipID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The friendship with id " + id + " no longer exists.", enfe);
            }
            Account accountID2 = friendship.getAccountID2();
            if (accountID2 != null) {
                accountID2.getFriendshipList().remove(friendship);
                accountID2 = em.merge(accountID2);
            }
            Account accountID1 = friendship.getAccountID1();
            if (accountID1 != null) {
                accountID1.getFriendshipList().remove(friendship);
                accountID1 = em.merge(accountID1);
            }
            em.remove(friendship);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Friendship> findFriendshipEntities() {
        return findFriendshipEntities(true, -1, -1);
    }

    public List<Friendship> findFriendshipEntities(int maxResults, int firstResult) {
        return findFriendshipEntities(false, maxResults, firstResult);
    }

    private List<Friendship> findFriendshipEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Friendship.class));
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

    public Friendship findFriendship(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Friendship.class, id);
        } finally {
            em.close();
        }
    }

    public int getFriendshipCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Friendship> rt = cq.from(Friendship.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
