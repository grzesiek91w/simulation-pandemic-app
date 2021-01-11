package com.gwachala.springapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	String home(ModelMap modal) {
		modal.addAttribute("title", "Simulation Pandemic App");
		modal.addAttribute("message", "Building an Application with Spring Boot");
		return "hello";
	}
}
