package com.record.travel.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.record.travel.dto.PageRequestDTO;
import com.record.travel.dto.PageResultDTO;
import com.record.travel.dto.TravelImageDTO;
import com.record.travel.dto.TravelrecordDTO;
import com.record.travel.entity.TravelImage;
import com.record.travel.entity.Travelrecord;
import com.record.travel.entity.User;

public interface TravelrecordService {
	// 등록
	Long register(TravelrecordDTO dto);

	// 목록
	PageResultDTO<TravelrecordDTO, Object[]> getList(PageRequestDTO requestDTO);

	// 조회
	TravelrecordDTO read(Long tnum);

	// 수정
	void modify(TravelrecordDTO dto);

	// 삭제 (글 + 댓글)
	void removeWithReply(Long tnum);

	// 서비스 계층에서 파라미터를 DTO 타입으로 받기 때문에 JPA로 처리하기 위해 엔티티 타입의 객체로 변환해야 함
	// DTO -> entity로 변환
	/*
	 * default Travelrecord dtoToEntity(TravelrecordDTO dto) { User user =
	 * User.builder().email(dto.getWriterEmail()).build();
	 * 
	 * Travelrecord entity =
	 * Travelrecord.builder().tnum(dto.getTnum()).title(dto.getTitle()).content(dto.
	 * getContent()) .writer(user).travelDate(dto.getTravelDate()).build(); return
	 * entity; }
	 */
	
	//이미지 객체까지 같이 처리하기 위해 map타입 이용, 반환
	default Map<String, Object> dtoToEntity(TravelrecordDTO dto) {

		Map<String, Object> entityMap = new HashMap<>();

		User user = User.builder().email(dto.getWriterEmail()).build();

		Travelrecord travelrecord = Travelrecord.builder().tnum(dto.getTnum()).title(dto.getTitle()).content(dto.getContent())
				.writer(user).travelDate(dto.getTravelDate()).build();
		
		entityMap.put("travelrecord", travelrecord);
		
		List<TravelImageDTO> imageDTOList = dto.getImageDTOList();
		
		if(imageDTOList != null && imageDTOList.size() > 0) {
			List<TravelImage> travelImageList = imageDTOList.stream().map(travelImageDTO -> {
				TravelImage travelImage = TravelImage.builder().uuid(travelImageDTO.getUuid()).imageName(travelImageDTO.getImageName()).
						imagePath(travelImageDTO.getFolderPath()).travelrecord(travelrecord).build();
				return travelImage;
			}).collect(Collectors.toList());
			entityMap.put("imageList", travelImageList);
		}
		return entityMap;
	}

	// entity -> DTO로 변환
	default TravelrecordDTO entityToDto(Travelrecord travelrecord, User user, Long replyCount) {
		TravelrecordDTO dto = TravelrecordDTO.builder().tnum(travelrecord.getTnum()).title(travelrecord.getTitle())
				.content(travelrecord.getContent()).travelDate(travelrecord.getTravelDate())
				.regDate(travelrecord.getRegDate()).modDate(travelrecord.getModDate()).writerEmail(user.getEmail())
				.writerName(user.getName()).replyCount(replyCount.intValue()).build();
		return dto;
	}
}
