<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="${pageContext.request.contextPath}/businessman/css/Style.css" type="text/css" rel="stylesheet">
<!-- 引入 echarts.js -->
<script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
<title>销量预测图</title>
</head>
<body class="main">
	<div id="salesPredictFigure" style="width: 400px;height:400px;"></div>
    <script type="text/javascript">
    	var y_list =[];
    	<c:forEach items="${sales_list}" var="p">
    		y_list.push("${p}")
    	</c:forEach>
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('salesPredictFigure'));
 
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '根据当前销量预测明天销量',
               	x:'center'//居中放置
            },
            tooltip: {},
            legend: {
                data:['销量'],
                right:'0'
            },
            xAxis: {
                data: ['1','2','3','4','5','6','明天'],
                boundaryGap:false
            },
            yAxis: {
            	type:'value',
            	scale:true
            },
            series: [{
                name: '销量',
                type: 'line',
                data: y_list,
                label:{//将具体数值显示在柱体上方
	                show:true
	            	}
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
</body>
</html>