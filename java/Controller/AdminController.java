package Controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import Entities.Movie;
import Entities.Type;
import Repository.MovieRepository;
import Repository.TypeRepository;
import Controller.storage.StorageFileNotFoundException;
import Controller.storage.StorageService;
import Controller.storage.StorageProperties;

@Controller
public class AdminController {

	private final StorageService storageService;

	@Autowired
	public AdminController(StorageService storageService) {
		this.storageService = storageService;
	}

	@Autowired
	public MovieRepository movieRepo;

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
	public TypeRepository typeRepo;

	@GetMapping("/PageMovie")
	public String PageMovie(Model model, HttpServletRequest request, HttpSession session) {
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

	@PostMapping("/AddMovie")
	public String AddMovie(@RequestParam("file") MultipartFile file, @RequestParam("namemovie") String namemovie,
			@RequestParam("pricemovie") String pricemovie,@RequestParam("type") int type,
			@RequestParam("descrip") String descrip, Model model, HttpServletRequest request, HttpSession session) {
		//RedirectAttributes redirectAttributes
		System.out.println(namemovie+"  "+pricemovie+"  "+type+"  "+descrip);
		
		String page = "page_admin_MMovie";
		String header;
		HttpSession Session = request.getSession();
		if (Session.getAttribute("status").equals("admin")) {
			storageService.store(file);
			Movie movie = new Movie();
			movie.setMovie_name(namemovie);
			int price2 = Integer.parseInt(pricemovie);
			movie.setMovie_price(price2);
			//int type2 = Integer.parseInt(type);
			movie.setMovie_detail(descrip);
			Date date = new Date();
			movie.setMovie_date(date);

			StorageProperties imgg = new StorageProperties();
			//String img1 = imgg.getLocation();
			String img2 = file.getOriginalFilename();
			String img = "/assets/imageMovie/" + img2;
			movie.setMovie_img(img);

			movieRepo.save(movie);

			header = "header_login_admin";
			return "redirect:/ManagementMovie";
		} else {
			page = "home";
			header = "header_login_success";
			model.addAttribute("header", header);
			return page;
			
		}
		
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
			long count = movieRepo.count();
			System.out.println(count);
			int countt = (int)count;
			int count1 = countt % 10;
			int count2 = countt/10;
			if(count1!=0){
				count2 = count2 + 1;
			}
			model.addAttribute("count",  count2);
			List<Movie> movie = null;
			System.out.println(keyword);
			if (keyword == null) {
				movie = movieRepo.getMovielmit(pagelimit);
			} else if (keyword != null) {
				movie = movieRepo.searchMovie(keyword);
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

	//////////// upload
	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {

		model.addAttribute("files",
				storageService.loadAll()
						.map(path -> MvcUriComponentsBuilder
								.fromMethodName(AdminController.class, "serveFile", path.getFileName().toString())
								.build().toString())
						.collect(Collectors.toList()));

		return "uploadForm";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		System.out.println(file.getFilename());
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
}
