<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>登入登出日志</title>
</head>
<body class="main">
	<form action="${pageContext.request.contextPath}/getLoginout" method="POST">
		<table width="100%" bgcolor="#cce7ea" style="border: 1px solid #8ba7e3">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26px"><strong>查&nbsp;&nbsp;&nbsp;询&nbsp;&nbsp;&nbsp;条&nbsp;&nbsp;&nbsp;件</strong></td>
			</tr>
			
			<tr>
				<td align="right" class="ta_01">查询日期范围：</td>
				<td class="ta_01"><input type="text" name="year" class="bg">年
					<select name="month" id="month">
						<option value="" selected="selected">--选择月份--</option>
						<option value="01">1</option>
						<option value="02">2</option>
						<option value="03">3</option>
						<option value="04">4</option>
						<option value="05">5</option>
						<option value="06">6</option>
						<option value="07">7</option>
						<option value="08">8</option>
						<option value="09">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
					</select>月
				</td>
				<td><span style="color:red">如不填写，默认返回当月数据</span></td>
			</tr>
			<tr>
				<td align="center" colSpan="4"><span style="color:red">因为5月才开始测试，所以暂时只有输2022年5月or6月才有返回值</span></td>
			</tr>
			<tr style="WIDTH:100%" align="right">
				<td class="ta_01" style="WIDTH: 100%" align="right"
					bgColor="#f5fafe" colSpan="4">
					<input type="reset" name="reset" class="button_view" value="重置" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" id="search" name="operation" value="search" class="button_view">
										查询				
					</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" id="download" name="operation" value="download" class="button_view">
						下载
					</button>
					
				</td>
			</tr>	
		</table>
	</form>
	<table width="100%">
		<TBODY>
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26px"><strong>登&nbsp;&nbsp;入&nbsp;&nbsp;登&nbsp;&nbsp;出&nbsp;&nbsp;日&nbsp;&nbsp;志</strong></td>
			</tr>
			<tr>
				<td>
					<table cellspacing="0" cellpadding="1" rules="all" bordercolor="green"
						border="1px" id="DataGrid1" style="BORDER-RIGHT:green 1px solid; BORDER-LEFT:green 1px solid;
						 BORDER-TOP:green 1px solid; BORDER-BOTTOM:green 1px solid; WIDTH:100%; WORD-BREAK:break-all;
						 BORDER-COLLAPSE: collapse; BACKGROUND-COLOR:#cce7ea;WORD-WRAP:break-word;">
						 <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
						 	<td align="center" width="15%">用户ID</td>
						 	<td align="center" width="20%">登入时间</td>
						 	<td align="center" width="20%">登出时间</td>
						 	<td align="center" width="12%">用户角色</td>
						 	<td align="center" width="20%">IP地址</td>
						 	<td align="center" width="13%">地域</td>
						 </tr>
						 <c:forEach items="${month_loginout}" var="p">
						 	<tr onmouseover="this.style.backgroundcolor='white'" onmouseout="this.style.backgroundcolor='#F5FAFE';">
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="15%">${p[0] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="20%">${p[1] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="20%">${p[2] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="12%">${p[3] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="20%">${p[4] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="13%">${p[5] }</td>
						 	</tr>
						 </c:forEach>
					</table>
				</td>
			</tr>
		</TBODY>
	</table>
</body>
</html>