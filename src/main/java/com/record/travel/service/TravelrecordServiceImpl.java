package com.record.travel.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.record.travel.dto.PageRequestDTO;
import com.record.travel.dto.PageResultDTO;
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
	
	//등록
	@Override
	public Long register(TravelrecordDTO dto) {
		log.info("===============");
		log.info(dto);
		
		Travelrecord entity = dtoToEntity(dto);
		log.info(entity);
		
		repository.save(entity);
		
		return entity.getTnum();
	}
	
	//목록
	@Override
	public PageResultDTO<TravelrecordDTO, Travelrecord> getList(PageRequestDTO requestDTO) {
		Pageable pageable = requestDTO.getPageable(Sort.by("tnum").descending());
		Page<Travelrecord> result = repository.findAll(pageable);
		
		Function<Travelrecord, TravelrecordDTO> fn = (entity -> entityToDto(entity));
		return new PageResultDTO<>(result, fn);
	}
	
	//조회
	@Override
	public TravelrecordDTO read(Long tnum) {
		Optional<Travelrecord> result = repository.findById(tnum);
		
		return result.isPresent()? entityToDto(result.get()) : null;
	}
	
	//수정
	@Override
	public void modify(TravelrecordDTO dto) {
		Optional<Travelrecord> result = repository.findById(dto.getTnum());
		
		if(result.isPresent()) {
			Travelrecord entity = result.get();
			entity.changTitle(dto.getTitle());
			entity.changeContent(dto.getContent());
			entity.changeTravelDate(dto.getTravelDate());
			
			repository.save(entity);
		}
	}

	//삭제
	@Override
	public void remove(Long tnum) {
		repository.deleteById(tnum);
		
	}
	
	
}
