<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/admin/css/Style.css" type="text/css" rel="stylesheet">
	<title>咸鱼后台--销售权限申请列表</title>
</head>
<body class="main">
	<table width="100%">
		<tr>
			<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="3" height="26px">
				<strong>销&nbsp;&nbsp;售&nbsp;&nbsp;权&nbsp;&nbsp;限&nbsp;&nbsp;申&nbsp;&nbsp;请&nbsp;&nbsp;列&nbsp;&nbsp;表</strong>
			</td>
		</tr>
		<tr>
			<td>
				<table cellspacing="0" cellpadding="1" rules="all" bordercolor="green"
							border="1px" id="DataGrid1" style="BORDER-RIGHT:green 1px solid; BORDER-LEFT:green 1px solid;
							 BORDER-TOP:green 1px solid; BORDER-BOTTOM:green 1px solid; WIDTH:100%; WORD-BREAK:break-all;
							 BORDER-COLLAPSE: collapse; BACKGROUND-COLOR:#cce7ea;WORD-WRAP:break-word;">
						 <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
						 	<td align="center" width="40%">用户ID</td>
						 	 <td align="center" width="30%"></td>
						 	 <td align="center" width="30%"></td>
						 </tr>
						 <c:forEach items="${al }" var="entry" >
						 	<tr onmouseover="this.style.backgroundcolor='white'" onmouseout="this.style.backgroundcolor='#F5FAFE';">
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="40%">${entry[0] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="30%"><a href="${pageContext.request.contextPath}/updateRight?applyid=${entry[0]}&agree=yes" style="text-decoration:underline;">同意</a></td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="30%"><a href="${pageContext.request.contextPath}/updateRight?applyid=${entry[0]}&agree=no" style="text-decoration:underline;">不同意</a></td>
						 	</tr>
						 </c:forEach>
				 </table>
			</td>
		</tr>
	</table>

</body>
</html>