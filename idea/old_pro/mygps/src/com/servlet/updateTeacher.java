package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

public class updateTeacher extends HttpServlet {

	private ServletContext application;
    private WebApplicationContext ctx;
	/**
	 * Constructor of the object.
	 */
	public updateTeacher() {
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

		request.setCharacterEncoding("UTF-8"); 
		PersistenceLayerLiuhx persistenceLayer = (PersistenceLayerLiuhx) ctx.getBean("persistenceLayerLiuhx");
		//获取选中教师信息
		Integer id = Integer.parseInt(request.getParameter("id"));
		String teacherName = request.getParameter("teacherName");
		String jobNumber=request.getParameter("jobNumber");
		String idToken=request.getParameter("idToken");
		
	//	Long duration = Long.parseLong(request.getParameter("duration"));
		//在数据库中更新对应的数据记录
		Integer gender=Integer.parseInt(request.getParameter("gender"));
		String telephone=request.getParameter("telephone"); 
		String mobile=request.getParameter("mobile");
		String email=request.getParameter("email");
		String department=request.getParameter("department");
		

		String result = persistenceLayer.updateTeacherById(id, department,  teacherName, jobNumber,idToken,gender,telephone,mobile,email);
		//response.setContentType("text/javascript;charset=utf-8");
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
