package com.ipartek.formacion.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ipartek.formacion.controller.LoginServlet;

/**
 * Servlet Filter implementation class FilterAll
 */
public class FilterAll implements Filter {
	
	private final static Logger LOG = Logger.getLogger(FilterAll.class);

    /**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		LOG.trace("Filtro destruido");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		LOG.trace("Filtro ejecutandose");		
		// place your code here
		
		LOG.debug("IP: " + request.getRemoteAddr());
		LOG.debug("User-Agent: " + ((HttpServletRequest)request).getHeader("User-Agent"));
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		LOG.trace("Filtro inicializado");
	}

}
