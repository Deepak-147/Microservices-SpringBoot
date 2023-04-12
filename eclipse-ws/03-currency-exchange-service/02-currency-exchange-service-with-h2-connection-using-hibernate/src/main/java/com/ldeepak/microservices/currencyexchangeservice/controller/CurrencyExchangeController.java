package com.ldeepak.microservices.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ldeepak.microservices.currencyexchangeservice.bean.CurrencyExchange;
import com.ldeepak.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment environment;

	@Autowired
	private CurrencyExchangeRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		
		if (currencyExchange == null) {
			throw new RuntimeException("Unable to find data for " + from + " to " +to);
		}
		
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);

		return currencyExchange;
	}
}
