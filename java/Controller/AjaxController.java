package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {
	@GetMapping("/ajax")
	public String ajax(){
		return "ajax";
	}
	@GetMapping("/ajaxtest")
	public @ResponseBody String ajaxtest(){
		return "cakezaza";
	}

}
