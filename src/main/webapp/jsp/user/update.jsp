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

    });
</script>
<div id="right">
    <form action="/user/update" method="post">

        <input type="text" name="id" value="${user.id}" style="display: none;">

        <%--修改的时候用户名不能重复--%>
        用户名：<input type="text" name="username" value="${user.username}"/><br><br>
        邮箱：<input type="text" name="email" value="${user.email}"/><br><br>
        真实姓名：<input type="text" name="realName" value="${user.realName}"/><br><br>
        年龄：<input type="text" name="age" value="${user.age}"/><br><br>
        性别：<input type="radio" name="sex" value="1"  <c:if test="${user.sex==1}">checked</c:if> />男
              <input type="radio" name="sex" value="0"  <c:if test="${user.sex==0}">checked</c:if> />女<br><br>

        简介：<textarea name="description">${user.description}</textarea><br><br>

        部门：<select id="deptId" name="deptId">
        <c:forEach var="dept" items="${deptList}">
            <option value="${dept.id}"
                    <c:if test="${user.deptId==dept.id}">selected</c:if> >${dept.name}</option>
        </c:forEach>
    </select><br><br>
        <input type="submit" value="修改"/>
        <input type="reset" value="重置"/>
    </form>
</div>
</body>
</html>
