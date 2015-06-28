package n3.web.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import n3.web.entity.Account;
import n3.web.entity.Comment;
import n3.web.entity.Thread;
import n3.web.entityController.exceptions.NonexistentEntityException;
import n3.web.exEntityController.ExCommentController;
import n3.web.exEntityController.ExThreadController;

import org.apache.log4j.Logger;

public class CommentService extends BaseService{

	/* Logger to log information */
	private final static Logger LOG = Logger.getLogger(CommentService.class);
	/* Request Jpa controller */
	private final ExCommentController controller;
	
	public CommentService() {
	    controller = new ExCommentController(super.getEmf());
	}
	
	/* Default constructor to create controller */
	public CommentService(EntityManagerFactory emf) {
		controller = new ExCommentController(emf);
	}
	
	public List<Comment> getAllCommentOfThread(Thread thread) {
		return controller.getAllCommentOfThread(thread);
	}
	
	public List<Comment> getAllCommentOfAcc(Account account) {
		return controller.getAllCommentOfAcc(account);
	}
	
	public Comment findCommentByID(String id) {
		int commentID = Integer.parseInt(id);
		return controller.findComment(commentID);
	}
	
	public void createComment(Thread thread,Account account, String comment) {
		Comment cmt = new Comment();
		Date date = new Date();
		ThreadService threadService = new ThreadService();
		
		cmt.setContent(comment);
		cmt.setHide(false);
		cmt.setLastEdit(date);
		cmt.setLastEditBy(account);
		cmt.setAccountID(account);
		cmt.setThreadID(thread);
		
		thread.setLastUpdate(date);
		thread.setLastUpdateBy(account);
		threadService.updateThread(thread);
		
		controller.create(cmt);
	}
	
	public boolean editComment(Comment cmt, Account account) {
		Date date = new Date();
		cmt.setLastEdit(date);
		cmt.setLastEditBy(account);
		try {
			controller.edit(cmt);
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
