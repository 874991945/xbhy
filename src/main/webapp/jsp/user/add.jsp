<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<%@include file="../common/top.jsp" %>
<%@include file="../common/left.jsp" %>

<script>
    $(function () {
        $.ajax({
            url:"/dept/list",
            type:"get",
            data:"",
            dataType:"json",
            success:function (data) {
                var html='<option value="-1">请选择</option>';
                for (var i = 0; i < data.length; i++) {
                    html+='<option value="'+data[i].id+'">'+data[i].name+'</option>';
                }
                $("#deptId").append(html);
            }
        })
    })
</script>
<div id="right">

    <form action="/user/add">
        用户名：<input type="text" name="username" value=""><br><br>
        密码：<input type="text" name="password"><br><br>
        邮箱：<input type="text" name="email"><br><br>
        真实姓名：<input type="text" name="realName"><br><br>
        年龄：<input type="text" name="age"><br><br>
        性别：<input type="radio" name="sex" value="1">男
        <input type="radio" name="sex" value="0">女<br><br>
        简介：<textarea name="description"></textarea><br><br>
        部门：<select id="deptId">

    </select><br><br>
        <input type="submit" value="保存">
        <input type="reset" value="重置">
    </form>

</div>
</body>
</html>