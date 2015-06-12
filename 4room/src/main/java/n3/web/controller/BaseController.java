package n3.web.controller;

import java.util.List;

import n3.web.entity.Account;
import n3.web.entity.Thread;
import n3.web.service.AccountService;
import n3.web.service.RatingService;
import n3.web.service.ThreadService;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;

public class BaseController {

	/* Logger to log information */
	private final static Logger LOG = Logger.getLogger(BaseController.class);
	public void initData(Model model, Account account) {
		RatingService ratingService = new RatingService();
		ThreadService threadService = new ThreadService();
		AccountService accountService = new AccountService();

		model.addAttribute("RATE", ratingService.listTop10Rating());
		model.addAttribute("THREAD", threadService.listTop10Thread());
		model.addAttribute("MEMBERS", accountService.listAllAccount());

		if (account != null) {
			List<Thread> list = threadService.getThreadByAccID(account);
			int numThread = 0;
			if (list != null) {
				numThread = list.size();
			}
			model.addAttribute("NUMTHREAD", numThread);
			model.addAttribute("NUMCOMMENT", 0);
			model.addAttribute("NUMMESS", 0);
		} else {
			model.addAttribute("NUMTHREAD", 0);
			model.addAttribute("NUMCOMMENT", 0);
			model.addAttribute("NUMMESS", 0);
		}
	}
}
