package com.school.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ManageController {
	
	 	@RequestMapping("/test")
	    @ResponseBody
		public String Test() {
			return "test";
		}
}
