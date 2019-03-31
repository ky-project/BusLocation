<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//判断用户是否已经登录
	//if (session.getAttribute("LoggedInAdmin")==null || session.getAttribute("LoggedInAdmin").toString()=="") {
   	//	response.sendRedirect("login.jsp"); 
   	//	return;
	//}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>gps校车定位管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script>
		var loginUserId='';
		var loginUserName='';
		var loginUserPower='';
	</script>
  </head>
  
<frameset rows="73px,*" cols="*" frameborder="no" border="0" framespacing="0">
	 <frame src="top.html" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
	 <frameset rows="*" cols="175px,*" framespacing="0" frameborder="no" border="0">
	 	<frame src="left.html" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
	    <frame src="main.html" name="mainFrame" id="mainFrame" title="mainFrame" />
	</frameset>
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>
