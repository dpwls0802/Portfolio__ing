package com.record.travel.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.record.travel.dto.ReplyDTO;

@SpringBootTest
public class ReplyServiceTests {
	
	@Autowired
	private ReplyService replyService;
	
	//게시글 댓글 목록 조회
	@Test
	public void getList() {
		Long tnum = 35L;
		List<ReplyDTO> replyDTOList = replyService.getList(tnum);
		
		replyDTOList.forEach(replyDTO -> System.out.println(replyDTO));
	}
	
}
