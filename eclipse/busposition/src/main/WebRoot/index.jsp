<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>tmp</title>
</head>
<body>
    <h1>普通用户登录</h1>
    <form action="user/login" method="post">
        <input name="workId" type="text"/><br/>
        <input name="password" type="text"/><br/>
        <input value="提交" type="submit"/>
    </form>
    <hr/>
    <h1>管理员登录</h1>
    <form action="user/admin/login" method="post">
        <input name="workId" type="text"/><br/>
        <input name="password" type="text"/><br/>
        <input value="提交" type="submit"/>
    </form>
    <hr/>
    <a href="bus/track">查询所有路线的实时定位</a>
    <hr/>
    <a href="bus/route/station">查询所有路线的站点信息</a>
    <hr/>
    <a href="route/base/info">查询所有路线name和id</a>
    <hr/>
    <h1>根据路线id查询站点</h1>
    <form action="route/station/info" method="post">
        <input name="routeId" type="text"/><br/>
        <input value="提交" type="submit"/>
    </form>
    <hr/>
    <h1>根据路线id查询定位</h1>
    <form action="route/one/position" method="post">
        <input name="routeId" type="text"/><br/>
        <input name="startIndex" type="text"/><br/>
        <input value="提交" type="submit"/>
    </form>

</body>
</html>
