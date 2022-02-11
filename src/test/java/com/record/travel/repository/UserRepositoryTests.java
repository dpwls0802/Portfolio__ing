package com.record.travel.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.record.travel.entity.User;

@SpringBootTest
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void insertUsers() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			User user = User.builder().email(i + "@abc.com").password("1234").name("사용자"+i).build();
			
			userRepository.save(user);
		});
	}
}
