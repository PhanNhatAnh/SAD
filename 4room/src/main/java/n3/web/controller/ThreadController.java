package n3.web.controller;

import javax.servlet.http.HttpServletRequest;

import n3.web.entity.Account;
import n3.web.entity.Comment;
import n3.web.service.CommentService;
import n3.web.service.ThreadService;
import n3.web.entity.Thread;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    	model.addAttribute("USER", account);
    	model.addAttribute("THREAD", thread);
    	model.addAttribute("COMMENTS", commentService.getAllCommentOfThread(thread));
        
    	return "thread";
    }
	
	@RequestMapping(value = "sendComment", method = RequestMethod.POST)
    public ModelAndView sendComment(@RequestParam(value = "comment", required = false) String comment,
    		@RequestParam(value = "threadID") String threadID,
    		Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("thread");
		ThreadService threadService = new ThreadService();
		CommentService commentService = new CommentService();
		Thread thread = threadService.findThreadByID(threadID);
    	Account account = (Account) request.getSession().getAttribute("USER");

		if ((comment != null) || (!"".equals(comment.trim()))) {
	    	commentService.createComment(thread, account, comment);
		} else {
			//Do nothing.
		}		
    	
    	initData(model, account);
    	model.addAttribute("USER", account);
    	mav.addObject("THREAD", thread);
    	mav.addObject("COMMENTS", commentService.getAllCommentOfThread(thread));
        
    	return mav;
    }
	
	@RequestMapping(value = "editComment", method = RequestMethod.GET)
    public ModelAndView editCommentDefault(@RequestParam(value = "id") String commentID,
    		Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("comment");
		CommentService commentService = new CommentService();
		Comment comment = commentService.findCommentByID(commentID);
    	Account account = (Account) request.getSession().getAttribute("USER");

    	initData(model, account);
    	mav.addObject("USER", account);
    	mav.addObject("COMMENT", comment);
        
    	return mav;
    }
	
	@RequestMapping(value = "editComment", method = RequestMethod.POST)
    public ModelAndView editComment(@RequestParam(value = "commentID") String commentID,
    		@RequestParam(value = "comment", required = false) String cmt,
    		Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("thread");
		CommentService commentService = new CommentService();
		Comment comment = commentService.findCommentByID(commentID);
		Thread thread = comment.getThreadID();
    	Account account = (Account) request.getSession().getAttribute("USER");

		if ((cmt != null) || (!"".equals(cmt.trim()))) {
			comment.setContent(cmt);
			commentService.editComment(comment, account);
		} else {
			//Do nothing.
		}		
    	
    	initData(model, account);
    	model.addAttribute("USER", account);
    	mav.addObject("THREAD", thread);
    	mav.addObject("COMMENTS", commentService.getAllCommentOfThread(thread));
        
    	return mav;
    }
}
