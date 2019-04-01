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


public class getReguBus extends HttpServlet {

	private ServletContext application;
    private WebApplicationContext ctx;
	/**
	 * Constructor of the object.
	 */
	public getReguBus() {
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

		int pageNum = (request.getParameter("page")==null)?1:Integer.parseInt(request.getParameter("page"));
	    int pageSize = (request.getParameter("rows")==null)?10:Integer.parseInt(request.getParameter("rows"));
		PersistenceLayerLiuhx persistenceLayer = (PersistenceLayerLiuhx) ctx.getBean("persistenceLayerLiuhx");
		//先返回指定日期内的所有监考记录
		//List<Admin> list;
	//	String groupName = request.getParameter("groupName1");
		//list = persistenceLayer.findAllGroup(groupName);
	//	list = persistenceLayer.findAllGroup();

		
		//根据datagrid控件的参数获取指定页的监考记录数据
		String invList = persistenceLayer.findAllReguBus(pageNum, pageSize);		
		response.setContentType("text/plain;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.println(invList);
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
