package com.record.travel.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class PageResultDTO<DTO, EN> {
	
	private List<DTO> dtoList;
	
	//엔티티 객체 dto로 변환
	public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
		dtoList = result.stream().map(fn).collect(Collectors.toList());
	}
}
