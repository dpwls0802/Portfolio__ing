package com.record.travel.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.record.travel.entity.Reply;
import com.record.travel.entity.Travelrecord;

@SpringBootTest
public class ReplyRepositoryTests {
	
	@Autowired
	ReplyRepository replyRepository;
	
	@Test
	public void insertReply() {
		
		IntStream.rangeClosed(1, 200).forEach(i -> {
			long tnum = (long)(Math.random() * 100) + 1;
			Travelrecord travelrecord = Travelrecord.builder().tnum(tnum).build();
			
			Reply reply = Reply.builder().replyText(i+"번째 댓글 내용").replyer("게스트").travelrecord(travelrecord).build();
			replyRepository.save(reply);
		});
	}
	
}
