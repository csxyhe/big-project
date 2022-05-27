<%-- 注册界面 --%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>咸鱼电商--注册</title>
        <%-- 导入表单数据验证代码，直接嵌入的原因是：js文件的编码转换好像有问题，我尝试用文本编辑器把它改成utf-8后好了一段时间，后来又出现乱码 --%>
        <script  type="text/javascript">
		      //用于检验注册表单中的数据的合法性
		      //声明变量
		      var usernameObj;
		      var passwordObj;
		      var confirmObj;
		      var usernameMsg;
		      var passwordMsg;
		      var confirmMsg;
		      //页面加载之后，获取页面中的对象
		      window.onload = function(){
		          usernameObj = document.getElementById("username");
		          passwordObj = document.getElementById("password");
		          confirmObj = document.getElementById("repassword");
		          usernameMsg = document.getElementById("usernameMsg");
		          passwordMsg = document.getElementById("passwordMsg");
		          confirmMsg = document.getElementById("confirmMsg");
		      }
		      //校验整个表单
		      function checkForm(){
		          var bUsername = checkUsername();
		          var bPassword = checkPassword();
		          var bConfirm = checkConfirm();
		          //若上面四个值中有一个为False，事件将被取消
		          return bEmail && bUsername && bPassword && bConfirm;
		      }
		      //验证用户名
		      //返回bool值,TRUE表示合法
		      function checkUsername()
		      {
		          //设计一个用于验证用户名是否合法的正则表达式
		          //要求：字母数字或下划线4-10位，但不能是数字做开头
		          var regex = /^[a-zA-Z_]\w{3,9}$/;
		          var value = usernameObj.value;
		          var msg = "";
		          if(!value)
		              msg = "用户名必须填写";
		          else if(!regex.test(value))
		              msg = "用户名不合法";
		          //将msg嵌入到HTML代码中
		          usernameMsg.innerHTML = msg; 
		          return msg == "";    
		      }
		      //验证密码
		      //返回bool值,TRUE表示合法
		      function checkPassword()
		      {
		          //设计一个用于验证用户名是否合法的正则表达式
		          //要求：设置6-16位任意字符
		          var regex = /^.{6,16}$/;
		          var value = passwordObj.value;
		          var msg = "";
		          if(!value)
		              msg = "密码不能为空";
		          else if(!regex.test(value))
		              msg = "密码不合法";
		          //将msg嵌入到HTML代码中
		          passwordMsg.innerHTML = msg;  
		          return msg == "";     
		      }
		      //验证重复密码
		      //返回bool值,TRUE表示合法
		      function checkConfirm()
		      {
		          var ori_value = passwordObj.value
		          var value = confirmObj.value;
		          var msg = "";
		          if(!value)
		             { msg = "确认密码必须填写";}
		          else if(ori_value!=value)
		             { msg = "两次输入的密码不相同";}
		          //将msg嵌入到HTML代码中
		          confirmMsg.innerHTML = msg;  
		          return msg == "";  
		      }
        </script>
        <%-- 导入css界面设计代码 --%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
    </head>
    <body class="main">
    	<jsp:include page="head.jsp"></jsp:include>
    	<jsp:include page="menu_search.jsp"></jsp:include>
        <div id = "divcontent" align="center">
            <form action="${pageContext.request.contextPath}/register" method="post" onsubmit="return checkForm();">
                <table width="850px" border="0" align="left" cellspacing="0">
                    <tr>
                        <td style="padding: 30px"><h1>新用户注册</h1></td>
                        <table width="70%" border="0" cellspacing="2" class="upline">
                            <tr>
                                <td align="right"> 用户名：</td>
                                <td><input type="text" name="username" id="username" class="textinput" onkeyup="return checkUsername();" /></td>
                                <td colspan="2"><span style="color:red" id="usernameMsg"></span></td>
                                <td colspan="2"><font color="#999999">字母数字或下划线4-10位，但不能是数字做开头</font></td>
                            </tr>
                            <tr>
                                <td align="right"> 密码：</td>
                                <td><input type="password" name="password" id="password" class="textinput" onkeyup="return checkPassword();"/></td>
                                <td colspan="2"><span style="color:red" id="passwordMsg"></span></td>
                                <td><font color="#999999">密码请设置6-16位字符</font></td>
                            </tr>
                            <tr>
                                <td align="right"> 确认密码：</td>
                                <td><input type="password" name="repassword" id="repassword" class="textinput" placeholder="请再输入一遍密码" onkeyup="return checkConfirm();"/></td>
                                <td colspan="2"><span style="color:red" id="confirmMsg"></span>&nbsp;</td>
                            </tr>
                            <tr>
                                <td align="right">性别：</td>
                                <td><input type="radio" name="gender" value="M" />男<input type="radio" name="gender" value="F" />女</td>
                            </tr>
                        </table>
                     </tr>
                	<tr>
                		<table width="70%" border="0" cellspacing="0">
		                	<tr>
								<td style="padding-top: 20px; text-align: center">
									<input type="image" src="${pageContext.request.contextPath}/client/images/signup.gif" name="submit" border="0" width="140" height="35"/>
								</td>
							</tr>
                		</table>
                	</tr>
                </table>
            </form>
        </div>
    </body>
</html>
