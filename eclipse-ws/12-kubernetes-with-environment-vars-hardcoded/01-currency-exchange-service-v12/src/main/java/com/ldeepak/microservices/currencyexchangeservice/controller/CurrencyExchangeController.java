package com.ldeepak.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ldeepak.microservices.currencyexchangeservice.bean.CurrencyExchange;
import com.ldeepak.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@Autowired
	private Environment environment;

	@Autowired
	private CurrencyExchangeRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		logger.info("retrieveExchangeValue called with {} to {}", from, to);
		
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		
		if (currencyExchange == null) {
			throw new RuntimeException("Unable to find data for " + from + " to " +to);
		}
		
		String port = environment.getProperty("local.server.port");
		
		//CHANGE-KUBERNETES
		String host = environment.getProperty("HOSTNAME");
		String version = "v12";
		
		currencyExchange.setEnvironment(port + " " + version + " " + host);

		return currencyExchange;
	}
}
