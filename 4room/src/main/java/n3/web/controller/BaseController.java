package n3.web.controller;

import java.util.List;

import n3.web.entity.Rating;
import n3.web.service.RatingService;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;

public class BaseController {

	/* Logger to log information */
	private final static Logger LOG = Logger.getLogger(BaseController.class);
	public void initData(Model model) {
		RatingService ratingService = new RatingService();
		model.addAttribute("RATE", ratingService.listAllRating());
	}
}
