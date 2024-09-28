package com.mogharib.student.management.system;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableJpaRepositories(basePackages = "com.mogharib.student.management.system.repository")
@EnableCaching

@OpenAPIDefinition(
		info = @Info(
				title = "Student Management System",
				description = "Student Management System API with CRUD operations",
				version = "v1",
				contact = @Contact(
						name = "mohamed gharib",
						email = "9mohamedabdelhamied8@gmail.com")
		)
)

public class StudentManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}

}
