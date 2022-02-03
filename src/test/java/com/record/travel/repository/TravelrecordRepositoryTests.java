package com.record.travel.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.record.travel.entity.Travelrecord;
import com.record.travel.entity.User;

@SpringBootTest
public class TravelrecordRepositoryTests {
	@Autowired
	private TravelrecordRepository travelrecordRepository;

	// 등록
	@Test
	public void insertTravelrecord() {
		IntStream.rangeClosed(101, 200).forEach(i -> {
			User user = User.builder().email(i + "@abc.com").build();

			Travelrecord travelrecord = Travelrecord.builder().title(i + "번째 제목").content(i + "번째 내용")
					.writer(user).travelDate("2021-01-" + (i % 10) + " ~ 2021-02-0" + (i % 10)).build();
			System.out.println(travelrecordRepository.save(travelrecord));
		});
	}

	// 조회
	//@Transactional //지연 로딩을 적용해 User테이블과 연결이 끊김 -> 트랜잭션으로 처리해 다시 연결 
	//@Test
	public void selectTravelrecord() {
		Long tnum = 100L;
		Optional<Travelrecord> result = travelrecordRepository.findById(tnum);
		
		Travelrecord travelrecord = result.get();
		System.out.println(travelrecord);
		System.out.println(travelrecord.getWriter());
	}

	// 수정
	//@Test
	/*
	 * public void update() { Travelrecord travelrecord =
	 * Travelrecord.builder().tnum(400L).title("제목 변경").content("내용 변경").
	 * writer("작성자 변경") .travelDate("여행날짜 변경").build();
	 * System.out.println(travelrecordRepository.save(travelrecord));
	 * 
	 * }
	 */

	// 삭제
	// @Test
	public void delete() {
		Long tnum = 400L;
		travelrecordRepository.deleteById(tnum);
	}

	// 전체 삭제
	//@Test
	public void deleteAll() {
		travelrecordRepository.deleteAll();
	}

}
