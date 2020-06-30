<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<%@include file="../common/top.jsp" %>
<%@include file="../common/left.jsp" %>

<div id="right">
        会议标题：${meeting.title}<br><br>
        会议内容：${meeting.content}<br><br>
        发布时间：${meeting.publishDate}<br><br>
        会议状态：${meeting.status}<br><br>
        抄送人：${meeting.makeUser}
        <%--部门：<select id="deptId" name="deptId">
        <c:forEach var="dept" items="${deptList}">
            <option value="${dept.id}"
                    <c:if test="${user.deptId==dept.id}">selected</c:if> >${dept.name}</option>
        </c:forEach>
    </select><br><br>--%>
</div>
</body>
</html>
