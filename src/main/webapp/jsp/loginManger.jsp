<%--
  Created by IntelliJ IDEA.
  User: BOSSliu666
  Date: 2020/7/18
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>管理端登陆</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginok.css">
    <script src="${pageContext.request.contextPath}/js/Login.js"></script>
<%--    <script src="${pageContext.request.contextPath}/js/Card.js"></script>--%>

</head>
<body>

<%
    String path = request.getContextPath();
%>
<input type="hidden" id="path" value=<%=path%> >
<div id="particles-js"></div>
<div id="wrapper">
    <div>
        <h2 style="font-size:60px;color: darkorange;font-family: 方正粗黑宋简体">共享文档系统</h2>
        <h2 style="font-size:60px;color: darkorange;font-family: 方正粗黑宋简体">管理端</h2>
    </div>

    <div id="login" align="center">
        <form  class="layui-form" method="post" action="${pageContext.request.contextPath}loginServlet?methodName=login">
            <ul class="group_input" align="center">
                <li align="center">
                    <input type="text" class="mobile required" id="account" name="AdminAccount" required lay-verify="AdminAccount"  placeholder="请输入账号" />
                </li>
                <li align="center">
                    <input type="password" class="psd required" id="password"  name="AdminPassword" required lay-verify="AdminPassword"  placeholder="请输入密码" />
                </li>
                <li align="center">
                    <input type="text" class="psd required" id="vCode"  name="vCode" required lay-verify="required"  placeholder="请输入验证码" />
                </li>
                <li align="center" style="margin-top: 10px">
                    <img style="width: 90px;height: 30px" src = "${pageContext.request.contextPath}/verifyCodeServlet" id="vCodeImg" onclick="changeImg()">
                    <label style="font-size: 10px;margin: 5px;color: #0f88eb" onclick="changeImg()">看不清，请刷新</label>
                </li>
            </ul>
            <button type="button" class="submit_btn" lay-submit lay-filter="loginManger">登陆</button>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/particles.min.js"></script>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
<script src="${pageContext.request.contextPath}/js/stats.js"></script>

</body>
</html>

