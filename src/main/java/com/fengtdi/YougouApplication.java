package com.fengtdi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class YougouApplication {

	public static void main(String[] args) {
		SpringApplication.run(YougouApplication.class, args);
	}

}
