<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/admin/css/Style.css" type="text/css" rel="stylesheet">
	<title>咸鱼后台--编辑商品</title>
	<script type="text/javascript">
		function setCategory(t){
			var category = document.getElementById("category");//这个category变量关联到该jsp文件中id为category的变量
			var ops = category.options;
			for(var i=0;i<ops.length;i++){
				if (ops[i].value == t){
					ops[i].selected="selected";//设置其为默认选项
					return;
				}
			}
		};
	</script>
</head>
<body onload="setCategory('${p.category }')">
	<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/editProduct" enctype="multipart/form-data" method="POST">
		<input type="hidden" name="p_id" value="${p.p_id}" />&nbsp;
		<table width="100%" bgcolor="#cce7ea" style="border: 1px solid #8ba7e3">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26px"><strong>编辑商品</strong></td>
			</tr>
			<tr height="30px">
				<td align="right" class="ta_01">商品名称：</td>
				<td class="ta_01"><input type="text" name="name" class="bg" value="${p.name }" /></td>
				<td align="right" class="ta_01">商品价格：</td>
				<td class="ta_01"><input type="text" name="price" class="bg" value="${p.price }"/>元</td>
			</tr>
			<tr height="30px">
				<td align="right" class="ta_01">商品数量：</td>
				<td class="ta_01"><input type="text" name="pnum" class="bg" value="${p.pnum }"/></td>
				<td align="right" class="ta_01">商品类型：</td>
				<td class="ta_01">
					<select name="category" id="category">
						<option value="">--选择商品类别--</option>
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
				<td align="right" class="ta_01">商品图片：</td>
				<td class="ta_01" colspan="3"><input type="file" name="upload" size="30" value=""/></td>
			</tr>
			<tr height="150px">
				<td align="right" class="ta_01">商品描述：</td>
				<td class="ta_01"><textarea rows="5" cols="50" name="description" style="WIDTH: 96%">${p.description }</textarea></td>
			</tr>
			<tr style="WIDTH: 100%" align="center">
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="4">
					<input type="submit" name="submit" class="button_ok" value="确定" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" name="reset" class="button_cancel" value="重置" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" name="return" class="button_ok" value="返回"  onclick="history.go(-1)" />
					<span id="Label1"></span>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>