package com.record.travel.service;

import java.util.List;

import com.record.travel.dto.ReplyDTO;
import com.record.travel.entity.Reply;
import com.record.travel.entity.Travelrecord;
import com.record.travel.entity.User;

public interface ReplyService {

	// 등록
	Long register(ReplyDTO replyDTO);

	// 목록
	List<ReplyDTO> getList(Long tnum);

	// 수정
	void modify(ReplyDTO replyDTO);

	// 삭제
	void remove(Long rnum);

	// DTO -> entity
	default Reply dtoToEntity(ReplyDTO replyDTO) {
		Travelrecord travelrecord = Travelrecord.builder().tnum(replyDTO.getTnum()).build();
		
		User user = User.builder().email(replyDTO.getReplyerEmail()).build();

		Reply reply = Reply.builder().rnum(replyDTO.getRnum()).replyText(replyDTO.getReplyText())
				.replyer(user).travelrecord(travelrecord).build();

		return reply;
	}

	// entity -> DTO
	default ReplyDTO entityToDTO(Reply reply, User user) {
		ReplyDTO replyDTO = ReplyDTO.builder().rnum(reply.getRnum()).replyText(reply.getReplyText())
				.replyerName(user.getName()).replyerEmail(user.getEmail()).regDate(reply.getRegDate()).modDate(reply.getModDate()).build();

		return replyDTO;

	}

}
