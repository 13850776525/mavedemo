<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/7/15
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
<%--    <link rel="stylesheet" href="/HosptialWork/css/Register_jframe.css">--%>
    <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginok.css">
    <script src="${pageContext.request.contextPath}/js/Login.js"></script>
</head>

<%
    String path = request.getContextPath();
%>
<body>
<input type="hidden" id="path" value="<%=path%>">


<div id="particles-js"></div>
<div id="wrapper">
    <div>
        <h2 style="font-size:60px;color: darkorange;font-family: 方正粗黑宋简体">共享文档系统</h2>
    </div>

    <nav class="switch_nav">
        <a href="#" id="switch_signup" class="switch_btn on">注册</a>
        <a href="${pageContext.request.contextPath}/jsp/login.jsp" id="switch_login" class="switch_btn">登陆</a>
        <div class="switch_bottom" id="switch_bottom"></div>
    </nav>
    <div id="login" align="center">
        <form class="layui-form" method="post" action="${pageContext.request.contextPath}loginServlet?methodName=RegUser">
            <ul class="group_input" align="center">
                <li align="center">
                    <input type="text" id="UserName" class="mobile required" name="UserName" required lay-verify="UserName"  placeholder="请输入姓名" />
                </li>
                <br>
                <li align="center">
                    <input type="password" id="UserPassword" class="psd required" name="UserPassword" required lay-verify="UserPassword" placeholder="请输入密码" />
                </li>
                <br>
                <li align="center">
                    <input type="password" id="UserPassword1" class="psd required" name="UserPassword1"  required lay-verify="UserPassword1"   placeholder="请重新输入密码" />
                </li>
                <br>
                <li align="center">
                    <input type="text" id="UserPhone" class="psd required" name="UserPhone"  required lay-verify="UserPhone"  placeholder="请输入手机号" />
                </li>
                <br>
                <li align="center">
                    <input type="text" id="UserEmall" class="psd required" name="UserEmall"  required lay-verify="UserEmall"  placeholder="请输入邮箱" />
                </li>
                <br>
                <li align="center">
                    <input  style="width: 15px;height: 15px" type="radio" autocomplete="off" value="男" name="UserSex" checked="checked"><label style="font-size: 15px">男</label>
                    <input  style="width: 15px;height: 15px" type="radio" autocomplete="off" value="女" name="UserSex" ><label style="font-size: 15px">女</label>
                </li>
            </ul>
            <button type="button" class="submit_btn" lay-submit lay-filter="RegUser">注册</button>
            <div class="text-right">
                <a href="${pageContext.request.contextPath}/jsp/login.jsp">已有账号，点击登录></a>
            </div>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/particles.min.js"></script>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
<script src="${pageContext.request.contextPath}/js/stats.js"></script>




<script>
    $(function(){
        //为表单元素添加失去焦点事件
        $("form :input").blur(function(){
            var $parent = $(this).parent();
            $parent.find(".msg").remove(); //删除以前的提醒元素（find()：查找匹配元素集中元素的所有匹配元素）
            //验证姓名
            if($(this).is("#UserName")){
                var nameVal = $.trim(this.value);
                var regName = /[~#^$@%&!*()<>:;'"{}【】  ]/;
                if(nameVal == "" || nameVal.length < 2 || regName.test(nameVal)){
                    var errorMsg = " 姓名非空，长度2-20位，不包含特殊字符！";
                    $parent.append("<div style='font-size: 8px;color: #e1251b' class='msg onError'>" + "*"+ errorMsg + "</div>");
                } else{
                    var okMsg=" 输入正确";
                    $parent.append("<div title='okMsg' style='font-size: 8px;color:gray' class='msg onSuccess'>"+ "*" + okMsg + "</div>");
                }
            }
            //验证手机号
            if($(this).is("#UserPhone")){
                var mobileVal = $.trim(this.value);
                var regMobile = /^1[3|4|5|7|8][0-9]{9}$/;
                if(mobileVal == "" || !regMobile.test(mobileVal)){
                    var errorMsg = " 请输入有效的11位手机号码！";
                    $parent.append("<div  style='font-size: 8px;color: #e1251b' class='msg onError'>" + "*" + errorMsg + "</div>");
                } else{
                    var okMsg=" 输入正确";
                    $parent.append("<div title='okMsg' style='font-size: 8px;color:gray' class='msg onSuccess'>" + "*" + okMsg + "</div>");
                }
            }
            //验证密码
            if($(this).is("#UserPassword")){
                var psdVal = $.trim(this.value);
                var regPsd = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
                if(psdVal== "" || !regPsd.test(psdVal)){
                    var errorMsg = " 密码为6-20位字母、数字的组合！";
                    $parent.append("<div style='font-size: 8px;color: #e1251b'  class='msg onError'>" + "*" + errorMsg + "</div>");
                }
                else{
                    var okMsg=" 输入正确";
                    $parent.append("<div title='okMsg'  style='font-size: 8px;color:gray' class='msg onSuccess'>" + "*" + okMsg + "</div>");
                }
            }
            //验证邮箱号
            if($(this).is("#UserEmall")){
                var mobileVal = $.trim(this.value);
                var regMobile = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
                if(mobileVal == "" || !regMobile.test(mobileVal)){
                    var errorMsg = " 请输入正确的邮箱地址！";
                    $parent.append("<div  style='font-size: 8px;color: #e1251b' class='msg onError'>" + "*" + errorMsg + "</div>");
                } else{
                    var okMsg=" 输入正确";
                    $parent.append("<div title='okMsg' style='font-size: 8px;color:gray' class='msg onSuccess'>" + "*" + okMsg + "</div>");
                }
            }
            //验证密码一致
            if($(this).is("#UserPassword1")){
                var psdVal = $.trim(this.value);
                var regPsd = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
                if(psdVal== "" || !regPsd.test(psdVal)){
                    var errorMsg = " 密码为6-20位字母、数字的组合！";
                    $parent.append("<div style='font-size: 8px;color: #e1251b'  class='msg onError'>" + "*" + errorMsg + "</div>");
                }else if($("#UserPassword").val()!=psdVal){
                    var errorMsg = " 密码不一致！";
                    $parent.append("<div style='font-size: 8px;color: #e1251b'  class='msg onError'>" + "*" + errorMsg + "</div>");
                }
                else{
                    var okMsg=" 输入正确";
                    $parent.append("<div title='okMsg'  style='font-size: 8px;color:gray' class='msg onSuccess'>" + "*" + okMsg + "</div>");
                }
            }
        }).keyup(function(){
            //triggerHandler 防止事件执行完后，浏览器自动为标签获得焦点
            $(this).triggerHandler("blur");
        }).focus(function(){
            $(this).triggerHandler("blur");
        });

        // //点击重置按钮时，通过trigger()来触发文本框的失去焦点事件
        // $("#btnSubmit").click(function(){
        //     //trigger 事件执行完后，浏览器会为submit按钮获得焦点
        //     $("form .required:input").trigger("blur");
        //     var numError = $("form .onError").length;
        //     if(numError){
        //         return false;
        //     }
        //     alert('注册成功！')
        //
        // });
    })

</script>
</body>
</html>
