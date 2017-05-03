
package Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import Entities.Users;
import Repository.UsersRepository;

@SessionAttributes
@Controller
public class HomeController {
	@Autowired
	public UsersRepository userRepo;

	@GetMapping("/home")
	public String home(Model model) {
		String login = "header_login";
		model.addAttribute("login", login);

		return "home";
	}

	@PostMapping("/checkLogin")
	public String checkLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model, HttpServletRequest request, HttpSession session) {

		HttpSession Session = request.getSession();
		List<Users> user = userRepo.findByUsernameAndPassword(username, password);

		if (user != null) {
			String fullname = user.get(0).getFullname();
			String email = user.get(0).getEmail();
			String password2 = user.get(0).getPassword();
			Session.setAttribute("email", email);
			Session.setAttribute("fullname", fullname);
			Session.setAttribute("password", password2);
			String header = "header_login_success";
			model.addAttribute("header", fullname);
			model.addAttribute("header", header);
			return "home";
		} else {
			String header = "header_login";
			model.addAttribute("header", header);
			return "home";
		}

	}

	@GetMapping("/checkLogout")
	public String checkLogout(Model model) {
		String header = "header_login";
		model.addAttribute("header", header);
		return "home";
	}

	@GetMapping("/home_session")
	public String checkSesion(Model model, HttpServletRequest request, HttpSession session) {
		String email = (String) session.getAttribute("email");
		String password = (String) session.getAttribute("password");
		String login = "header_homeMember";
		model.addAttribute("login", login);
		model.addAttribute("email", email);
		model.addAttribute("password", password);
		return "home_session";
	}

}
