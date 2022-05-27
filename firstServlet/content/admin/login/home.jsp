<%-- 咸鱼的管理员后台管理系统主界面 --%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
		if (top.location != self.location){     
		     top.location=self.location;     
		}
	</script>
	<title>咸鱼电商--管理员后台</title>
</head>
<frameset rows="30%,*" frameborder=no border="0" framespacing="0">
	<frame src="${pageContext.request.contextPath}/admin/login/top.jsp" name="topFrame" scrolling="NO" noresize>
	<frameset cols="20%,*" frameborder=no border="0" framespacing="0">
		<frame src="${pageContext.request.contextPath}/admin/login/left.jsp" name="leftFrame" noresize scrolling="YES"/>
		<frame src="${pageContext.request.contextPath}/admin/login/welcome.jsp"  name="mainFrame"/>
	</frameset>
</frameset>
<body>
	
</body>
</html>