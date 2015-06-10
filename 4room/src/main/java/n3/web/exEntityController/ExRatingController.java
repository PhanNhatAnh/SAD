package n3.web.exEntityController;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import n3.web.entity.Rating;
import n3.web.entityController.RatingJpaController;

public class ExRatingController extends RatingJpaController{

	/** Logging. */
    private final static Logger LOG = Logger.getLogger(ExRatingController.class);
    
    public ExRatingController(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Rating> listAllRating() {
    	List<Rating> list = new ArrayList<Rating>();
    	EntityManager em = getEntityManager();
    	try {
            Query query = em.createNamedQuery("Rating.findAll");
            list = query.getResultList();
            
            return list;
        } finally {
            em.close();
        }
	}
}
