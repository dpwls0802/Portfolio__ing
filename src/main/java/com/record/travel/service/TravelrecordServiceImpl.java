package com.record.travel.service;

import org.springframework.stereotype.Service;

import com.record.travel.dto.TravelrecordDTO;
import com.record.travel.entity.Travelrecord;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TravelrecordServiceImpl implements TravelrecordService {
	
	@Override
	public Long register(TravelrecordDTO dto) {
		log.info("===============");
		log.info(dto);
		
		Travelrecord entity = dtoToEntity(dto);
		log.info(entity);
		
		return null;
	}
}
