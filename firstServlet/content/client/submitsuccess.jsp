<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>提交成功</title>
		<%-- 导入css界面设计代码 --%>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
	</head>
	<body class="main">
		<jsp:include page="head.jsp"></jsp:include>
	   	<jsp:include page="menu_search.jsp"></jsp:include>
		<div id = "divcontent" align="center">
			<p>您的申请已经成功提交给管理员！若审核通过，将于3个工作日内将您的新密码发送到您填写的邮箱中，请注意查收^-^<br />
			</p>
		</div>
	</body>
</html>