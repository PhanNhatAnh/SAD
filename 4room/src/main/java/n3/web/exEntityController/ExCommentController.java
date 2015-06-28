package n3.web.exEntityController;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import n3.web.entity.Account;
import n3.web.entity.Comment;
import n3.web.entity.Thread;
import n3.web.entityController.CommentJpaController;

public class ExCommentController extends CommentJpaController{

	/** Logging. */
    private final static Logger LOG = Logger.getLogger(ExCommentController.class);
    
    public ExCommentController(EntityManagerFactory emf) {
        super(emf);
    }
    
    public List<Comment> getAllCommentOfThread(Thread thread) {
    	List<Comment> list = new ArrayList<Comment>();
    	EntityManager em = getEntityManager();
    	try {
            Query query = em.createQuery("SELECT c FROM Comment c WHERE c.threadID = :threadID");
            query.setParameter("threadID", thread);
            list = query.getResultList();
            
            return list;
        } finally {
            em.close();
        }
	}

	public List<Comment> getAllCommentOfAcc(Account account) {
		List<Comment> list = new ArrayList<Comment>();
    	EntityManager em = getEntityManager();
    	try {
            Query query = em.createQuery("SELECT c FROM Comment c WHERE c.accountID = :accountID");
            query.setParameter("accountID", account);
            list = query.getResultList();
            
            return list;
        } finally {
            em.close();
        }
	}
}
