package com.example;

import com.example.models.User;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.sql.Connection;

@SpringBootApplication
@PropertySource("abc.properties")
public class SpringBootSecurityJwtApplication {

	public static void main(String[] args) {

    ConfigurableApplicationContext applicationContext
			= SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	Test test = applicationContext.getBean(Test.class);
		System.out.println(test.getAppName());

	}
}

