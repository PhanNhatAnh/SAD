package n3.web.service;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import n3.web.entity.Comment;
import n3.web.entity.Thread;
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
}
