package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hibernatespring.*;
import com.persistencelayer.PersistenceLayerLiuhx;

public class updateMenu extends HttpServlet {
	
	private ServletContext application;
    private WebApplicationContext ctx;
    
	/**
	 * Constructor of the object.
	 */
	public updateMenu() {
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
		//获取更新的菜单信息
		Integer menuId = Integer.parseInt(request.getParameter("menuId"));
		String menuName = request.getParameter("menuName");
		String menuType = request.getParameter("menuType");
		String menuKeyOrUrl = request.getParameter("menuKeyOrUrl");
		//Long fatherMenuId = Long.parseLong(request.getParameter("fatherMenuId"));
		//在数据库中修改指定的菜单
		Tmenu menu = new Tmenu();
		menu.setMenuId(menuId);
		menu.setMenuName(menuName);
		menu.setMenuType(menuType);
		menu.setMenuKeyOrUrl(menuKeyOrUrl);
		String result = persistenceLayer.updateMenu(menu);
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
		//在本处获取applicationContext.xml
        application = getServletContext();
        ctx = WebApplicationContextUtils.getWebApplicationContext(application);
	}

}
