<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/admin/css/Style.css" type="text/css" rel="stylesheet">
	<title>咸鱼后台--客户购买记录</title>
	
</head>
<body class="main">
	<form action="${pageContext.request.contextPath}/orderManage" method="POST">
		<table width="100%" bgcolor="#cce7ea" style="border: 1px solid #8ba7e3">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26px"><strong>查&nbsp;&nbsp;&nbsp;询&nbsp;&nbsp;&nbsp;条&nbsp;&nbsp;&nbsp;件</strong></td>
			</tr>
			<tr height="30px">
				<td align="right" class="ta_01">用户编号：</td>
				<td class="ta_01"><input type="text" name="user_id" class="bg" /></td>
				<td align="right" class="ta_01">用户性别：</td>
				<td class="ta_01"><input type="text" name="gender" class="bg" />男/女</td>
				
			</tr>
			<tr height="30px">
				<td align="right" class="ta_01">日期范围：</td>
				<td class="ta_01"><input type="text" name="year" class="bg" />年
					<select name="month" id="month">
						<option value="" selected="selected">--选择月份--</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
					</select>月
				</td>
			</tr>
			<tr style="WIDTH:100%" align="right">
				<td class="ta_01" style="WIDTH: 100%" align="right"
					bgColor="#f5fafe" colSpan="4">
					<input type="reset" name="reset" class="button_view" value="重置" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" id="search" name="search" value="查询" class="button_view">
										查询
					</button>
				</td>
			</tr>	
		</table>
	</form>
	<table width="100%">
		<TBODY>
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26px"><strong>订&nbsp;&nbsp;&nbsp;单&nbsp;&nbsp;&nbsp;列&nbsp;&nbsp;&nbsp;表</strong></td>
			</tr>
			<tr>
				<td>
					<table cellspacing="0" cellpadding="1" rules="all" bordercolor="green"
						border="1px" id="DataGrid1" style="BORDER-RIGHT:green 1px solid; BORDER-LEFT:green 1px solid;
						 BORDER-TOP:green 1px solid; BORDER-BOTTOM:green 1px solid; WIDTH:100%; WORD-BREAK:break-all;
						 BORDER-COLLAPSE: collapse; BACKGROUND-COLOR:#cce7ea;WORD-WRAP:break-word;">
						 <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
						 	<td align="center" width="24%">商品编号</td>
						 	<td align="center" width="24%">用户编号</td>
						 	<td align="center" width="14%">用户性别</td>
						 	<td align="center" width="14%">消费金额</td>
						 	<td align="center" width="24%">日期</td>
						 </tr>
						 <c:forEach items="${od}" var="entry">
						 	<tr onmouseover="this.style.backgroundcolor='white'" onmouseout="this.style.backgroundcolor='#F5FAFE';">
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="24%">${entry[0] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="24%">${entry[1] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="14%">${entry[2] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="14%">${entry[3]*entry[4] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="24%">${entry[5] }</td>
						 	</tr>
						 </c:forEach>
					</table>
				</td>
			</tr>
		</TBODY>
	</table>

	
	
</body>
</html>