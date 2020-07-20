package com.rajesh.mdc.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.mdc.model.Greeting;
import com.rajesh.mdc.service.RandomGeneratorService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class GreetController {
	
	private static final Logger logger = LoggerFactory.getLogger(GreetController.class);
	
	private final AtomicInteger counter = new AtomicInteger();
	
	private RandomGeneratorService randomGeneratorService;
	
	public GreetController(RandomGeneratorService randomGeneratorService) {
		this.randomGeneratorService = randomGeneratorService;
	}

	@ApiOperation(value="")
	@ApiImplicitParams({
        @ApiImplicitParam(name="name",value="User's name",required=false,dataType="string",paramType="query",defaultValue="World")
      })
	@ApiResponses(value = {@ApiResponse(code=200,message="Success",response=Greeting.class)}) 
	@GetMapping(path="/greet")
	public Greeting greet(@RequestParam(value="name") String name) {
		logger.info("event=request received, nameParameter=" + name);
		Greeting greeting = new Greeting(counter.incrementAndGet(), String.format("Hello %s", name),
				randomGeneratorService.generate());
		logger.info("event=request completed, response=" + greeting.toString());
		return greeting;
	}

}
