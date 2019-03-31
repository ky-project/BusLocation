package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoggedInFilter implements Filter {
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res =(HttpServletResponse)response;
		HttpSession session = req.getSession();
		   
		String path = req.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String loggedId = (String)session.getAttribute("LoggedInAdmin");
		if(loggedId!=null && !loggedId.equals("")){
			chain.doFilter(request, response);
		}
		else {
			String urlString = req.getRequestURI();
			if (urlString.endsWith("login.jsp") || urlString.indexOf("dutyTask.jsp")!=-1 || urlString.indexOf("serviceView.jsp")!=-1 || urlString.indexOf("collegeFace.jsp")!=-1  || urlString.indexOf("TeacherInfo.jsp")!=-1 || urlString.indexOf("invigilationTask.jsp")!=-1) {
				chain.doFilter(request, response);
				return;
			}
			res.sendRedirect(basePath+"login.jsp");
		}
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

}
