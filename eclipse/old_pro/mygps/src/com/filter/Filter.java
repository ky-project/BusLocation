package com.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public interface Filter {
	public void init(FilterConfig config) throws ServletException;
	public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws ServletException, IOException;
	public void destory();
}
