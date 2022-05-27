<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>确认订单</title>
	<%-- 导入css界面设计代码 --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
</head>
<body class="main">
	<jsp:include page="head.jsp"></jsp:include>
   	<jsp:include page="menu_search.jsp"></jsp:include>
   	<div id="divcontent" align="center">
   		<p>你的订单已生成，详细信息可以登录你的邮箱查看！</p>
   	</div>
	
</body>
</html>