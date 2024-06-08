package com.ldeepak.microservices.currencyconversionservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ldeepak.microservices.currencyconversionservice.bean.CurrencyConversion;
 
/**
 * When a service is up, Kubernetes provides a default environment variable in format (APPNAME_SERVICE_HOST)
 * In case of currency exchange service it is CURRENCY_EXCHANGE_SERVICE_HOST
 * The problem with using kubernetes provided environment variable is that, it provided only when the service is up.
 * For example if we deploy currency conversion first, it will have not knowledge of currency exchange service as it is not up.
 * To deal with this, we use custom created environment variables, in this case CURRENCY_EXCHANGE_URI
 */
//CHANGE-KUBERNETES
//@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000")
@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_URI:http://localhost}:8000")
//@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
