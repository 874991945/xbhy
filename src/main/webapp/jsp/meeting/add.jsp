<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<%@include file="../common/top.jsp" %>
<%@include file="../common/left.jsp" %>

<div id="right">

    <form action="/meeting/add">
        会议标题：<input type="text" name="title"><br><br>
        会议内容：<textarea name="content"></textarea><br><br>
        开始时间：<input type="datetime-local" name="startTime"><br><br>
        结束时间：<input type="datetime-local" name="endTime"><br><br>

        <input type="submit" value="保存">
        <input type="reset" value="重置">
    </form>

</div>
</body>
</html>
