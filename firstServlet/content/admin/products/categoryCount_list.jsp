<%-- 类别信息统计/按月份来查找 --%>
<%-- 每个类别统计信息包括类别名称，当月销量，当月销售额 --%>
<%-- 总共就六个类别，附个柱状统计图（直观） --%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- 引入 echarts.js -->
<script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
<title>类别信息统计</title>
</head>
<body class="main">
	<form action="${pageContext.request.contextPath}/categorysalesStatistics" method="POST">
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
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26px"><strong>类&nbsp;&nbsp;&nbsp;别&nbsp;&nbsp;&nbsp;统&nbsp;&nbsp;&nbsp;计</strong></td>
			</tr>
			<tr>
				<td>
					<table cellspacing="0" cellpadding="1" rules="all" bordercolor="green"
						border="1px" id="DataGrid1" style="BORDER-RIGHT:green 1px solid; BORDER-LEFT:green 1px solid;
						 BORDER-TOP:green 1px solid; BORDER-BOTTOM:green 1px solid; WIDTH:100%; WORD-BREAK:break-all;
						 BORDER-COLLAPSE: collapse; BACKGROUND-COLOR:#cce7ea;WORD-WRAP:break-word;">
						 <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
						 	<td align="center" width="10%">类别名称</td>
						 	<td align="center" width="12%">月销量</td>
						 	<td align="center" width="14%">月销售额</td>
						 </tr>
						 <c:forEach items="${csales}" var="c">
						 	<tr onmouseover="this.style.backgroundcolor='white'" onmouseout="this.style.backgroundcolor='#F5FAFE';">
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="20%">${c[0] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="20%">${c[1] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="10%">${c[2] }</td>
						 	</tr>
						 </c:forEach>
					</table>
				</td>
			</tr>
		</TBODY>
	</table>
	<% if(request.getAttribute("isShow")!=null){ %>
		<script type="text/javascript">
			//先定义三个空列表，分别从JSTL中读入三个列表
			var category_list = [];
			var num_list = [];
			var category_money_list = [];//name+value
			if ("${csales}"!=null)
			{
				<c:forEach items="${csales}" var="c">
					category_list.push("${c[0] }");
					num_list.push(parseInt("${c[1] }"));
					//money_list.push(parseInt("${c[2] }"));
					category_money_list.push({name:"${c[0] }",value:"${c[2] }"})
				</c:forEach>
			}
		</script>
		<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
		<!-- 画月销量图 -->
	    <div id="numFigure" style="width: 600px;height:300px;"></div>
	    <script type="text/javascript">
	        // 基于准备好的dom，初始化echarts实例
	        // 会将图表加载到页面中id为numFigure的位置中，即 div定义的空间内
	        var myChart = echarts.init(document.getElementById('numFigure'));
	 
	        // 指定图表的配置项和数据（两个数组）
	        var option = {
	            title: {
	                text: '月销售量',
	                x:'center'//居中放置
	            },
	            tooltip: {},
	            xAxis: {
	            	type:'category', //类目轴
	                data: category_list
	            },
	            yAxis: {
	            	type:'value' //数值轴，从series的data中找值
	            },
	            series: [
	            	{
	                name: '销量',
	                type: 'bar',
	                data: num_list,
	                markLine:{
	                    data:[
	                        {
	                       	name:'平均值',
	                        type:'average'
	                        
	                    	}
	                    ]
	                },
		            label:{//将具体数值显示在柱体上方
		                show:true,
		                position:'top'
		            	}
	            	}
	           	]
	        };
	 
	        // 使用刚指定的配置项和数据显示图表。
	        myChart.setOption(option);
	    </script>
	    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	    <!-- 画月销售额图 -->
		<div id="moneyFigure" style="width: 600px;height:400px;"></div>
	    <script type="text/javascript">
	        // 基于准备好的dom，初始化echarts实例
	        var myChart = echarts.init(document.getElementById('moneyFigure'));
	 
	        // 指定图表的配置项和数据（一个数组，里面放键值对）
	        var option = {
	            title: {
	                text: '月销售额',
	               	x:'center'//居中放置
	            },
	            series: [{
	                name: '销售额',
	                type: 'pie',
	                label:{
		            	normal:{
		            		formatter:'{b}:{c}'+'\n\r'+'({d}%)'
		            	}
		            },
	                data: category_money_list
	            }]
	        };
	 
	        // 使用刚指定的配置项和数据显示图表。
	        myChart.setOption(option);
    </script>
    <%} %>
</body>
</html>