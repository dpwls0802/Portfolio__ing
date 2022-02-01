package com.record.travel.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.record.travel.dto.PageRequestDTO;
import com.record.travel.dto.PageResultDTO;
import com.record.travel.dto.TravelrecordDTO;

@SpringBootTest
public class TravelrecordServiceTests {

	@Autowired
	private TravelrecordService service;

	//등록
	@Test
	public void register() {
		TravelrecordDTO travelrecordDTO = TravelrecordDTO.builder().title("제목 테스트").content("내용 테스트").writerEmail("1@abc.com")
				.travelDate("여행기간 테스트").build();
		
		Long tnum = service.register(travelrecordDTO);
	}
	
	//목록
	//@Test
	public void list() {
		//페이지요청
		PageRequestDTO pageRequestDTO = new PageRequestDTO();
		//페이지결과
		PageResultDTO<TravelrecordDTO, Object[]> resultDTO = service.getList(pageRequestDTO);
		
		System.out.println("=========================");
		for(TravelrecordDTO travelrecordDTO : resultDTO.getDtoList()) {
			System.out.println(travelrecordDTO);
			
		}
	}
}
