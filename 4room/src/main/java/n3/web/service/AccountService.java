package n3.web.service;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import n3.web.entity.Account;
import n3.web.entity.Accounticon;
import n3.web.entityController.exceptions.IllegalOrphanException;
import n3.web.entityController.exceptions.NonexistentEntityException;
import n3.web.exEntityController.ExAccountController;
import n3.web.model.fbAcc;

import org.apache.log4j.Logger;

public class AccountService extends BaseService {

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

	public List<Account> listAllAccount() {
		return controller.findAccountEntities();
	}

	public boolean deleteAccByID(String ID) {
		int accID = Integer.parseInt(ID);
		try {
			controller.destroy(accID);
		} catch (IllegalOrphanException e) {
			LOG.debug("IllegalOrphanException: " + e.getMessage());
			return false;
		} catch (NonexistentEntityException e) {
			LOG.debug("NonexistentEntityException: " + e.getMessage());
			return false;
		}
		return true;
	}

	public boolean updateAccByID(Account account) {
		try {
			controller.edit(account);
		} catch (Exception e) {
			LOG.debug("Exception: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	public Account findAccByID(String ID) {
		int accID = Integer.parseInt(ID);
		return controller.findAccount(accID);
	}
	
	public Account findAccByUsername(String username) {
		return controller.findAccByUsername(username);
	}

	public Account createAcc(fbAcc fbAcc) {
		Account account = new Account(1, fbAcc.getName(), "member");
		Accounticon accounticon = new Accounticon(1); 
		
		account.setAccIconID(accounticon);
		account.setEmail(fbAcc.getEmail());
		account.setFbID(fbAcc.getId());
		account.setUsername(fbAcc.getName());
		account.setFirstname(fbAcc.getFirst_name());
		account.setLastname(fbAcc.getLast_name());
		account.setLinkFB(fbAcc.getLink());
		account.setRole("member");
		account.setGender(fbAcc.getGender());
		
		controller.create(account);
		
		return account;
	}
	
	public void createAcc(Account account) {
		controller.create(account);
	}
}
