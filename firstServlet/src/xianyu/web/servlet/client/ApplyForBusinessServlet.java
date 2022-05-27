//模拟用户向管理员提交相关资质证明，管理员审核通过后，给他发送显示商家身份秘钥的过程
//用于用户向管理员的发送申请请求（不能发邮件[这变成了跨平台，不方便]，改成在数据库中新建一张表储存这些请求ID）
package xianyu.web.servlet.client;
import java.io.IOException;
import java.sql.SQLException;

import xianyu.service.UserService;
import xianyu.service.ApplicationService;
import javax.servlet.*;
import javax.servlet.http.*;
import xianyu.domain.User;
public class ApplyForBusinessServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doPost(req,resp);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		User user = ((User)req.getSession().getAttribute("user"));
		int id = user.getId();
		String newEmail = req.getParameter("email");
		String oldEmail = user.getEmail();
		if (oldEmail==null || !newEmail.equals(oldEmail)) {
			//如果原来没有填入邮箱信息，或者新填写的邮箱跟存储的邮箱不一致，则重新写入数据库
			UserService us = new UserService();
			us.modifyEmail(newEmail, id);
		}
		ApplicationService as = new ApplicationService();
		try {
			as.addApplication(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		//做完后，跳转到申请成功界面
		resp.sendRedirect(req.getContextPath() + "/client/applysuccess.jsp");
	}
}
