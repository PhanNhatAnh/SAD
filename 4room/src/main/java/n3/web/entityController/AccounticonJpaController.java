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
import n3.web.entity.Account;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import n3.web.entity.Accounticon;
import n3.web.entityController.exceptions.IllegalOrphanException;
import n3.web.entityController.exceptions.NonexistentEntityException;

/**
 *
 * @author Aking
 */
public class AccounticonJpaController implements Serializable {

    public AccounticonJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Accounticon accounticon) {
        if (accounticon.getAccountList() == null) {
            accounticon.setAccountList(new ArrayList<Account>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Account> attachedAccountList = new ArrayList<Account>();
            for (Account accountListAccountToAttach : accounticon.getAccountList()) {
                accountListAccountToAttach = em.getReference(accountListAccountToAttach.getClass(), accountListAccountToAttach.getAccountID());
                attachedAccountList.add(accountListAccountToAttach);
            }
            accounticon.setAccountList(attachedAccountList);
            em.persist(accounticon);
            for (Account accountListAccount : accounticon.getAccountList()) {
                Accounticon oldAccIconIDOfAccountListAccount = accountListAccount.getAccIconID();
                accountListAccount.setAccIconID(accounticon);
                accountListAccount = em.merge(accountListAccount);
                if (oldAccIconIDOfAccountListAccount != null) {
                    oldAccIconIDOfAccountListAccount.getAccountList().remove(accountListAccount);
                    oldAccIconIDOfAccountListAccount = em.merge(oldAccIconIDOfAccountListAccount);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Accounticon accounticon) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Accounticon persistentAccounticon = em.find(Accounticon.class, accounticon.getAccIconID());
            List<Account> accountListOld = persistentAccounticon.getAccountList();
            List<Account> accountListNew = accounticon.getAccountList();
            List<String> illegalOrphanMessages = null;
            for (Account accountListOldAccount : accountListOld) {
                if (!accountListNew.contains(accountListOldAccount)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Account " + accountListOldAccount + " since its accIconID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Account> attachedAccountListNew = new ArrayList<Account>();
            for (Account accountListNewAccountToAttach : accountListNew) {
                accountListNewAccountToAttach = em.getReference(accountListNewAccountToAttach.getClass(), accountListNewAccountToAttach.getAccountID());
                attachedAccountListNew.add(accountListNewAccountToAttach);
            }
            accountListNew = attachedAccountListNew;
            accounticon.setAccountList(accountListNew);
            accounticon = em.merge(accounticon);
            for (Account accountListNewAccount : accountListNew) {
                if (!accountListOld.contains(accountListNewAccount)) {
                    Accounticon oldAccIconIDOfAccountListNewAccount = accountListNewAccount.getAccIconID();
                    accountListNewAccount.setAccIconID(accounticon);
                    accountListNewAccount = em.merge(accountListNewAccount);
                    if (oldAccIconIDOfAccountListNewAccount != null && !oldAccIconIDOfAccountListNewAccount.equals(accounticon)) {
                        oldAccIconIDOfAccountListNewAccount.getAccountList().remove(accountListNewAccount);
                        oldAccIconIDOfAccountListNewAccount = em.merge(oldAccIconIDOfAccountListNewAccount);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = accounticon.getAccIconID();
                if (findAccounticon(id) == null) {
                    throw new NonexistentEntityException("The accounticon with id " + id + " no longer exists.");
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
            Accounticon accounticon;
            try {
                accounticon = em.getReference(Accounticon.class, id);
                accounticon.getAccIconID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The accounticon with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Account> accountListOrphanCheck = accounticon.getAccountList();
            for (Account accountListOrphanCheckAccount : accountListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Accounticon (" + accounticon + ") cannot be destroyed since the Account " + accountListOrphanCheckAccount + " in its accountList field has a non-nullable accIconID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(accounticon);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Accounticon> findAccounticonEntities() {
        return findAccounticonEntities(true, -1, -1);
    }

    public List<Accounticon> findAccounticonEntities(int maxResults, int firstResult) {
        return findAccounticonEntities(false, maxResults, firstResult);
    }

    private List<Accounticon> findAccounticonEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Accounticon.class));
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

    public Accounticon findAccounticon(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Accounticon.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccounticonCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Accounticon> rt = cq.from(Accounticon.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
