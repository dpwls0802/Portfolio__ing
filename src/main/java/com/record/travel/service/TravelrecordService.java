package com.record.travel.service;

import com.record.travel.dto.TravelrecordDTO;
import com.record.travel.entity.Travelrecord;

public interface TravelrecordService {
	Long register(TravelrecordDTO dto);
	
	//서비스 계층에서 파라미터를 DTO 타입으로 받기 때문에 JPA로 처리하기 위해 엔티티 타입의 객체로 변환해야 함
	//DTO -> entity로 변환
	default Travelrecord dtoToEntity(TravelrecordDTO dto) {
		Travelrecord entity = Travelrecord.builder().tnum(dto.getTnum()).title(dto.getTitle()).content(dto.getContent())
				.writer(dto.getWriter()).travelDate(dto.getTravelDate()).build();
		return entity;
	}
}
