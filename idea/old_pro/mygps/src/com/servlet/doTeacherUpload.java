package com.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.hibernatespring.*;
import com.persistencelayer.PersistenceLayerLiuhx;
//import com.myeclipse.servlet.Cell;
//import com.myeclipse.servlet.Sheet;
//import com.myeclipse.servlet.Workbook;


import jxl.*;
import jxl.read.biff.BiffException;

public class doTeacherUpload extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doTeacherUpload() {
		super();
	}

	private ServletContext application;
    private WebApplicationContext ctx;
    
    private String returnString;
	
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
		returnString = "";
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			SmartUpload mySmartUpload = new SmartUpload();
			long timeSet = System.currentTimeMillis();	//生成时间戳，作为文件名
			mySmartUpload.initialize(this.getServletConfig(), request, response);
			String uploadFileName = "";
			mySmartUpload.upload();		
			com.jspsmart.upload.File myFile = mySmartUpload.getFiles().getFile(0);;
			if (!myFile.isMissing()) {				
				uploadFileName = myFile.getFileName();
				int location = uploadFileName.lastIndexOf(".");
				String suffixFilename = uploadFileName.substring(location + 1, uploadFileName.length()-1);
				//uploadFileName = "invigilation" + timeSet + "." + suffixFilename;
				uploadFileName = "teacher" + timeSet + "." + suffixFilename;
				String path = this.getServletContext().getRealPath("/");
				//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				uploadFileName = path + "/UploadFiles/" + uploadFileName;
				myFile.saveAs(uploadFileName);
				Boolean importOk;
				int flag;
				
				try {
					flag=yanzheng(uploadFileName);
					if(flag==0)
					{
					importOk = this.constructInvigilation(uploadFileName);
					if (returnString.equals("")) {
						returnString = "Excel教师信息数据导入成功！";
					}
					else {
						returnString = returnString.substring(0, returnString.length()-1) + "！";
					}
					result.put("success", returnString);
					}
					else result.put("success", "excel中第"+flag+"记录工号已存在");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					result.put("success", "教师信息数据表格式不规范！");
					e.printStackTrace();
				}
				
				
			}
			else {
				result.put("success", "上传文件缺失！");
			}
		} catch (SmartUploadException e) {
			result.put("success", e.getMessage());
		}
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().create();
		response.setContentType("text/plain;charset=utf-8");
	  	response.setHeader("Cache-Control", "no-cache");
	  	PrintWriter out = response.getWriter();
	  	out.println(gson.toJson(result));
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
        
        returnString = "";
	}

	/**
	 * 从Excel文件读出监考课程、时间、时长，并生成数据库记录，同时返回此新记录的ID
	 * @param uploadFileName
	 * @return
	 * @throws ParseException 
	 */
	public Boolean constructInvigilation(String uploadFileName) throws ParseException {		
		try {
			Workbook workbook = Workbook.getWorkbook(new File(uploadFileName));
			int sheetCount = workbook.getNumberOfSheets();
			if (sheetCount==0) {
				return false;
			}
			PersistenceLayerLiuhx persistenceLayer = (PersistenceLayerLiuhx) ctx.getBean("persistenceLayerLiuhx");
			Sheet sheet;
			sheet = workbook.getSheet(0);
			int rows = sheet.getRows();    
			int columns = sheet.getColumns();  
			 for (int i = 1; i <rows; i++) {    
			   boolean hasText = false;    
			  // 过滤掉没有文本内容的行    
		       for (int j = 0; j < columns; j++)    

			     if (sheet.getCell(j, i).getContents().length()!=0) {    
                       hasText = true;    
		               break;    
			      }  
		       if (hasText) {  
		    	   try {
		    		   
		    		 //获取随机验证码  1000-9999
		    			long Temp=Math.round(Math.random()*8999+1000);
		    			//获取新建的监考信息
		    			String teacherName = sheet.getCell(3, i).getContents();
		    			String jobNumber=sheet.getCell(2, i).getContents();
		    			//String idToken=request.getParameter("idToken");
		    			String idToken=Long.toString(Temp);
		    			String departmentname=sheet.getCell(0, i).getContents();
		    			String groupname=sheet.getCell(1, i).getContents();
		    			Integer gender=Integer.parseInt(sheet.getCell(4, i).getContents());
		    			String telephone=sheet.getCell(5, i).getContents(); 
		    			String mobile=sheet.getCell(6, i).getContents();
		    			String email=sheet.getCell(7, i).getContents();
		    			
		    			String result="";
		    			result = persistenceLayer.newTeacher(departmentname,teacherName, jobNumber,idToken,gender,telephone,mobile,email);
		    	   }catch (Exception e) { 
                           e.printStackTrace(); 
                         } 
		    			
		    	   }
		       }
				workbook.close();
				return true;
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
	}
	
	/**
	 * 在数据库中插入指定课程的所有考场监考人员安排情况
	 * @param sheet
	 * @param course
	 * @param invigilationId
	 * @return
	 */
	public int yanzheng(String uploadFileName) throws ParseException {		
		try {
			Workbook workbook = Workbook.getWorkbook(new File(uploadFileName));
//			int sheetCount = workbook.getNumberOfSheets();
//			if (sheetCount==0) {
//				return false;
//			}
			PersistenceLayerLiuhx persistenceLayer = (PersistenceLayerLiuhx) ctx.getBean("persistenceLayerLiuhx");
			Sheet sheet;
			sheet = workbook.getSheet(0);
		int rows = sheet.getRows();    
//		int columns = sheet.getColumns();  
		 for (int i = 1; i <rows; i++) {    
	
		     String jobNum=sheet.getCell(2, i).getContents();  
		     int flag=persistenceLayer.getjobNo(jobNum);
		        if(flag==0)
		        	{workbook.close();
		        	return  i;   
		        	}
		      }	
		 workbook.close();
			return 0;
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}
	}
	
	/**
	 * 获取距离指定日期n天后的日期
	 * @param date
	 * @param day
	 * @return
	 */
	public String getDistanceDay(String date, int day) {	 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		Calendar c = Calendar.getInstance();
		try {
			d = dateFormat.parse(date);
			c.setTime(d);
			c.add(Calendar.DATE, day);
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		return dateFormat.format(c.getTime());
	}
	
	/**
	 * 获取一个工作表中的所有教师列表
	 * @param sheet
	 * @return
	 */
	public List getCourseList(Sheet sheet) {
		List<String> courseList = new ArrayList<String>();
		  // 一共有多少行多少列数据    
		int rows = sheet.getRows();    
		int columns = sheet.getColumns();     
		int rowCount = sheet.getRows();
		
		Cell courseCell;
		String courseName;
		for (int i=2; i<rowCount; i++) {
			courseCell = sheet.getCell(3, i);
			courseName = courseCell.getContents();
			if (courseList.size() == 0) {				
				courseList.add(courseName);
			}
			else if (courseList.indexOf(courseName)==-1) {
				courseList.add(courseName);
			}
		}
		return courseList;
	}

}
