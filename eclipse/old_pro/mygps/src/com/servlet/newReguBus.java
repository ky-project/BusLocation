package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;

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


public class newReguBus extends HttpServlet {

	private ServletContext application;
    private WebApplicationContext ctx;
	/**
	 * Constructor of the object.
	 */
	public newReguBus() {
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
		//获取新建的校车线路信息
		Integer regId = Integer.parseInt(request.getParameter("regId"));
		Integer busId = Integer.parseInt(request.getParameter("busId"));
		String regStart=request.getParameter("regStart");
		String regEnd=request.getParameter("regEnd");
		
		//SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		//Date date = new Date();  
		//String time = df1.format(date);  

		String regStartTime = request.getParameter("regStartTime");
		Time ts = Time.valueOf(regStartTime);  
		//System.out.println(ts); 

		String regNote = request.getParameter("regNote");
		String regContent = request.getParameter("regContent");
		System.out.println(regContent); 
		
		//在数据库中插入新监考
		String result = persistenceLayer.newReguBus(regId,busId, regStart,regEnd,ts,regNote,regContent);
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
