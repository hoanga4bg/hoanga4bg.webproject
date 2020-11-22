package com.laptrinhweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping(path = "/")
	
	@GetMapping
	public String home() {
		return "home";
	}
	
}
