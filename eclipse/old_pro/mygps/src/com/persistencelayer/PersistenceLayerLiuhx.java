package com.persistencelayer;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
//import org.zstuky.wechat.pojo.Button;
//import org.zstuky.wechat.pojo.CommonButton;
//import org.zstuky.wechat.pojo.ComplexButton;
//import org.zstuky.wechat.pojo.Menu;
//import org.zstuky.wechat.pojo.ViewButton;


import org.zstuky.wechat.pojo.Button;
import org.zstuky.wechat.pojo.CommonButton;
import org.zstuky.wechat.pojo.ComplexButton;
import org.zstuky.wechat.pojo.Menu;
import org.zstuky.wechat.pojo.ViewButton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hibernatespring.*;




public class PersistenceLayerLiuhx {

	private AdminDAO adminDAO;
	private BusDAO busDAO;
	private GpsDAO gpsDAO;
	private TmenuDAO menuDAO;
	private RegubusDAO regubusDAO;
	private StationDAO stationDAO;
	private TeacherDAO teacherDAO;
	private NewsDAO newsDAO;
	
	
	public NewsDAO getNewsDAO() {
		return newsDAO;
	}

	public void setNewsDAO(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}

	public AdminDAO getAdminDAO() {
		return adminDAO;
	}

	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}

	public BusDAO getBusDAO() {
		return busDAO;
	}

	public void setBusDAO(BusDAO busDAO) {
		this.busDAO = busDAO;
	}

	public GpsDAO getGpsDAO() {
		return gpsDAO;
	}

	public void setGpsDAO(GpsDAO gpsDAO) {
		this.gpsDAO = gpsDAO;
	}

	public TmenuDAO getMenuDAO() {
		return menuDAO;
	}

	public void setMenuDAO(TmenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	public RegubusDAO getRegubusDAO() {
		return regubusDAO;
	}

	public void setRegubusDAO(RegubusDAO regubusDAO) {
		this.regubusDAO = regubusDAO;
	}

	public StationDAO getStationDAO() {
		return stationDAO;
	}

	public void setStationDAO(StationDAO stationDAO) {
		this.stationDAO = stationDAO;
	}

	public TeacherDAO getTeacherDAO() {
		return teacherDAO;
	}

	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}


	/**
  	 * 检验登录用户的输入用户名和密码
  	 * @param userName
  	 * @param userPassword
  	 * @return
  	 */
  	public Boolean CheckLoginUser(String userName, String userPassword) {
  		List<Admin> listAdmin = adminDAO.findByAdminId(userName);
  		if (listAdmin==null || listAdmin.size()==0) {
  			return false;
  		}
  		Admin admin = listAdmin.get(0);
  		if (admin.getPassword().equals(userPassword)) {
  			return true;
  		}
  		else {
  			return false;
  		}
  	}
  	
  	
  //根据管理员账户ID查询此管理员的账户信息
  	public String getAdminInfoByAccount(String account) {
  		List<Admin> listAdmin = adminDAO.findByAdminId(account);
  	
  		GsonBuilder builder = new GsonBuilder();
  		Gson gson = builder.serializeNulls().create();
  		if(listAdmin==null) {
  			Map<String,Object> result = new HashMap<String,Object>();
  			result.put("errorMsg", "No administrator accounts.");
  			return gson.toJson(result);
  		}
  		else {
  			return gson.toJson(listAdmin);
  		}
  	}

  	/**
  	 * 获取在主页显示的新闻列表
  	 * @param newsCount
  	 * @return
  	 */
  	public String InitMainPageNewsList(Integer newsCount) {
  		List<News> listService = newsDAO.findAll();
  		List<News> listReturn = new ArrayList<News>();
  		for (News vTmp: listService) {
  			 {
  				listReturn.add(vTmp);
  				if (listReturn.size()>=newsCount) break;
  			}
  		}
  		GsonBuilder builder = new GsonBuilder();
  		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
  		Gson gson = builder.serializeNulls().create();
  		return gson.toJson(listReturn);
  	}
  	
	/**
  	 * 根据服务ID，获取新闻的具体信息
  	 * @param serviceId
  	 * @return
  	 */
  	public String NewsGetObjectById(Integer serviceId) {
  		News theService = newsDAO.findById(serviceId);  		//List<TService> listService = new ArrayList<TService>();
  		//listService.add(theService);
  		GsonBuilder builder = new GsonBuilder();
  		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
  		Gson gson = builder.serializeNulls().create();
  		//return gson.toJson(listService);
  		return gson.toJson(theService);
  	}

  	//修改管理员密码
  	public boolean UpdateUserPassword(String userName, String userPassword) {
		Map<String,Object> result = new HashMap<String,Object>();
		//System.out.println("111");
		try {
			List<Admin> listAdmin = adminDAO.findByAdminId(userName);
			Admin admin = listAdmin.get(0);
			admin.setPassword(userPassword);
			System.out.println("111");
			adminDAO.merge(admin);
			result.put("success", true);
			return true;
		}
		catch(RuntimeException e){
			result.put("errorMsg", e.getMessage()); 
			return false;
		}
		
	}
	  
  	 //根据查询内容获取教师列表
	public String findTeacherByNeirong(String leibie,String neirong,int page, int rows) {
		List listTeacherDo;
		listTeacherDo=teacherDAO.findTeacher();
		if(page==0) page=1;
		if(neirong.equalsIgnoreCase("all"))
		{
			listTeacherDo=teacherDAO.findTeacher();
			//System.out.print(111);
		}
		else
		{
		if(leibie.equalsIgnoreCase("0"))
		{
			listTeacherDo=teacherDAO.findByJobNumber("%"+neirong+"%");
			//System.out.print(leibie);
		}
    	
		else if(leibie.equalsIgnoreCase("1"))
		{
			listTeacherDo=teacherDAO.findByTeaName("%"+neirong+"%");
			//System.out.print(222);
		}
		else 
		{
			listTeacherDo=teacherDAO.findByDepartment("%"+neirong+"%");
			//System.out.print(333);
		}
		}

		String queryString="select TeacherID,JobNumber,TeaName,Mobile,E_Mail,ID_Token,Department,Gender,Telphone from teacher";			
		List<Map<String,String>> jsonInvigilationDo = new ArrayList<Map<String,String>>();
		for(int i=0; i<listTeacherDo.size(); i++) {
			Object[] obj = (Object[])listTeacherDo.get(i);
			Map<String, String> mapInvigilationDo = new HashMap<String, String>();
			mapInvigilationDo.put("teacherId", obj[0].toString());	
			mapInvigilationDo.put("jobNumber", obj[1].toString());	
			mapInvigilationDo.put("teaName", obj[2].toString());
			mapInvigilationDo.put("mobile", obj[3].toString());
			mapInvigilationDo.put("EMail", obj[4].toString());
			mapInvigilationDo.put("idToken", obj[5].toString());
			mapInvigilationDo.put("department", obj[6].toString());
			mapInvigilationDo.put("gender", obj[7].toString());
			mapInvigilationDo.put("telphone", obj[8].toString());
			jsonInvigilationDo.add(mapInvigilationDo);
		}
		
		List currentPage = new ArrayList<Teacher>();
		for (int i=0; i<rows; i++) {
		int j = (page - 1) * rows + i;
		if (j >= jsonInvigilationDo.size()) {
			break;	//当前页的记录数不足rows条记录
		}
		else {
			currentPage.add(jsonInvigilationDo.get(j));
		}
	}
		//以JSon的数据返回，datagrid要实现分页功能的话，要求返回total参数和rows参数，rows是具体的记录数据
		Gson gson = new GsonBuilder().serializeNulls().create();
		Map<String,Object> result = new HashMap<String,Object>();
		if(listTeacherDo.size()==0) {
			//result.put("errorMsg", "No listTeacherDo.");
			result.put("total", 0);
			result.put("rows", currentPage);
			
		}
		else {
			result.put("total", listTeacherDo.size());
			result.put("rows", currentPage);
		
		}
		return gson.toJson(result);
		
		
		
	}
	  //验证工号是否存在
    public int getjobNo(String jobNumber) {
		
    	int flag=1;	
    	List<Teacher> listTeacer=teacherDAO.findByJobNumber(jobNumber);
        if(listTeacer.size()>0)
        {
        	flag=0;
        	
        }
		return flag;
		
    }
    
  //添加一个新的教师信息
  	public String newTeacher(String department,String teacherName, String jobNumber, String idToken, Integer gender, String telephone, String mobile, String email) {
  		Teacher teacher = new Teacher();
  		teacher.setDepartment(department);
  		teacher.setEMail(email);
  		teacher.setTeaName(teacherName);
  		teacher.setJobNumber(jobNumber);
  		teacher.setIdToken(idToken);
  		teacher.setMobile(mobile);
  		teacher.setGender(gender);
  		teacher.setTelphone(telephone);

  		teacherDAO.save(teacher);
  		Map<String,Object> result = new HashMap<String,Object>();
  		result.put("success", true);	//result.put("errorMsg", false);
  		GsonBuilder builder = new GsonBuilder();
  		Gson gson = builder.serializeNulls().create();
  		return gson.toJson(result);
  	}
  	
  //更新一个教师信息
  	public String updateTeacherById(Integer id,String dep, String teacherName, String jobNumber, String idToken, Integer gender, String telephone, String mobile, String email ) {
  		Teacher teacher = teacherDAO.findById(id);
  		teacher.setTeaName(teacherName);
  		teacher.setJobNumber(jobNumber);
  		teacher.setIdToken(idToken);
  		teacher.setEMail(email);
  		teacher.setGender(gender);
  		teacher.setMobile(mobile);
  		teacher.setDepartment(dep);
  		teacher.setTelphone(telephone);
  		teacherDAO.merge(teacher);
  		//invigilation.setTInvigilationdos(null);
  		//List<TInvigilation> list = new ArrayList<TInvigilation>();
  		//list.add(invigilation);
  		Map<String,Object> result = new HashMap<String,Object>();
  		result.put("success", true);
  		//result.put("errorMsg", false);
  		GsonBuilder builder = new GsonBuilder();
  		Gson gson = builder.serializeNulls().create();
  		return gson.toJson(result);
  	}
  	
  //根据id删除一组教师信息
  	public String deleteTeacherByIds(String ids) {
  		String[] teacherIds = ids.split(",");
  		for(String id: teacherIds) {
  			Teacher teacher = teacherDAO.findById(Integer.parseInt(id));
  			teacherDAO.delete(teacher);
  		}
  		//删除成功后用JSon返回succes信息
  		Map<String,Object> result = new HashMap<String,Object>();
  		result.put("success", true);
  		GsonBuilder builder = new GsonBuilder();
  		Gson gson = builder.serializeNulls().create();
  		return gson.toJson(result);
  	}
  	
  //验证工号是否存在
    public String getNo(String jobNumber) {
		
    	int flag=1;	
    	List<Teacher> listTeacer=teacherDAO.findByJobNumber(jobNumber);
        if(listTeacer.size()>0)
        {
        	flag=0;
        	//Map<String,Object> result = new HashMap<String,Object>();
        	String result="该教师工号已存在，请检查！";
    		//result.put("success", true);
    		GsonBuilder builder = new GsonBuilder();
    		Gson gson = builder.serializeNulls().create();
    		return gson.toJson(result);
        }
        else 
        {
        	//Map<String,Object> result = new HashMap<String,Object>();
    		//result.put("false", true);
        	String result="请放心添加";
    		GsonBuilder builder = new GsonBuilder();
    		Gson gson = builder.serializeNulls().create();
    		return gson.toJson(result);
        }
		//return flag;
		
    }
    
    /**
	 * 根据教师姓名获取此教工的信息，如果找到多个，只返回找到的第一个
	 * @param teacherName
	 * @return
	 */
	public Teacher findTeacherByName(String teacherName) {
		List<Teacher> listTeacher = teacherDAO.findByTeaName(teacherName);
		if (listTeacher==null || listTeacher.size()==0) {
			return null;
		}
		else {
			return listTeacher.get(0);
		}
	}
	
	//添加一个新的管理员信息
	public String newAdmin(String adminId, String password,String adminName,String telphone,String mobile,String root) {
			Admin admin = new Admin();
			admin.setAdminId(adminId);
			admin.setAdminName(adminName);
			admin.setMobile(mobile);
			admin.setPassword(password);
			admin.setRoot(root);
			admin.setTelphone(telphone);
			
			adminDAO.save(admin);
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("success", true);	//result.put("errorMsg", false);
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.serializeNulls().create();
			return gson.toJson(result);
		}
	
	//更新一个管理员信息
	public String updateAdminById(Integer id, String adminId, String password,String adminName,String telphone,String mobile,String root) {
	    	Admin admin = adminDAO.findById(id);
		    //admin.setAdminId(adminId);
		    admin.setAdminName(adminName);
		    admin.setMobile(mobile);
		    admin.setPassword(password);
		    admin.setRoot(root);
		    admin.setTelphone(telphone);
			adminDAO.merge(admin);
		
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("success", true);
			//result.put("errorMsg", false);
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.serializeNulls().create();
			return gson.toJson(result);
	  }
	//根据id删除管理员信息
	public String deleteAdminById(Integer id) {
		Admin admin = adminDAO.findById(id);
	    adminDAO.delete(admin);
		//删除成功后用JSon返回succes信息
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", true);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().create();
		return gson.toJson(result);
	  }
		
	//获得所有管理员信息
    public String findAllAdmin( int page, int rows) {
			    
		List<Admin> list;
	    list = adminDAO.findAll();
			 
	    List<Admin> currentPage = new ArrayList<Admin>();
		//对list按rows条记录来进行分页，并获取第page页的记录
		for (int i=0; i<rows; i++) {
			int j = (page - 1) * rows + i;
			if (j >= list.size()) {
				break;	//当前页的记录数不足rows条记录
			}
			else {
				currentPage.add(list.get(j));
			}
		}
		//以JSon的数据返回，datagrid要实现分页功能的话，要求返回total参数和rows参数，rows是具体的记录数据
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("total", list.size());
		result.put("rows", currentPage);
		GsonBuilder builder = new GsonBuilder();			
		Gson gson = builder.serializeNulls().create();		
		String invList = gson.toJson(result);

		return invList;
	}
		 
	//添加一个新的校车信息
	public String newBus(Integer busId, String type,Integer seatSum,String license,String driverName,String driverTel) {
		Bus bus = new Bus();
		bus.setBusId(busId);
	    bus.setDriverName(driverName);
		bus.setDriverTel(driverTel);
		bus.setLicense(license);
		bus.setSeatSum(seatSum);
	    bus.setType(type);
					
	    busDAO.save(bus);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", true);	//result.put("errorMsg", false);
	    GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().create();
		return gson.toJson(result);
	}
			
	//更新一个校车信息
	public String updateBusById(Integer busId, String type,Integer seatSum,String license,String driverName,String driverTel) {
		Bus bus = busDAO.findById(busId);
		bus.setDriverName(driverName);
		bus.setDriverTel(driverTel);
		bus.setLicense(license);
		bus.setSeatSum(seatSum);
		bus.setType(type);
		busDAO.merge(bus);
				
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", true);
		//result.put("errorMsg", false);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().create();
		return gson.toJson(result);
	}
    //根据id删除校车信息
    public String deleteBusById(Integer id) {
	    Bus bus = busDAO.findById(id);
	    busDAO.delete(bus);
	    //删除成功后用JSon返回succes信息
	    Map<String,Object> result = new HashMap<String,Object>();
	    result.put("success", true);
	    GsonBuilder builder = new GsonBuilder();
	    Gson gson = builder.serializeNulls().create();
	    return gson.toJson(result);
    }
    
    //获取视图中的教师列表信息
   	public List<Bus> findBus() {
   		List<Bus> list;
   		//list = vteacherDAO.findTeacher();
   		//list=vteacherDAO.findAll();
   		list=busDAO.findAll();
   		
   		return list;
   	}

	//获取所有校车信息
	public String findAllBus( int page, int rows) {
					    
	    List<Bus> list;
	    list = busDAO.findAll();
					 
	    List<Bus> currentPage = new ArrayList<Bus>();
	    //对list按rows条记录来进行分页，并获取第page页的记录
	    for (int i=0; i<rows; i++) {
		  int j = (page - 1) * rows + i;
		  if (j >= list.size()) {
			  break;	//当前页的记录数不足rows条记录
		  }
		  else {
			  currentPage.add(list.get(j));
		  }
	    }
	  //以JSon的数据返回，datagrid要实现分页功能的话，要求返回total参数和rows参数，rows是具体的记录数据
	  Map<String,Object> result = new HashMap<String,Object>();
	  result.put("total", list.size());
	  result.put("rows", currentPage);
	  GsonBuilder builder = new GsonBuilder();			
	  Gson gson = builder.serializeNulls().create();		
	  String invList = gson.toJson(result);

	  return invList;
    }
	
	
	//添加一个新的站点信息
	public String newStation(Integer staId, String staName,Float staLati,Float staLongi) {
		Station station = new Station();
		station.setStaId(staId);
		station.setStaName(staName);
		station.setStaLati(staLati);
		station.setStaLongi(staLongi);
					
		stationDAO.save(station);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", true);	//result.put("errorMsg", false);
	    GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().create();
		return gson.toJson(result);
	}
			
	//更新一个站点信息
	public String updateStationById(Integer staId, String staName,Float staLati,Float staLongi) {
		Station station = stationDAO.findById(staId);
		station.setStaName(staName);
		station.setStaLati(staLati);
		station.setStaLongi(staLongi);
		stationDAO.merge(station);
				
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", true);
		//result.put("errorMsg", false);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().create();
		return gson.toJson(result);
	}
    //根据id删除站点信息
    public String deleteStationById(Integer id) {
    	Station station = stationDAO.findById(id);
    	stationDAO.delete(station);
	    //删除成功后用JSon返回succes信息
	    Map<String,Object> result = new HashMap<String,Object>();
	    result.put("success", true);
	    GsonBuilder builder = new GsonBuilder();
	    Gson gson = builder.serializeNulls().create();
	    return gson.toJson(result);
    }
	//获取所有校车站点信息
	public String findAllStation( int page, int rows) {
					    
	    List<Station> list;
	    list = stationDAO.findAll();
					 
	    List<Station> currentPage = new ArrayList<Station>();
	    //对list按rows条记录来进行分页，并获取第page页的记录
	    for (int i=0; i<rows; i++) {
		  int j = (page - 1) * rows + i;
		  if (j >= list.size()) {
			  break;	//当前页的记录数不足rows条记录
		  }
		  else {
			  currentPage.add(list.get(j));
		  }
	    }
	  //以JSon的数据返回，datagrid要实现分页功能的话，要求返回total参数和rows参数，rows是具体的记录数据
	  Map<String,Object> result = new HashMap<String,Object>();
	  result.put("total", list.size());
	  result.put("rows", currentPage);
	  GsonBuilder builder = new GsonBuilder();			
	  Gson gson = builder.serializeNulls().create();		
	  String invList = gson.toJson(result);

	  return invList;
    }	
	
	
	//添加一个新的线路信息
		public String newReguBus(Integer regId, Integer busId,  String regStart,String regEnd,Time ts,String regNote,String regContent) {
			Regubus regubus = new Regubus();
			regubus.setRegId(regId);
			regubus.setBusId(busId);
			regubus.setRegContent(regContent);
			regubus.setRegStart(regStart);
			regubus.setRegEnd(regEnd);
			regubus.setRegNote(regNote);
			regubus.setRegStartTime(ts);
						
			regubusDAO.save(regubus);
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("success", true);	//result.put("errorMsg", false);
		    GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.serializeNulls().create();
			return gson.toJson(result);
		}
				
		//更新一个线路信息
		public String updateReguBusById(Integer regId, Integer busId,  String regStart,String regEnd,Time ts,String regNote,String regContent) {
			Regubus regubus = regubusDAO.findById(regId);
			regubus.setBusId(busId);
			regubus.setRegContent(regContent);
			regubus.setRegStart(regStart);
			regubus.setRegEnd(regEnd);
			regubus.setRegNote(regNote);
			regubus.setRegStartTime(ts);
			regubusDAO.merge(regubus);
					
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("success", true);
			//result.put("errorMsg", false);
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.serializeNulls().create();
			return gson.toJson(result);
		}
	    //根据id删除线路信息
	    public String deleteReguBusById(Integer id) {
	    	Regubus regubus = regubusDAO.findById(id);
	    	regubusDAO.delete(regubus);
		    //删除成功后用JSon返回succes信息
		    Map<String,Object> result = new HashMap<String,Object>();
		    result.put("success", true);
		    GsonBuilder builder = new GsonBuilder();
		    Gson gson = builder.serializeNulls().create();
		    return gson.toJson(result);
	    }
		//获取所有线路信息
		public String findAllReguBus( int page, int rows) {
						    
		    List<Regubus> list;	
		    list =regubusDAO.findAll();
						 
		    List<Regubus> currentPage = new ArrayList<Regubus>();
		    //对list按rows条记录来进行分页，并获取第page页的记录
		    for (int i=0; i<rows; i++) {
			  int j = (page - 1) * rows + i;
			  if (j >= list.size()) {
				  break;	//当前页的记录数不足rows条记录
			  }
			  else {
				  currentPage.add(list.get(j));
			  }
		    }
		  //以JSon的数据返回，datagrid要实现分页功能的话，要求返回total参数和rows参数，rows是具体的记录数据
		  Map<String,Object> result = new HashMap<String,Object>();
		  result.put("total", list.size());
		  result.put("rows", currentPage);
		  GsonBuilder builder = new GsonBuilder();			
		  Gson gson = builder.serializeNulls().create();		
		  String invList = gson.toJson(result);

		  return invList;
	    }
		

		//添加一个新的新闻信息
		public String newNews(String newTitle, String newContent,String faPeople,Timestamp faTime) {
				News news = new News();
				news.setNewTitle(newTitle);
				news.setNewContent(newContent);
				news.setFaPeople(faPeople);
				news.setFaTime(faTime);
								
				newsDAO.save(news);
				Map<String,Object> result = new HashMap<String,Object>();
				result.put("success", true);	//result.put("errorMsg", false);
				GsonBuilder builder = new GsonBuilder();
				Gson gson = builder.serializeNulls().create();
				return gson.toJson(result);
			}
		
		//更新一条新闻信息
		public String updateNewsById(Integer id, String newTitle, String newContent,String faPeople,Timestamp faTime) {
		    	News news = newsDAO.findById(id);
		    	news.setNewTitle(newTitle);
				news.setNewContent(newContent);
				news.setFaPeople(faPeople);
				news.setFaTime(faTime);
			
				newsDAO.merge(news);
			
				Map<String,Object> result = new HashMap<String,Object>();
				result.put("success", true);
				//result.put("errorMsg", false);
				GsonBuilder builder = new GsonBuilder();
				Gson gson = builder.serializeNulls().create();
				return gson.toJson(result);
		  }
		//根据id删除新闻信息
		public String deleteNewsById(Integer id) {
			News news = newsDAO.findById(id);
		    newsDAO.delete(news);
			//删除成功后用JSon返回succes信息
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("success", true);
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.serializeNulls().create();
			return gson.toJson(result);
		  }
			
		//获得所有新闻信息
	    public String findAllNews( int page, int rows) {
				    
			List<News> list;
		    list = newsDAO.findAll();
				 
		    List<News> currentPage = new ArrayList<News>();
			//对list按rows条记录来进行分页，并获取第page页的记录
			for (int i=0; i<rows; i++) {
				int j = (page - 1) * rows + i;
				if (j >= list.size()) {
					break;	//当前页的记录数不足rows条记录
				}
				else {
					currentPage.add(list.get(j));
				}
			}
			//以JSon的数据返回，datagrid要实现分页功能的话，要求返回total参数和rows参数，rows是具体的记录数据
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("total", list.size());
			result.put("rows", currentPage);
			GsonBuilder builder = new GsonBuilder();			
			Gson gson = builder.serializeNulls().create();		
			String invList = gson.toJson(result);

			return invList;
		}		
	    /**
		 * 组装菜单，并返回
		 * 
		 * @2015-02-01
		 */
		public Menu assembleMenu() {
			List<Tmenu> listMenu = menuDAO.findAll();
			List<ComplexButton> mainMenuList = new ArrayList<ComplexButton>();
			Menu weChatMenu = new Menu();
			if (listMenu.size() == 0) return null;
			//遍历数据库表记录，先找到父菜单
			for(Tmenu vTmp1: listMenu) {
				//FatherMenuId等于空，说明它是父菜单
				if (vTmp1.getFatherMenuId()==null || vTmp1.getFatherMenuId()==0) {
					ComplexButton tmpMainMenu = new ComplexButton();
					tmpMainMenu.setName(vTmp1.getMenuName());
					List<Button> subMenuList = new ArrayList<Button>();
					//遍历数据库表记录，查找当前主菜单的所有子菜单
					for(Tmenu vTmp2: listMenu) {
						if (vTmp2.getFatherMenuId() == vTmp1.getMenuId()) {
							if (vTmp2.getMenuType().toLowerCase().equals("click")) {
								CommonButton tmpSubMenu1 = new CommonButton();
								tmpSubMenu1.setName(vTmp2.getMenuName());
								tmpSubMenu1.setType(vTmp2.getMenuType().toLowerCase());
								tmpSubMenu1.setKey(vTmp2.getMenuKeyOrUrl());
								subMenuList.add(tmpSubMenu1);
							}
							else if (vTmp2.getMenuType().toLowerCase().equals("view")) {
								ViewButton tmpSubMenu2 = new ViewButton();
								tmpSubMenu2.setName(vTmp2.getMenuName());
								tmpSubMenu2.setType(vTmp2.getMenuType().toLowerCase());
								tmpSubMenu2.setUrl(vTmp2.getMenuKeyOrUrl());
								subMenuList.add(tmpSubMenu2);	
							}
						}					
					}
					Button[] subMenu = new Button[subMenuList.size()];
					for (int i=0; i<subMenuList.size(); i++) {
						subMenu[i] = subMenuList.get(i);
					}
					tmpMainMenu.setSub_button(subMenu);
					mainMenuList.add(tmpMainMenu);
				}
			}
			ComplexButton[] mainMenu = new ComplexButton[mainMenuList.size()];
			for (int i=0; i<mainMenuList.size(); i++) {
				mainMenu[i] = mainMenuList.get(i);
			}
			weChatMenu.setButton(mainMenu);
			return weChatMenu;
		}
		
		
		
		/**
		 * 获取菜单列表
		 * @param menuLevel
		 * @param fatherId
		 * @return
		 */
		 public String findMenuList(String menuLevel, String fatherId) {
		    List<Tmenu> listMenu = menuDAO.findAll();
			List<Map<String,String>> jsonMenu = new ArrayList<Map<String,String>>();
			for(Tmenu vMenu: listMenu) {
				if (menuLevel.equalsIgnoreCase("main")) {
					if (vMenu.getFatherMenuId()==null || vMenu.getFatherMenuId()==0) {
						Map<String, String> mapMenu = new HashMap<String, String>();
						mapMenu.put("menuId", String.valueOf(vMenu.getMenuId()));	
						mapMenu.put("menuName", vMenu.getMenuName());	
						mapMenu.put("menuType", vMenu.getMenuType());
						mapMenu.put("menuKeyOrUrl", vMenu.getMenuKeyOrUrl());
						mapMenu.put("fatherMenuId", "");
						jsonMenu.add(mapMenu);
						if (jsonMenu.size()>=3) break;	//一级菜单最多只能3个
					}
				}
				else if (menuLevel.equalsIgnoreCase("sub")) {
					if (vMenu.getFatherMenuId()!=null && String.valueOf(vMenu.getFatherMenuId()).equalsIgnoreCase(fatherId)) {
						Map<String, String> mapMenu = new HashMap<String, String>();
						mapMenu.put("menuId", String.valueOf(vMenu.getMenuId()));	
						mapMenu.put("menuName", vMenu.getMenuName());	
						mapMenu.put("menuType", vMenu.getMenuType());
						mapMenu.put("menuKeyOrUrl", vMenu.getMenuKeyOrUrl());
						mapMenu.put("fatherMenuId", String.valueOf(vMenu.getFatherMenuId()));
						jsonMenu.add(mapMenu);
						if (jsonMenu.size()>=5) break;	//二级菜单最多只能5个
					}					
				}
			}
			Gson gson = new GsonBuilder()
			//.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
			.serializeNulls().create();
			Map<String,Object> result = new HashMap<String,Object>();
			if(jsonMenu.size()==0) {
				result.put("errorMsg", "No InvigilationDo.");
				//return gson.toJson(result);
			}
			else {
				result.put("total", jsonMenu.size());
				result.put("rows", jsonMenu);
				//return gson.toJson(jsonInvigilationDo);
			}
			return gson.toJson(result);
		}
		  
		  /**
		   * 在数据库中插入一个新的菜单
		   * @param menu
		   * @return
		   */
		  public String newMenu(Tmenu menu) {
			  Map<String,Object> result = new HashMap<String,Object>();
			  try {
				  menuDAO.save(menu);
				  result.put("success", true);
			  }
			  catch(RuntimeException e){
				  result.put("errorMsg", e.getMessage()); 
			  }
			  GsonBuilder builder = new GsonBuilder();
			  Gson gson = builder.serializeNulls().create();
			  return gson.toJson(result);
		  }
		  
		  /**
		   * 更新数据库中的菜单
		   * @param menu
		   * @return
		   */
		  public String updateMenu(Tmenu menu) {
			  Map<String,Object> result = new HashMap<String,Object>();
			  try {
				  Tmenu tmpMenu = menuDAO.findById(menu.getMenuId());
				  menu.setFatherMenuId(tmpMenu.getFatherMenuId());
				  menuDAO.merge(menu);
				  result.put("success", true);
			  }
			  catch(RuntimeException e){
				  result.put("errorMsg", e.getMessage()); 
			  }
			  GsonBuilder builder = new GsonBuilder();
			  Gson gson = builder.serializeNulls().create();
			  return gson.toJson(result);
		  }
		  
		  /**
		   * 根据ID删除某个菜单
		   * @param menuId
		   * @return
		   */
		  public String deleteMenu(Integer menuId) {
			  Map<String,Object> result = new HashMap<String,Object>();
			  try {
				  Tmenu menu = menuDAO.findById(menuId);
				  menuDAO.delete(menu);
				  result.put("success", true);
			  }
			  catch(RuntimeException e){
				  result.put("errorMsg", e.getMessage()); 
			  }
			  GsonBuilder builder = new GsonBuilder();
			  Gson gson = builder.serializeNulls().create();
			  return gson.toJson(result);
		  }
		  
		  
		  //以下是微信部分的操作
		  /**
			 * 根据微信的OpenID搜索是否已经绑定工号
			 */
			public int getWeChatOpenIdFlag(String weChatOpenId) {
			
					List<Teacher> listTeacher = teacherDAO.findAll();

					int flag=0;
					for(int i=0; i<listTeacher.size(); i++) {
					if (listTeacher.get(i).getWeChatOpenId().equalsIgnoreCase(weChatOpenId)) {
						flag=1;
					}
					
				} 
					return flag;
			}
			
			/**
			 * 根据微信的OpenID获取教师的工号
			 * @param weChatOpenId
			 * @return
			 */
			public String getJobNumberByOpenID(String weChatOpenId) {
				try {
					List<Teacher> listTeacher = teacherDAO.findByWeChatOpenId(weChatOpenId);
					if (listTeacher==null || listTeacher.size()==0) {
						return "";
					}
					else {
						return listTeacher.get(0).getJobNumber();
					}
				} catch (RuntimeException re) {
					return "";
				}
			}
			
			//验证工号和确认码
	       public int testJobNumber(String weChatOpenId, String content) {
			int flag=0;	
	    	
	    	String[] sourceStrArray = content.split(",");
	    	List<Teacher> listTeacer=teacherDAO.findByJobNumberAndToken(sourceStrArray[0],sourceStrArray[1]);
	    	if(listTeacer.size()>0)
	    	{
	    	    Integer id=listTeacer.get(0).getTeacherId();
	       	    Teacher teacher=teacherDAO.findById(id);
	       	    teacher.setWeChatOpenId(weChatOpenId);
	       	    teacherDAO.merge(teacher);
	       	
	         	Map<String,Object> result = new HashMap<String,Object>();
				result.put("success", true);
			
				GsonBuilder builder = new GsonBuilder();
				Gson gson = builder.serializeNulls().create();
				gson.toJson(result);
				flag=1;
	    	}
	    	
				return flag;
		    }
	       
	       //更改手机号码
			public int updateMobile(String weChatOpenId,String mobile) {
				
				int flag=0;	
				List<Teacher> listTeacer=teacherDAO.findByWeChatOpenId(weChatOpenId);
				
	            if(listTeacer.size()>0)
	            {
				    Integer id=listTeacer.get(0).getTeacherId();
	        	    Teacher teacher=teacherDAO.findById(id);
	        	    teacher.setMobile(mobile);
	        	    teacherDAO.merge(teacher);
	        	
	        	    Map<String,Object> result = new HashMap<String,Object>();
				    result.put("success", true);
			
				    GsonBuilder builder = new GsonBuilder();
				    Gson gson = builder.serializeNulls().create();
				    gson.toJson(result);
				    flag=1;
	            }
				return flag;
		    }
			
			//更改办公电话
	        public int updateTelphone(String weChatOpenId,String telephone) {
				
	        	int flag=0;	
	        	List<Teacher> listTeacer=teacherDAO.findByWeChatOpenId(weChatOpenId);
	            if(listTeacer.size()>0)
	            {
	        	  Integer id=listTeacer.get(0).getTeacherId();
	        	  Teacher teacher=teacherDAO.findById(id);
	        	  teacher.setTelphone(telephone);
	        	  teacherDAO.merge(teacher);
	        	
	        	  Map<String,Object> result = new HashMap<String,Object>();
				  result.put("success", true);
			
				  GsonBuilder builder = new GsonBuilder();
				  Gson gson = builder.serializeNulls().create();
				  gson.toJson(result);
				  flag=1;
	            }
				return flag;
				
		    }
	      //获得当前办公电话
	        public String getTelephone(String weChatOpenId) {
				
	        	String telephone="";	
	        	List<Teacher> listTeacer=teacherDAO.findByWeChatOpenId(weChatOpenId);
	            if(listTeacer.size()>0)
	            {
	            	telephone=listTeacer.get(0).getTelphone();
	            }
				return telephone;
				
		    }
	      //获得当前手机号码
	        public String getMobile(String weChatOpenId) {
				
	        	String telephone="";	
	        	List<Teacher> listTeacer=teacherDAO.findByWeChatOpenId(weChatOpenId);
	            if(listTeacer.size()>0)
	            {
	            	telephone=listTeacer.get(0).getMobile();
	            }
				return telephone;
				
		    }
}
