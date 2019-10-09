package com.tcc.recognize;


import com.tcc.recognize.property.FileStorageProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}