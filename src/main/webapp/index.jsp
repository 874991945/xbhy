<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<style>
    #dengLu{
        background-color: orange;
        width: 178px;
        height: 30px;
    }
    div{
        margin-left:800px;
        margin-top:250px;
    }
    body{
        background-color: green;
    }
</style>
<body>
<div id="div">
    <form action="/login" method="post">
    <input type="text" name="username" placeholder="请输入账号">&nbsp;&nbsp;<a href="/jsp/login/addUserName.jsp" class="btn btn-small">注册账号</a><br><br>
    <input type="text" name="password" placeholder="请输入密码">&nbsp;&nbsp;<a href="/jsp/login/forget.jsp" class="btn btn-small">找回密码</a><br><br>
        <input type="checkbox" name="remember" value="1">自动登录 <br><br>
    <input type="submit" id="dengLu" value="登录" class="btn btn-info">
</form>
</div>

</body>
</html>
