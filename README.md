# JavaWeb
<p>an online shopping website written by Servlet, JavaBean and JSP</p>
<p>做了一个叫做咸鱼的小型电商网站</p>
<p>有数据可视化、数据批量导出、销量预测、用户个性化推荐等功能^-^</p>
<h2>学号：201930341069</h2>
<h2>姓名：何旭怡</h2>

<h3>介绍一下各个package下的java代码的作用：</h3>
<ul>
  <li>xianyu.Dao:跟数据库进行交互</li>
  <li>xianyu.domain:各个JavaBean，用于封装数据的类</li>
  <li>xianyu.Exception:异常处理</li>
  <li>xianyu.service:使用xianyu.Dao中的方法为servlet提供service方法</li>
  <li>xianyu.utils:各种工具，包括随机生成32位编号，发邮件等等</li>
  <li>xianyu.web.listener:监视用户浏览商品时长</li>
  <li>xianyu.web.filter:统一全站编码</li>
  <li>xianyu.web.servlet.client:处理客户请求的各种Servlet</li>
  <li>xianyu.web.servlet.manager:处理管理员请求的各种Servlet</li>
  <li>xianyu.web.servlet.business:处理销售人员请求的各种Servlet</li>
</ul>

