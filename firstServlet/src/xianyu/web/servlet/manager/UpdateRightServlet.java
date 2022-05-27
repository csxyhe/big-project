//是否执行提升权限操作（让用户成为销售人员）
//传入两个参数，分别是申请者的用户ID和管理员点击的同意/不同意（applyid and agree）
//如果同意其成为销售人员，则1.修改其在users表中的role，2.重新生成一个12位的密码写入到Users表中，并发送到他填写的邮箱，3.删除applications表中的这条记录
//如果不同意其成为销售人员，则1.发送拒绝通知到他填写的邮箱，3.删除applications表中的这条记录
package xianyu.web.servlet.manager;
import java.io.IOException;
import java.sql.SQLException;

import xianyu.utils.BusinessPasswordUtils;
import xianyu.utils.GetRealIPUtils;
import xianyu.domain.User;
import xianyu.service.ApplicationService;
import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.http.*;
import xianyu.service.UserService;
import xianyu.utils.MailUtils;
public class UpdateRightServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		int applyid = Integer.parseInt(req.getParameter("applyid"));
		String agree = req.getParameter("agree");
		UserService us = new UserService();
		String email = us.returnEmail(applyid);//获得这个用户的邮箱
		if(agree.equals("yes")) {//同意
			String newPassword = BusinessPasswordUtils.BusinessPassword();
			User user = (User) req.getSession().getAttribute("user");
			int userid = user.getId();
			String role = user.getRole();
			String ip = GetRealIPUtils.getIP(req);
			us.modifyPasswordAndRole(applyid, "business", newPassword,userid,role,"添加销售",ip);
			String emailMsg="恭喜你获得销售人员权限，你新的登录密码是"+ newPassword +"。请保管好你的登录密码！";
			try {
				MailUtils.sendMail(email, emailMsg, 2);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {//不同意
			String emailMsg="很抱歉，你的申请未获得管理员的同意。";
			try {
				MailUtils.sendMail(email, emailMsg, 2);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ApplicationService as = new ApplicationService();
		try {//处理完后，在application表中删除这条记录
			as.deleteid(applyid);
			req.getRequestDispatcher("/showApplication").forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		doGet(req,resp);
	}
}
