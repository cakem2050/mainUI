package Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Session;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Entities.Users;
import Repository.UsersRepository;

@Controller
public class UserController {
	@Autowired
	public UsersRepository userRepo;
	
	@GetMapping("/recharge")
	public String recharge(Model model, HttpServletRequest request, HttpSession session){
		HttpSession Session = request.getSession();
		String page = "home";
		if (Session.getAttribute("fullname") != null) {
			String header;
			if(Session.getAttribute("status").equals("admin")){
				page = "page_admin";
				header = "header_login_admin";
			}else{
				header = "header_login_success";
			}
			String fullname2 = (String) Session.getAttribute("fullname");
			int Money = (int) Session.getAttribute("money");
			model.addAttribute("money",Money);
			model.addAttribute("fullname", fullname2);
			model.addAttribute("header", header);
		} else {
			String header = "header_login";
			model.addAttribute("header", header);
		}
		return "recharge";
	}
	
	@PostMapping("/AddMoney")
	public String addMoney(@RequestParam("value") int money,Model model,HttpServletRequest request,Session session){
		HttpSession Session = request.getSession();
		if (Session.getAttribute("fullname") != null) {
			int sessionmoney = (int) Session.getAttribute("money");
			sessionmoney = sessionmoney+money;
			Session.setAttribute("money", sessionmoney);
			int id = (int) Session.getAttribute("id");
			Users user =  userRepo.findOne(id);
			user.setMoney(sessionmoney);
			userRepo.save(user);
		}
		return "redirect:/home";
	}

}
