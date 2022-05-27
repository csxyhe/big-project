<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Calendar" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
		function openWin(product_id){
			var iHeight = 500;
			var iWidth = 500;
			//window.screen.height/window.screen.width分别获取屏幕的高度和宽度
			//让小图略微居中
			var iTop = (window.screen.height-30-iHeight)/2;
			var iLeft = (window.screen.width-30-iWidth)/2;
			window.open(
				'${pageContext.request.contextPath}/predictSales?p_id='	+ product_id,
				'',
				'height=' + iHeight + ',width=' + iWidth + ',top=' + iTop + ',left=0' + iLeft + ',toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no'
			)
		}
	</script>
	<link href="${pageContext.request.contextPath}/businessman/css/Style.css" type="text/css" rel="stylesheet">
	<title>咸鱼后台--销量统计</title>
</head>
<body class="main">
	<form action="${pageContext.request.contextPath}/showStatistics" method="POST">
		<table width="100%" bgcolor="#cce7ea" style="border: 1px solid #8ba7e3">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26px"><strong>查&nbsp;&nbsp;&nbsp;询&nbsp;&nbsp;&nbsp;条&nbsp;&nbsp;&nbsp;件</strong></td>
			</tr>
			
			<tr>
				<td align="right" class="ta_01">查询日期范围：</td>
				<td class="ta_01"><input type="text" name="year" id="year" class="bg">年
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
			<tr style="WIDTH:100%" align="right">
				<td class="ta_01" style="WIDTH: 100%" align="right"
					bgColor="#f5fafe" colSpan="4">
					<input type="reset" name="reset" class="button_view" value="重置" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" id="search" name="operation" value="search" class="button_view">
										查询				
					</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>	
		</table>
	</form>
	<table width="100%">
		<tr>
			<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="3" height="26px"><strong>销&nbsp;&nbsp;&nbsp;量&nbsp;&nbsp;&nbsp;统&nbsp;&nbsp;&nbsp;计</strong></td>
		</tr>
		<tr>
			<td>
				<%
			 	 	boolean isPredict = false;//isPredict表示是否出现销售预测曲线查询按钮
			 	 	//只有查询该月数据时，出现销量预测按钮
			 	 	String year = (String)request.getAttribute("year");
			 	 	String month = (String)request.getAttribute("month");
			 	 	if(year==null || month==null){
			 	 		isPredict = true;
			 	 	}else{
			 	 		Calendar now = Calendar.getInstance();
				 	 	String nowYear = now.get(Calendar.YEAR) + "";
				 	 	String nowMonth;
				 	 	int nowMon= now.get(Calendar.MONTH) + 1;
				 	 	if(nowMon<10){
				 	 		nowMonth = "0"+nowMon;
				 	 	}else{
				 	 		nowMonth = ""+nowMon;
				 	 	}
				 	 	if(year.equals(nowYear) && month.equals(nowMonth)){
				 	 		isPredict = true;
				 	 	}
			 	 	}
			 	 	
			 	 %>
				<table cellspacing="0" cellpadding="1" rules="all" bordercolor="green"
							border="1px" id="DataGrid1" style="BORDER-RIGHT:green 1px solid; BORDER-LEFT:green 1px solid;
							 BORDER-TOP:green 1px solid; BORDER-BOTTOM:green 1px solid; WIDTH:100%; WORD-BREAK:break-all;
							 BORDER-COLLAPSE: collapse; BACKGROUND-COLOR:#cce7ea;WORD-WRAP:break-word;">
						 <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
						 	 <%
						 	 	if(isPredict){
				 	 		 %>
				 	 		 <td align="center" width="25%">商品编号</td>
						 	 <td align="center" width="20%">商品名称</td>
						 	 <td align="center" width="20%">商品销量</td>
				 	 		 <td align="center" width="20%">库存量</td> 
				 	 		 <td align="center" width="15%"></td>
				 	 		 <%
						 	 	}else{
						 	 %>
						 	 <td align="center" width="30%">商品编号</td>
						 	 <td align="center" width="30%">商品名称</td>
						 	 <td align="center" width="20%">商品销量</td>
					 	 	 <td align="center" width="20%">库存量</td>
						 	 <%
						 	 	}
						 	 %>
						 </tr>
						 <c:forEach items="${statis }" var="entry">
						 	<tr onmouseover="this.style.backgroundcolor='white'" onmouseout="this.style.backgroundcolor='#F5FAFE';">
						 		<%
							 	 	if(isPredict){
					 	 		 %>
					 	 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="25%">${entry[0] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="20%">${entry[1] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="20%">${entry[2] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="20%">${entry[3] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="15%"><button onclick="openWin('${entry[0]}')">销量预测</button></td>
					 	 		 <%
							 	 	}else{
							 	 %>
							 	<td style="CURSOR: hand;HEIGHT:22px" align="center" width="30%">${entry[0] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="30%">${entry[1] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="20%">${entry[2] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="20%">${entry[3] }</td>
							 	 <%
							 	 	}
							 	 %>
						 		
						 	</tr>
						 </c:forEach>
						 
				 </table>
			 </td>
		 </tr>
	</table>
	
</body>
</html>