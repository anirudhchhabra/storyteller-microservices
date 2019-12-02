package com.ps.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ArchimedesApplication {
	
    public static void main(String[] args) {
//    	new SpringApplicationBuilder(ArchimedesApplication.class).web(true).run(args);
    	SpringApplication.run(ArchimedesApplication.class, args);
    }
}
