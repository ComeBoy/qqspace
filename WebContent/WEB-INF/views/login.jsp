<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body {
	background-image: url(res/images/3.png);
}

.submit {
	width: 240px;
	height: 30px;
	background: #8FCC33;
	margin-top: 10px;
}

.form {
	border: 1px;
	background-color: #fff;
	width: 420px;
	height: 320px;
	margin-right: 510px;
	margin-top: 190px;
	float: right;
}

.online {
	display: inline;
}

.di {
	height: 16px;
	margin-bottom: 8px;
	width: 100%;
	position: absolute;
	bottom: 0;
	text-align: right;
	font-size: 12px;
}
</style>
</head>
<body>
	<form action="login" method="post">
		<div class="online">
			<div align="left"
				style="margin-left: 510px; margin-top: 88px; float: left;">
				<img alt="分享生活，留住感动" src="res/img/qzone-login-logo-dark.32.png">
			</div>
			<div class="form" align="center">
				<div align="center"
					style="font-size: 20px; margin-bottom: 16px; margin-top: 9px; font-family: helvetica, arial, 微软雅黑, 华文黑体;">账号密码登陆</div>
				<div
					style="font-family: helvetica, arial, 微软雅黑, 华文黑体; margin-bottom: 36px;">
					推荐使用<a href="javascript:void(0);" tabindex="7">快速安全登录</a>，防止盗号。
				</div>
				<div class="item fore1">
					<div class="item-ifo" style="margin-bottom: 20px">
						<input type="text" name="qq" class="text" required="required"
							style="height: 30px; width: 260px; display: block;" />
						<div class="i-name ico"></div>
						<label id="qq_error" class="hide"><b></b></label>
					</div>
				</div>

				<div class="item fore2">
					<div class="item-ifo" style="margin-bottom: 15px">
						<input type="password" name="password" class="text"
							required="required"
							style="height: 30px; width: 260px; display: block;" />
						<div class="i-pass ico"></div>
						<label id="loginpwd_error" class="hide"></label>
					</div>
				</div>
				<input type="submit" value="登&nbsp;录" class="submit"
					style="font-size: 18px; height: 40px; width: 265px; color: #fff; display: block; font-family: helvetica, arial, 微软雅黑, 华文黑体;" /><br>
				<div id="di">
					<a href="#" class="link" id="forgetpwd" target="_blank">忘了密码？</a><span
						class="dotted">|</span> <a href="zhucess" id="" class="link"
						target="_blank">注册新帐号</a><span class="dotted">|</span> <a
						class="link" id="feedback_web" href="#" target="_blank">意见反馈</a>
				</div>
			</div>
		</div>
	</form>
</body>
</html>