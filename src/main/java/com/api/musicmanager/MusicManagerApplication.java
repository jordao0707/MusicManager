package com.api.musicmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MusicManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicManagerApplication.class, args);
	}

	@GetMapping()
	public String test(){
		return "It's working !";
	}

	

}
