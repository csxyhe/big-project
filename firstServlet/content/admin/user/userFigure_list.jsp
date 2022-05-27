<%-- 展示用户画像数据 --%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/admin/css/Style.css" type="text/css" rel="stylesheet">
	<title>咸鱼后台--用户画像数据</title>
</head>
<body class="main">
	<form action="${pageContext.request.contextPath}/getUserFigure" method="POST">
		<table>
			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="right"
					bgColor="#f5fafe" colSpan="4">
					<button type="submit" id="search" name="operation" value="search" class="button_view">
										查看			
					</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" id="download" name="operation" value="download" class="button_view">
						下载
					</button>
				</td>
			</tr>
		</table>
	</form>
	<% if (request.getAttribute("isSearch")!=null) {%>
		<table width="100%">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="3" height="26px">
					<strong>用&nbsp;&nbsp;户&nbsp;&nbsp;画&nbsp;&nbsp;像&nbsp;&nbsp;数&nbsp;&nbsp;据</strong>
				</td>
			</tr>
			<tr>
				<td>
					<table cellspacing="0" cellpadding="1" rules="all" bordercolor="green"
								border="1px" id="DataGrid1" style="BORDER-RIGHT:green 1px solid; BORDER-LEFT:green 1px solid;
								 BORDER-TOP:green 1px solid; BORDER-BOTTOM:green 1px solid; WIDTH:100%; WORD-BREAK:break-all;
								 BORDER-COLLAPSE: collapse; BACKGROUND-COLOR:#cce7ea;WORD-WRAP:break-word;">
							 <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
							 	<td align="center" width="8%">用户ID</td>
							 	 <td align="center" width="8%">性别</td>
							 	 <td align="center" width="9">地域</td>
							 	 <td align="center" width="20%">消费偏好类别</td>
							 	 <td align="center" width="20%">最近一次消费间隔</td>
							 	 <td align="center" width="15%">近期消费次数</td>
							 	 <td align="center" width="20%">近期消费总额</td>
							 </tr>
							 <c:forEach items="${figuredata }" var="entry" >
							 	<tr onmouseover="this.style.backgroundcolor='white'" onmouseout="this.style.backgroundcolor='#F5FAFE';">
							 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="8%">${entry[0] }</td>
							 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="8%">${entry[1] }</td>
							 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="9%">${entry[2] }</td>
							 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="20%">${entry[3] }</td>
							 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="20%">${entry[4] }</td>
							 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="15%">${entry[5] }</td>
							 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="20%">${entry[6] }</td>
							 	</tr>
							 </c:forEach>
					 </table>
				</td>
			</tr>
		</table>
	<%} %>
</body>
</html>