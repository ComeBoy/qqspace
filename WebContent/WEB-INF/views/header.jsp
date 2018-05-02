<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${user.username}的空间</title>
<script src="res/js/jquery.js" type="text/javascript"></script>
<script src="res/js/iframe.js" type="text/javascript"></script>
<link href="res/img/css.css" rel="stylesheet" />
</head>
<body>
	<div class="w960">
		<div class="logo">
			<a href="#" title="#">我的空间</a>
		</div>
		<div class="top_r">
			<pre style="background: #3E87CC; color: #fff">中午好，${user.username}  <a
					href="#">退出</a>
				</pre>
		</div>
	</div>
	<div id="main">
		<div class="layout-head w960">
			<div class="head-info">
				<h1>${user.username}的空间</h1>
			</div>
		</div>
		<div class="head_nav">
			<div class="nav_bg"></div>
			<div class="w960">
				<ul>
					<li><a href="queryFeels">主页</a></li>
					<li><a href="grzl">个人资料</a></li>
					<li><a href="jumpMessage">留言板</a></li>
					<li><a href="queryFeel">说说</a></li>
					<li><a href="showfriend">好友</a></li>
				</ul>
				<div class="head-avatar">
					<a href="updateheadpic" target="iframepage"><img
						src="res/headpic/${user.headpic}" alt="" /></a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

