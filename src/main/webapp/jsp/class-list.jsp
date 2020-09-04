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
        <title>会员等级</title>
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
            <a href="">系统配置</a>
            <a>
              <cite>会员等级</cite></a>
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
<%--                                <div class="layui-inline layui-show-xs-block">--%>
<%--                                    <input class="layui-input"  autocomplete="off" placeholder="账号" name="account" id="account">--%>
<%--                                </div>--%>
<%--                                <div class="layui-inline layui-show-xs-block">--%>
<%--                                    <select name="role" id="role" style="width: 150px;height: 30px">--%>
<%--                                        <option value="">请选择</option>--%>
<%--                                        <option value="0">超级管理员</option>--%>
<%--                                        <option value="1">普通管理员</option>--%>
<%--                                    </select>--%>
<%--                                </div>--%>
<%--                                <div class="layui-inline layui-show-xs-block">--%>
<%--                                    <input type="text" name="name"  placeholder="请输入用户名" autocomplete="off" class="layui-input" id="name">--%>
<%--                                </div>--%>
<%--                                <div class="layui-inline layui-show-xs-block">--%>
<%--                                    <button class="layui-btn"  data-type="reload" id="searchBtn"><i class="layui-icon">&#xe615;</i></button>--%>
<%--                                </div>--%>
                            </div>
                            </div>
                        </div>
                    <div class="layui-btn-group demoTable">
                        <button class="layui-btn layui-btn-danger" data-type="getCheckData"> <i class="layui-icon"></i>批量删除</button>
<%--                        <button class="layui-btn" onclick="xadmin.open('添加用户','${pageContext.request.contextPath}/jsp/admin-add.jsp',600,400)"><i class="layui-icon"></i>添加</button>--%>
                    </div>
