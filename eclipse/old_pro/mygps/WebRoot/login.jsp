<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<% 
	String realPath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	session.setAttribute("contextPath", realPath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录界面</title>
<script type="text/javascript">
<!--
var contextPath = '<%="http://"  +  request.getServerName()  +  ":"  +  request.getServerPort()  +  request.getContextPath()%>';
//-->
</script>
<link href="${sessionScope.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${sessionScope.contextPath}/js/jquery-1.8.3.min.js"></script>
<link rel="icon" href="${sessionScope.contextPath}/images/favicon.ico" mce_href="images/favicon.ico" type="image/x-icon"/>
<link rel="shortcut icon" href="${sessionScope.contextPath}/images/favicon.ico" mce_href="images/favicon.ico" type="image/x-icon"/>
<style type="text/css">
  .error{color:red;border-color:red;text-align: center;}
</style>
</head>
<body>
<div id="login_container">
	<div id="login_bg">
    	<div id="login_text_bg">
    		<div id="login_text">
            	<form  action="servlet/userLogin" method="post"  >
                    <table>
                        <tr>
                            <td>用户名</td>
                           
                        </tr>
                        <tr>
                            <td id="username"><input name="username" type="text" size="24" value="Admin"/></td>
                           
                        </tr>
                        <tr>
                            <td>密码</td>
                            
                        </tr>
                        <tr>
                            <td id="password"><input name="password" type="password" size="24" value=""/></td>
                           
                        </tr>
                        <tr>
                            <td>
                                <input type="checkbox" name="remember-password" /><span>记住密码</span>
                            
                                <input type="checkbox" name="auto-login" /><span>自动登录</span>
                            </td>
                        </tr>
                        <tr>
                            <td >
                               <ul>
                                <li>
                                <a href="#">忘记密码？</a>
                                </li>
                                <li>
                                <input type="submit" value=""/>
                               </li>
                               </ul>
                            </td>
                        </tr>
                    </table>
                    <div class="error">${errorMessage}</div>
            	</form>
    		</div>
    	</div>
	</div>
    <!--footer   版权说明-->
    <div id="login_footer">
		<P>
        	<span>版权所有</span>
            <span><a href="#">浙江理工大学科艺学院</a></span>
            <span><a href="#">GPS校车定位后端管理系统</a></span>
        </P>
   		<p>
        	<span>地址：浙江省杭州市文一西路960号</span>
            <span>邮编：311121</span>
            <span>邮箱：webmaster@zstu.edu.cn</span>
        </p>
    </div>
</div>
</body>
</html>

