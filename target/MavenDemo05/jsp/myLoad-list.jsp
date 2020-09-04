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
        <title>我的下载</title>
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
            <a href="">文档中心</a>
            <a>
              <cite>我的下载</cite></a>
          </span>
          <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
            <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
        </div>
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                        <div class="layui-card-body ">
                            <table class="layui-hide" id="demo" lay-filter="test"></table>
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
                $=layui.$,
                util = layui.util;
            table.render({
                elem: '#demo'  //绑定table id
                ,url:'${pageContext.request.contextPath}/documentControl/findMyLoad'  //数据请求路径
                ,cellMinWidth: 80
                ,cols: [[
                    // {type:'checkbox',width:'10%', title: '选择', sort: true,align:'center'}
                    ,{field:'loadId', width:'20%', title: '文档ID', sort: true,edit:true,align:'center'}
                    ,{field:'loadName',width:'20%', title: '文档标题',align:'center'}
                    ,{field:'loadTime',width:'20%', title: '下载时间', sort: true,edit:true,align:'center', templet: function (d) {
                            return util.toDateString(d.loadTime, "yyyy-MM-dd") }
                    }
                    ,{field:'loadAdmin',width:'20%', title: '文档作者ID',edit:true,align:'center'}
                    ,{field:'loadType',width:'20%', title: '文档类型名',edit:true,align:'center'}
                ]]
                ,page: true//开启分页
                ,limit:10//默认十条数据一页
                ,limits:[10,20,30,50]  //数据分页条
                ,id: 'testReload'
            });


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
                        var dtypeId= {"dtypeId":data.dtypeId}
                        //向服务端发送删除指令
                        $.ajax({
                            url:$("#path").val()+"/documentControl/deleteDtype",
                            method:'post',
                            data:dtypeId,
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
                }
            });
        });


        function turnState(node){
            layer.confirm('是否确定修改状态？',function () {
                var state = node.name;
                var id = node.title;
                var admins = {"state":state,"id":id}
                $.ajax({
                    url:$("#path").val()+"/documentControl/updateDtypeState",
                    method: 'post',
                    data:admins,
                    dataType: 'text',
                    success:function (data) {
                        if (data=="成功"){
                            if (state=="开启"){
                                $(node).css("background-color",function(){return "green";});
                                $(node).text('开启')
                                $(node).attr('name','禁用')
                                $(node).parent().parent().parent().find("[data-field=dtypeState]").children("div").text("禁用")
                                layer.msg('已禁用!',{icon: 5,time:1000});
                            }else{
                                $(node).css("background-color",function(){return "dimgrey";});
                                $(node).text('禁用')
                                $(node).parent().parent().parent().find("[data-field=dtypeState]").children("div").text("开启")
                                $(node).attr('name','开启')
                                layer.msg('已开启',{icon: 5,time:1000});
                            }
                        }else{
                            layer.msg(data);
                        }
                    }
                });
                return false;
            })
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


    </script>
    <script>var _hmt = _hmt || []; (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
      })();</script>
</html>