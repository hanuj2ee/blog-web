package com.blog.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Spring boot main Blog web application
 */
@SpringBootApplication(scanBasePackages = {"com.blog.rest.controller", "com.blog.rest.service"})
@EnableJpaRepositories("com.blog.dao")
@EntityScan("com.blog.client.pojo")
@EnableSwagger2
public class BlogWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(BlogWebApplication.class, args);
	}
}

