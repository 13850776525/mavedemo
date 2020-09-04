<%--
  Created by IntelliJ IDEA.
  User: BOSSliu666
  Date: 2020/7/21
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>管理员界面</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<%--    <script src="${pageContext.request.contextPath}/js/SellCard.js"></script>--%>
<%--    <script src="${pageContext.request.contextPath}/js/Reg.js"></script>--%>
    <style>
        #tabledemo{
            width: 950px;
            height: auto;
            margin: 0px auto;
            align-content: center;
            /*border: 1px red solid;*/
        }
    </style>
</head>
<body>
<%
    String path = request.getContextPath();
%>
<input type="hidden" id="path" value=<%=path%> >
<div class="layui-anim layui-anim-up" >
    <button class="layui-btn" style="width: 100%">管理员界面</button>
</div>

<%--<table class="layui-hide" id="test"  lay-filter="demo"></table>--%>
<div id="tabledemo">
    <table class="layui-hide" id="demo" lay-filter="test"></table>
</div>


<script type="text/html" id="barDemo">
<%--    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>--%>
    <a class="layui-btn layui-btn-xs" lay-event="edit">保存</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

</body>


<script>

    layui.use(['form','layer','table'], function(){
        var table = layui.table,
        form = layui.form,
        $=layui.$;
        table.render({
            elem: '#demo'  //绑定table id
            ,url:'${pageContext.request.contextPath}/loginServlet?methodName=findAdmin'  //数据请求路径
            ,cellMinWidth: 80
            ,cols: [[
                {field:'name', width:200, title: '姓名', sort: true,edit:true}
                ,{field:'account', width:200, title: '账号'}
                ,{field:'password', width:150, title: '密码', sort: true,edit:true}
                ,{field:'roleid', width:150, title: '角色',edit:true}
                ,{fixed: 'right',title: '操作', width:250,align:'center', toolbar: '#barDemo'}//一个工具栏  具体请查看layui官网
            ]]
            ,page: true//开启分页
            ,limit:10//默认十条数据一页
            ,limits:[10,20,30,50]  //数据分页条
            ,id: 'testReload'
        });
        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            var admin = JSON.stringify(data);
            console.log(data)
            var admins= {"admin":admin}
            if(layEvent === 'detail'){
                layer.msg('查看操作');
            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                    $.ajax({
                        url:$("#path").val()+"/loginServlet?methodName=deleteManger",
                        method:'post',
                        data:admins,
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
</script>

</html>




