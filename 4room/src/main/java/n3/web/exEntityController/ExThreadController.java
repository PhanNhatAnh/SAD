package n3.web.exEntityController;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import n3.web.entity.Rating;
import n3.web.entity.Thread;
import n3.web.entityController.ThreadJpaController;

import org.apache.log4j.Logger;

public class ExThreadController extends ThreadJpaController{

	/** Logging. */
    private final static Logger LOG = Logger.getLogger(ExThreadController.class);
    
    public ExThreadController(EntityManagerFactory emf) {
        super(emf);
    }
    
    public List<Thread> listTop10Thread() {
    	List<Thread> list = new ArrayList<Thread>();
    	EntityManager em = getEntityManager();
    	try {
            Query query = em.createNativeQuery("SELECT * FROM Thread LIMIT 0, 10",
            		Thread.class);
            list = query.getResultList();
            
            return list;
        } finally {
            em.close();
        }
	}
}
