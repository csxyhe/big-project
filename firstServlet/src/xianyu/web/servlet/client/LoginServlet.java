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
		// ��ȡ�û��ڵ�¼ҳ����������û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserService us = new UserService();
		try {
			User user = us.returnUser(username, password);
			String ip = GetRealIPUtils.getIP(request);
			String province = GetProvinceUtils.getProvince(ip);
			if(!ip.equals("local")) {
				try {
					/* us.modify_map("�㶫ʡ", user.getId()); */
					us.modify_map(province, user.getId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// ����¼�ɹ������û���Ϣ�ݴ浽session��
			request.getSession().setAttribute("user", user);
			String currentTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
			currentTime = URLEncoder.encode(currentTime,"utf-8");
			Cookie cookie = new Cookie("logintime",currentTime);
			cookie.setMaxAge(80*60);
			response.addCookie(cookie);
			// ��ȡ�û���role��role��Ϊ��user���͡�business���͡�admin������
			String role = user.getRole();
			// ����ǹ���Ա���ͽ������Ա��̨����ϵͳ������������Ա������������Ա��̨����ϵͳ�����򽻸��Ƽ�servlet����һ���������û���ɫ��
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
			// �����쳣����������Ϣ���浽request�У�������requestת������¼ҳ�������ʾ
			e.printStackTrace();
			request.setAttribute("register_message", e.getMessage());
			request.getRequestDispatcher("/client/login.jsp").forward(request, response);
			return;
		}
	}
}