<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>My JSP 'info.jsp' starting page</title>
<link href="res/img/css.css" rel="stylesheet" />
<link href="res/css/easyui.css" rel="stylesheet" />
</head>
<body>
	<div class="inner">
		<div class="hd">
			<h3>
				基本资料 <a href="modifyInfor.jsp" target="iframepage" class="comfirm">修改</a>
			</h3>
		</div>
		<div class="infos">
			<img src="res/headpic/${user.headpic}" width="80" height="80" /><br>
			QQ号码：${user.qq}<br> 昵称：${user.username}<br> 性别：
			<c:if test="${user.sex==1}">男</c:if>
			<c:if test="${user.sex==0}">女</c:if>
			<br> 出生日期：${user.birthday}<br>
			<c:if test="${!empty user.hometown }">
							家乡住址：${user.hometown}<br>
			</c:if>
			<c:if test="${empty user.hometown }">
							家乡住址：未填写<br>
			</c:if>
			<c:if test="${!empty user.nowaddress }">
							现在住址：${user.nowaddress}<br>
			</c:if>
			<c:if test="${empty user.nowaddress }">
							现在住址：未填写<br>
			</c:if>
			婚姻状况：
			<c:if test="${user.ismarry==1}">是</c:if>
			<c:if test="${user.ismarry==0}">否</c:if>
			<c:if test="${user.ismarry==-1}">保密</c:if>
			<br>
		</div>
	</div>
	<div class="inner">
		<div class="hd">
			<h3>登陆信息</h3>
		</div>
		<div class="infos">
			QQ状态：
			<c:if test="${user.state==1}">正常</c:if>
			<c:if test="${user.state==0}">冻结</c:if>
			<br> 最近登录时间：${datetime}<br>
			注册时间：${user.lastvisit}<br>
		</div>
	</div>
	<script type="text/javascript" src="res/js/jquery.js"></script>
	<script type="text/javascript" src="res/js/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$(".comfirm").linkbutton();
		});
	</script>
</body>
</html>
