<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>说说</title>
</head>
<body>
	<span id="inforback"></span>
	<div style="width: 100%; background: #E6E6E6;font-family: helvetica, arial, 微软雅黑, 华文黑体" align="center">
		近期发表的说说
	
	</div>
	<!--qq end-->
	<div class="msgCon">
		<c:set var="feels" value="${feel}" />
		<c:set var="pager" value="${feel}" />
		<c:if test="${!empty feel}">
			<c:forEach items="${feel}" var="feel" varStatus="status">
				<div class='msgBox'>
					<dl>
						<dt>
							<c:if test="${user.userid==feel.userid}">
								<img src="res/headpic/${user.headpic}"
									alt="${user.username}" height="50" width="50">
							</c:if>
						</dt>
						<dd>
							<c:if test="${feel.userid==user.userid}">
									${user.username}
								</c:if>
							<span style="font-size: 10px;">${feel.feeltime}</span>
							<c:if test="${user.userid==feel.userid}">
								<!-- TODO -->
								<a href="delectFeel/${feel.feelid}"> <span
									style="font-size: 10px;" onclick="return operaconfirm('删除');">删除</span>
								</a>
								<!-- TODO -->
							</c:if>
						</dd>
					</dl>
					<div class='msgTxt'>
						${feel.feelcontent}<br>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</div>
	<script type="text/javascript" src="res/js/jquery.js"></script>
	<script type="text/javascript">
		$(function() {
			//点击小图片，显示表情
			$(".bq").click(function(e) {
				$(".face").slideDown();//慢慢向下展开
				e.stopPropagation(); //阻止冒泡事件
			});

			//在桌面任意地方点击，他是关闭
			$(document).click(function() {
				$(".face").slideUp();//慢慢向上收
			});

			//点击小图标时，添加功能
			$(".face ul li").click(function() {
				var simg = $(this).find("img").clone();
				$(".message").append(simg);
			});
			//点击提交按钮把div中的值复制给textarea控件
			$(".submit").click(function() {
				var txt = $(".message").html();
				if (txt == "") {
					$('.message').focus();//自动获取焦点
					return;
				}
				$(".feelcontent").html(txt);
			});
		});
		function operaconfirm(op) {
			question = confirm("你确定要" + op + "么？");
			if (question != "0") {
				return true;
			}
			return false;
		}
		function onsub() {
			var text = $('.feelcontent').val();
			if (text == "") {
				alert("请输入内容方可提交");
				return false;
			}
		}
	</script>
</body>
</html>
