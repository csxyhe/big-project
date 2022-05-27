<%-- 咸鱼的后台管理系统主界面菜单栏，作用：提供功能选择栏--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/admin/css/left.css" type="text/css" rel="stylesheet">
<title>菜单栏</title>
</head>
<body class="main">
	<table width="100%" border="0">
		<tr>
			<td><a href="${pageContext.request.contextPath}/businessman/products/list.jsp" target="mainFrame" class="left_list">商品管理</a></td>
		</tr>
		<tr>
			<td><a href="${pageContext.request.contextPath}/businessman/products/statis_list.jsp" target="mainFrame" class="left_list">销售统计</a></td>
		</tr>
		<tr>
			<td><a href="${pageContext.request.contextPath}/showBrowse" target="mainFrame" class="left_list">客户浏览日志</a></td>
		</tr>
		<tr>
			<td><a href="${pageContext.request.contextPath}/businessman/orders/order_list.jsp" target="mainFrame" class="left_list">客户购买记录</a></td>
		</tr>
	</table>
</body>
</html>