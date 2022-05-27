<%-- 展示拥有销售人员身份的用户名单 --%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/admin/css/Style.css" type="text/css" rel="stylesheet">
	<title>咸鱼后台--销售人员名单</title>
	<script type="text/javascript">
		function confirmDel(param){
			if(window.confirm("确定要撤销该用户的销售人员权限？")){
				document.location="cancelRight?del_id="+param;
			}
		}
	</script>
</head>
<body class="main">
	<table width="100%">
		<tr>
			<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="3" height="26px">
				<strong>销&nbsp;&nbsp;售&nbsp;&nbsp;人&nbsp;&nbsp;员&nbsp;&nbsp;名&nbsp;&nbsp;单</strong>
			</td>
		</tr>
		<tr>
			<td>
				<table cellspacing="0" cellpadding="1" rules="all" bordercolor="green"
							border="1px" id="DataGrid1" style="BORDER-RIGHT:green 1px solid; BORDER-LEFT:green 1px solid;
							 BORDER-TOP:green 1px solid; BORDER-BOTTOM:green 1px solid; WIDTH:100%; WORD-BREAK:break-all;
							 BORDER-COLLAPSE: collapse; BACKGROUND-COLOR:#cce7ea;WORD-WRAP:break-word;">
						 <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
						 	<td align="center" width="40%">用户ID</td>
						 	 <td align="center" width="30%">用户名</td>
						 	 <td align="center" width="30%"></td>
						 </tr>
						 <c:forEach items="${bl }" var="entry" >
						 	<tr onmouseover="this.style.backgroundcolor='white'" onmouseout="this.style.backgroundcolor='#F5FAFE';">
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="40%">${entry[0] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="30%">${entry[1] }</td>
						 		<td style="CURSOR: hand;HEIGHT:22px" align="center" width="30%"><input type="button" value="删除 "  onclick="confirmDel(${entry[0] })"/></td>
						 	</tr>
						 </c:forEach>
				 </table>
			</td>
		</tr>
	</table>

</body>
</html>