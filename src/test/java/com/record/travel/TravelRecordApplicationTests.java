package com.record.travel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.record.travel.repository.TravelrecordRepository;

@SpringBootTest
class TravelRecordApplicationTests {
	
	@Autowired
	private TravelrecordRepository travelrecordRepository;
	
	@Test
	void contextLoads() {
	}

}
