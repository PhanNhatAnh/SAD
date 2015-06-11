package n3.web.service;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import n3.web.entity.Thread;
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
}
