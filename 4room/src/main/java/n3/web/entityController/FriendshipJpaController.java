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
            Account accountID = friendship.getAccountID();
            if (accountID != null) {
                accountID = em.getReference(accountID.getClass(), accountID.getAccountID());
                friendship.setAccountID(accountID);
            }
            em.persist(friendship);
            if (accountID != null) {
                accountID.getFriendshipList().add(friendship);
                accountID = em.merge(accountID);
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
            Account accountIDOld = persistentFriendship.getAccountID();
            Account accountIDNew = friendship.getAccountID();
            if (accountIDNew != null) {
                accountIDNew = em.getReference(accountIDNew.getClass(), accountIDNew.getAccountID());
                friendship.setAccountID(accountIDNew);
            }
            friendship = em.merge(friendship);
            if (accountIDOld != null && !accountIDOld.equals(accountIDNew)) {
                accountIDOld.getFriendshipList().remove(friendship);
                accountIDOld = em.merge(accountIDOld);
            }
            if (accountIDNew != null && !accountIDNew.equals(accountIDOld)) {
                accountIDNew.getFriendshipList().add(friendship);
                accountIDNew = em.merge(accountIDNew);
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
            Account accountID = friendship.getAccountID();
            if (accountID != null) {
                accountID.getFriendshipList().remove(friendship);
                accountID = em.merge(accountID);
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
