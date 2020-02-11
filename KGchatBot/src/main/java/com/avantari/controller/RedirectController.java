package com.avantari.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {
	
	@RequestMapping("/")
	public String getHomepage() {
		System.out.println("getHomePage");
		return "login.jsp";
	}
	
	@RequestMapping("/redirectHome")
	public String submitRating() {
		
		return "login.jsp";
	}
}
