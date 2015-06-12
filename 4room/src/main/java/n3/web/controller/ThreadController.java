package n3.web.controller;

import javax.servlet.http.HttpServletRequest;

import n3.web.entity.Account;
import n3.web.service.CommentService;
import n3.web.service.ThreadService;
import n3.web.entity.Thread;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThreadController extends BaseController{

	@RequestMapping(value = "thread", method = RequestMethod.GET)
    public String threadDefault(@RequestParam(value = "threadID") String threadID,
    		Model model, HttpServletRequest request) {
		ThreadService threadService = new ThreadService();
		CommentService commentService = new CommentService();
		Thread thread = threadService.findThreadByID(threadID);
    	Account account = (Account) request.getSession().getAttribute("USER");
    	initData(model, account);
    	model.addAttribute("THREAD", thread);
    	model.addAttribute("COMMENTS", commentService.getAllCommentOfThread(thread));
        
    	return "thread";
    }
}
