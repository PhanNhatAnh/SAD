package n3.web.exEntityController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import n3.web.entity.Account;
import n3.web.entityController.AccountJpaController;

import org.apache.log4j.Logger;

public class ExAccountController extends AccountJpaController{

	/** Logging. */
    private final static Logger LOG = Logger.getLogger(ExRatingController.class);
    
    public ExAccountController(EntityManagerFactory emf) {
        super(emf);
    }
    
    public Account checkLogin(String username, String password) {
		Account account = null;
		EntityManager em = getEntityManager();
    	try {
            Query query = em.createQuery("SELECT a FROM Account a WHERE a.username = :username and a.password = :password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            account = (Account) query.getSingleResult();
            
            return account;
        } finally {
            em.close();
        }
	}
}
