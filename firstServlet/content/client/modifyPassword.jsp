<%-- 用户修改密码界面，需要输入旧密码和新密码 --%>
<%-- 当密码修改完毕后，要记得将session中的user对象更新 --%>
<%@ page language="java" import="java.util.*" 
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="UTF-8">
	<title>咸鱼电商--修改密码</title>
	<%-- 导入css界面设计代码 --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
    <script type="text/javascript">
    	//声明变量
    	var newpasswordObj;
    	var newpasswordMsg;
    	//页面加载之后，获取页面中的对象
    	window.onload = function(){
    		newpasswordObj = document.getElementById("newpassword");
    		newpasswordMsg = document.getElementById("newpasswordMsg");
    	}
    	function checkForm(){
    		return checkNewpassword();
    	}
    	//验证密码
	    //返回bool值,TRUE表示合法
	    function checkNewassword()
	    {
	        //设计一个用于验证用户名是否合法的正则表达式
	        //要求：设置6-16位任意字符
	        var regex = /^.{6,16}$/;
	        var value = passwordObj.value;
	        var msg = "";
	        if(!value)
	            msg = "密码不能为空";
	        else if(!regex.test(value))
	            msg = "密码不合法";
	        //将msg嵌入到HTML代码中
	        passwordMsg.innerHTML = msg;  
	        return msg == "";     
	    }
    </script>
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
		<form action="${pageContext.request.contextPath}/modifyPassword" method="post" onsubmit="return checkForm();">
			<table width="850px" border="0" align="left" cellspacing="0">
				<tr>
					<td style="padding: 30px"><h1>修改密码</h1></td>
					<table width="70%" border="0" cellspacing="2" class="upline">
						<tr>
							<td style="text-align:right; padding-top:5px;width:25%">原密码：</td>
							<td style="text-align:left"><input type="password" name="oldpassword" id="oldpassword" class="textinput"/></td>
						</tr>
						<tr>
							<td style="text-align:right; padding-top:5px;">新密码：</td>
							<td style="text-align:left"><input type="password" name="newpassword" id="newpassword" class="textinput" onkeyup="return checkNewpassword();"/></td>
							<td colspan="2"><span style="color:red" id="newpasswordMsg"></span></td>
						</tr>
						<tr>
							<td style="text-align:center;padding-top:20px;"><font
								color="#ff0000">${requestScope["match_message"]}</font>
							</td>
						</tr>
					</table>
				</tr>
				<tr>
					<table width="70%" border="0" cellspacing="0">
	                	<tr>
							<td style="padding-top: 20px; text-align: center">
								<input type="submit" name="submit" value="确定" onclick="return checkform()"/>
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