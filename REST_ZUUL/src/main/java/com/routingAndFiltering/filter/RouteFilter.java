package com.routingAndFiltering.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class RouteFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(RouteFilter.class);

	/**
	 * Se ejecuta cuando el request es ruteado, despues del pre-filter.
	 */
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		log.info("RouteFilter: {} desde {}", request.getMethod(), request.getRequestURL().toString());

		return null;
	}

	/**
	 * Le decimos que lo debe aplicar siempre. Podriamos poner condiciones para que
	 * lo aplique a ciertos request y a otros no.
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * Puede haber muchos Route Filter, por eso se indica un orden.
	 */
	@Override
	public int filterOrder() {
		return 1;
	}

	/**
	 * Puede haber muchos tipos de filtros, este es un "route" filtro. Tambien puede
	 * haber muchos route filter por eso se indica un orden en el metodo
	 * filterOrder()
	 */
	@Override
	public String filterType() {
		return "route";
	}
}