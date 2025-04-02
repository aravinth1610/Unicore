package com.unicore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.unicore.interceptor.Interceptor;

@Configuration
//@EnableAsync
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class WebInterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("InterceptorRegistry=====================================");
		 registry.addInterceptor(new Interceptor()).addPathPatterns("/**");
	}
}
