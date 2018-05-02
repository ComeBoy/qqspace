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
			基本资料
			<c:if test="${ship.state==1}">
				<a href="feelmy?fuserid=${fuser.userid}&currentPage=1"
					target="iframepage" class="comfirm">说说</a>
				<a href="showMessages?receiveid=${fuser.userid}&currentPage=1"
					target="_self" class="comfirm">留言板</a>
				<a href="blacfuser?fusermapfid=${ship.friendid}&&fusermapuid=${ship.userid}"
					class="comfirm" target="iframepage"
					onclick="return operaconfirm('拉黑');">拉黑</a>
				<a
					href="outfuser?fusermapfid=${ship.friendid}&&fusermapuid=${ship.userid}&&p=1"
					class="comfirm" target="iframepage"
					onclick="return operaconfirm('删除');">删除</a>
			</c:if>
			<c:if test="${ship.state==-1 }">
				<a href="hffuser?fusermapfid=${ship.friendid}&&fusermapuid=${ship.userid}"
					class="comfirm" target="iframepage"
					onclick="return operaconfirm('恢复好友');">恢复</a>
				<a
					href="outfuser?fusermapfid=${ship.friendid}&&fusermapuid=${ship.userid}&&p=1"
					class="comfirm" target="iframepage"
					onclick="return operaconfirm('删除');">删除</a>
			</c:if>
			<c:if test="${ship.state==0 }">
				<a href="outfuser?fusermapuid=${ship.userid}&&fusermapfid=0&&p=0" class="comfirm"
					target="iframepage" onclick="return operaconfirm('拒绝');">拒绝</a>
				<a href="agreefuser?fusermapid=${ship.userid}" class="comfirm"
					target="iframepage">同意</a>
			</c:if>
		</div>
		<div class="infos">
			QQ号码：${fuser.qq}<br> 昵称：${fuser.username}<br> 性别：
			<c:if test="${fuser.sex==1}">男</c:if>
			<c:if test="${fuser.sex==0}">女</c:if>
			<br> 出生日期：${fuser.birthday}<br>
			<c:if test="${!empty fuser.hometown }">
							家乡住址：${fuser.hometown}<br>
			</c:if>
			<c:if test="${empty fuser.hometown}">
							家乡住址：未填写<br>
			</c:if>
			<c:if test="${!empty fuser.nowaddress }">
							现在住址：${fuser.nowaddress}<br>
			</c:if>
			<c:if test="${empty fuser.nowaddress }">
							现在住址：未填写<br>
			</c:if>
			婚姻状况：
			<c:if test="${fuser.ismarry==1}">是</c:if>
			<c:if test="${fuser.ismarry==0}">否</c:if>
			<c:if test="${fuser.ismarry==-1}">保密</c:if>
			<br> 成为好友时间：
			<c:if test="${empty birthday}">
							${birthday}<br>
			</c:if>
		</div>
		<c:if test="${empty state}">
			<div class="inner">
				<div class="hd">
					<h3>登陆信息</h3>
				</div>
				<div class="infos">
					QQ状态：
					<c:if test="${fuser.state==1}">正常</c:if>
					<c:if test="${fuser.state==0}">冻结</c:if>
					<br> 最近登录时间：${fuser.lastvisit}<br>
				</div>
			</div>
		</c:if>

		<script type="text/javascript" src="res/js/jquery.js"></script>
		<script type="text/javascript" src="res/js/jquery.easyui.min.js"></script>
		<script type="text/javascript">
			$(function() {
				$(".comfirm").linkbutton();
			});
		</script>
		<script type="text/javascript">
			function operaconfirm(opera) {
				question = confirm("你确认要" + opera + "么");
				if (question != "0") {
					return true;
				}
				return false;
			}
		</script>
</body>
</html>
