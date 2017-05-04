package Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Entities.Movie;
import Entities.Users;
import Entities.Ajaxlogin;
import Repository.MovieRepository;
import Repository.UsersRepository;

@Controller
public class HomeController {

	@Autowired
	public UsersRepository userRepo;

	@Autowired
	public MovieRepository movieRepo;

	@GetMapping("/home")
	public String home(Model model, HttpServletRequest request, HttpSession session) {
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
			model.addAttribute("fullname", fullname2);
			model.addAttribute("header", header);
		} else {
			String header = "header_login";
			List<Movie> movie = movieRepo.getMovie();
			model.addAttribute("movieNew", movie);
			model.addAttribute("header", header);
		}
		return page;
	}
	//
	// if (userRepo.findByUsernameAndPasswordContains(username, password) !=
	// null) {
	// String fullname = user.get(0).getFullname();
	// Session.setAttribute("fullname", fullname);
	// obj.setMassage1("success");
	// } else{
	// obj.setMassage1("error");
	// obj.setMassage2("Username or Password incorrect");
	// }

	@PostMapping("/checkLogin")
	public @ResponseBody Ajaxlogin checkLogin(@RequestParam("username") String username,
			@RequestParam("password") String password, Model model, HttpServletRequest request, HttpSession session) {

		Ajaxlogin obj = new Ajaxlogin();

		HttpSession Session = request.getSession();
		List<Users> user = userRepo.findByUsernameAndPasswordContains(username, password);
		if (user.isEmpty()) {
			obj.setMassage1("error");
			obj.setMassage2("Username or Password incorrect");
		} else {
			obj.setMassage1("success");
			String fullname = user.get(0).getFullname();
			String status = user.get(0).getStatus();
			Session.setAttribute("status", status);
			Session.setAttribute("fullname", fullname);
		}
		return obj;

	}

	@GetMapping("/checkLogout")
	public String checkLogout(Model model, HttpServletRequest request, HttpSession session) {
		request.getSession().removeAttribute("fullname");
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

	@PostMapping("/signup")
	public String signup(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("email") String email, @RequestParam("fullname") String fullname,
			@RequestParam("tel") String tel, @RequestParam("address") String address, Model model) {
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