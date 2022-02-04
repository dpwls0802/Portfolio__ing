package com.record.travel.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.record.travel.dto.ReplyDTO;
import com.record.travel.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

//댓글 JSON으로 만들어 처리, 별도의 화면 X
@RestController
@RequestMapping("/reply")
@Log4j2
@RequiredArgsConstructor

public class ReplyController {
	
	private final ReplyService replyService;
	
	//게시글 댓글 목록 조회
	@GetMapping(value="/travelrecord/{tnum}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReplyDTO>> getListByTravelrecord(@PathVariable("tnum") Long tnum) {
		log.info("tnum : " + tnum);
		
		return new ResponseEntity<>(replyService.getList(tnum), HttpStatus.OK);
	}
	
	//등록
	@PostMapping("")
	public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO) {
		log.info(replyDTO);
		
		Long rnum = replyService.register(replyDTO);
		
		return new ResponseEntity<>(rnum, HttpStatus.OK);
	}
	
	//삭제
	@DeleteMapping("/{rnum}")
	public ResponseEntity<String> remove(@PathVariable("rnum") Long rnum) {
		log.info("댓글 번호 :" + rnum);
		
		replyService.remove(rnum);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	//수정
	@PutMapping("/{rnum}")
	public ResponseEntity<String> modify(@RequestBody ReplyDTO replyDTO) {
		log.info(replyDTO);
		
		replyService.modify(replyDTO);
		
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
}
