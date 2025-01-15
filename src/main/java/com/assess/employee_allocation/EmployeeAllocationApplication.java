package com.assess.employee_allocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class EmployeeAllocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeAllocationApplication.class, args);
	}

	@Bean
	public JavaMailSender javaMailSender() {
		return new JavaMailSenderImpl(); // Configure with actual email properties
	}
}
