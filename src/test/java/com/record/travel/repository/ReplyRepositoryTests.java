package com.record.travel.repository;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.record.travel.entity.Reply;
import com.record.travel.entity.Travelrecord;
import com.record.travel.entity.User;

@SpringBootTest
public class ReplyRepositoryTests {
	
	@Autowired
	ReplyRepository replyRepository;
	
	//댓글 등록(더미)
	@Test
	public void insertReply() {
		
		IntStream.rangeClosed(1, 200).forEach(i -> {
			//게시글
			long tnum = (long)(Math.random() * 100) + 1;
			
			Travelrecord travelrecord = Travelrecord.builder().tnum(tnum).build();
			
			//댓글 작성자
			long email = ((long)(Math.random() * 100) + 1);
			 
			User user = User.builder().email(email + "@abc.com").build();
			
			Reply reply = Reply.builder().replyText(i+"번째 댓글 내용").replyer(user).travelrecord(travelrecord).build();
			replyRepository.save(reply);
		});
	}
	
	//댓글 목록 조회
	//@Test
	public void listByTravelrecord() {
		List<Reply> replyList = replyRepository.getReplyByTravelrecord(Travelrecord.builder().tnum(21L).build());
		
		replyList.forEach(reply -> System.out.println(reply));
	}
	
}
