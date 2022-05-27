<%-- 咸鱼的后台管理系统主界面顶部，作用：提示已经进入后台 --%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/businessman/css/Style.css" type="text/css" rel="stylesheet">
	<script type="text/javascript">
	function exitSys() {
		var flag = window.confirm("确认退出系统吗?");
		if (flag) {
			window.top.open('${pageContext.request.contextPath}/logout', '_parent', '');
			window.top.close();
		}
		//如果你使用的是firefox浏览器必须要做以下设置 
		//1、在地址栏输入about:config然后回车，警告确认 
		//2、在过滤器中输入”dom.allow_scripts_to_close_windows“，双击即可将此值设为true 即可完成了 

	}
</script>
</head>
<body>

	<div class="height1">
		<img class="img_logo" src="${pageContext.request.contextPath}/businessman/images/top.png" height="250px" width="100%" />
	</div>
	<table width="100%" border="0">
		<tr align="right">
			<td height="20px"><font color="blue"><a href="javascript:void(0)"
											onclick="exitSys()">注销</a> </font></td>
		</tr>
	</table>
	
	
</body>
</html>