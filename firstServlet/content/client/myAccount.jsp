<%-- 用户专属的我的账户界面 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="xianyu.domain.User"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="UTF-8">
	<title>我的账户</title>
	<%-- 导入css界面设计代码 --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
</head>
<body class="main">
<%
if(session.getAttribute("browseuser")!=null){
	session.removeAttribute("browseuser");
}
%>
	<jsp:include page="head.jsp"></jsp:include>
   	<jsp:include page="menu_search.jsp"></jsp:include>
   	<div id="divcontent" align="center">
   		<table style="text-align:center;">
   			<tr>
   				<td>用户名：<input type="text" value="${user.username }" readonly="readonly"></td>
   			</tr>
   			<tr>
   				<td>性别：&nbsp;&nbsp;
   				<%
   				User user = (User) request.getSession().getAttribute("user"); 
   				if (user.getGender().equals("F")){
   				%>
   				<input type="text" value="女" readonly="readonly">
   				<%}else {%>
   				<input type="text" value="男" readonly="readonly">
   				<%} %>
   				</td>
   			</tr>
   			<tr>
   				<td>邮箱：&nbsp;&nbsp;<input type="text" 
   				<%
   				if (user.getEmail()!=null){
   				%>
   				value="${user.email }"
   				<%
   				}else{
   				%>
   				value="------"
   				<%	
   				}
   				%> readonly="readonly"></td>
   			</tr>
   			<tr>
	   			<td>
	   				<br/>
	   				<br/>
	   				购买记录：<a href="${pageContext.request.contextPath}/getBuyRecord?within_month=true">近一个月内</a>&nbsp;&nbsp;
	   				<a href="${pageContext.request.contextPath}/getBuyRecord">全部</a>
	   			</td>
			</tr>
   		</table>
   	</div>
</body>
</html>