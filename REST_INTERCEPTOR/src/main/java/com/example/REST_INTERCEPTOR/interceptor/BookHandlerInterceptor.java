package com.example.REST_INTERCEPTOR.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class BookHandlerInterceptor implements HandlerInterceptor {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request.getParameter("id") != null) {
			System.out.println(dateFormat.format(new Date()) + " - En preHandler()");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if (request.getParameter("id") != null) {
			System.out.println(dateFormat.format(new Date()) + " - En postHandle()");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (request.getParameter("id") != null) {
			System.out.println(dateFormat.format(new Date()) + " - En afterCompletion()");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
		}
	}
}