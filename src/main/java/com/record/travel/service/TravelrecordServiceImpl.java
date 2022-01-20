package com.record.travel.service;

import org.springframework.stereotype.Service;

import com.record.travel.dto.TravelrecordDTO;
import com.record.travel.entity.Travelrecord;
import com.record.travel.repository.TravelrecordRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입
public class TravelrecordServiceImpl implements TravelrecordService {
	
	private final TravelrecordRepository repository; //JPA 처리 위해 주입
	
	@Override
	public Long register(TravelrecordDTO dto) {
		log.info("===============");
		log.info(dto);
		
		Travelrecord entity = dtoToEntity(dto);
		log.info(entity);
		
		repository.save(entity);
		
		return entity.getTnum();
	}
}
