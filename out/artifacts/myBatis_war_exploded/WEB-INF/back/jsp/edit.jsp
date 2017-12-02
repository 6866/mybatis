<%--
  Created by IntelliJ IDEA.
  User: yangxingrom
  Date: 2017/11/22
  Time: 8:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>edit</title>
    <script type="text/javascript" src="resources/js/edit.js?version=1.0"></script>
</head>
<body>
    <form action="edit.action" method="post">
    <input type="hidden" name="id" value="${command.id}">
        <table border="2" id="ta1">
            <tr>
                <th>修改内容</th>
                <th>编辑</th>
            </tr>
            <tr>
                <td>命令：<input type="text" name="command" value="${command.command}"/></td>
            </tr>
            <tr>
                <td>描述：<input type="text" name="description" value="${command.description}"/></td>
            </tr>
            <c:forEach items="${command.contents}" var="content" varStatus="status">
                <tr>
                    <td>内容:<textarea name="content_${content.id}" cols="30" rows="4">${content.content}</textarea></td>
                    <td><a href="deleteContent.action?commandId=${command.id}&id=${content.id}" onclick="remove(this)">删除这行</a></td>
                </tr>
            </c:forEach>
            <tr>
                <td><a href="#" onclick="addRow()">添加一行</a></td>
                <td><input type="submit" value="提交修改"></td>
            </tr>
        </table>
    </form>
</body>
</html>
