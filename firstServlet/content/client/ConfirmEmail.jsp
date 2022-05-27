<%-- 确认邮箱信息是否正确，用于发送信息 --%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="xianyu.domain.User" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>邮箱确认</title>
		<%-- 导入css界面设计代码 --%>
		<script type="text/javascript">
			//用于验证输入的邮箱的正确性
			//声明变量
			var emailObj;
			var emailMsg;
			//页面加载之后，获取页面中的对象
			window.onload = function(){
				emailObj = document.getElementById("email");
				emailMsg = document.getElementById("emailMsg");
			}
			//校验邮箱信息
			function checkEmail(){
				var regex = /^[\w-]+@([\w-]+\.)+[a-zA-Z]{2,4}$/;
				var value = emailObj.value;
				var msg = "";
				if(!regex.test(value))
					msg="邮箱格式不合法";
				emailMsg.innerHTML = msg;
				return msg =="";
			}
		</script>
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
	   	<%
	   	User user = (User) request.getSession().getAttribute("user");
	   	%>
		<div id = "divcontent" align="center">
			<form action="${pageContext.request.contextPath}/applyForBusiness" method="post">
				<table width="850px" border="0" align="left" cellspacing="0">
					<tr>
						<td style="padding: 30px"><h1>请确认邮箱信息，若申请通过，管理员将发送新的登录密码給您^-^</h1></td>
						<table width="70%" border="0" cellspacing="2" class="upline">
							<tr>
								<td>邮箱：&nbsp;&nbsp;</td>
								<td><input type="text" name="email" id="email"
								<%
				   				if (user.getEmail()!=null){
				   				%>
				   				value="${user.email }"
				   				<%
				   				}else{
				   				%>
				   				value="请输入邮箱"
				   				<%	
				   				}
				   				%> onkeyup="return checkEmail();" /></td>
				   				<td colspan="2"><span style="color:red" id="emailMsg"></span>&nbsp;</td>
							</tr>
						</table>
					</tr>
					<tr align="center">
						<td>
							
							<button type="submit" id="submit" name="submit" value="确认" class="button_view">
												确认
							</button>
						</td>
					</tr>	
				</table>
				
			</form>
		</div>
	</body>
</html>

