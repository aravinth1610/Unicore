package com.unicore.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.unicore.utils.MaskingUtil;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
//@Slf4j
public class LoggingWebAspect {

	@Pointcut(value="execution(* com.booknetwork.*.*.*(..))")
	public void pointcutExecution() {}
	
	
	@Around("pointcutExecution()")
//	@Async
	public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().toString();
		Object[] args = joinPoint.getArgs();

//		log.info("Entering Method: {} with Arguments: {}", methodName, MaskingUtil.toJsonString(args));

		Object result = joinPoint.proceed();

//		log.info("Exiting Method: {} with Result: {}", methodName, MaskingUtil.toJsonString(result));
		return result;
	}

}
