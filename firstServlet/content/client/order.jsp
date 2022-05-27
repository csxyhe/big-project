<%-- 订单信息页面 --%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="xianyu.domain.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>咸鱼电商--结算中心</title>
	<%-- 导入css界面设计代码 --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
    <script type="text/javascript">
  		//用于检验注册表单中的数据的合法性
   		//声明变量
    	var addressObj;
    	var nameObj;
    	var emailObj;
    	var addressMsg;
    	var nameMsg;
    	var emailMsg;
    	//页面加载之后，获取页面中的对象
    	window.onload = function(){
    		addressObj = document.getElementById("receiverAddress");
    		nameObj = document.getElementById("receiverName");
    		emailObj = document.getElementById("receiverEmail");
    		addressMsg = document.getElementById("receiverAddressMsg");
    		nameMsg = document.getElementById("receiverNameMsg");
    		emailMsg = document.getElementById("receiverEmailMsg");
    	}
    	//校验用户输入的数据
    	function checkOnSubmit(){
    		var bAddress = checkReceiverAddress();
    		var bName = checkReceiverName();
    		var bEmail = checkReceiverEmail();
    		if (bAddress && bName && bEmail){
    			document.getElementById("orderForm").submit();
    		}
    		else{
    			return "";
    		}	
    	}
    	//验证地址
    	function checkReceiverAddress() {	
			var value =addressObj.value;
			var msg = "";
			if (!value)
				msg = "收货地址必须填写";	
			addressMsg.innerHTML = msg;
			return msg == "";
		}
    	//验证姓名
    	function checkReceiverName() {	
			var value =nameObj.value;
			var msg = "";
			if (!value)
				msg = "收货人必须填写";	
			nameMsg.innerHTML = msg;
			return msg == "";
		}
    	//验证邮箱信息
    	function checkReceiverEmail(){
    		var regex = /^[\w-]+@([\w-]+\.)+[a-zA-Z]{2,4}$/;
			var value = emailObj.value;
			var msg = "";
			if (!value)
    			msg = "邮箱必须填写";
			if(!regex.test(value))
				msg="邮箱格式不合法";
			emailMsg.innerHTML = msg;
			return msg =="";
    	}
    </script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<jsp:include page="menu_search.jsp"></jsp:include>
	<br />
	<br />
	<form id="orderForm" action="${pageContext.request.contextPath }/createOrder" method="post">
		<table cellspacing="0" class="infocontent">
			<tr>
				<td style="text-align:center;">
					<img src="${pageContext.request.contextPath }/client/images/buy2.png" width="424px" height="32" /><br />
				</td>
			</tr>
			<tr>
				<td>
					<table cellspacing="1" class="carttable">
						<tr>
							<td width="10%">序号</td>
							<td width="30%">商品名称</td>
							<td width="20%">类别</td>
							<td width="20%">价格</td>
							<td width="10%">数量</td>
							<td width="10%">小计</td>
						</tr>
					</table>
					<c:set value="0" var="totalPrice" />
					<c:forEach items="${cart }" var="entry" varStatus="vs">
						<table width="100%" border="0" cellspacing="0">
							<tr>
								<td width="10%">${vs.count }</td>
								<td width="30%">${entry.key.name }</td>
								<td width="20%">${entry.key.category }</td>
								<td width="20%">${entry.key.price }</td>
								<td width="10%"><input type="text" value="${entry.value }" style="width:20px" readonly="readonly" /></td>
								<td width="10%">${entry.key.price*entry.value }</td>
						</tr>
						</table>
					<c:set value="${totalPrice + entry.key.price*entry.value }" var="totalPrice" />
					</c:forEach>
					<table cellspacing="1" class="carttable">
						<tr>
							<td style="text-align:right; padding-right:40px;">
								<font style="color:#FF0000">合计：&nbsp;&nbsp;&nbsp;&nbsp;${totalPrice }元</font>
								<input type="hidden" name="money" value="${totalPrice }"><!--  用户不可见，用于表单数据上传 -->
							</td>
						</tr>
					</table>
					<p>
						收货地址：<input type="text" name="receiverAddress" id="receiverAddress" 
							value="" style="width:350px" onkeyup="checkReceiverAddress();" />
							&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red" id="receiverAddressMsg"></span>
						<br /><br />
						收货人：&nbsp;&nbsp;&nbsp;<input type="text" name="receiverName" id="receiverName" value="${user.username }" onkeyup="checkReceiverName();"/>
						&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red" id="receiverNameMsg"></span>
						<br /><br />
						邮箱：<input type="text" name="receiverEmail" id="receiverEmail" value="${user.email }" onkeyup="checkReceiverEmail();" />
						&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red" id="receiverEmailMsg"></span>
					</p>
						<img src="${pageContext.request.contextPath }/client/images/submitOrder.png" width="50px" height="25px" border="0" onclick="checkOnSubmit();"/>
					<p>
						
					</p>
		</table>
	</form>
</body>
</html>