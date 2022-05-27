package xianyu.web.servlet.client;
import java.io.*;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import xianyu.utils.GetRealIPUtils;
import xianyu.utils.GetProvinceUtils;
import xianyu.domain.User;
import javax.servlet.*;
import javax.servlet.http.*;
import xianyu.service.UserService;
import xianyu.Exception.LoginException;
import xianyu.service.UserService;
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取用户在登录页面上输入的用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserService us = new UserService();
		try {
			User user = us.returnUser(username, password);
			String ip = GetRealIPUtils.getIP(request);
			String province = GetProvinceUtils.getProvince(ip);
			if(!ip.equals("local")) {
				try {
					/* us.modify_map("广东省", user.getId()); */
					us.modify_map(province, user.getId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// 若登录成功，将用户信息暂存到session中
			request.getSession().setAttribute("user", user);
			String currentTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
			currentTime = URLEncoder.encode(currentTime,"utf-8");
			Cookie cookie = new Cookie("logintime",currentTime);
			cookie.setMaxAge(80*60);
			response.addCookie(cookie);
			// 获取用户的role，role分为“user”和“business”和“admin”三种
			String role = user.getRole();
			// 如果是管理员，就进入管理员后台管理系统；若是销售人员，进入销售人员后台管理系统；否则交给推荐servlet做进一步处理（纯用户角色）
			if (role.equals("admin")) {
				response.sendRedirect(request.getContextPath()+"/admin/login/home.jsp");
				return;
			} else if (role.equals("business")){
				response.sendRedirect(request.getContextPath()+"/businessman/login/home.jsp");
				return;
			}
			else{
				request.getRequestDispatcher("/recommend").forward(request, response);
				return;
			}
		} catch (LoginException e) {
			// 出现异常，将错误信息储存到request中，并将该request转发给登录页面进行显示
			e.printStackTrace();
			request.setAttribute("register_message", e.getMessage());
			request.getRequestDispatcher("/client/login.jsp").forward(request, response);
			return;
		}
	}
}