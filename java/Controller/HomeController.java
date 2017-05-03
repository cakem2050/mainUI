package Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Entities.Users;
import Repository.UsersRepository;

@Controller
public class HomeController {
	
	@Autowired
	public UsersRepository userRepo;
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
//	@GetMapping("/home")
//	public String home(Model model) {
//		String login = "header_login";
//		model.addAttribute("login", login);
//		return "home";
//	}

	@PostMapping("/checkLogin")
	public String checkLogin(@RequestParam("email") String email, @RequestParam("password") String password,
			Model model, HttpServletRequest request, HttpSession session) {

		HttpSession Session = request.getSession();

		if (password.equals("1234") && email.equals("ford")) {

			Session.setAttribute("emaikuuukl", email);
			Session.setAttribute("password", password);

			String login = "header_homeMember";
			model.addAttribute("login", login);
			return "home";
		} else {
			String login = "header_login";
			model.addAttribute("login", login);
			return "home";
		}

	}
	
	@GetMapping("/home_session")
	public String checkSesion(Model model, HttpServletRequest request, HttpSession session){
		String email = (String)session.getAttribute("email");
		String password = (String)session.getAttribute("password");
		String login = "header_homeMember";
		model.addAttribute("login", login);
		model.addAttribute("email", email);
		model.addAttribute("password", password);
		return "home_session";
	}
	
	@PostMapping("/signup")
	public String signup(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("email") String email, @RequestParam("fullname") String fullname, @RequestParam("tel") String tel,
			@RequestParam("address") String address, Model model) {
		Users user = new Users();
		user.setEmail(email);
		user.setPassword(password);
		user.setFullname(fullname);
		user.setAddress(address);
		user.setTel(tel);
		user.setUsername(username);
		user.setStatus("nomal");
		userRepo.save(user);
		return "home";
	}
}
