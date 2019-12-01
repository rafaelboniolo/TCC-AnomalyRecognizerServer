package com.tcc.recognizer;

import com.tcc.recognizer.property.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({
		ApplicationProperties.class
})
@SpringBootApplication
public class AnomalyRecognizerApplication {
	public static void main(String[] args) {
		SpringApplication.run(AnomalyRecognizerApplication.class, args);
	}
}