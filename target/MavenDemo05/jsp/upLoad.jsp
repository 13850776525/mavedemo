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
    <%--    <script src="${pageContext.request.contextPath}/js/Card.js"></script>--%>

</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>选完文件后不自动上传</legend>
</fieldset>



<div class="layui-fluid">
    <div class="layui-row">
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>文档标题
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="title" lay-verify="required" lay-reqtext="不能为空！" placeholder="请输入"
                           autocomplete="off" class="layui-input" id="title">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>文档积分
                </label>
                <div class="layui-input-inline">
                <input type="text" name="score" lay-verify="required" lay-reqtext="不能为空！" placeholder="请输入积分数"
                       autocomplete="off" class="layui-input" id="score">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-upload">
                    <button type="button" class="layui-btn layui-btn-normal" id="test8">选择文件</button>
                    <button type="button" class="layui-btn" id="test9" style="width: 0px;height: 0px">开始上传</button>
                    <button type="button" class="layui-btn" id="btnSubmit">提交</button>
                </div>
            </div>
    </div>
</div>

<%
    String path = request.getContextPath();
%>
<input type="hidden" id="path" value=<%=path%> >


<script>
    layui.use('upload', function(){
        var $ = layui.jquery
            ,upload = layui.upload;
        var $path = $("#path").val();
        //同时绑定多个元素，并将属性设定在元素上
        upload.render({
            elem: '.demoMore'
            ,before: function(){
                layer.tips('接口地址：'+ this.url, this.item, {tips: 1});
            }
            ,done: function(res, index, upload){
                var item = this.item;
                console.log(item); //获取当前触发上传的元素，layui 2.1.0 新增
            }
        })

        //表单验证
        $("#btnSubmit").on("click",function () {
            $("#test9").click();
        })

        upload.render({
            elem: '#test8'
            ,url: $("#path").val()+"/documentControl/upload" //改成您自己的上传接口
            ,auto: false
            //,multiple: true
            ,bindAction: '#test9'
            ,data:{
                title:function () {
                    return $("#title").val();
                },
                score:function () {
                    return $("#score").val();
                }
            }
            ,done: function(res){
                layer.msg('上传成功');
                console.log(res)
            },before: function(){
                layer.tips('接口地址：'+ this.url, this.item, {tips: 1});
            },error: function() {
                //演示失败状态，并实现重传
                layer.msg('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            }
        });




    });
</script>
</body>
</html>

