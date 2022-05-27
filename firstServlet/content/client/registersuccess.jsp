<%-- 注册成功后跳转到的界面 --%>
<%-- 设计目的：给出简单的注册成功提示，并给出能跳转到主界面的超链接 --%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>成功注册</title>
		<%-- 导入css界面设计代码 --%>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
	</head>
	<body class="main">
		<jsp:include page="head.jsp"></jsp:include>
	   	<jsp:include page="menu_search.jsp"></jsp:include>
		<div id = "divcontent" align="center">
			<p>恭喜你完成注册！<br />
			</p>
		</div>
	</body>
</html>