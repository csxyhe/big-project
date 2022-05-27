<%@ page language="java" import="java.util.*" 
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="UTF-8">
	<title>咸鱼电商--登录</title>
	<%-- 导入css界面设计代码 --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
</head>
<body class="main">
	<jsp:include page="head.jsp"></jsp:include>
   	<jsp:include page="menu_search.jsp"></jsp:include>
	<div id="divcontent" align="center">
		<form action="${pageContext.request.contextPath}/login" method="post">
			<table width="850px" border="0" align="left" cellspacing="0">
				<tr>
					<td style="padding: 30px"><h1>用户登录</h1></td>
					<table width="70%" border="0" cellspacing="2" class="upline">
						<tr>
							<td style="text-align:right; padding-top:5px;width:25%">用户名：</td>
							<td style="text-align:left"><input type="text" name="username" id="username" class="textinput" /></td>
						</tr>
						<tr>
							<td style="text-align:right; padding-top:5px;">密&nbsp;&nbsp;&nbsp;码：</td>
							<td style="text-align:left"><input type="password" name="password" id="password" class="textinput" /></td>
						</tr>
						<tr>
							<td style="text-align:center;padding-top:20px;"><font
								color="#ff0000">${requestScope["register_message"]}</font>
							</td>
						</tr>
					</table>
				</tr>
				<tr>
					<table width="70%" border="0" cellspacing="0">
	                	<tr>
							<td style="padding-top: 20px; text-align: center">
								<input type="image" src="${pageContext.request.contextPath}/client/images/loginbutton.PNG" 
									name="submit" border="0" width="120" height="35" onclick="return checkform()"/>
							</td>
						</tr>
					</table>
				</tr>
			</table>
			<hr />
			<a href="${pageContext.request.contextPath}/client/register.jsp">还没有注册？</a>
		</form>
	</div>

</body>
</html>