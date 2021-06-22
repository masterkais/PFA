package com.pfa.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminSystemRestService {
@GetMapping("/hi")
public String hi() {
	return "hello kais";
}
}
