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
        <title>伟伟文件传输系统</title>
        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <meta http-equiv="Cache-Control" content="no-siteapp" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/xadmin.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme4211.min.css">
        <!-- <link rel="stylesheet" href="./css/theme5.css"> -->
        <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/xadmin.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
        <!--[if lt IE 9]>
          <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
          <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script>
            // 是否开启刷新记忆tab功能
            // var is_remember = false;
        </script>
    </head>
    <body class="index">
        <!-- 顶部开始 -->
        <div class="container">
            <div class="logo">
                <a href="./index.jsp">文件传输系统</a></div>
            <div class="left_open">
                <a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
            </div>
            <ul class="layui-nav left fast-add" lay-filter="">
                <li class="layui-nav-item">
                    <a href="javascript:;">+新增</a>
                    <dl class="layui-nav-child">
                        <!-- 二级菜单 -->
                        <dd>
                            <a onclick="xadmin.open('最大化','http://www.baidu.com','','',true)">
                                <i class="iconfont">&#xe6a2;</i>弹出最大化</a></dd>
                        <dd>
                            <a onclick="xadmin.open('弹出自动宽高','http://www.baidu.com')">
                                <i class="iconfont">&#xe6a8;</i>弹出自动宽高</a></dd>
                        <dd>
                            <a onclick="xadmin.open('弹出指定宽高','http://www.baidu.com',500,300)">
                                <i class="iconfont">&#xe6a8;</i>弹出指定宽高</a></dd>
                        <dd>
                            <a onclick="xadmin.add_tab('在tab打开','member-list.html')">
                                <i class="iconfont">&#xe6b8;</i>在tab打开</a></dd>
                        <dd>
                            <a onclick="xadmin.add_tab('在tab打开刷新','member-del.html',true)">
                                <i class="iconfont">&#xe6b8;</i>在tab打开刷新</a></dd>
                    </dl>
                </li>
            </ul>
            <ul class="layui-nav right" lay-filter="">
                <li class="layui-nav-item">
                    <a href="javascript:;">${name}</a>
                    <dl class="layui-nav-child">
                        <!-- 二级菜单 -->
                        <dd>
                            <a onclick="xadmin.open('个人信息','http://www.baidu.com')">个人信息</a></dd>
                        <dd>
                            <a onclick="xadmin.open('切换帐号','http://www.baidu.com')">切换帐号</a></dd>
                        <dd>
                            <a href="./login.html">退出</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item to-index">
                    <a href="/">前台首页</a></li>
            </ul>
        </div>
        <!-- 顶部结束 -->
        <!-- 中部开始 -->
        <!-- 左侧菜单开始 -->
        <div class="left-nav">
            <div id="side-nav">
                <ul id="nav">
                        <c:if test="${not empty adminList}">
                            <c:forEach items="${adminList}" var="OneList">
                            <li>
                                <a href="javascript:;">
                                <i class="iconfont left-nav-li" lay-tips="${OneList.key.rootName}">&#xe723;</i>
                                <cite>${OneList.key.rootName}</cite>
                                <i class="iconfont nav_right">&#xe697;</i></a>
                                <ul class="sub-menu">
                                    <c:forEach items="${OneList.value}" var="Apply">
                                        <li>
                                            <a onclick="xadmin.add_tab('${Apply.rootName}','${pageContext.request.contextPath}'+'${Apply.rootUrl}')">
                                                <i class="iconfont">&#xe723;</i>
                                                <cite>${Apply.rootName}</cite>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </li>
                            </c:forEach>
                        </c:if>
