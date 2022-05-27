package xianyu.web.servlet.manager;
import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

import xianyu.domain.User;
import xianyu.service.UserService;
import xianyu.utils.GetRealIPUtils;
import xianyu.utils.MailUtils;
public class CancelRightServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException,ServletException{
		int id = Integer.parseInt(request.getParameter("del_id"));
		UserService us = new UserService();
		User user = (User)request.getSession().getAttribute("user");
		int userid = user.getId();
		String role = user.getRole();
		String ip = GetRealIPUtils.getIP(request);
		us.modifyPasswordAndRole(id, "user", "123456",userid,role,"删除销售",ip);
		try {
			MailUtils.sendMail(us.returnEmail(id), "很抱歉，由于某些原因（比如销售情况异常、被用户举报），你的销售人员权限被撤销了，请使用默认密码123456进行登录", 3);
			request.getRequestDispatcher("/showBusiness").forward(request, response);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException,ServletException{
		doGet(request,response);	
	}
}
