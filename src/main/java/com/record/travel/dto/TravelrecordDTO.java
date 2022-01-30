package com.record.travel.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class TravelrecordDTO {
	
	private Long tnum;
	private String title;
	private String content;
	private String travelDate;
	private LocalDateTime regDate, modDate;
	
	private String writerEmail;
	private String writerName;
	
	private int replyCount;
}
