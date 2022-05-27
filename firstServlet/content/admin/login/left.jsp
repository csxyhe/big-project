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
 		<div>
			<ul style="list-style-type: none;">
 				<li>销售人员管理
 					<ul style="list-style-type: none;">
	 					<li><a href="${pageContext.request.contextPath}/showApplication" target="mainFrame" class="left_list">销售权申请处理</a></li>
	 					<!-- 将撤销功能并入到销售人员名单中 -->
	 					<li><a href="${pageContext.request.contextPath}/showBusiness" target="mainFrame" class="left_list">销售人员名单</a> </li>
	 					<li><a href="${pageContext.request.contextPath}/admin/user/businessCount_list.jsp" target="mainFrame" class="left_list">销售业绩查询</a></li>
 					</ul>
 				</li>
 				<li>商品管理
 					<ul style="list-style-type: none;">
 					<li><a href="${pageContext.request.contextPath}/admin/products/productCount_list.jsp" target="mainFrame" class="left_list">单品信息统计</a></li>
 					<li><a href="${pageContext.request.contextPath}/admin/products/categoryCount_list.jsp" target="mainFrame" class="left_list">类别信息统计</a></li>
 					</ul>
 				</li>
 				<li>日志管理
 					<ul style="list-style-type: none;">
	 					<li><a href="${pageContext.request.contextPath}/admin/log/operation_list.jsp" target="mainFrame" class="left_list">操作日志</a></li>
	 					<li><a href="${pageContext.request.contextPath}/admin/log/loginout_list.jsp" target="mainFrame" class="left_list">登入登出日志</a></li>
	 					<li><a href="${pageContext.request.contextPath}/admin/log/browse_list.jsp" target="mainFrame" class="left_list">浏览日志</a></li>
 					</ul>
 				</li>
 				<li><a href="${pageContext.request.contextPath}/admin/user/userFigure_list.jsp" target="mainFrame" class="left_list">用户画像</a>
 				</li>
 				<li><a href="${pageContext.request.contextPath}/showAbnormality" target="mainFrame" class="left_list">销售异常</a>
 				</li>
			</ul>
 		</div>

</body>
</html>