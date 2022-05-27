// 从数据库中读出所有role为'business'的用户信息，并且返回给business_list.jsp页面
package xianyu.web.servlet.manager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import xianyu.domain.User;
import xianyu.service.UserService;
import xianyu.utils.GetRealIPUtils;
public class ShowBusinessServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		UserService us = new UserService();
		List<Object[]> bl = new ArrayList<Object[]>();
		try {
			User user = (User) request.getSession().getAttribute("user");
			int userid = user.getId();
			String role = user.getRole();
			String ip = GetRealIPUtils.getIP(request);
			bl = us.returnBusiness(userid, role,"查询销售","销售人员名单",ip);
			request.setAttribute("bl", bl);
			request.getRequestDispatcher("/admin/user/business_list.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
			
	}
}
