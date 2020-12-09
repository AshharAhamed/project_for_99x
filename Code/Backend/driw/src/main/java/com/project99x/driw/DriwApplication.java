package com.project99x.driw;

import com.project99x.driw.Services.ProductService;
import com.project99x.driw.Services.ServicesImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class DriwApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriwApplication.class, args);
	}

}

