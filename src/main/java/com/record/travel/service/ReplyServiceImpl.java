package com.record.travel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.record.travel.dto.ReplyDTO;
import com.record.travel.entity.Reply;
import com.record.travel.entity.Travelrecord;
import com.record.travel.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
	
	private final ReplyRepository replyRepository;

	//등록
	@Override
	public Long register(ReplyDTO replyDTO) {
		Reply reply = dtoToEntity(replyDTO);
		replyRepository.save(reply);
		
		return reply.getRnum();
	}

	//목록
	@Override
	public List<ReplyDTO> getList(Long tnum) {
		List<Reply> result = replyRepository.getReplyByTravelrecord(Travelrecord.builder().tnum(tnum).build());
		
		return result.stream().map(reply, user -> entityToDTO(reply, user)).collect(Collectors.toList());
	}
	
	//수정
	@Override
	public void modify(ReplyDTO replyDTO) {
		Reply reply = dtoToEntity(replyDTO);
		replyRepository.save(reply);
	}
	
	//삭제
	@Override
	public void remove(Long rnum) {
		replyRepository.deleteById(rnum);
	}
	
	
}
