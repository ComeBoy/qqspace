<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="header.jsp" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'message.jsp' starting page</title>
</head>

<body>
	<div class="layout-body">
		<div class="w960  clf">
			<div class="m1_main">
				<div class="inner">
					<div class="hd">
						<a href="#">留言板</a>
						<hr>
						<br>
					</div>
					<br>
					<div style="background: #ffffcc; width: 100%; height: 20px">
						&nbsp;&nbsp;&nbsp;&nbsp;留言板已默认为大家开启了非好友留言的主人审核功能，查看、保护隐私都由自己来决定！提高网络安全防范意识，保护号码使用安全，<a
							href="#">近期安全工作请点此了解！&nbsp;&nbsp; × </a>
					</div>
					<br>
					<h4>
						主人寄语&nbsp;<a href="#">编辑你的寄语</a>
					</h4>
					<br>
					<hr>
					<br>
					<div align="center">
						真诚欢迎您每一次的来访,由于广告太多，空间已经开启审核。<br>
						凡是进我空间的朋友，无论你怎么看我，怎么说我，我都祝你能在浏览此空间时有个愉快的心情。<br> 谢谢支持<br>
					</div>
					<br> <br>
					<div class="inner">
						<div class="hd">
							<h3>
								发表你的留言&nbsp;|&nbsp;<a href="#">送个礼物</a>
							</h3>
						</div>
						<div class="quote_f">
							<form action="leaveMessage" method="post">
								<input name="senderid" value="${user.userid}" type="hidden">
								<%-- <input name="receiveid" value="${message.receiveid}" type="hidden"> --%>
								<textarea style="color: rgb(0, 0, 0);" cols="60" name="content"
									rows="5" id="msg"></textarea>
								<br> <input id="pltj" value="提&nbsp;交" class="anbu"
									type="submit">
							</form>
						</div>
					</div>
					<c:set var="messages" value="${message}" />
					<c:set var="pager" value="${message}" />
					<c:if test="${!empty message}">
						<c:forEach items="${message}" var="message" varStatus="status">
							<div class="comm_content">
								<div class="c_list clf">
									<div class="c_hpic">
										<img src="res/headpic/${user.headpic}" alt="#" height="50"
											width="50">
									</div>
									<div class="c_fr">
										<p class="c_meta">
											<a href="#" target="_blank">${user.username}</a> <span
												class="c_time">${message.messagetime}</span> <span
												class="c_time"> </span> <span class="c_time"> <c:if
													test="${host.userid==receiver.userid}">
													<a href="delectMessage/${message.messageid}"
														onclick="return operaconfirm('删除');">删除</a>
												</c:if>
											</span>
										</p>
										<div class="c_nr">${message.content}</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
				<div class="comm_content">
					<%--  <c:if test="${!empty showMessageback }">
				   		<div class="c_list clf">
						   <div id="pl_page">
								<div class="pagelistbox">
									<span> 
									  ${showMessageback }
									</span>
								</div>
						   </div> 
					    </div>
				   </c:if> --%>
					<%-- <c:if test="${!empty messages }">
						<div class="c_list clf">
						   <div id="pl_page">
								<div class="pagelistbox">
									<span> 
										<c:if test="${pager.isFirstPage==false}">
										<a href="<%=request.getContextPath()%>/user/showMessages?receiveid=${receiver.userid }&currentPage=1">首页</a>
										</c:if> 
										<c:if test="${pager.hasPreviousPage==true}">
											<a href="<%=request.getContextPath()%>/user/showMessages?receiveid=${receiver.userid }&currentPage=${pager.pageNum-1}">上一页</a>
										</c:if> 
										<c:if test="${pager.hasNextPage==true}">
												<a href="<%=request.getContextPath()%>/user/showMessages?receiveid=${receiver.userid }&currentPage=${pager.pageNum+1}">下一页</a>
										</c:if> 
										<c:if test="${pager.isLastPage ==false}">
											<a href="<%=request.getContextPath()%>/user/showMessages?receiveid=${receiver.userid }&currentPage=${pager.pages}">尾页</a>
										</c:if> 
										当前第 ${pager.pageNum}页, 总共${pager.pages} 页
									</span>
								</div>
							</div> 
						</div>
					</c:if> --%>
				</div>
				<br>
			</div>
		</div>
	</div>
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
