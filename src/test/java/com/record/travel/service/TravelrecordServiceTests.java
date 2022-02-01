package com.record.travel.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.record.travel.dto.PageRequestDTO;
import com.record.travel.dto.PageResultDTO;
import com.record.travel.dto.TravelrecordDTO;
import com.record.travel.entity.Travelrecord;

@SpringBootTest
public class TravelrecordServiceTests {

	@Autowired
	private TravelrecordService service;

	//등록
	//@Test
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

	//조회
	//@Test
	public void read() {
		Long tnum = 100L;
		TravelrecordDTO travelrecordDTO = service.read(tnum);
		
		System.out.println(travelrecordDTO);
	}
	
	//삭제
	//@Test
	public void remove() {
		Long tnum = 99L;
		service.removeWithReply(tnum);
	}
	
	//수정
	@Test
	public void modify() {
		TravelrecordDTO travelrecordDTO = TravelrecordDTO.builder().tnum(98L).title("제목 변경").content("내용 변경").travelDate("여행일자 변경").build();
		
		service.modify(travelrecordDTO);
	}
	
}
