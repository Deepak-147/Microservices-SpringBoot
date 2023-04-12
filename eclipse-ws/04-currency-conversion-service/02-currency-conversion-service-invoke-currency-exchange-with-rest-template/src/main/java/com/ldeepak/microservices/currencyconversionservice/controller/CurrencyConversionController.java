package com.ldeepak.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ldeepak.microservices.currencyconversionservice.bean.CurrencyConversion;

@RestController
public class CurrencyConversionController {

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		//Make call to currency-exchange-service
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
		
		CurrencyConversion body = responseEntity.getBody();
		
		CurrencyConversion currencyConversion = new CurrencyConversion(body.getId(), from, to, quantity, body.getConversionMultiple(), quantity.multiply(body.getConversionMultiple()), body.getEnvironment() + " Rest template");
		
		return currencyConversion;
	}
}
