<%-- 选择菜单栏+搜索框 --%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>
	//鼠标点击搜索框时执行
	function my_click(obj, myid){
		//点击时，如果取得的值和搜索框默认value值相同，则将搜索框清空
		if (document.getElementById(myid).value == document.getElementById(myid).defaultValue){
		  document.getElementById(myid).value = '';
		  obj.style.color='#000';
		}
	}
	//鼠标不聚焦在搜索框时执行
	function my_blur(obj, myid){
		//鼠标失焦时，如果搜索框没有输入值，则用搜索框的默认value值填充
		if (document.getElementById(myid).value == ''){
		 document.getElementById(myid).value = document.getElementById(myid).defaultValue;
		 obj.style.color='#999';
	 }
	}
	// 点击search按钮提交表单
	//function search(){
	//	document.getElementById("searchform").submit();
	//}
</script>
<div id="divmenu">
	<a href="${pageContext.request.contextPath}/showProductByPage?category=文具">文具</a>
	<a href="${pageContext.request.contextPath}/showProductByPage?category=书籍">书籍</a>
	<a href="${pageContext.request.contextPath}/showProductByPage?category=数码产品">数码产品</a>
	<a href="${pageContext.request.contextPath}/showProductByPage?category=日用品">日用品</a>
	<a href="${pageContext.request.contextPath}/showProductByPage?category=运动器材">运动器材</a>
	<a href="${pageContext.request.contextPath}/showProductByPage?category=服装">服装</a>
	<a href="${pageContext.request.contextPath}/showProductByPage" style="color:#FFFF00">全部商品目录</a>
</div>
<div id="divsearch">
	<form action="${pageContext.request.contextPath}/menuSearch" id="searchform" method="post">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td style="text-align:right; padding-right:220px">Search
				<input type="text" name="textfield" class="inputtable"
					id="textfield" value="请输入商品名称" 
					onmouseover="this.focus();" onclick="my_click(this,'textfield');" 
					onBlur="my_blur(this,'textfield');" />
					<input type="submit" value="搜索" />
				</td>
			</tr>
		</table>
	</form>
</div>
