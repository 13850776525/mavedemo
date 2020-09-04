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
        <title>用户界面</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.5.1.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/xadmin.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme4211.min.css">
        <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/xadmin.js"></script>
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
        <div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">首页</a>
            <a href="">用户管理</a>
            <a>
              <cite>用户管控</cite></a>
          </span>
          <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
            <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
        </div>
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body ">
                            <div class="demoTable">
                                <div class="layui-inline layui-show-xs-block">
                                    <label>姓名：</label>
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <input class="layui-input"  autocomplete="off" placeholder="姓名" name="userName" id="userName">
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <label>账号：</label>
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <input type="text" name="userAccount"  placeholder="账号" autocomplete="off" class="layui-input" id="userAccount">
                                </div>

                                <div class="layui-inline layui-show-xs-block">
                                    <label>会员等级：</label>
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <select name="userClass" id="userClass" style="width: 150px;height: 30px">
                                        <option value="">请选择</option>
                                        <option value="1">普通会员</option>
                                        <option value="2">白银会员</option>
                                        <option value="3">黄金会员</option>
                                        <option value="4">钻石会员</option>
                                        <option value="5">黑金会员</option>
                                        <option value="6">超级会员</option>
                                    </select>
                                </div>

                                <div class="layui-inline layui-show-xs-block">
                                    <input type="date" name="startTime" autocomplete="off" class="layui-input" id="startTime">
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <label>至</label>
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <input type="date" name="stopTime"  autocomplete="off" class="layui-input" id="stopTime">
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <button class="layui-btn"  data-type="reload" id="searchBtn"><i class="layui-icon">&#xe615;</i></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                        <div class="layui-card-body ">
                            <table class="layui-hide" id="demo" lay-filter="test"></table>
                            <script type="text/html" id="checkboxTpl">
                                {{# if (d.userState ==="禁用"){}}
                                <button class="layui-btn" name="{{d.userState}}"  title="{{d.userAccount}}" onclick="turnState(this)" style="background-color: #ff7800">开启</button>
                                {{#  } else { }}
                                <button class="layui-btn" name="{{d.userState}}"  title="{{d.userAccount}}" onclick="turnState(this)"  style="background-color:dimgrey">禁用</button>
                                {{#}}}
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </body>
    <script>

        layui.use(['form','layer','table','util'], function(){
            var table = layui.table,
                form = layui.form,
                util = layui.util,
                $=layui.$;
            table.render({
                elem: '#demo'  //绑定table id
                ,url:'${pageContext.request.contextPath}/adminControl/findUser'  //数据请求路径
                ,cellMinWidth: 80
                ,cols: [[
                    {type:'checkbox',width:'5%', title: '选择', sort: true,align:'center'}
                    ,{field:'userName', width:'5%', title: '姓名', sort: true,edit:true,align:'center'}
                    ,{field:'userAccount',width:'5%', title: '账号',align:'center'}
                    ,{field:'userPhone',width:'10%', title: '电话', sort: true,edit:true,align:'center'}
                    ,{field:'userSex',width:'5%', title: '性别',edit:true,align:'center'}
                    ,{field:'userDate',width:'10%', title: '注册日期',edit:true,align:'center', templet: function (d) {
                            return util.toDateString(d.userDate, "yyyy-MM-dd") }
                    }
                    ,{field:'userUpnum',width:'10%', title: '上传文档数',edit:true,align:'center'}
                    ,{field:'userDownnum',width:'10%', title: '下载文档数',edit:true,align:'center'}
                    ,{field:'userScore',width:'5%', title: '积分',edit:true,align:'center'}
                    ,{field:'userClass',width:'10%', title: '会员',edit:true,align:'center'}
                    ,{field:'userState',width:'5%', title: '状态',edit:true,align:'center'}
                    ,{field:'userStates', title:'审核', width:'15%', templet: '#checkboxTpl', unresize: true,align:'center'}
                ]]
                ,page: true//开启分页
                ,limit:10//默认十条数据一页
                ,limits:[10,20,30,50]  //数据分页条
                ,id: 'testReload'
            });

            var $ = layui.$, active = {
                reload: function(){
                    var userName = $('#userName');
                    var userAccount = $('#userAccount');
                    var userClass = $('#userClass');
                    var startTime = $('#startTime');
                    var stopTime = $('#stopTime');
                    //执行重载
                    table.reload('testReload', {
                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                        ,where: {
                            key: {
                                userName: userName.val(),
                                userAccount: userAccount.val(),
                                userClass: userClass.val(),
                                startTime: startTime.val(),
                                stopTime: stopTime.val()
                            }
                        }
                    }, 'data');
                },
            };

            $('.demoTable .layui-btn').on('click', function(){
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });
        });

        function turnState(node){
            layer.confirm('是否确定修改审核状态？',function () {
                var state = node.name;
                var id = node.title;
                var admins = {"state":state,"id":id}
                $.ajax({
                    url:$("#path").val()+"/adminControl/updateUserState",
                    method: 'post',
                    data:admins,
                    dataType: 'text',
                    success:function (data) {
                        if (data=="成功"){
                            if (state=="开启"){
                                $(node).css("background-color",function(){return "#ff7800";});
                                $(node).text('开启')
                                $(node).attr('name','禁用')
                                console.log($(node).parent().parent().parent().find("[data-field=userState]").children("div").text())
                                $(node).parent().parent().parent().find("[data-field=userState]").children("div").text("禁用")
                                layer.msg('已开启!',{icon: 5,time:1000});
                            }else{

                                $(node).css("background-color",function(){return "dimgrey";});
                                $(node).text('禁用')
                                console.log($(node).parent().parent().parent().find("[data-field=userState]").children("div").text())
                                $(node).parent().parent().parent().find("[data-field=userState]").children("div").text("开启")
                                $(node).attr('name','开启')
                                layer.msg('已禁用',{icon: 5,time:1000});
                            }
                        }else{
                            layer.msg(data);
                        }
                    }
                });
                return false;
            })
        }


    </script>
    <script>var _hmt = _hmt || []; (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
      })();</script>
</html>