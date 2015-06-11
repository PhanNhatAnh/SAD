package n3.web.service;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;

import n3.web.entity.Rating;
import n3.web.exEntityController.ExRatingController;

public class RatingService extends BaseService{

	/* Logger to log information */
	private final static Logger LOG = Logger.getLogger(RatingService.class);
	/* Request Jpa controller */
	private final ExRatingController controller;
	
	public RatingService() {
	    controller = new ExRatingController(super.getEmf());
	}
	
	/* Default constructor to create controller */
	public RatingService(EntityManagerFactory emf) {
		controller = new ExRatingController(emf);
	}
	
	public List<Rating> listTop10Rating() {
		return controller.listTop10Rating();
	}
}
