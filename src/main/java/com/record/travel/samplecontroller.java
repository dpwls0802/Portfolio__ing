package com.record.travel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class samplecontroller {

	@GetMapping("/hello")
	public String[] hello() {
		return new String[] {"hello", "World"};
	}
}
