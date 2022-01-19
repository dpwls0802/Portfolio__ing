package com.record.travel.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.record.travel.entity.Travelrecord;

@SpringBootTest
public class TravelrecordRepositoryTests {
	@Autowired
	private TravelrecordRepository travelrecordRepository;

	@Test
	public void insert() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Travelrecord travelrecord = Travelrecord.builder().title(i + "번째 제목").content(i + "번째 내용")
					.writer((i % 10) + "번째 작성자").travelDate("2021-01-"+ (i%10) + " ~ 2021-02-" + (i%10)).build();
			System.out.println(travelrecordRepository.save(travelrecord));
		});
	}

}
