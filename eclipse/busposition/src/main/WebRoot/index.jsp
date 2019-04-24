<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>tmp</title>
</head>
<body>
    <form action="user/login" method="post">
        <input name="workId" type="text"/><br/>
        <input name="password" type="text"/><br/>
        <input value="提交" type="submit"/>
    </form>

    <hr/>
    <a href="bus/track">查询所有路线的实时定位</a>
    <hr/>
    <a href="bus/route/station">查询所有路线的站点信息</a>
</body>
</html>
