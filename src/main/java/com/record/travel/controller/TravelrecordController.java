package com.record.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/travelrecord")
@Log4j2
public class TravelrecordController {
	
	//목록
	@GetMapping("/list")
	public String list() {
		log.info("=============list=========");
		return "/travelrecord/list";
	}
	
	//등록
	@GetMapping("/register")
	public String register() {
		log.info("=============register=========");
		return "/travelrecord/register";
	}
}
