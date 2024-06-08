package com.ldeepak.microservices.namingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
 * 
 * Eureka naming server simplifies the complexities of service discovery and communication in a microservices architecture. 
 * By providing a centralized registry of service instances and supporting dynamic load balancing and failover, Eureka enables developers to build highly resilient, scalable, and self-healing microservices applications.
 * 
 * Service Registry: When a microservice starts up, it registers itself with the Eureka server by sending a heartbeat message. Eureka keeps track of all registered services in its registry.
 * Service Discovery: Client microservices, which need to communicate with other services, query the Eureka server to discover the locations of the desired service instances.
 */
@EnableEurekaServer
@SpringBootApplication
public class NamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NamingServerApplication.class, args);
	}

}
