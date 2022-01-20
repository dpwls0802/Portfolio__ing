package com.record.travel.service;

import com.record.travel.dto.TravelrecordDTO;
import com.record.travel.entity.Travelrecord;

public interface TravelrecordService {
	Long register(TravelrecordDTO dto);

	default Travelrecord dtoToEntity(TravelrecordDTO dto) {
		Travelrecord entity = Travelrecord.builder().tnum(dto.getTnum()).title(dto.getTitle()).content(dto.getContent())
				.writer(dto.getWriter()).travelDate(dto.getTravelDate()).build();
		return entity;
	}
}
