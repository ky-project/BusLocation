package com.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class downloadFile extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public downloadFile() {
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
        
		//转码链接的中文参数  
        String fileName =new String(request.getParameter("fileName").getBytes("ISO-8859-1"),"utf-8");  
          
        String filePath = request.getServletContext().getRealPath("/") + "/UploadFiles/" + fileName;  
        //System.out.println(filePath);  
        File file = new File(filePath);  
          
        //逆编码,传送文件名  
        response.setHeader("Content-Disposition", "attachment;filename=" +   
                 new String(fileName.getBytes("utf-8"),"ISO-8859-1"));  
          
        response.setHeader("Content-Length", "" + file.length());  
        response.setContentType("application/octet-stream");  
        InputStream inStream = new FileInputStream(file);  
        //写出数据  
        write(inStream,response.getOutputStream());  
        return;  
          
    } 
	
    private static void write(InputStream inStream,OutputStream outStream){  
          
        BufferedInputStream in = new BufferedInputStream(inStream);  
        BufferedOutputStream out = new BufferedOutputStream(outStream);  
        int len =0;  
        byte[] b =new byte[1024];  
        try {  
            while((len=in.read(b))>=0){  
                out.write(b, 0, len);  
                out.flush();  
            }  
        } catch (IOException e) {  
            return ;  
        }  
    }


	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
