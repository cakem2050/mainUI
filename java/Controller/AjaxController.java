package Controller;

import Entities.Ajaxlogin;
import Entities.Movie;
import Entities.OrderMovie;
import Entities.Users;
import Repository.MovieRepository;
import Repository.OrderMovieRepository;
import Repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AjaxController {
	public HttpServletRequest request;
	public HttpSession session;

	@Autowired
	ServletContext context;

	@Autowired
	public MovieRepository movieRepo;

	@Autowired
	public UserRepository userRepo;

	@GetMapping("/Movie")
	public String movie(@Param("pageindex") Integer pageindex, Model model, HttpServletRequest request,
			HttpSession session) {
		HttpSession Session = request.getSession();
		String page = "showmovie";
		Integer pagelimit;
		Integer nextpage, beforepage, pageactive;
		if (pageindex != null && pageindex != 1) {
			pageactive = pageindex;
			if (pageindex == 2) {
				pagelimit = 20;
			} else {
				pagelimit = (pageindex - 1) * 20;
			}
			model.addAttribute("page", pagelimit);
			beforepage = pageindex - 1;
			nextpage = pageindex + 1;
			model.addAttribute("pagebefore", beforepage);
			model.addAttribute("pagenext", nextpage);
			model.addAttribute("pageactive", pageactive);
		} else {
			pageactive = 1;
			pagelimit = 0;
			beforepage = 0;
			nextpage = 2;
			model.addAttribute("pagebefore", beforepage);
			model.addAttribute("pagenext", nextpage);
			model.addAttribute("pageactive", pageactive);
		}
		if (Session.getAttribute("fullname") != null) {
			String header;
			if (Session.getAttribute("status").equals("admin")) {
				page = "page_admin";
				header = "header_login_admin";
			} else {
				header = "header_login_success";
			}
			String fullname2 = (String) Session.getAttribute("fullname");
			int Money = (int) Session.getAttribute("money");
			model.addAttribute("money", Money);
			model.addAttribute("fullname", fullname2);
			model.addAttribute("header", header);
		} else {
			String header = "header_login";
			model.addAttribute("header", header);
			return "redirect:/home";
		}
		List<Movie> movie = (List<Movie>) movieRepo.getMovieAlllmit(pagelimit);
		int count = (int) movieRepo.count();
		int countpage = (int) Math.ceil(count / 20.0);
		model.addAttribute("movie", movie);
		model.addAttribute("countpage", countpage);
		return page;
	}

	@GetMapping("/Profile")
	public String profile(Model model, HttpServletRequest request) {
		String header;
		HttpSession Session = request.getSession();
		if (Session.getAttribute("status") != null) {
			header = "header_login_success";
		} else {
			return "redirect:/home";
		}

		Integer id = (Integer) Session.getAttribute("id");
		Users user = userRepo.findOne(id);
		String fullname2 = (String) Session.getAttribute("fullname");
		int Money = (int) Session.getAttribute("money");

		model.addAttribute("user", user);
		model.addAttribute("money", Money);
		model.addAttribute("fullname", fullname2);
		model.addAttribute("header", header);
		return "profile";
	}

	@GetMapping("/EditProfile")
	public String editProfile(HttpSession Session, Model model) {
		String header;
		if (Session.getAttribute("status") != null) {
			header = "header_login_success";
		} else {
			return "redirect:/home";
		}
		String fullname2 = (String) Session.getAttribute("fullname");
		int Money = (int) Session.getAttribute("money");
		int id = (int) Session.getAttribute("id");
		Users user = userRepo.findOne(id);
		model.addAttribute("header", header);
		model.addAttribute("user", user);
		model.addAttribute("money", Money);
		model.addAttribute("fullname", fullname2);
		return "/EditProfile";
	}

	@PostMapping("/EditProfile")
	public String comitEditProfile(@RequestParam("fullname") String fullname, @RequestParam("email") String email,
			@RequestParam("tel") String tel,@RequestParam("address") String address , HttpSession Session, Model model) {
		String header;
		if (Session.getAttribute("status") != null) {
			header = "header_login_success";
		} else {
			return "redirect:/home";
		}
		int id = (int) Session.getAttribute("id");
		Users select = userRepo.findOne(id);
		Users user = new Users();
		
		user.setUser_id(id);
		user.setUsername(select.getUsername());
		user.setMoney(select.getMoney());
		user.setStatus(select.getStatus());
		user.setPassword(select.getPassword());
		user.setFullname(fullname);
		user.setTel(tel);
		user.setEmail(email);
		user.setAddress(address);
		user.setUser_have_movie(select.getUser_have_movie());
		
		userRepo.save(user);
		return "redirect:/Profile";
	}
	
	@Autowired
	public OrderMovieRepository orderRepo;
	
	@GetMapping("/BuyMovie")
	public String buymovie(@RequestParam("idmovie") int movie_id,HttpSession Session,Model model){
		String header;
		if (Session.getAttribute("status") != null) {
			header = "header_login_success";
		} else {
			return "redirect:/home";
		}
		String fullname2 = (String) Session.getAttribute("fullname");
		int Money = (int) Session.getAttribute("money");
		int id = (int) Session.getAttribute("id");
		OrderMovie order = new OrderMovie();
		order.setUser_id(id);
		order.setMovie_id(movie_id);
		orderRepo.save(order);
		model.addAttribute("header", header);
		model.addAttribute("money", Money);
		model.addAttribute("fullname", fullname2);
		return "redirect:/Movie";
	}
	
	@GetMapping("/MovieDetail")
	public String movieDetail(@RequestParam("movieid") int id ,HttpSession Session,Model model){
		String header;
		if (Session.getAttribute("status") != null) {
			header = "header_login_success";
			model.addAttribute("header", header);
		} else {
			return "redirect:/home";
		}
		String fullname2 = (String) Session.getAttribute("fullname");
		int Money = (int) Session.getAttribute("money");
		model.addAttribute("fullname", fullname2);
		model.addAttribute("money", Money);
		Movie movie = new Movie();
		movie = movieRepo.findOne(id);
		model.addAttribute("movie",movie);
		return "movie_detail";
	}

}
