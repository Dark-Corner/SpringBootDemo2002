package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName="ParameterFilter", urlPatterns="/**")
public class ParameterFilter implements Filter{

	//通过日志打印出来与system的作用差不多
	private final static Logger LOGGER = LoggerFactory.getLogger(ParameterFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.debug("Init ParameterFilter.");
		Filter.super.init(filterConfig);
		
	}


	@Override
	public void destroy() {
		// TODO 
		LOGGER.debug("DesTroy ParameterFilter.");
		Filter.super.destroy();
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.debug("ParameterFilter  Action.");
		
		HttpServletRequest  httpRequest = (HttpServletRequest) request;
		
		HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpRequest){

			@Override
			public String getParameter(String name) {
				String value = httpRequest.getParameter(name);
				if(StringUtils.isNotBlank(value)){
					return value.replaceAll("fuck", "***");
				}
				
				return super.getParameter(name);
			}

			@Override
			public String[] getParameterValues(String name) {
				 	 	
				String[] values = httpRequest.getParameterValues(name);
				if(values != null && values.length >0){
					values[0] = values[0].replaceAll("fuck", "***");
					 return values;
				}
				
				return super.getParameterValues(name);
			}
			
			
		};
		chain.doFilter(wrapper, response);
	}

}
