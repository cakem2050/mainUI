package movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes
@Controller
public class HomeController {

	@GetMapping("/home")
	public String home(Model model) {
		String login = "header_login";
		model.addAttribute("login", login);
		return "home";
	}

	@PostMapping("/checkLogin")
	public String checkLogin(@RequestParam("email") String email, @RequestParam("password") String password,
			Model model, HttpServletRequest request, HttpSession session) {

		HttpSession Session = request.getSession();

		if (password.equals("1234") && email.equals("ford")) {

			Session.setAttribute("emaikkl", email);
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
	

}
