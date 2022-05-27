<%-- 购物车页面 --%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="xianyu.domain.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>咸鱼电商--购物车</title>
	<%-- 导入css界面设计代码 --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
    <script type="text/javascript">
    	// 修改商品数量
    	function changeProductNum(count, totalCount, p_id) {
			count = parseInt(count);
			totalCount = parseInt(totalCount);
			// 如果数量为0，判断是否要删除商品
			if (count == 0) {
				var flag = window.confirm("确认删除商品吗?");
				if (!flag) {
					count = 1;
				}
			}
			// 如果想要修改的数量大于库存量，发出告警，并且维持原来的数量
			if (count > totalCount) {
				alert("已达到商品最大购买量");
				count = totalCount;
			}
			// 跳转到servlet中修改购物车内容后重新展示
			window.location.href = "${pageContext.request.contextPath}/changeCart?p_id=" + p_id + "&count=" + count;
		}
	  	// 删除购物车中的商品
		function cart_del() {   
		    var msg = "您确定要删除该商品吗？";   
		    if (confirm(msg)==true){   
		    	return true;   
		    }else{   
		    	return false;   
		    }   
	  	}
    </script>
</head>
<body>
<%
if(session.getAttribute("browseuser")!=null){
	session.removeAttribute("browseuser");
}
%>
	<jsp:include page="head.jsp"></jsp:include>
	<jsp:include page="menu_search.jsp"></jsp:include>
	<br />
	<br />
	<table width="100%" border="0" cellspacing="0">
		<tr>
			<td width="100%" style="text-align:center;">
				<img src="${pageContext.request.contextPath}/client/images/buy1.png" width="424px" height="32" /><br /><br />
			</td>
		</tr>
		<tr>
			<td>
				<table cellspacing="1" class="carttable">
					<tr>
						<td width="10%">序号</td>
						<td width="30%">商品名称</td>
						<td width="10%">价格</td>
						<td width="20%">数量</td>
						<td width="20%">小计</td>
						<td width="10%">取消</td>
					</tr>
				</table>
				<c:set var="total" value="0" />
				<c:forEach items="${cart }" var="entry" varStatus="vs">
					<table width="100%" border="0" cellspacing="0">
						<tr>
							<td width="10%">${vs.count }</td>
							<td width="30%">${entry.key.name }</td>
							<td width="10%">${entry.key.price }</td>
							<td width="20%">
								<%-- 减少商品数量按钮 --%>
								<input type="button" value="-" style="width:20px" onclick="changeProductNum('${entry.value-1}','${entry.key.pnum }','${entry.key.p_id }')" />
								<input name="text" type="text" value="${entry.value }" style="width:40px;text-align:center" />
								<%-- 添加商品数量按钮 --%>
								<input type="button" value="+" style="width:20px" onclick="changeProductNum('${entry.value+1}','${entry.key.pnum }','${entry.key.p_id }')" />
							</td>
							<td width="20%">${entry.key.price*entry.value }</td>
							<%-- 删除商品操作 --%>
							<td width="10%"><a href="${pageContext.request.contextPath}/changeCart?p_id=${entry.key.p_id }&count=0" style="color:#FF0000; font-weight:bold"
								onclick="javascript:return cart_del()">X</a></td>
						</tr>
					</table>
				<c:set value="${total+entry.key.price*entry.value}" var="total" />
				</c:forEach>
				<%-- 合计信息 --%>
				<table cellspacing="1" class="carttable">
					<tr>
						<td style="text-align:right; padding-right:40px;"><font color="#FF6600">合计：${total }元</font>
					</tr>
				</table>
				<div style="text-align:right; margin-top:10px;">
				<%-- 结账 --%>
					<a href="${pageContext.request.contextPath}/client/order.jsp">
						<img src="${pageContext.request.contextPath}/client/images/orderButton.png" border="0" height="32px" width="64px"/>
					</a>
				</div>
			</td>
		</tr>
				
	</table>
</body>
</html>