package com.khs.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khs.exam.demo.vo.Rq;

@Controller
public class UsrAPITestController {
	
	@RequestMapping("usr/home/APITest")
	String showTestPage() {
		return "usr/home/APITest";
	}

	@RequestMapping("usr/home/vetLocationAPI")
	String showTest2Page() {
		return "usr/home/vetLocationAPI";
	}
}
