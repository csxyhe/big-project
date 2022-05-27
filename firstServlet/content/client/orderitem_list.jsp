<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%-- 导入css界面设计代码 --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
	<title>购买详情</title>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
   	<jsp:include page="menu_search.jsp"></jsp:include>
   	<table cellspacing="0" class="infocontent">
		<TBODY>
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26px"><strong>购&nbsp;&nbsp;买&nbsp;&nbsp;详&nbsp;&nbsp;情</strong></td>
			</tr>
			<tr>
				<td>
					<table cellspacing="1" class="carttable">
						<tr>
						 	<td align="center" width="20%">商品编号</td>
						 	<td align="center" width="20%">商品名</td>
						 	<td align="center" width="20%">单价</td>
						 	<td align="center" width="20%">类别</td>
						 	<td align="center" width="20%">购买数量</td>
						 </tr>
						 <c:forEach items="${oi_list}" var="p">
						 	<tr onmouseover="this.style.backgroundcolor='white'" onmouseout="this.style.backgroundcolor='#F5FAFE';">
						 		<td align="center" width="20%">${p[0] }</td>
						 		<td align="center" width="20%">${p[1] }</td>
						 		<td align="center" width="20%">${p[2] }</td>
						 		<td align="center" width="20%">${p[3] }</td>
						 		<td align="center" width="20%">${p[4] }</td>
						 	</tr>
						 </c:forEach>
					</table>
				</td>
			</tr>
		</TBODY>
	</table>
</body>
</html>