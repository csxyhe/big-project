<%-- 销售异常统计，如当前无异常，则输出“无异常”--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/admin/css/Style.css" type="text/css" rel="stylesheet">
	<title>咸鱼后台--销售异常统计</title>
</head>
<body class="main">
	<%
		List<Object[]> ab = (List<Object[]>)request.getAttribute("abnormal");
		if(ab.isEmpty()){
	%>
	当前没有统计到销售异常哟！
	<%
		}else{
			
	%>
	<table width="100%">
		<tr>
			<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="3" height="26px">
				<strong>销&nbsp;&nbsp;售&nbsp;&nbsp;异&nbsp;&nbsp;常</strong>
			</td>
		</tr>
		<tr>
			<td>
				<table cellspacing="0" cellpadding="1" rules="all" bordercolor="green"
							border="1px" id="DataGrid1" style="BORDER-RIGHT:green 1px solid; BORDER-LEFT:green 1px solid;
							 BORDER-TOP:green 1px solid; BORDER-BOTTOM:green 1px solid; WIDTH:100%; WORD-BREAK:break-all;
							 BORDER-COLLAPSE: collapse; BACKGROUND-COLOR:#cce7ea;WORD-WRAP:break-word;">
						 <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
						 	<td align="center" width="30%">销售人员ID</td>
						 	 <td align="center" width="30%">累计刷单次数</td>
						 	 <td align="center" width="40%">时间段</td>
						 </tr>
						 <c:forEach items="${abnormal }" var="entry" >
						 	<tr onmouseover="this.style.backgroundcolor='white'" onmouseout="this.style.backgroundcolor='#F5FAFE';">
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="30%">${entry[0] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="30%">${entry[1] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="40%">${entry[2] }</td>
						 	</tr>
						 </c:forEach>
				 </table>
			</td>
		</tr>
	</table>
	<%} %>
</body>
</html>