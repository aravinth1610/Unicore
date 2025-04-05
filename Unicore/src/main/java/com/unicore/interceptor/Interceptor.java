package com.unicore.interceptor;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Interceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String clientIpAddress = request.getRemoteAddr();
		String localIpAddress = request.getLocalAddr();
		String requestUserId = request.getHeader("X-UserID-X");
		String userId = requestUserId != null ? requestUserId : "unknown";
		String uniqueId = request.getHeader("X-UUID-X");
		
		MDC.put("X-User-ID", userId);
		MDC.put("X-UUID-X", uniqueId);

		System.out.println("userId===========" + userId);

		log.info("[UUID] ID: {} - [USER] ID: {} - IN - ClientIp: {} - LocalIp: {} - Method: {} - URI: {}", uniqueId, userId, clientIpAddress, localIpAddress, request.getMethod(), request.getRequestURI());

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) throws Exception {

		String clientIpAddress = request.getRemoteAddr();
		String localIpAddress = request.getLocalAddr();

		log.info("[UUID] ID: {} - [USER] ID: {} - OUT - ClientIp: {} - LocalIp: {} - Status: {}", MDC.get("X-UUID-X"), MDC.get("X-User-ID"), clientIpAddress, localIpAddress, response.getStatus());

		MDC.remove("X-User-ID");
		MDC.remove("X-UUID-X");
		MDC.clear();
	}
}
