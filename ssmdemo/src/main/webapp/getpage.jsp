<%--
  Created by IntelliJ IDEA.
  User: 旭哥
  Date: 2019/3/19
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td>姓名</td>
        <td>年龄</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${page}" var="d">
        <tr>
            <td>${d.name}</td>
            <td>${d.age}</td>
            <td>更新 &nbsp删除</td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="4" align="center">${pageString}</td>
    </tr>
</table>
</body>
</html>
