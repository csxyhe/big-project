<%-- 销售人员仅能查看自己所销售的商品的情况 --%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/businessman/css/Style.css" type="text/css" rel="stylesheet">
	<title>咸鱼后台--查看商品</title>
	<script type="text/javascript">
		//跳转到添加商品界面
		function addProduct() {
			window.location.href = "${pageContext.request.contextPath}/businessman/products/add.jsp";
		}
	</script>
</head>
<body class="main">
	<form action="${pageContext.request.contextPath}/findProductByManyCondition_B" method="POST">
		<table width="100%" bgcolor="#cce7ea" style="border: 1px solid #8ba7e3">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26px"><strong>查&nbsp;&nbsp;&nbsp;询&nbsp;&nbsp;&nbsp;条&nbsp;&nbsp;&nbsp;件</strong></td>
			</tr>
			<tr height="30px">
				<td align="right" class="ta_01">商品编号：</td>
				<td class="ta_01"><input type="text" name="p_id" class="bg" /></td>
				<td align="right" class="ta_01">商品类型：</td>
				<td class="ta_01">
					<select name="category" id="category">
						<option value="" selected="selected">--选择商品类别--</option>
						<option value="文具">文具</option>
						<option value="书籍">书籍</option>
						<option value="数码产品">数码产品</option>
						<option value="日用品">日用品</option>
						<option value="运动器材">运动器材</option>
						<option value="服装">服装</option>
					</select>
				</td>
			</tr>
			<tr height="30px">
				<td align="right" class="ta_01">商品名称：</td>
				<td class="ta_01"><input type="text" name="name" class="bg" /></td>
				<td align="right" class="ta_01">价格区间（元）：</td>
				<td class="ta_01"><input type="text" name="minprice" class="bg" />--<input type="text" name="maxprice" class="bg" /></td>
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
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26px"><strong>商&nbsp;&nbsp;&nbsp;品&nbsp;&nbsp;&nbsp;列&nbsp;&nbsp;&nbsp;表</strong></td>
			</tr>
			<tr>
				<td><input type="button" class="button_add" value="添加" onclick="addProduct()"></td>
			</tr>
			<tr>
				<td>
					<table cellspacing="0" cellpadding="1" rules="all" bordercolor="green"
						border="1px" id="DataGrid1" style="BORDER-RIGHT:green 1px solid; BORDER-LEFT:green 1px solid;
						 BORDER-TOP:green 1px solid; BORDER-BOTTOM:green 1px solid; WIDTH:100%; WORD-BREAK:break-all;
						 BORDER-COLLAPSE: collapse; BACKGROUND-COLOR:#cce7ea;WORD-WRAP:break-word;">
						 <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
						 	<td align="center" width="24%">商品编号</td>
						 	<td align="center" width="18%">商品名称</td>
						 	<td align="center" width="9%">商品价格</td>
						 	<td align="center" width="9%">商品数量</td>
						 	<td align="center" width="8%">商品类别</td>
						 	<td align="center" width="8%">编辑</td>
						 	<td align="center" width="8%">删除</td>
						 </tr>
						 <c:forEach items="${ps}" var="p">
						 	<tr onmouseover="this.style.backgroundcolor='white'" onmouseout="this.style.backgroundcolor='#F5FAFE';">
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="200">${p.p_id }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="18%">${p.name }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="8%">${p.price }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="8%">${p.pnum }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center">${p.category }</td>
						 		<td align="center" style="HEIGHT:22px" width="7%">
						 			<a href="${pageContext.request.contextPath}/findProductById?p_id=${p.p_id }&type=business">
						 				<img src="${pageContext.request.contextPath}/businessman/images/i_edit.gif" border="0" style="CURSOR: hand"/>
						 			</a>
						 		</td>
						 		<td align="center" style="HEIGHT:22px" width="7%">
						 			<a href="${pageContext.request.contextPath}/deleteProduct?p_id=${p.p_id }">
						 				<img src="${pageContext.request.contextPath}/businessman/images/i_del.gif" border="0" 
						 					width="16" height="16" style="CURSOR: hand"/>
						 			</a>
						 		</td>
						 	</tr>
						 </c:forEach>
					</table>
				</td>
			</tr>
		</TBODY>
	</table>

	
	
</body>
</html>