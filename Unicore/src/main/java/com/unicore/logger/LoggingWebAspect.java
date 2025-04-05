package com.unicore.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class LoggingWebAspect {
	
//	@PostConstruct
//	public void init() {
//	    log.info("LoggingAspect is initialized.");
//	}


//	@Pointcut("execution(* com..controller..*(..))")
	@Pointcut("execution(* com..controller..*(..)) || execution(* com..services..*(..))")
	public void appPointcut() {
	}
	

	@Around ("appPointcut()")
	public Object auditManager(ProceedingJoinPoint joinPoint) throws Throwable {
		
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		Object[] args = joinPoint.getArgs();

		long startTime = System.currentTimeMillis();

		
		log.info("[UUID] ID: {} - [USER] ID: {} - IN | Class: [{}] | Method: [{}] | Arguments: {}", MDC.get("X-UUID-X"), MDC.get("X-User-ID"), className, methodName, new ObjectMapper().writeValueAsString(args));

		Object result;
		try {
			result = joinPoint.proceed();

			long executionTime = System.currentTimeMillis() - startTime;
			log.info("[UUID] ID: {} - [USER] ID: {} - OUT | Class: [{}] | Method: [{}] | Result: {} | Execution Time: {} ms", MDC.get("X-UUID-X"), MDC.get("X-User-ID"), className, methodName, new ObjectMapper().writeValueAsString(result), executionTime);

			return result;
		} catch (Exception e) {
			log.error("[UUID] ID: {} - [USER] ID: {} - ERROR | Class: [{}] | Method: [{}] | Exception: {}", MDC.get("X-UUID-X"), MDC.get("X-User-ID"), className, methodName, e.getMessage(), e);
            return "Exception in "+className;
			// throw e;
		}
	}

}
