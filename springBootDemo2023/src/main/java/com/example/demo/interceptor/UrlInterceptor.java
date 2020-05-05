package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class UrlInterceptor implements HandlerInterceptor {

	private final static Logger LOGGER = LoggerFactory.getLogger(UrlInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.debug("pre ---------------------------- ");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOGGER.debug("post ---------------------------- ");
		
		
		if(modelAndView == null || modelAndView.getViewName().startsWith("redirect")){
			return;
		}
		
		String uri = request.getServletPath();
		String template = (String) modelAndView.getModelMap().get("template");
		
		if(StringUtils.isNoneBlank(template)){
			if(uri.startsWith("/")){
				uri = uri.substring(1);
			}
			modelAndView.getModelMap().put("template", uri);
		}
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		LOGGER.debug("after ---------------------------- ");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
		
}
