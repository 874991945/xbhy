<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<%@include file="../common/top.jsp" %>
<%@include file="../common/left.jsp" %>
<div id="right">

    <a href="/jsp/meeting/add.jsp" class="btn btn-mini">添加</a>
    <form action="/meeting/list" method="post">
        <%--用户名：<input type="text" name="username" value="${username}">--%>
        <%--<input type="submit" value="查询" class="btn btn-info">--%>
    </form>

    <table class="table table-bordered">
        <tr>
            <td>序号</td>
            <td>会议题目</td>
            <td>会议内容</td>
            <td>发布时间</td>
            <td>开始时间</td>
            <td>结束时间</td>
            <td>会议状态</td>
            <td>抄送人</td>
            <td>操作</td>
        </tr>

        <c:forEach var="meeting" items="${list}" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${meeting.title}</td>
                <td>${meeting.content}</td>
                <td>${meeting.publishDate}</td>
                <td>
                    <fmt:formatDate var="startTime" value="${meeting.startTime}"
                                    pattern="yyyy年MM月dd日 HH时mm分ss秒"></fmt:formatDate>
                        ${startTime}
                </td>
                <td>
                    <fmt:formatDate var="endTime" value="${meeting.endTime}"
                                    pattern="yyyy年MM月dd日 HH时mm分ss秒"></fmt:formatDate>
                        ${endTime}
                </td>
                <td>${meeting.status}</td>
                <td>${meeting.makeUser}</td>
                <td><a href="/meeting/toUpdate?id=${meeting.id}" class="btn btn-primary">修改</a>
                    <a href="/meeting/delete?id=${meeting.id}" class="btn btn-danger">删除</a></td>
            </tr>
        </c:forEach>
    </table>

   <%-- 当前页：${page.pageCurrent}
    <a href="/user/list?page=${page.pageCurrent-1>0?page.pageCurrent-1:1}&username=${username}">上一页</a>
    <a href="/user/list?page=${page.pageCurrent+1>=page.pageCount?page.pageCount:page.pageCurrent+1}&username=${username}">下一页</a>
    共${page.pageCount}页<br>
    总记录数：${page.count}--%>
</div>
</body>
</html>
