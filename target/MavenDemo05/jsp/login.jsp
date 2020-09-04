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
    <title>登陆</title>
<%--    <meta name="renderer" content="webkit|ie-comp|ie-stand">--%>
<%--    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">--%>
<%--    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />--%>
<%--    <meta http-equiv="Cache-Control" content="no-siteapp" />--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">--%>
<%--    @media only screen and (max-width: 500px) {--%>
<%--        body {--%>
<%--            background-color: lightblue;--%>
<%--        }--%>
<%--    }--%>
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
    </div>

    <nav class="switch_nav">
                <a href="${pageContext.request.contextPath}/jsp/Register.jsp" id="switch_signup" class="switch_btn">注册</a>
                <a href="#" id="switch_login" class="switch_btn on">登陆</a>
        <div class="switch_bottom" id="switch_bottom"></div>
    </nav>
    <div id="login" align="center">
        <form  class="layui-form" method="post" action="">
            <ul class="group_input" align="center">
                <li align="center">
                    <input type="text" class="mobile required" id="account" name="UserAccount" required lay-verify="UserAccount"  placeholder="请输入账号" />
                </li>
                <li align="center">
                    <input type="password" class="psd required" id="password"  name="UserPassword" required lay-verify="UserPassword"  placeholder="请输入密码" />
                </li>
                <li align="center">
                    <input type="text" class="psd required" id="vCode"  name="vCode" required lay-verify="required"  placeholder="请输入验证码" />
                </li>
                <li align="center" style="margin-top: 10px">
                    <img style="width: 90px;height: 30px" src = "${pageContext.request.contextPath}/verifyCodeServlet" id="vCodeImg" onclick="changeImg()">
                    <label style="font-size: 10px;margin: 5px;color: #0f88eb" onclick="changeImg()">看不清，请刷新</label>
                </li>
            </ul>
            <button type="button" class="submit_btn" lay-submit lay-filter="login">登陆</button>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/particles.min.js"></script>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
<script src="${pageContext.request.contextPath}/js/stats.js"></script>

<%--<script>--%>
<%--    $(function(){--%>
<%--        //为表单元素添加失去焦点事件--%>
<%--        $("form :input").blur(function(){--%>
<%--            var $parent = $(this).parent();--%>
<%--            $parent.find(".msg").remove(); //删除以前的提醒元素（find()：查找匹配元素集中元素的所有匹配元素）--%>
<%--            //验证手机号--%>
<%--            if($(this).is("#account")){--%>
<%--                var mobileVal = $.trim(this.value);--%>
<%--                var regMobile = /^[0-9]{5}$/;--%>
<%--                if(mobileVal == "" || !regMobile.test(mobileVal)){--%>
<%--                    var errorMsg = " 请输入账号！";--%>
<%--                    $parent.append("<span class='msg onError'>" + errorMsg + "</span>");--%>
<%--                } else{--%>
<%--                    var okMsg=" 输入正确";--%>
<%--                    $parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");--%>
<%--                }--%>
<%--            }--%>
<%--            //验证密码--%>
<%--            if($(this).is("#password")){--%>
<%--                var psdVal = $.trim(this.value);--%>
<%--                var regPsd = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;--%>
<%--                if(psdVal== "" || !regPsd.test(psdVal)){--%>
<%--                    var errorMsg = " 请输入密码！";--%>
<%--                    $parent.append("<span class='msg onError'>" + errorMsg + "</span>");--%>
<%--                }--%>
<%--                else{--%>
<%--                    var okMsg=" 输入正确";--%>
<%--                    $parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");--%>
<%--                }--%>
<%--            }--%>
<%--        }).keyup(function(){--%>
<%--            //triggerHandler 防止事件执行完后，浏览器自动为标签获得焦点--%>
<%--            $(this).triggerHandler("blur");--%>
<%--        }).focus(function(){--%>
<%--            $(this).triggerHandler("blur");--%>
<%--        });--%>
<%--    })--%>
<%--</script>--%>
</body>
</html>

