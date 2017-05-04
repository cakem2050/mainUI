package Controller;
import Entities.Ajaxlogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {
	public HttpServletRequest request;
	public HttpSession session;
	@GetMapping("/ajax")
	public String ajax(){
		return "ajax";
	}
	@RequestMapping(value = "/ajaxtest", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String ajaxtest(@RequestParam("username") String name,@RequestParam("password") String password,Model model){
		return name+password;
	}

}
