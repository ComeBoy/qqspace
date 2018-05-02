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
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<title>说说</title>
<style type="text/css">
#btn {
	margin-left: 20px;
	margin-right: 20px;
	margin-top: 5px;
	float: right;
}
</style>
</head>
<body background="#3E87CC">
	<div id="qq">
		<form method="post" action="speak">
			<p>有什么新鲜事想告诉大家?</p>
			<div class="message" contentEditable='true'></div>
			<textarea hidden class="feelcontent" name="feelcontent"
				required="required"></textarea>
			<div class="But">
				<img src="res/images/bba_thumb.gif" class='bq' /> <span
					onclick="onsub()">发表 <input type="submit" class="submit">
				</span>
				<!--face begin-->
				<div class="face">
					<ul>
						<li><img src="res/images/zz2_thumb.gif" title="[织]"></li>
						<li><img src="res/images/horse2_thumb.gif" title="神马]"></li>
						<li><img src="res/images/fuyun_thumb.gif" title="[浮云]"></li>
						<li><img src="res/images/geili_thumb.gif" title="[给力]"></li>
						<li><img src="res/images/wg_thumb.gif" title="[围观]"></li>
						<li><img src="res/images/vw_thumb.gif" title="[威武]"></li>
						<li><img src="res/images/panda_thumb.gif" title="[熊猫]"></li>
						<li><img src="res/images/rabbit_thumb.gif" title="兔子]"></li>
						<li><img src="res/images/otm_thumb.gif" title="[奥特曼]"></li>
						<li><img src="res/images/j_thumb.gif" title="[囧]"></li>
						<li><img src="res/images/hufen_thumb.gif" title="[互粉]"></li>
						<li><img src="res/images/liwu_thumb.gif" title="[礼物]"></li>
						<li><img src="res/images/smilea_thumb.gif" title="呵呵]"></li>
						<li><img src="res/images/tootha_thumb.gif" title="嘻嘻]"></li>
						<li><img src="res/images/laugh.gif" title="[哈哈]"></li>
						<li><img src="res/images/tza_thumb.gif" title="[可爱]"></li>
						<li><img src="res/images/kl_thumb.gif" title="[可怜]"></li>
						<li><img src="res/images/kbsa_thumb.gif" title="[挖鼻屎]"></li>
						<li><img src="res/images/cj_thumb.gif" title="[吃惊]"></li>
						<li><img src="res/images/shamea_thumb.gif" title="[害羞]"></li>
						<li><img src="res/images/zy_thumb.gif" title="[挤眼]"></li>
						<li><img src="res/images/bz_thumb.gif" title="[闭嘴]"></li>
						<li><img src="res/images/bs2_thumb.gif" title="[鄙视]"></li>
						<li><img src="res/images/lovea_thumb.gif" title="[爱你]"></li>
						<li><img src="res/images/sada_thumb.gif" title="[泪]"></li>
						<li><img src="res/images/heia_thumb.gif" title="[偷笑]"></li>
						<li><img src="res/images/qq_thumb.gif" title="[亲亲]"></li>
						<li><img src="res/images/sb_thumb.gif" title="[生病]"></li>
						<li><img src="res/images/mb_thumb.gif" title="[太开心]"></li>
						<li><img src="res/images/ldln_thumb.gif" title="[懒得理你]"></li>
						<li><img src="res/images/yhh_thumb.gif" title="[右哼哼]"></li>
						<li><img src="res/images/zhh_thumb.gif" title="[左哼哼]"></li>
						<li><img src="res/images/x_thumb.gif" title="[嘘]"></li>
						<li><img src="res/images/cry.gif" title="[衰]"></li>
						<li><img src="res/images/wq_thumb.gif" title="[委屈]"></li>
						<li><img src="res/images/t_thumb.gif" title="[吐]"></li>
						<li><img src="res/images/k_thumb.gif" title="[打哈气]"></li>
						<li><img src="res/images/bba_thumb.gif" title="[抱抱]"></li>
						<li><img src="res/images/angrya_thumb.gif" title="[怒]"></li>
						<li><img src="res/images/yw_thumb.gif" title="[疑问]"></li>
						<li><img src="res/images/cza_thumb.gif" title="[馋嘴]"></li>
						<li><img src="res/images/88_thumb.gif" title="[拜拜]"></li>
						<li><img src="res/images/sk_thumb.gif" title="[思考]"></li>
						<li><img src="res/images/sweata_thumb.gif" title="[汗]"></li>
						<li><img src="res/images/sleepya_thumb.gif" title="[困]"></li>
						<li><img src="res/images/sleepa_thumb.gif" title="[睡觉]"></li>
						<li><img src="res/images/money_thumb.gif" title="[钱]"></li>
						<li><img src="res/images/sw_thumb.gif" title="[失望]"></li>
						<li><img src="res/images/cool_thumb.gif" title="[酷]"></li>
						<li><img src="res/images/hsa_thumb.gif" title="[花心]"></li>
						<li><img src="res/images/hatea_thumb.gif" title="[哼]"></li>
						<li><img src="res/images/gza_thumb.gif" title="[鼓掌]"></li>
						<li><img src="res/images/dizzya_thumb.gif" title="[晕]"></li>
						<li><img src="res/images/bs_thumb.gif" title="[悲伤]"></li>
						<li><img src="res/images/crazya_thumb.gif" title="[抓狂]"></li>
						<li><img src="res/images/h_thumb.gif" title="[黑线]"></li>
						<li><img src="res/images/yx_thumb.gif" title="[阴险]"></li>
						<li><img src="res/images/nm_thumb.gif" title="[怒骂]"></li>
						<li><img src="res/images/hearta_thumb.gif" title="[心]"></li>
						<li><img src="res/images/unheart.gif" title="[伤心]"></li>
						<li><img src="res/images/pig.gif" title="[猪头]"></li>
						<li><img src="res/images/ok_thumb.gif" title="[ok]"></li>
						<li><img src="res/images/ye_thumb.gif" title="[耶]"></li>
						<li><img src="res/images/good_thumb.gif" title="[good]"></li>
						<li><img src="res/images/no_thumb.gif" title="[不要]"></li>
						<li><img src="res/images/z2_thumb.gif" title="[赞]"></li>
						<li><img src="res/images/come_thumb.gif" title="[来]"></li>
						<li><img src="res/images/sad_thumb.gif" title="[弱]"></li>
						<li><img src="res/images/lazu_thumb.gif" title="[蜡烛]"></li>
						<li><img src="res/images/clock_thumb.gif" title="[钟]"></li>
						<li><img src="res/images/cake.gif" title="[蛋糕]"></li>
						<li><img src="res/images/m_thumb.gif" title="[话筒]"></li>
						<li><img src="res/images/weijin_thumb.gif" title="[围脖]"></li>
						<li><img src="res/images/lxhzhuanfa_thumb.gif" title="[转发]"></li>
						<li><img src="res/images/lxhluguo_thumb.gif" title="[路过这儿]"></li>
						<li><img src="res/images/bofubianlian_thumb.gif"
							title="[bofu变脸]"></li>
						<li><img src="res/images/gbzkun_thumb.gif" title="[gbz困]"></li>
						<li><img src="res/images/boboshengmenqi_thumb.gif"
							title="[生闷气]"></li>
						<li><img src="res/images/chn_buyaoya_thumb.gif" title="[不要啊]"></li>
						<li><img src="res/images/daxiongleibenxiong_thumb.gif"
							title="[dx泪奔]"></li>
						<li><img src="res/images/cat_yunqizhong_thumb.gif"
							title="[运气中]"></li>
						<li><img src="res/images/youqian_thumb.gif" title="[有钱]"></li>
						<li><img src="res/images/cf_thumb.gif" title="[冲锋]"></li>
						<li><img src="res/images/camera_thumb.gif" title="[照相机]"></li>
					</ul>
				</div>
				<!--face end-->
			</div>
		</form>
	</div>
	<!--qq end-->
	<div class="msgCon">
		<c:if test="${!empty feel}">
			<c:forEach items="${feel}" var="feel" varStatus="status">
				<div class='msgBox'>
					<dl>
						<dt>
							<c:forEach items="${userlist}" var="userlist">
								<c:if test="${userlist.userid==feel.userid}">
									<img src="res/headpic/${userlist.headpic}"
										alt="${userlist.username}" height="50" width="50">
								</c:if>
							</c:forEach>
						</dt>
						<dd>
							<c:forEach items="${userlist}" var="userlist">
								<c:if test="${feel.userid==userlist.userid}">
									${userlist.username}
								</c:if>
							</c:forEach>
							<span style="font-size: 10px;">${feel.feeltime}</span>
						</dd>
					</dl>
					<div class='msgTxt' >
						${feel.feelcontent}<br>
						<%-- <c:if test="${!empty feel.feel.pic}">
							<img src="img/${feel.feel.pic}" height="50" width="70">
						</c:if> --%>
					</div>
					<br>
					<!-- 图标 -->
					<div style="width: 97%; height: 28px" align="right">
						<button id="btn">
							<span class="glyphicon glyphicon-share"></span>
						</button>
						<button id="btn">
							<span
								onclick="tc(down${status.count},f${status.count},t${status.count})"
								class="glyphicon glyphicon-comment"></span>
						</button>
						<button id="btn">
							<span class="glyphicon glyphicon-thumbs-up"></span>
						</button>
					</div>
					<form action="comment1/${feel.feelid}" method="post">
						<div style="width: 98%" id="clears">
							<!-- 点击这个框显示 -->
							<input type="text" value="" id="down${status.count}"
								style="width: 98%; height: 35px"
								onclick="tc(down${status.count},f${status.count},t${status.count})">
							<div class="fff" id="f${status.count}">
								<textarea rows="4" cols="100%" id="t${status.count}"
									class="textareas" name="textsss"></textarea>
								<div style="margin-top: 10px">
									<img align="left" src="res/images/bba_thumb.gif" class='bq' />
									
									<input style="margin-left: 65%" type="radio">私密评论😘&nbsp;&nbsp;
									<input type="submit" style="color: #fff; background: #338FCC"
										value="&nbsp;发&nbsp;表&nbsp;">

								</div>
							</div>
						</div>
					</form>
					<!-- 显示评论区 -->
					<div style="margin-left: 50px;">
						<c:if test="${!empty speak}">
							<c:forEach items="${speak}" var="speak">
								<c:forEach items="${userlist}" var="userlist">
									<c:if test="${userlist.userid==speak.userid}">
										<c:if test="${feel.feelid==speak.pid}">
											<div class='msgBox' style="margin: 0 0 10px 0">
												<div style="margin-top:20px; width: 40px; height: 40px">
													<img src="res/headpic/${userlist.headpic}" alt=""
														height="30" width="30">
												</div>

												<div style="float: left;">
													<c:if test="${speak.userid==userlist.userid}">
												&nbsp;&nbsp;${userlist.username}:${speak.context}<br>
													</c:if>
													<span style="font-size: 10px;">&nbsp;&nbsp;${speak.time}</span>
												</div>
											</div>
										</c:if>
									</c:if>
								</c:forEach>
							</c:forEach>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</div>
	<script type="text/javascript" src="res/js/jquery.js"></script>
	<script type="text/javascript">
		function tc(d,f,t) {
			$(d).hide();
			$(f).slideDown();//慢慢向下展开   评论框
			$(t).focus();//自动获取焦点
		};
		//点击小图片，显示表情
		$(function(e) {
			$(".fff").hide();
			$(".bq").click(function(e) {
				$(".face").slideDown();//慢慢向下展开
				e.stopPropagation(); //阻止冒泡事件
			});
			//在桌面任意地方点击，他是关闭
			$(document).click(function(e) {
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
