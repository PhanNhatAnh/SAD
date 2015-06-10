package n3.web.controller;

import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 * 
 * @author A.King
 *
 */
@Controller
public class LoginController {

    /** Error when invalid username and password were inputed. */
    private static final String WRONG_USER_AND_PASS_ERROR = "Wrong Username or Password!";
    /** Message when logout was successful. */
    private static final String LOGOUT_SUCCESS = "Logout Success!";
    /** Error when the action was not authorized to do. */
    private static final String NOT_AUTHORIZED_ERROR = "You do not have permission to access this page!";

    /**
     * Home login.
     * 
     * @param error variable to save error
     * @param logout variable to save login
     * @param model response container
     * @return render login page
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String homelogin(@RequestParam(value = "ERROR", required = false) String error,
            @RequestParam(value = "LOGOUT", required = false) String logout, Model model) {

        if (error != null) {
            model.addAttribute("ERROR", WRONG_USER_AND_PASS_ERROR);
        } else {
            // Do noting
        }

        if (logout != null) {
            model.addAttribute("SUCCESS", LOGOUT_SUCCESS);
        } else {
            // Do nothing.
        }

        return "login";
    }

    /**
     * Access denying.
     * 
     * @param model response container
     * @param principal session scope
     * @return render login page
     */
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(ModelMap model, Principal principal) {
        SecurityContextHolder.clearContext();

        if (principal != null) {
            model.addAttribute("ERROR", "Hi " + principal.getName()
                    + ", you do not have permission to access this page!");
        } else {
            model.addAttribute("ERROR", NOT_AUTHORIZED_ERROR);
        }

        return "login";
    }

}
