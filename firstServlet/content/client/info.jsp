<%-- 商品的详细信息展示页面 --%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="xianyu.domain.User"%>
<%@ page import="xianyu.domain.Product"%>
<%@ page import="xianyu.web.listener.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${p.name }</title>
	<%-- 导入css界面设计代码 --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
</head>
<body class="main">
<%
User user = (User) request.getSession().getAttribute("user"); 
if (user != null){
	//可能他刷新了页面
	//当用户处于登录状态时，记录他的浏览记录才有意义
	BrowseTimeListener btlistener=new BrowseTimeListener();
	btlistener.getUserid(user.getId());
	btlistener.getBusinessid(((Product)request.getAttribute("p")).getB_id());
	btlistener.getProductid(((Product)request.getAttribute("p")).getP_id());
	session.setAttribute("browseuser",btlistener);
}
%>

	<jsp:include page="head.jsp"></jsp:include>
   	<jsp:include page="menu_search.jsp"></jsp:include>
	<table>
		<tr>
			<td width="30%">
				<div class="divpdcover">
					<img src="${pageContext.request.contextPath}${p.imgurl}" class="divpdcover" />
				</div>
			</td>
			<td style="padding:20px 5px 5px 5px">
				<br /><br /><br />
				名称：<font class="bookname">${p.name }</font><hr />
				价格：${p.price }元<hr />
				库存：${p.pnum }件<hr />
				简介：${p.description }<hr />
				<br />
				<div style="text-align:right; margin-top:10px;">
					<%
					if (user != null){
					%>
						<a href="${pageContext.request.contextPath}/addCart?id=${p.p_id }">
						<img src="${pageContext.request.contextPath}/client/images/buyBotton.png" width="50px" height="25px" />
					</a>
					<%}else{ %>
						<a href="${pageContext.request.contextPath}/client/login.jsp">
						<img src="${pageContext.request.contextPath}/client/images/buyBotton.png" width="50px" height="25px" />
						</a>
					<%} %>
				</div>
				
			</td>

		</tr>
	</table>
</body>
</html>