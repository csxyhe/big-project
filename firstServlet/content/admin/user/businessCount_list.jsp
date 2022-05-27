<%-- 呈现某销售人员销售业绩统计结果 --%>
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
	<title>咸鱼后台--销售业绩查询</title>
	<script type="text/javascript">
		//必须输入用户ID才能进行查询
		var userid;
		window.onload = function(){
			userid = document.getElementById("u_id");
		}
		function checkForm(){
			if (!userid){
				return false;
			}else{
				return true;
			}
		}
	</script>
</head>
<body class="main">
	<form action="${pageContext.request.contextPath}/businessStatistics" method="POST" onsubmit="return checkForm();">
		<table width="100%" bgcolor="#cce7ea" style="border: 1px solid #8ba7e3">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26px"><strong>查&nbsp;&nbsp;&nbsp;询&nbsp;&nbsp;&nbsp;条&nbsp;&nbsp;&nbsp;件</strong></td>
			</tr>
			<tr height="30px">
				<td align="right" class="ta_01">销售人员用户ID：</td>
				<td class="ta_01"><input type="text" name="u_id" id="u_id" class="bg" />&nbsp;&nbsp;<span style="color:red">ID不能为空</span></td>
			</tr>
			<tr>
				<td align="right" class="ta_01">日期范围：</td>
				<td class="ta_01"><input type="text" name="year" class="bg" id="year" />年
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
			<tr>
				<td align="center" colSpan="4"><span style="color:red">因为5月才开始测试，所以暂时只有输2022年5月or6月才有返回值</span></td>
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
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26px"><strong>业&nbsp;&nbsp;&nbsp;绩&nbsp;&nbsp;&nbsp;明&nbsp;&nbsp;&nbsp;细</strong></td>
			</tr>
			<tr>
				<td>
					<table cellspacing="0" cellpadding="1" rules="all" bordercolor="green"
						border="1px" id="DataGrid1" style="BORDER-RIGHT:green 1px solid; BORDER-LEFT:green 1px solid;
						 BORDER-TOP:green 1px solid; BORDER-BOTTOM:green 1px solid; WIDTH:100%; WORD-BREAK:break-all;
						 BORDER-COLLAPSE: collapse; BACKGROUND-COLOR:#cce7ea;WORD-WRAP:break-word;">
						 <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
						 	<td align="center" width="20%">商品名称</td>
						 	<td align="center" width="15%">商品价格</td>
						 	<td align="center" width="15%">商品数量</td>
						 	<td align="center" width="20%">商品类别</td>
						 	<td align="center" width="30%">交易时间</td>
						 </tr>
						 <c:set var="total_num" value="0" />
						 <c:set var="total_money" value="0" />
						 <c:forEach items="${psperb}" var="p">
						 	<tr onmouseover="this.style.backgroundcolor='white'" onmouseout="this.style.backgroundcolor='#F5FAFE';">
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="20%">${p[0] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="15%">${p[1] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="15%">${p[2] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="20%">${p[3] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="30%">${p[4] }</td>
						 		<c:set value="${total_num+p[2]}" var="total_num" />
						 		<c:set value="${total_money+p[2]*p[1]}" var="total_money" />
						 	</tr>
						 </c:forEach>
					</table>
				</td>
			</tr>
		</TBODY>
	</table>
	<table width="100%">
		<TBODY>
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26px"><strong>业&nbsp;&nbsp;&nbsp;绩&nbsp;&nbsp;&nbsp;统&nbsp;&nbsp;&nbsp;计</strong></td>
			</tr>
			<tr>
				<td>
					<table cellspacing="0" cellpadding="1" rules="all" bordercolor="green"
						border="1px" id="DataGrid1" style="BORDER-RIGHT:green 1px solid; BORDER-LEFT:green 1px solid;
						 BORDER-TOP:green 1px solid; BORDER-BOTTOM:green 1px solid; WIDTH:100%; WORD-BREAK:break-all;
						 BORDER-COLLAPSE: collapse; BACKGROUND-COLOR:#cce7ea;WORD-WRAP:break-word;">
						 <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
						 	<td align="center" width="50%">销售总额</td>
						 	<td align="center" width="50%">${total_money }元</td>
						 </tr>
						 <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
						 	<td align="center" width="50%">销售商品数量</td>
						 	<td align="center" width="50%">${total_num }件</td>
						 </tr>
					</table>
				</td>
			</tr>
		</TBODY>
	</table>
	
</body>
</html>