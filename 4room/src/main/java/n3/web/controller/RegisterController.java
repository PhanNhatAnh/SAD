package n3.web.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Register Controller.
 * 
 * @author A.King
 *
 */
@Controller
public class RegisterController {
    /** Logger object. */
    private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

    /** Message when register was successful. */
    private static final String REGISTER_SUCCESS = "Congratulation! Register success.";


    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String inviteHome(Model model) {

    	return "register";
    }

    /**
     * Register.
     * 
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String makeRegister(Model model) {

        return "register";
    }
}
