<%-- 想做那种每行并排3个商品的效果，暂时还不知道行不行 --%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>咸鱼电商</title>
	<%-- 导入css界面设计代码 --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
</head>
<body class="main">
	<jsp:include page="head.jsp"></jsp:include>
   	<jsp:include page="menu_search.jsp"></jsp:include>
   	<div id="divpagecontent">
   		<h1>${bean.category}</h1>&nbsp;&nbsp;&nbsp;&nbsp;共${bean.totalNum}种商品
   		<table width="80%" cellspacing="0" style="border-collapse:separate; border-spacing:0px 10px;" align="center">
   				<c:forEach items="${bean.ps }" var="p" varStatus="status">
   					<c:if test="${status.count % 3 eq 1 }">
					<tr>
					</c:if>
		  				<td>
		  					<table>
		  						<tr>
		  							<td style="vertical-align: middle !important;">
		  								<div class="divpdpic">
											<a href="${pageContext.request.contextPath}/findProductById?p_id=${p.p_id }">
												<img class="divpdpic" src="${pageContext.request.contextPath }${p.imgurl }">
											</a>
										</div>
		  							</td>
		  						</tr>
		  						<tr>
		  							<td style="text-align:left;">名称：${p.name }</td>
		  						</tr>
		  						<tr>
		  							<td>价格：${p.price }元</td>
		  						</tr>
		  					</table>
						</td>
					<c:if test="${status.count % 3 eq 0 ||status.last eq true}">
					</tr>
					</c:if>
   				</c:forEach>
   		</table>
		<div class="pagination">
			<ul>
				<%-- 上一页 --%>
				<c:if test="${bean.currentPage!=1}">
					<li>
						<a class="disablepage_a" href="${pageContext.request.contextPath}/showProductByPage?currentPage=${bean.currentPage-1}&category=${bean.category}">
							上一页
						</a>
					</li>
				</c:if>
				<%-- 没有上一页 --%>
				<c:if test="${bean.currentPage==1}">
					<li><font color="grey">上一页</font><li>
				</c:if>
				<c:forEach begin="1" end="${bean.totalPage}" var="pageNum">
					<c:if test="${pageNum==bean.currentPage}">
						<li class="currentpage">${pageNum }</li>
					</c:if>
					<c:if test="${pageNum!=bean.currentPage}">
						<li><a href="${pageContext.request.contextPath}/showProductByPage?currentPage=${pageNum}&category=${bean.category}">${pageNum}</a>
						</li>
					</c:if>
				</c:forEach>
				<%-- 没有下一页 --%>
				<c:if test="${bean.currentPage==bean.totalPage||bean.totalPage==0}">
					<li><font color="grey">下一页</font></li>
				</c:if>
				<%-- 下一页 --%>
				<c:if test="${bean.currentPage!=bean.totalPage&&bean.totalPage!=0}">
					<li>
						<a class="disablepage_a" href="${pageContext.request.contextPath}/showProductByPage?currentPage=${bean.currentPage+1}&category=${bean.category}">
							下一页
						</a>
					</li>
				</c:if>
			</ul>
		</div>
   		
   	</div>
</body>   