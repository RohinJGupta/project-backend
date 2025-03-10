package com.supine.project_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication()
public class ProjectBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectBackendApplication.class, args);
	}

}



//notes
//may need to use dto for updates since we are not passing in all fields, just certain ones that are allowed by the UI
//create trigger in supabase before testing for adding users
//then create supabase client sign-in/sign-up service on backend
//use a butterfly flow to see which one is best
//Ideas: Marketing tool with AI trend detection and sounds, Food waste discount app.
