package com.ldeepak.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ldeepak.microservices.currencyconversionservice.bean.CurrencyConversion;
import com.ldeepak.microservices.currencyconversionservice.proxy.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {
	
	//CHANGE-KUBERNETES
	private Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);
	
	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		//CHANGE-KUBERNETES
		logger.info("calculateCurrencyConversionFeign called with {} to {} with {}", from, to, quantity);
		
		// Make call to currency-exchange-service using feign
		CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);

		return new CurrencyConversion(currencyConversion.getId(), from, to, quantity,
				currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment() + " Feign");
	}
}
