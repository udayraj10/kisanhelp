package dev.kisanhelp.project_kh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectKhApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectKhApplication.class, args);
		System.out.println("Server started on port 8080...");
	}
}
