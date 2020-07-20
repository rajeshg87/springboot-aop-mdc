package com.rajesh.mdc.service;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RandomGeneratorService {
	
	private static final Logger logger = LoggerFactory.getLogger(RandomGeneratorService.class);
	
	public int generate() {
		logger.info("action=generating random number");
		Random random = new Random();
		return random.nextInt();
	}
}
