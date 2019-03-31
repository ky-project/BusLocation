package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.persistencelayer.PersistenceLayerLiuhx;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class passwordModify extends HttpServlet {

	private ServletContext application;
	private WebApplicationContext ctx;

	/**
	 * Constructor of the object.
	 */
	public passwordModify() {
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
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8"); 
		HttpSession session = request.getSession();
		String username=session.getAttribute("LoggedInAdmin").toString();
		String oldPassword = request.getParameter("_edit_oldpassword");
		String newPassword = request.getParameter("_edit_newpassword");

		//System.out.println(username + "|" + oldPassword + "|" + newPassword);

		PersistenceLayerLiuhx persistenceLayer = (PersistenceLayerLiuhx) ctx
				.getBean("persistenceLayerLiuhx");
		Boolean checkOK = persistenceLayer
				.CheckLoginUser(username, oldPassword);
		response.setContentType("text/plain;charset=utf-8"); // 瀹氫箟杩斿洖鐨勪俊鎭负json鏍煎紡
		PrintWriter out = response.getWriter();

		Map<String, String> s = new HashMap<String, String>();
		Gson gson = new Gson();
		// System.out.println(checkOK);
		if (checkOK) {
			//HttpSession session = request.getSession();
			// session.setAttribute("LoggedInAdmin", username);
			boolean result = persistenceLayer.UpdateUserPassword(username,newPassword);
			if (result) {
				s.put("code", "0");
				s.put("message", "修改密码成功!");
				out.println(gson.toJson(s));

			} else {
				s.put("code", "-1");
				s.put("message", "找不到对应的管理员信息，请重新输入！");
				out.println(gson.toJson(s));
			}

		} else {
			s.put("code", "-1");
			s.put("message", "旧密码输入错误！");
			out.println(gson.toJson(s));
		}
		out.flush();
		out.close();

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		application = getServletContext();
		ctx = WebApplicationContextUtils.getWebApplicationContext(application);
	}
}