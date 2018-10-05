package com.mo2christian.recognition.translate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class TranslateApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TranslateApplication.class, args);
	}
}
