package com.rajesh.mdc.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.mdc.model.Greeting;

@RestController
public class GreetController {
	
	private final AtomicInteger counter = new AtomicInteger();
	
	@GetMapping(path="/greet")
	public Greeting greet(@RequestParam(value="name") String name) {
		
		Greeting greeting = new Greeting(counter.incrementAndGet(), String.format("Hello %s", name), 1234);
		return greeting;
	}

}
