package com.routingAndFiltering.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class PostFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(PostFilter.class);

	/**
	 * Se ejecuta luego de ser ruteado, o sea despues del route-Filter.
	 */
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletResponse response = ctx.getResponse();

		log.info("PostFilter: estado devuelto {}", response.getStatus());

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
	 * Puede haber muchos Post Filtros, por eso se indica un orden.
	 */
	@Override
	public int filterOrder() {
		return 1;
	}

	/**
	 * Puede haber muchos tipos de filtros, este es un "post" filtro. Tambien puede
	 * haber muchos post Filtros por eso se indica un orden en el metodo
	 * filterOrder()
	 */
	@Override
	public String filterType() {
		return "post";
	}
}