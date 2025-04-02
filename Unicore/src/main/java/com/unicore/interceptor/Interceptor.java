package com.unicore.interceptor;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Interceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String clientIpAddress = request.getRemoteAddr();
		String localIpAddress = request.getLocalAddr();
		String userId = request.getHeader("X-Request-ID");
		MDC.put("X-User-ID", userId);

		System.out.println("userId==========="+userId);
//	      ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
//	      ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);
	      
//	       request.setAttribute("wrappedRequest", wrappedRequest);
//	       request.setAttribute("wrappedResponse", wrappedResponse);
//
//	       String requestBody = "";
//	        try {
//	            requestBody = new String(wrappedRequest.getContentAsByteArray(), StandardCharsets.UTF_8);
//	        } catch (Exception ignored) {}
//
//	        log.info("[USER] ID: {} - Method: {} - URI: {} - Body: {}", userId, request.getMethod(), request.getRequestURI(), requestBody);
		
        log.info("[USER] ID: {} - Method: {} - URI: {}", userId, request.getMethod(), request.getRequestURI());

	        return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
//        ContentCachingResponseWrapper wrappedResponse = (ContentCachingResponseWrapper) request.getAttribute("wrappedResponse");
//        String responseBody = "";
//
//        if (wrappedResponse != null) {
//            responseBody = new String(wrappedResponse.getContentAsByteArray(), StandardCharsets.UTF_8);
//            wrappedResponse.copyBodyToResponse();
//        }
//
//        int status = response.getStatus();
//        log.info("[USER] ID: {} - Status: {} - Body: {}", MDC.get("X-User-ID"), status, responseBody);

		MDC.remove("user");
		MDC.clear();
	}
}
