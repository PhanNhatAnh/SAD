package n3.web.controller;

import n3.web.service.AccountService;
import n3.web.service.RatingService;
import n3.web.service.ThreadService;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;

public class BaseController {

	/* Logger to log information */
	private final static Logger LOG = Logger.getLogger(BaseController.class);
	public void initData(Model model) {
		RatingService ratingService = new RatingService();
		ThreadService threadService = new ThreadService();
		AccountService accountService = new AccountService();
		model.addAttribute("RATE", ratingService.listTop10Rating());
		model.addAttribute("THREAD", threadService.listTop10Thread());
		model.addAttribute("MEMBERS", accountService.listAllAccount());
	}
}
