package movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@Autowired
	public UsersRepository userRepo;
	
	@GetMapping("/home")
	public String home() {
		return "home";
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
		userRepo.save(user);
		return "home";
	}
}
