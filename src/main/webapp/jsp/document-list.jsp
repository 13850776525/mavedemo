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
        <title>管理员界面</title>
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
            <a href="">文档</a>
            <a>
              <cite>我的文档</cite></a>
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
                                    <label>标题：</label>
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <input class="layui-input"  autocomplete="off" placeholder="文档标题" name="account" id="docTitle">
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <label>上传者账号：</label>
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <input type="text" name="name"  placeholder="上传者账号" autocomplete="off" class="layui-input" id="userId">
                                </div>


                                <div class="layui-inline layui-show-xs-block">
                                    <label>文件类型：</label>
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <select name="docType" id="docType" style="width: 150px;height: 30px">
                                        <option value="">请选择</option>
                                        <option value="PPT">PPT</option>
                                        <option value="WORD">WORD</option>
                                        <option value="EXE">EXE</option>
                                    </select>
                                </div>


                                <div class="layui-inline layui-show-xs-block">
                                    <input type="date" name="name" autocomplete="off" class="layui-input" id="startTime">
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <label>至</label>
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <input type="date" name="name"  autocomplete="off" class="layui-input" id="stopTime">
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <button class="layui-btn"  data-type="reload" id="searchBtn"><i class="layui-icon">&#xe615;</i></button>
                                </div>
                            </div>
                            </div>
                        </div>
<%--                        <div class="layui-card-header">--%>
<%--                            <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>--%>
<%--                            <button class="layui-btn" onclick="xadmin.open('添加用户','${pageContext.request.contextPath}/jsp/admin-add.jsp',600,400)"><i class="layui-icon"></i>添加</button>--%>
<%--                        </div>--%>
                        <div class="layui-card-body ">
                            <table class="layui-hide" id="demo" lay-filter="test"></table>
<%--                            <script type="text/html" id="barDemo">--%>
<%--                                <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>--%>
<%--                                <a class="layui-btn layui-btn-xs" lay-event="edit">保存</a>--%>
<%--                                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>--%>
<%--                            </script>--%>
                            <script type="text/html" id="checkboxTpl">
                                {{# if (d.docState ==="1"){}}
                                <button class="layui-btn" name="{{d.docState}}"  title="{{d.id}}" onclick="turnState(this)" style="background-color: #ff7800">已审核</button>
                                {{#  } else { }}
                                <button class="layui-btn" name="{{d.docState}}"  title="{{d.id}}" onclick="turnState(this)"  style="background-color:dimgrey">未审核</button>
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
                ,url:'${pageContext.request.contextPath}/documentControl/findDoc'  //数据请求路径
                ,cellMinWidth: 80
                ,cols: [[
                    {type:'checkbox',width:'10%', title: '选择', sort: true,align:'center'}
                    ,{field:'docTitle', width:'20%', title: '标题', sort: true,edit:true,align:'center'}
                    ,{field:'docType',width:'10%', title: '类型',align:'center'}
                    , {field: 'docTime',width:'20%', title: '上传时间',  align: 'center', templet: function (d) {
                            return util.toDateString(d.docTime, "yyyy-MM-dd") }
                    }
                    ,{field:'docScore',width:'10%', title: '所需积分',edit:true,align:'center'}
                    ,{field:'docNum',width:'10%', title: '下载数量',edit:true,align:'center'}
                    ,{field:'userId',width:'10%', title: '上传用户',edit:true,align:'center'}
                    // ,{fixed: 'right',title: '操作', width:'20%',align:'center', toolbar: '#barDemo'}//一个工具栏  具体请查看layui官网
                    ,{field:'docState', title:'审核', width:'10%', templet: '#checkboxTpl', unresize: true}
                ]]
                ,page: true//开启分页
                ,limit:10//默认十条数据一页
                ,limits:[10,20,30,50]  //数据分页条
                ,id: 'testReload'
            });

            //-----带条件查询
            var $ = layui.$, active = {
                reload: function(){
                    var docTitle = $('#docTitle');
                    var userId = $('#userId');
                    var startTime = $('#startTime');
                    var stopTime = $('#stopTime');
                    var docType = $('#docType');
                    //执行重载
                    table.reload('testReload', {
                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                        ,where: {
                            key: {
                                docType:docType.val(),
                                docTitle: docTitle.val(),
                                userId: userId.val(),
                                startTime: startTime.val(),
                                stopTime: stopTime.val()
                            }
                        }
                    }, 'data');
                }
            };

            $('.demoTable .layui-btn').on('click', function(){
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
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
                            url:$("#path").val()+"/documentControl/deleteManger",
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
                            url:$("#path").val()+"/documentControl/updateManger",
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

      function delAll (argument) {
        var data = tableCheck.getData();
        layer.confirm('确认要删除吗？'+data,function(index){
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
      }
        function turnState(node){
        layer.confirm('是否确定修改审核状态？',function () {
            var state = node.name;
            var id = node.title;
            var admins = {"state":state,"id":id}
            $.ajax({
                url:$("#path").val()+"/documentControl/updateState",
                method: 'post',
                data:admins,
                dataType: 'text',
                success:function (data) {
                    if (data=="成功"){
                        if (state=="1"){
                            $(node).css("background-color",function(){return "dimgrey";});
                            $(node).text('未审核')
                            $(node).val("已注销");
                            $(node).attr('name','0')
                            layer.msg('取消审核成功!',{icon: 5,time:1000});
                        }else{
                            $(node).css("background-color",function(){return "#ff7800";});
                            $(node).text('已审核')
                            $(node).attr('name','1')
                            layer.msg('审核通过!',{icon: 5,time:1000});
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