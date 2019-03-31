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

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zstuky.wechat.pojo.AccessToken;
import org.zstuky.wechat.util.WeixinUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.persistencelayer.PersistenceLayerLiuhx;

public class createMenu extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(createMenu.class);  	
	private ServletContext application;
    private WebApplicationContext ctx;

	/**
	 * Constructor of the object.
	 */
	public createMenu() {
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

	    // 第三方用户唯一凭证  
        String appId = "wx19b2a11e7f6026a4";
		//String appId = "wx1f682771cefc4046";

        // 第三方用户唯一凭证密钥  
        String appSecret = "6834bc3e0364c07cdbe7752961d5ecc9"; 
		//String appSecret = "71c63ba957b2d38f0b3e22f37b17207a";
  
        // 调用接口获取access_token  
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);  
  
        Map<String,Object> reMsg = new HashMap<String,Object>();
        if (null != at) { 
        	PersistenceLayerLiuhx persistenceLayer = (PersistenceLayerLiuhx) ctx.getBean("persistenceLayerLiuhx");
            // 调用接口创建菜单  
            int result = WeixinUtil.createMenu(persistenceLayer.assembleMenu(), at.getToken());
            // 判断菜单创建结果
            if (0 == result) {  
            	log.info("菜单创建成功！");
            	reMsg.put("success", "微信用户的菜单更新成功！");
            }
            else {
            	log.info("菜单创建失败，错误码：" + result);
            	reMsg.put("errorMsg", "微信用户的菜单更新失败，错误码：" + result);
            }
        }
        else {
        	log.info("菜单创建失败!");
        	reMsg.put("errorMsg", "微信用户的菜单更新失败!");
        }
        GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().create();
		response.setContentType("text/plain;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");		
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(reMsg));
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