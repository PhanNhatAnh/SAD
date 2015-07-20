package n3.web.controller;

import javax.servlet.http.HttpServletRequest;

import n3.web.entity.Account;
import n3.web.service.ThreadService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends BaseController{
    /**
     * Simply selects the home view to render by returning its name.
     * 
     * @param model is variable to send to JSP Page
     * @return home
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homeDefault(Model model, HttpServletRequest request) {
    	Account account = (Account) request.getSession().getAttribute("USER");
    	initData(model, account);
    	model.addAttribute("USER", account);
        return "index";
    }

    /**
     * Render home page.
     * 
     * @param model is variable to send to JSP Page
     * @return home
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request) {
    	Account account = (Account) request.getSession().getAttribute("USER");
    	initData(model, account);
    	model.addAttribute("USER", account);
        return "home";
    }
    
    @RequestMapping(value = "oldest", method = RequestMethod.GET)
    public ModelAndView oldestThread(Model model, HttpServletRequest request) {
    	ModelAndView mav = new ModelAndView("home");
    	Account account = (Account) request.getSession().getAttribute("USER");
    	initData(model, account);
    	model.addAttribute("USER", account);
    	ThreadService threadService = new ThreadService();
    	mav.addObject("THREAD", threadService.listBottom10Thread());

        return mav;
    }
    
    @RequestMapping(value = "newest", method = RequestMethod.GET)
    public ModelAndView newestThread(Model model, HttpServletRequest request) {
    	ModelAndView mav = new ModelAndView("home");
    	Account account = (Account) request.getSession().getAttribute("USER");
    	initData(model, account);
    	model.addAttribute("USER", account);
    	ThreadService threadService = new ThreadService();
    	mav.addObject("THREAD", threadService.listTop10Thread());

        return mav;
    }
}
