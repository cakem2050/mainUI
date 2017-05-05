package Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import Entities.Type;
import Repository.TypeRepository;

@Controller
public class AdminController {

	@GetMapping("/UserManagement")
	public String adminmanagement(Model model, HttpServletRequest request, HttpSession session) {
		String page = "page_admin";
		String header;
		HttpSession Session = request.getSession();
		if (Session.getAttribute("status").equals("admin")) {
			header = "header_login_admin";
		} else {
			header = "header_login_success";
		}
		model.addAttribute("header",header);
		return page;
	}
	
	@Autowired
	TypeRepository typeRepo;
	
	@GetMapping("/AddMovie")
	public String addmovie(Model model, HttpServletRequest request, HttpSession session) {
		Iterable<Type> type = typeRepo.findAll();
		model.addAttribute("type",type);
		
		String page = "page_admin_addmovie";
		String header;
		HttpSession Session = request.getSession();
		if (Session.getAttribute("status").equals("admin")) {
			header = "header_login_admin";
		} else {
			header = "header_login_success";
		}
		model.addAttribute("header",header);
		return page;
	}
	
	@PostMapping("/setmovie")
	public String setMovie(Model model){
		
		return null;
	}

}
