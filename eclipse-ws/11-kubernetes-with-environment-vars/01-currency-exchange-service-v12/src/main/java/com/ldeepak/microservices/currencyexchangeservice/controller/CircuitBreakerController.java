package com.ldeepak.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
	
	//By Default 3 retries
//	@Retry(name = "default")
	
	//Customised number of retries
//	@Retry(name = "sample-api", fallbackMethod = "fallbackMethod")
	
	// Directly returns fallback response, if the API is repeatedly failing.
	@CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
	public String sampleApi() {
		logger.info("Sample API called");
		
		// Introducing error. As this URL will not work.
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url",
				String.class);

		return forEntity.getBody();
	}
	
	public String fallbackMethod(Exception ex) {
		return "Sample API Fallback response";
	}
}
