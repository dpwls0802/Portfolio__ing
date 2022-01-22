package com.record.travel.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TravelrecordDTO {
	
	private Long tnum;
	private String title;
	private String content;
	private String writer;
	private String travelDate;
	private LocalDateTime regDate, modDate;
}
