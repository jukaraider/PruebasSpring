package com.example.REST_INTERCEPTOR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.REST_INTERCEPTOR.interceptor.BookHandlerInterceptor;

@Component
public class WebAppConfiguration implements WebMvcConfigurer {

	@Autowired
	BookHandlerInterceptor bookInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(bookInterceptor);
	}
}