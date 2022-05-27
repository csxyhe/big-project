<%-- 因为电商平台中每个页面抬头部分的信息是相同的，将它们抽取到head.jsp中 --%>
<%-- 判断用户是否已经登录，若未登录，页面显示“新用户注册”选项；若已经登录，页面显示“注销”选项 --%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="xianyu.domain.User"%>
<div id="divhead">
	<table class="headtable">
		<tr>
			<td>
				<a href="#">
					<img src="${pageContext.request.contextPath}/client/images/fish.jpg" width="200" height="80" border="0" />
				</a>
			</td>
			<td style="text-align:right">
				<img src="${pageContext.request.contextPath}/client/images/buycar.jpg" width="26" height="23"
					style="margin-bottom: -4px" />&nbsp;
				<a href="${pageContext.request.contextPath}/client/cart.jsp">购物车</a>
				|<a href="${pageContext.request.contextPath}/myAccount">我的账户</a>
				<%
				User user = (User) request.getSession().getAttribute("user");
				if (user == null){
				%>
				|<a href="${pageContext.request.contextPath}/client/register.jsp">新用户注册</a>
				<%
				}else{
				%>
				|<a href="${pageContext.request.contextPath}/client/modifyPassword.jsp">修改密码</a>
				|<a href="${pageContext.request.contextPath}/client/ConfirmEmail.jsp">成为商家</a>
				|<a href="${pageContext.request.contextPath}/logout" target="_parent" onclick="javascript:return confirm_logout()">注销</a>
				<%} %>
			</td>
		</tr>
	</table>
</div>
