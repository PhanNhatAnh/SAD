package n3.web.service;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import n3.web.entity.Account;
import n3.web.entity.Thread;
import n3.web.entityController.exceptions.IllegalOrphanException;
import n3.web.entityController.exceptions.NonexistentEntityException;
import n3.web.exEntityController.ExThreadController;

import org.apache.log4j.Logger;

public class ThreadService extends BaseService{

	/* Logger to log information */
	private final static Logger LOG = Logger.getLogger(ThreadService.class);
	/* Request Jpa controller */
	private final ExThreadController controller;
	
	public ThreadService() {
	    controller = new ExThreadController(super.getEmf());
	}
	
	/* Default constructor to create controller */
	public ThreadService(EntityManagerFactory emf) {
		controller = new ExThreadController(emf);
	}
	
	public List<Thread> listTop10Thread() {
		return controller.listTop10Thread();
	}
	
	public List<Thread> listBottom10Thread() {
		return controller.listBottom10Thread();
	}
	
	public List<Thread> getThreadByAccID(Account accID) {
		return controller.getThreadByAccID(accID);
	}

	public Thread findThreadByID(String threadID) {
		int id = Integer.parseInt(threadID);
		return controller.findThread(id);
	}
	
	public boolean updateThread(Thread thread) {
		try {
			controller.edit(thread);
		} catch (IllegalOrphanException e) {
			LOG.debug("IllegalOrphanException: " + e.getMessage());
			return false;
		} catch (NonexistentEntityException e) {
			LOG.debug("NonexistentEntityException: " + e.getMessage());
			return false;
		} catch (Exception e) {
			LOG.debug("Exception: " + e.getMessage());
			return false;
		}
		return true;
	}
}
