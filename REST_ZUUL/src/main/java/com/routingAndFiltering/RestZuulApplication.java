package com.routingAndFiltering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.routingAndFiltering.filter.ErrorFilter;
import com.routingAndFiltering.filter.PostFilter;
import com.routingAndFiltering.filter.PreFilter;
import com.routingAndFiltering.filter.RouteFilter;

@SpringBootApplication
@EnableZuulProxy
public class RestZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestZuulApplication.class, args);
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}

	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}

	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
}
