package com.ldeepak.microservices.limitsservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ldeepak.microservices.limitsservice.bean.Limits;

@RestController
public class LimitsController {

	@GetMapping("/limits")
	public Limits retrieveLimits() {
		
//		Without using configuration
		return new Limits(1, 1000);
	}
}
