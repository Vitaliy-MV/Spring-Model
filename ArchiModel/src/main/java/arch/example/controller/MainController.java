package arch.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

	@GetMapping
	public String start() {
		return "index";
	}
	@RequestMapping("/login")
	public String loginPage(){
	    return "login";
	}
}