<%--                        <div class="layui-card-header">--%>
<%--                            <button class="layui-btn layui-btn-danger" data-type="getCheckData" ><i class="layui-icon"></i>批量删除</button>--%>
<%--                            <button class="layui-btn" onclick="xadmin.open('添加用户','${pageContext.request.contextPath}/jsp/admin-add.jsp',600,400)"><i class="layui-icon"></i>添加</button>--%>
<%--                        </div>--%>
                        <div class="layui-card-body ">
                            <table class="layui-hide" id="demo" lay-filter="test"></table>
                            <script type="text/html" id="barDemo">
                                <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
                                <a class="layui-btn layui-btn-xs" lay-event="edit">保存</a>
                                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </body>
    <script>

        layui.use(['form','layer','table'], function(){
            var table = layui.table,
                form = layui.form,
                $=layui.$;
            table.render({
                elem: '#demo'  //绑定table id
                ,url:'${pageContext.request.contextPath}/adminControl/findClass'  //数据请求路径
                ,cellMinWidth: 80
                ,cols: [[
                    {field:'classId', width:'20%', title: '会员等级ID', sort: true,edit:true,align:'center'}
                    ,{field:'className',width:'20%', title: '会员等级名',align:'center'}
                    ,{field:'classDownload',width:'20%', title: '下载积分比例', sort: true,edit:true,align:'center'}
                    ,{field:'classUpload',width:'20%', title: '上传积分比例',edit:true,align:'center'}
                    ,{fixed: 'right',title: '操作', width:'20%',align:'center', toolbar: '#barDemo'}//一个工具栏  具体请查看layui官网
                ]]
                ,id: 'testReload'
            });

            var $ = layui.$, active = {
                reload: function(){
                    var account = $('#account');
                    var name = $('#name');
                    var role = $('#role');
                    //执行重载
                    table.reload('testReload', {
                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                        ,where: {
                            key: {
                                account: account.val(),
                                name: name.val(),
                                role: role.val()
                            }
                        }
                    }, 'data');
                },
                //批量删除数据
                getCheckData: function(){ //获取选中数据
                    var checkStatus = table.checkStatus('testReload');
                    if(checkStatus.data.length==0){
                        parent.layer.msg('请先选择要删除的数据行！', {icon: 2});
                        return ;
                    }
                    var ids = "";
                    for(var i=0;i<checkStatus.data.length;i++){
                        ids += checkStatus.data[i].account+",";
                    }
                    layer.confirm('是否删除选中的数据？', function(index){
                        layer.close(index);
                        parent.layer.msg('删除中...', {icon: 16,shade: 0.3,time:5000});
                        var id = {'account':ids}
                        $.ajax({
                            url:$("#path").val()+"/loginServlet?methodName=deleteManger",
                            method:'post',
                            data:id,
                            dataType:'text',
                            success:function(data){
                                layer.closeAll('loading');
                                if(data=="删除成功"){
                                    parent.layer.msg('删除成功！', {icon: 1,time:2000,shade:0.2});
                                    setTimeout(function(){
                                        location.reload(true);
                                    }, 1000);
                                }else{
                                    parent.layer.msg('删除失败！', {icon: 2,time:3000,shade:0.2});
                                }
                            },
                        });
                        return false;
                    });

                }
            };

            $('.demoTable .layui-btn').on('click', function(){
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });
            table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
                var admin = JSON.stringify(data);
                var admins= {"admin":admin}
                if(layEvent === 'detail'){
                    layer.msg('查看操作');
                } else if(layEvent === 'del'){
                    layer.confirm('真的删除行么', function(index){
                        obj.del(); //删除对应行（tr）的DOM结构
                        layer.close(index);
                        var account= {"account":data.account}
                        //向服务端发送删除指令
                        $.ajax({
                            url:$("#path").val()+"/loginServlet?methodName=deleteManger",
                            method:'post',
                            data:account,
                            dataType:'json',
                            success:function(data){
                                if(data=="删除成功"){
                                    layer.msg('删除成功！');
                                }else if(data=="删除失败"){
                                    layer.msg('删除失败！');
                                }
                            },
                        });
                        return false;
                    });
                } else if(layEvent === 'edit'){
                    layer.confirm('是否确定保存？',function (index) {
                        layer.close(index);
                        $.ajax({
                            url:$("#path").val()+"/loginServlet?methodName=updateManger",
                            method: 'post',
                            data:admins,
                            dataType: 'json',
                            success:function (data) {
                                if (data=="成功"){
                                    layer.msg('保存成功！');
                                }else{
                                    layer.msg(data);
                                }
                            }
                        });
                        return false;
                    })
                }
            });
        });

       /*用户-停用*/
      function member_stop(obj,id){
          layer.confirm('确认要停用吗？',function(index){
              if($(obj).attr('title')=='启用'){
                //发异步把用户状态进行更改
                $(obj).attr('title','停用')
                $(obj).find('i').html('&#xe62f;');
                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!',{icon: 5,time:1000});

              }else{
                $(obj).attr('title','启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!',{icon: 5,time:1000});
              }
              
          });
      }

      /*用户-删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              $(obj).parents("tr").remove();
              layer.msg('已删除!',{icon:1,time:1000});
          });
      }

        function deletes(){
            var checkStatus = table.checkStatus('demo');
            if(checkStatus.data.length==0){
                parent.layer.msg('请先选择要删除的数据行！', {icon: 2});
                return ;
            }
            var ids = "";
            for(var i=0;i<checkStatus.data.length;i++){
                ids += checkStatus.data[i].id+",";
            }
            parent.layer.msg('删除中...', {icon: 16,shade: 0.3,time:5000});
            $.post('url',
                {'ids':ids},
                function(data){
                    layer.closeAll('loading');
                    if(data.code==1){
                        parent.layer.msg('删除成功！', {icon: 1,time:2000,shade:0.2});
                        location.reload(true);
                    }else{
                        parent.layer.msg('删除失败！', {icon: 2,time:3000,shade:0.2});
                    }
                }
            );
        }


        // //提交表单
    // layui.use(['layer','form'], function () {
    //     var form = layui.form;
    //     var layer = layui.layer;
    //     $ = layui.jquery;
    //     form.on('submit(sreach)',function(data){
    //         $.ajax({
    //             url:$("#path").val()+"/loginServlet?methodName=findAdmin",
    //             method:'post',
    //             data:data.field,
    //             dataType:'text',
    //             success:function(data){
    //
    //             },
    //         });
    //         return false;
    //     });
    // })


    </script>
    <script>var _hmt = _hmt || []; (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
      })();</script>
</html>