package Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import Entities.Type;
import Repository.TypeRepository;

import Entities.Movie;
import Repository.MovieRepository;

@Controller
public class AdminController {

	@Autowired
	public MovieRepository mevieRepo;

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
		model.addAttribute("header", header);
		return page;
	}

	@Autowired
	TypeRepository typeRepo;

	@GetMapping("/AddMovie")
	public String addmovie(Model model, HttpServletRequest request, HttpSession session) {
		Iterable<Type> type = typeRepo.findAll();
		model.addAttribute("type", type);

		String page = "page_admin_addmovie";
		String header;
		HttpSession Session = request.getSession();
		if (Session.getAttribute("status").equals("admin")) {
			header = "header_login_admin";
		} else {
			page = "home";
			header = "header_login_success";
		}
		model.addAttribute("header", header);
		return page;
	}

	@GetMapping("/ManagementMovie")
	public String managementMovie(@Param("keyword") String keyword, @Param("pageindex") Integer pageindex, Model model,
			HttpServletRequest request, HttpSession session) {
		String page = "page_admin_MMovie";
		String header;
		Integer pagelimit;
		Integer nextpage, beforepage, pageactive;
		if (pageindex != null && pageindex != 1) {
			pageactive = pageindex;
			pagelimit = pageindex * 10;
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
		HttpSession Session = request.getSession();
		if (Session.getAttribute("status").equals("admin")) {
			header = "header_login_admin";
			long count = mevieRepo.count();
			model.addAttribute("count", (int) Math.ceil(count / 10));
			List<Movie> movie = null;
			if (keyword == null) {
				movie = mevieRepo.getMovielmit(pagelimit);
			} else if (keyword != null) {
				movie = mevieRepo.findByMovie_name(keyword);
			}
			model.addAttribute("allmovie", movie);
		} else {
			page = "home";
			header = "header_login_success";
		}
		model.addAttribute("header", header);
		return page;
	}

	@PostMapping("/setmovie")
	public String setMovie(Model model) {

		return null;
	}

}
