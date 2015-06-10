package n3.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String homeDefault(Model model) {
    	initData(model);
        return "home";
    }

    /**
     * Render home page.
     * 
     * @param model is variable to send to JSP Page
     * @return home
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
    	initData(model);
        return "home";
    }
}
