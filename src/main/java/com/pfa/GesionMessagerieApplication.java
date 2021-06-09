package com.pfa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
public class GesionMessagerieApplication implements CommandLineRunner {
@Autowired
private MailService mailService;
	public static void main(String[] args) {
		SpringApplication.run(GesionMessagerieApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
	
	}

}
