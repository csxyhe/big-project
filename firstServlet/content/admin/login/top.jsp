<%-- 咸鱼的后台管理系统主界面顶部，作用：提示已经进入后台 --%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/admin/css/Style.css" type="text/css" rel="stylesheet">
	<title>标题栏</title>
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
	
	<table width="100%" border="0">
		<tr align="center">
			<td>
				<div class="height1">
					<img class="img_logo" src="${pageContext.request.contextPath}/admin/images/top.png" height="200px" />
				</div>
			</td>
			<td align="center" height="20px"><font color="blue"><a href="javascript:void(0)"
											onclick="exitSys()">注销</a> </font></td>
		</tr>
	</table>
</body>
</html>
