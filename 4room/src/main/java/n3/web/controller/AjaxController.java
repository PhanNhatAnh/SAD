package n3.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import n3.web.entity.Account;
import n3.web.entity.Comment;
import n3.web.entity.Thread;
import n3.web.service.CommentService;
import n3.web.service.ThreadService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AjaxController {

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
}
