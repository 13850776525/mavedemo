<%--
  Created by IntelliJ IDEA.
  User: BOSSliu666
  Date: 2020/7/21
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html class="x-admin-sm">
    
    <head>
        <meta charset="UTF-8">
        <title>个人信息</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/xadmin.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme4211.min.css">
        <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/xadmin.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
        <!--[if lt IE 9]>
            <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
            <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
    <%
        String path = request.getContextPath();
    %>
    <input type="hidden" id="path" value=<%=path%> >
        <div class="layui-fluid">
            <div class="layui-row">
                <form class="layui-form">
                  <div class="layui-form-item">
                      <label for="userName" class="layui-form-label">
                          <span class="x-red">*</span>姓名
                      </label>
                      <div class="layui-input-inline">
                          <input type="text" id="userName" name="userName" required="" lay-verify="required"
                          autocomplete="off" class="layui-input" value="${user.userName}">
                      </div>
                      <div class="layui-form-mid layui-word-aux">
                          <span class="x-red">*</span>您的个人姓名
                      </div>
                  </div>

                    <div class="layui-form-item">
                        <label for="userPhone" class="layui-form-label">
                            <span class="x-red">*</span>电话
                        </label>
                        <div class="layui-input-inline">
                            <input type="text" id="userPhone" name="userPhone" required="" lay-verify="required"
                                   autocomplete="off" class="layui-input"  value="${user.userPhone}">
                        </div>
                        <div class="layui-form-mid layui-word-aux">
                            <span class="x-red">*</span>您的真实电话
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label for="userEmall" class="layui-form-label">
                            <span class="x-red">*</span>邮箱
                        </label>
                        <div class="layui-input-inline">
                            <input type="text" id="userEmall" name="userEmall" required="" lay-verify="required"
                                   autocomplete="off" class="layui-input"  value="${user.userEmall}">
                        </div>
                        <div class="layui-form-mid layui-word-aux">
                            <span class="x-red">*</span>您的个人邮箱
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label"><span class="x-red">*</span>性别</label>
                        <input value="男" type="radio" name="userSex" lay-skin="primary" title="男">
                        <input value="女" type="radio" name="userSex" lay-skin="primary" title="女" checked="checked">
                    </div>
                  <div class="layui-form-item">
                      <label for="L_pass" class="layui-form-label">
                          <span class="x-red">*</span>密码
                      </label>
                      <div class="layui-input-inline">
                          <input type="password" id="L_pass" name="pass" required="" lay-verify="pass"
                          autocomplete="off" class="layui-input"  value="${user.userPassword}">
                      </div>
                      <div class="layui-form-mid layui-word-aux">
                          <span class="x-red">*</span>6到16个字符
                      </div>
                  </div>
                  <div class="layui-form-item">
                      <label for="L_repass" class="layui-form-label">
                          <span class="x-red">*</span>确认密码
                      </label>
                      <div class="layui-input-inline">
                          <input type="password" id="L_repass" name="repass" required="" lay-verify="repass"
                          autocomplete="off" class="layui-input"  value="${user.userPassword}">
                      </div>
                      <div class="layui-form-mid layui-word-aux">
                          <span class="x-red">*</span>必须和上面密码一致
                      </div>
                  </div>
                  <div class="layui-form-item">
                      <label for="L_repass" class="layui-form-label">
                      </label>
                      <button  class="layui-btn" lay-submit lay-filter="add">
                          修改
                      </button>
                  </div>
              </form>
            </div>
        </div>
        <script>layui.use(['form', 'layer'],
            function() {
                $ = layui.jquery;
                var form = layui.form,
                layer = layui.layer;
                //自定义验证规则
                form.verify({
                    nikename: function(value) {
                        if (value.length < 2) {
                            return '昵称至少得2个字符';
                        }
                    },
                    pass: [/(.+){6,12}$/, '密码必须6到12位'],
                    repass: function(value) {
                        if ($('#L_pass').val() != $('#L_repass').val()) {
                            return '两次密码不一致';
                        }
                    }
                });
                //监听提交
                form.on('submit(add)', function(data) {
                    console.log(data);
                    //发异步，把数据提交给php
                    $.ajax({
                        url:$("#path").val()+"/adminControl/updateUserOk",
                        data:data.field,
                        method:'post',
                        dataType:'text',
                        success:function (data) {
                            if (data=="成功"){
                                layer.msg("修改成功！");
                                //关闭当前frame可以对父窗口进行刷新
                                // setTimeout(function(){
                                //     xadmin.father_reload();
                                //     }, 1000);
                            }else{
                                layer.msg(data);
                            }
                        }
                    });
                    return false;
                });

            });</script>
        <script>var _hmt = _hmt || []; (function() {
                var hm = document.createElement("script");
                hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hm, s);
            })();</script>
    </body>

</html>
