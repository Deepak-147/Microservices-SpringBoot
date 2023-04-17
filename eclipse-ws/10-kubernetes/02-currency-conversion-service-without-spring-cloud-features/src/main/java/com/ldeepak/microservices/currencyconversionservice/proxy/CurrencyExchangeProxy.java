package com.ldeepak.microservices.currencyconversionservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ldeepak.microservices.currencyconversionservice.bean.CurrencyConversion;
 

//CHANGE-KUBERNETES
@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000")
//@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
