package com.ldeepak.microservices.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring-cloud-starter-gateway simplifies the implementation of API gateways in microservices architectures by providing a comprehensive set of features for routing, security, resilience, and observability. 
 * It serves as a centralized entry point for incoming requests, offering flexibility and control over how those requests are processed and forwarded to downstream services.
 *
 */
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