<%--                    <ul class="sub-menu">--%>
<%--                        <c:if test="${not empty adminList}">--%>
<%--                            <c:forEach items="${adminList}" var="Apply">--%>
<%--                                <li>--%>
<%--                                    <a onclick="xadmin.add_tab('${Apply.rootName}','${pageContext.request.contextPath}'+'${Apply.rootUrl}')">--%>
<%--                                        <i class="iconfont">&#xe723;</i>--%>
<%--                                        <cite>${Apply.rootName}</cite>--%>
<%--                                    </a>--%>
<%--                                </li>--%>
<%--                            </c:forEach>--%>
<%--                        </c:if>--%>
<%--                    </ul>--%>
<%--                </ul>--%>
<%--                    <li>--%>
<%--                        <a href="javascript:;">--%>
<%--                            <i class="iconfont left-nav-li" lay-tips="订单管理">&#xe723;</i>--%>
<%--                            <cite>订单管理</cite>--%>
<%--                            <i class="iconfont nav_right">&#xe697;</i></a>--%>
<%--                        <ul class="sub-menu">--%>
<%--                            <li>--%>
<%--                                <a onclick="xadmin.add_tab('订单列表','order-list.html')">--%>
<%--                                    <i class="iconfont">&#xe6a7;</i>--%>
<%--                                    <cite>订单列表</cite></a>--%>
<%--                            </li>--%>
<%--                        </ul>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a href="javascript:;">--%>
<%--                            <i class="iconfont left-nav-li" lay-tips="分类管理">&#xe723;</i>--%>
<%--                            <cite>分类管理</cite>--%>
<%--                            <i class="iconfont nav_right">&#xe697;</i></a>--%>
<%--                        <ul class="sub-menu">--%>
<%--                            <li>--%>
<%--                                <a onclick="xadmin.add_tab('多级分类','cate.html')">--%>
<%--                                    <i class="iconfont">&#xe6a7;</i>--%>
<%--                                    <cite>多级分类</cite></a>--%>
<%--                            </li>--%>
<%--                        </ul>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a href="javascript:;">--%>
<%--                            <i class="iconfont left-nav-li" lay-tips="城市联动">&#xe723;</i>--%>
<%--                            <cite>城市联动</cite>--%>
<%--                            <i class="iconfont nav_right">&#xe697;</i></a>--%>
<%--                        <ul class="sub-menu">--%>
<%--                            <li>--%>
<%--                                <a onclick="xadmin.add_tab('三级地区联动','city.html')">--%>
<%--                                    <i class="iconfont">&#xe6a7;</i>--%>
<%--                                    <cite>三级地区联动</cite></a>--%>
<%--                            </li>--%>
<%--                        </ul>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a href="javascript:;">--%>
<%--                            <i class="iconfont left-nav-li" lay-tips="管理员管理">&#xe726;</i>--%>
<%--                            <cite>管理员管理</cite>--%>
<%--                            <i class="iconfont nav_right">&#xe697;</i></a>--%>
<%--                        <ul class="sub-menu">--%>
<%--                            <li>--%>
<%--                                <a onclick="xadmin.add_tab('管理员列表','admin-list.jsp')">--%>
<%--                                    <i class="iconfont">&#xe6a7;</i>--%>
<%--                                    <cite>管理员列表</cite></a>--%>
<%--                            </li>--%>
<%--                            <li>--%>
<%--                                <a onclick="xadmin.add_tab('角色管理','admin-role.html')">--%>
<%--                                    <i class="iconfont">&#xe6a7;</i>--%>
<%--                                    <cite>角色管理</cite></a>--%>
<%--                            </li>--%>
<%--                            <li>--%>
<%--                                <a onclick="xadmin.add_tab('权限分类','admin-cate.html')">--%>
<%--                                    <i class="iconfont">&#xe6a7;</i>--%>
<%--                                    <cite>权限分类</cite></a>--%>
<%--                            </li>--%>
<%--                            <li>--%>
<%--                                <a onclick="xadmin.add_tab('权限管理','admin-rule.html')">--%>
<%--                                    <i class="iconfont">&#xe6a7;</i>--%>
<%--                                    <cite>权限管理</cite></a>--%>
<%--                            </li>--%>
<%--                        </ul>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a href="javascript:;">--%>
<%--                            <i class="iconfont left-nav-li" lay-tips="图标字体">&#xe6b4;</i>--%>
<%--                            <cite>图标字体</cite>--%>
<%--                            <i class="iconfont nav_right">&#xe697;</i></a>--%>
<%--                        <ul class="sub-menu">--%>
<%--                            <li>--%>
<%--                                <a onclick="xadmin.add_tab('图标对应字体','unicode.html')">--%>
<%--                                    <i class="iconfont">&#xe6a7;</i>--%>
<%--                                    <cite>图标对应字体</cite></a>--%>
<%--                            </li>--%>
<%--                        </ul>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a href="javascript:;">--%>
<%--                            <i class="iconfont left-nav-li" lay-tips="其它页面">&#xe6b4;</i>--%>
<%--                            <cite>其它页面</cite>--%>
<%--                            <i class="iconfont nav_right">&#xe697;</i></a>--%>
<%--                        <ul class="sub-menu">--%>
<%--                            <li>--%>
<%--                                <a href="login.html" target="_blank">--%>
<%--                                    <i class="iconfont">&#xe6a7;</i>--%>
<%--                                    <cite>登录页面</cite></a>--%>
<%--                            </li>--%>
<%--                            <li>--%>
<%--                                <a onclick="xadmin.add_tab('错误页面','error.html')">--%>
<%--                                    <i class="iconfont">&#xe6a7;</i>--%>
<%--                                    <cite>错误页面</cite></a>--%>
<%--                            </li>--%>
<%--                            <li>--%>
<%--                                <a onclick="xadmin.add_tab('示例页面','demo.html')">--%>
<%--                                    <i class="iconfont">&#xe6a7;</i>--%>
<%--                                    <cite>示例页面</cite></a>--%>
<%--                            </li>--%>
<%--                            <li>--%>
<%--                                <a onclick="xadmin.add_tab('更新日志','log.html')">--%>
<%--                                    <i class="iconfont">&#xe6a7;</i>--%>
<%--                                    <cite>更新日志</cite></a>--%>
<%--                            </li>--%>
<%--                        </ul>--%>
<%--                    </li>--%>
                </ul>
            </div>
        </div>
        <!-- <div class="x-slide_left"></div> -->
        <!-- 左侧菜单结束 -->
        <!-- 右侧主体开始 -->
        <div class="page-content">
            <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
                <ul class="layui-tab-title">
                    <li class="home">
                        <i class="layui-icon">&#xe68e;</i>我的桌面</li></ul>
                <div class="layui-unselect layui-form-select layui-form-selected" id="tab_right">
                    <dl>
                        <dd data-type="this">关闭当前</dd>
                        <dd data-type="other">关闭其它</dd>
                        <dd data-type="all">关闭全部</dd></dl>
                </div>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show">
                        <iframe src='${pageContext.request.contextPath}/jsp/admin-list.jsp' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
                    </div>
                </div>
                <div id="tab_show"></div>
            </div>
        </div>
        <div class="page-content-bg"></div>
        <style id="theme_style"></style>
        <!-- 右侧主体结束 -->
        <!-- 中部结束 -->
        <script>//百度统计可去掉
            var _hmt = _hmt || []; (function() {
                var hm = document.createElement("script");
                hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hm, s);
            })();</script>
    </body>

</html>