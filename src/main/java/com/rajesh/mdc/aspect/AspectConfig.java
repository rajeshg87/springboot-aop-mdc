package com.rajesh.mdc.aspect;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Component
@Configuration
public class AspectConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(AspectConfig.class);	
	private static final String REF_ID = "refId";
	
	@Before("execution(* com.rajesh.mdc.controller..*.*(..))")
	public void mdcPut(JoinPoint joinPoint) {
		
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
 
                 //This is the information to record the http request.
        logger.info("==============================================================");
 
        //IP
        logger.info("IP = {}", request.getRemoteAddr());
        //URL
        logger.info("URL = {}", request.getRequestURL());
        //METHOD
        logger.info("METHOD = {}", request.getMethod());
        //CLASS_METHOD
        logger.info("CLASS_METHOD = {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //ARGS
        logger.info("ARGS = {}", joinPoint.getArgs());
        
		MDC.put(REF_ID, Stream.of(joinPoint.getArgs()).map(v -> (String) v).collect(Collectors.joining("-")));
 
        logger.info("==============================================================");

		
	}	
	
	@After("execution(* com.rajesh.mdc.controller..*.*(..))")
	public void mdcRemove(JoinPoint joinPoint) {
		MDC.remove(REF_ID);
	}	
}