package com.khs.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khs.exam.demo.vo.Rq;

@Controller
public class UsrAPIController {
	
	@RequestMapping("usr/home/translationAPI")
	String showTestPage() {
		return "usr/home/translationAPI";
	}

	@RequestMapping("usr/home/detectLanAPI")
	String showTest2Page() {
		return "usr/home/detectLanAPI";
	}
}
