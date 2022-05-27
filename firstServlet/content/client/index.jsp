<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="xianyu.domain.User" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>咸鱼电商</title>
	<%-- 导入css界面设计代码 --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
</head>
<body class="main">
	<jsp:include page="head.jsp"></jsp:include>
   	<jsp:include page="menu_search.jsp"></jsp:include>
   	<div id="divcontent" align="center">
   		<table>
   			<tr>
   				<td colspan="3" style="text-align:left"><strong>尊敬的顾客：</strong></td>
   			</tr>
   			<tr>
   				<td colspan="3" style="text-align:right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎使用咸鱼电商网站，祝您购物愉快</td>
   			</tr>
   			<tr>
   				<td><img src="${pageContext.request.contextPath}/client/images/welcomeCute.png" width="180px" height="150px"/> </td>
   			</tr>
   		</table>
   		<% User user = (User)session.getAttribute("user"); 
   		if(user!=null){
   		%>
   		<table width="80%" cellspacing="0" style="border-collapse:separate; border-spacing:0px 10px;" align="center">
   			<tr>
   				<td><strong>猜你喜欢</strong></td>
   			</tr>
   			<tr>
   				<c:forEach items="${recom_list }" var="p">
		  				<td>
		  					<table>
		  						<tr>
		  							<td style="vertical-align: middle !important;">
		  								<div class="divpdpic">
											<a href="${pageContext.request.contextPath}/findProductById?p_id=${p.p_id }">
												<img class="divpdpic" src="${pageContext.request.contextPath }${p.imgurl }">
											</a>
										</div>
		  							</td>
		  						</tr>
		  						<tr>
		  							<td style="text-align:left;">名称：${p.name }</td>
		  						</tr>
		  						<tr>
		  							<td>价格：${p.price }元</td>
		  						</tr>
		  					</table>
						</td>
   				</c:forEach>
  			</tr>
   		</table>
   		<%} %>
   	</div>
</body>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             