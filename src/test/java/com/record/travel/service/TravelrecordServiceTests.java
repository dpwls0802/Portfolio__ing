package com.record.travel.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.record.travel.dto.TravelrecordDTO;

@SpringBootTest
public class TravelrecordServiceTests {

	@Autowired
	private TravelrecordService service;

	@Test
	public void register() {
		TravelrecordDTO travelrecordDTO = TravelrecordDTO.builder().title("제목 테스트").content("내용 테스트").writer("작성자 테스트")
				.travelDate("여행기간 테스트").build();
		
		System.out.println(service.register(travelrecordDTO));
	}
}
