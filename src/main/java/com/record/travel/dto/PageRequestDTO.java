package com.record.travel.dto;

import java.awt.print.Pageable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
	
	private int page;
	private int size;
	
	public PageRequestDTO() {
		this.page = 1;
		this.size = 8;
	}
	
	public Pageable getPageable(Sort sort) {
		return (Pageable) PageRequest.of(page-1, size, sort);
	}
}