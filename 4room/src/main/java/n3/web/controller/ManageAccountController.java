package n3.web.controller;

import javax.servlet.http.HttpServletRequest;

import n3.web.entity.Account;
import n3.web.service.AccountService;

import org.junit.runners.Parameterized.Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ManageAccountController extends BaseController{

	@RequestMapping(value = "manageAcc", method = RequestMethod.GET)
    public String manageAcccount(Model model, HttpServletRequest request) {
    	Account account = (Account) request.getSession().getAttribute("USER");
    	initData(model, account);
    	AccountService accountService = new AccountService();
    	model.addAttribute("LISTACC", accountService.listAllAccount());
    	
        return "manageAcc";
    }
	
	@RequestMapping(value = "deleteAcc", method = RequestMethod.GET)
    public ModelAndView deleteAccount(Model model, HttpServletRequest request,
    		@RequestParam(value = "id") String accID ) {
		ModelAndView mav = new ModelAndView("manageAcc");
    	Account account = (Account) request.getSession().getAttribute("USER");
    	AccountService accountService = new AccountService();
    	accountService.deleteAccByID(accID);
    	mav.addObject("LISTACC", accountService.listAllAccount());
    	initData(model, account);
    	
        return mav;
    }
	
	@RequestMapping(value = "updateAcc", method = RequestMethod.GET)
    public ModelAndView updateAccount(Model model, HttpServletRequest request,
    		@RequestParam(value = "id") String accID,
    		@RequestParam(value = "username") String username,
    		@RequestParam(value = "isAdmin", required = false) String isAdmin) {
		ModelAndView mav = new ModelAndView("manageAcc");
    	Account account = (Account) request.getSession().getAttribute("USER");
    	AccountService accountService = new AccountService();
    	Account accUpdate = accountService.findAccByID(accID);
    	accUpdate.setUsername(username);
    	if (isAdmin != null) {
    		accUpdate.setRole("admin");
		} else {
			accUpdate.setRole("member");
		}
    	accountService.updateAccByID(accUpdate);
    	
    	mav.addObject("LISTACC", accountService.listAllAccount());
    	initData(model, account);
    	
        return mav;
    }
}
