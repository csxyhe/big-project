//����������session�д���Ĳ�ͬ�����ض���modifyPassword.jspҳ�棬���������Ϣ

package xianyu.web.servlet.client;
import java.io.IOException;

import xianyu.Exception.LoginException;
import xianyu.domain.User;
import javax.servlet.*;
import javax.servlet.http.*;
import xianyu.service.UserService;
public class ModifyPasswordServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		User user = (User) request.getSession().getAttribute("user");
		String ori_password = user.getPassword();
		String oldpassword = request.getParameter("oldpassword");
		if (!ori_password.equals(oldpassword)) {
			request.setAttribute("match_message", "ԭ�������");
			request.getRequestDispatcher("/client/login.jsp").forward(request, response);
		}else {
			String newpassword = request.getParameter("newpassword");
			UserService us = new UserService();
			us.user_modifyPasswordAndRole(user.getId(), user.getRole(), newpassword);
			String username = user.getUsername();
			try {
				//����֮��Ҫ����session�д����userֵ
				User newUser = us.returnUser(username, newpassword);
				request.getSession().setAttribute("user", newUser);
				request.getRequestDispatcher("/client/modifysuccess.jsp").forward(request, response);
			} catch (LoginException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		
	}
}
