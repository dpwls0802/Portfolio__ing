package com.record.travel.repository;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.record.travel.entity.TravelImage;
import com.record.travel.entity.Travelrecord;
import com.record.travel.entity.User;

@SpringBootTest
public class TravelrecordRepositoryTests {
	@Autowired
	private TravelrecordRepository travelrecordRepository;

	@Autowired
	private TravelImageRepository travelImageRepository;

	// 글 + 이미지 등록
	@Commit
	@Transactional
	@Test
	public void insertTravelrecord() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			
			User user = User.builder().email(i + "@abc.com").build();
			
			// 글
			Travelrecord travelrecord = Travelrecord.builder().title(i + "번째 제목").content(i + "번째 내용").writer(user)
					.travelDate("2021-01-0" + (i % 10) + " ~ 2021-02-0" + (i % 10)).build();
			
			System.out.println("---------------------------------");
			// 글 등록
			travelrecordRepository.save(travelrecord);
			
			//이미지
			int count = (int) (Math.random() * 5) +  1;

			for (int j = 0; j < count; j++) {
				TravelImage travelImage = TravelImage.builder().uuid(UUID.randomUUID().toString())
						.travelrecord(travelrecord).imageName("이미지" + j + ".jpg").build();
				//이미지 등록
				travelImageRepository.save(travelImage);
			}
			
			System.out.println("----------------------------------");
		});
	}

	// 조회
	// @Transactional //지연 로딩을 적용해 User테이블과 연결이 끊김 -> 트랜잭션으로 처리해 다시 연결
	// @Test
	public void selectTravelrecord() {
		Long tnum = 100L;
		Optional<Travelrecord> result = travelrecordRepository.findById(tnum);

		Travelrecord travelrecord = result.get();
		System.out.println(travelrecord);
		System.out.println(travelrecord.getWriter());
	}
	
	//목록
	//@Test
	public void list() {
		PageRequest  pageRequest = PageRequest.of(0, 8, Sort.by(Sort.Direction.DESC, "tnum"));
		
		Page<Object[]> result = travelrecordRepository.getTravelrecordList(pageRequest);
		
		for(Object[] objects : result.getContent()) {
			System.out.println(Arrays.toString(objects));
		}
				
	}
	// 수정
	// @Test
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
	// @Test
	public void deleteAll() {
		travelrecordRepository.deleteAll();
	}

}
