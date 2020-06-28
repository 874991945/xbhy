<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<script>
    function sendEmail() {
        $.ajax({
            url:"/email",
            type:"get",
            data:{"email":$("#email").val()},
            dataType:"text",
            success:function (data) {
                if (data == 1) {
                    alert("发送验证码成功。。。");
                }else {
                    alert("发送验证码失败。。。");
                }
            }
        })
    }
</script>
<body>

<form action="/login/forget" method="post">
    原账号：<input type="text" name="username"><br><br>
    新密码：<input type="text" name="newPs"><br><br>
    邮箱：<input type="text" name="email" id="email"><br><br>
    验证码：<input type="text" name="code"> <input onclick="sendEmail()" type="button" value="发送验证码" class="btn btn-primary"><br><br>
    <input type="submit" value="保存" class="btn btn-success">

</form>
</body>
</html>
