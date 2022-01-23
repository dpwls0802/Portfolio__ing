package com.record.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.record.travel.dto.PageRequestDTO;
import com.record.travel.service.TravelrecordService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/travelrecord")
@Log4j2
@RequiredArgsConstructor
public class TravelrecordController {
	
	private final TravelrecordService service;
	
	//목록
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		log.info("=============list=========" + pageRequestDTO);
		model.addAttribute("result", service.getList(pageRequestDTO));
	}
	
	//등록
	@GetMapping("/register")
	public String register() {
		log.info("=============register=========");
		return "/travelrecord/register";
	}
}
