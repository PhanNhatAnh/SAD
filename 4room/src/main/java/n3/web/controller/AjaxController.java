package n3.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import n3.web.entity.Account;
import n3.web.entity.Comment;
import n3.web.entity.Thread;
import n3.web.model.fbAcc;
import n3.web.service.AccountService;
import n3.web.service.CommentService;
import n3.web.service.ThreadService;
import n3.web.service.jsonService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AjaxController extends BaseController{

	/** Logger to log information */
	private final static Logger LOG = Logger.getLogger(AjaxController.class);
	@RequestMapping(value = "getComments", method = RequestMethod.GET)
    public int getComments(@RequestParam(value = "threadID") String threadID,
    		Model model, HttpServletRequest request) {
		String json = null;
		CommentService commentService = new CommentService();
		ThreadService threadService = new ThreadService();
		Thread thread = threadService.findThreadByID(threadID);
		List<Comment> listComments = commentService.getAllCommentOfThread(thread);
        
    	return listComments.size();
    }
	
	@RequestMapping(value = "loginFB", method = RequestMethod.POST)
    public String loginFB(@RequestParam(value = "json", required = false) String json,
    		HttpServletRequest request, Model model) {
    	jsonService jsonService = new jsonService();
    	fbAcc fbAcc = jsonService.parseJson(json);
    	
    	AccountService accountService = new AccountService();
    	Account account = accountService.findAccByUsername(fbAcc.getName());
    	if (account == null) {
			account = accountService.createAcc(fbAcc);
		} else {
			//Do nothing.
		}
    	request.getSession().setAttribute("USER", account);
    	model.addAttribute("USER", account);
    	initData(model,account);
    	
    	return "home";
    }
}
