<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>申请发送成功</title>
		<%-- 导入css界面设计代码 --%>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
	</head>
	<body class="main">
		<jsp:include page="head.jsp"></jsp:include>
	   	<jsp:include page="menu_search.jsp"></jsp:include>
		<div id = "divcontent" align="center">
			<p>请耐心等待管理员审核！<br />
			</p>
		</div>
	</body>
</html>