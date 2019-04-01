package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.persistencelayer.*;

public class getLoginUser extends HttpServlet {

	private ServletContext application;
    private WebApplicationContext ctx;
	
	/**
	 * Constructor of the object.
	 */  
	public getLoginUser() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//不能在这里new，否则会造成内存泄漏
		
		PersistenceLayerLiuhx persistenceLayer = (PersistenceLayerLiuhx) ctx.getBean("persistenceLayerLiuhx");
		//返回当前登录的管理员账户信息
		HttpSession session = request.getSession();
		if (session.getAttribute("LoggedInAdmin")==null || session.getAttribute("LoggedInAdmin").toString()=="") {
			response.sendRedirect("../login.jsp"); 
			return;
		}
		String adminInfo = persistenceLayer.getAdminInfoByAccount(session.getAttribute("LoggedInAdmin").toString());
		//String adminInfo = persistenceLayer.getAdminInfoByAccount("Admin");
		response.setContentType("text/plain;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.println(adminInfo);
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	 public void init() throws ServletException {
		 	//在本处获取applicationContext.xml
	        application = getServletContext();
	        ctx = WebApplicationContextUtils.getWebApplicationContext(application);       
	    }

}
