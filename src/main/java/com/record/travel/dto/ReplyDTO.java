package com.record.travel.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyDTO {
	
	private Long rnum;
	private String replyText;
	private String replyer;
	
	//글 번호
	private Long tnum; 
	private LocalDateTime regDate, modDate;
}
