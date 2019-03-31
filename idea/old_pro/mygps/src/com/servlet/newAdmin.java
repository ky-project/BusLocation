package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.persistencelayer.PersistenceLayerLiuhx;

public class newAdmin extends HttpServlet {

	private ServletContext application;
    private WebApplicationContext ctx;
	/**
	 * Constructor of the object.
	 */
	public newAdmin() {
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

		//ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		PersistenceLayerLiuhx persistenceLayer = (PersistenceLayerLiuhx) ctx.getBean("persistenceLayerLiuhx");
		//获取新建的管理员信息
		request.setCharacterEncoding("UTF-8"); 
		String adminID = request.getParameter("adminID");
		String password=request.getParameter("password");
		String adminName = request.getParameter("adminName");
		String telphone=request.getParameter("telphone");
		String mobile = request.getParameter("mobile");
		String root="普通管理员";
		
		//在数据库中插入新监考
		String result = persistenceLayer.newAdmin(adminID, password,adminName,telphone,mobile,root);
		response.setContentType("text/plain;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");		
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {	
		// Put your code here
				 application = getServletContext();
			        ctx = WebApplicationContextUtils.getWebApplicationContext(application);
	}

}
