package n3.web.service;

import javax.persistence.EntityManagerFactory;

import n3.web.entity.Account;
import n3.web.exEntityController.ExAccountController;

import org.apache.log4j.Logger;

public class AccountService extends BaseService{

	/* Logger to log information */
	private final static Logger LOG = Logger.getLogger(AccountService.class);
	/* Request Jpa controller */
	private final ExAccountController controller;
	
	public AccountService() {
	    controller = new ExAccountController(super.getEmf());
	}
	
	/* Default constructor to create controller */
	public AccountService(EntityManagerFactory emf) {
		controller = new ExAccountController(emf);
	}
	
	public Account checkLogin(String username, String password) {
		return controller.checkLogin(username, password);
	}
}
