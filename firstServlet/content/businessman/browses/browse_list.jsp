<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/businessman/css/Style.css" type="text/css" rel="stylesheet">
	<title>咸鱼后台--客户浏览日志</title>
</head>
<body class="main">
	<table width="100%">
		<tr>
			<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="3" height="26px">
				<strong>客&nbsp;&nbsp;户&nbsp;&nbsp;浏&nbsp;&nbsp;览&nbsp;&nbsp;日&nbsp;&nbsp;志</strong>
			</td>
		</tr>
		<tr>
			<td>
				<table cellspacing="0" cellpadding="1" rules="all" bordercolor="green"
							border="1px" id="DataGrid1" style="BORDER-RIGHT:green 1px solid; BORDER-LEFT:green 1px solid;
							 BORDER-TOP:green 1px solid; BORDER-BOTTOM:green 1px solid; WIDTH:100%; WORD-BREAK:break-all;
							 BORDER-COLLAPSE: collapse; BACKGROUND-COLOR:#cce7ea;WORD-WRAP:break-word;">
						 <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
							 <td align="center" width="15%">用户名</td>
						 	 <td align="center" width="20%">商品名称</td>
						 	 <td align="center" width="25%">开始时间</td>	
						 	 <td align="center" width="25%">结束时间</td>
						 	 <td align="center" width="15%">浏览时长（秒）</td>
						 </tr>
						 <c:forEach items="${bs }" var="entry">
						 	<tr onmouseover="this.style.backgroundcolor='white'" onmouseout="this.style.backgroundcolor='#F5FAFE';">
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="15%">${entry[0] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="20%">${entry[1] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="25%">${entry[2] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="25%">${entry[3] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="15%">${entry[4] }</td>
						 	</tr>
						 </c:forEach>
						 
				 </table>
			 </td>
		 </tr>
	</table>
	
</body>
</html>