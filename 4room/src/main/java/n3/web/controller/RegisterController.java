package n3.web.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import n3.web.entity.Account;
import n3.web.entity.Accounticon;
import n3.web.service.AccountService;

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
    public String registerDefault(Model model) {

    	return "register";
    }

    /**
     * Register.
     * 
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String makeRegister(@RequestParam(value = "txtUsername") String username,
    		@RequestParam(value = "txtPassword") String password,
    		@RequestParam(value = "txtEmail", required = false) String email,
    		@RequestParam(value = "txtFirstName", required = false) String firstName,
    		@RequestParam(value = "txtLastName", required = false) String lastName,Model model) {
    	AccountService accountService = new AccountService();
    	Account account = new Account(1, "username", "member");
    	Accounticon accounticon = new Accounticon(1); 
		
		account.setAccIconID(accounticon);
		account.setRole("member");
		account.setUsername(username);
		account.setFirstname(firstName);
		account.setEmail(email);
		account.setPasswords(password);
		account.setLastname(lastName);
		
    	accountService.createAcc(account);
    	
        return "register";
    }
}
