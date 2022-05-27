// 根据用户是否已经登录来进行网页重定向操作
package xianyu.web.servlet.client;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import xianyu.domain.User;

public class MyAccountServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException, IOException{
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
		/*
		 * 根据session中是否有"user"属性值来判断用户是否已经完成登录行为
		 * 若有"user"属性则证明已经登录了
		 * 未登录->/client/login.jsp
		 * 再通过User对象中的role属性值，判断该用户的身份
		 * '用户'->/client/myAccount.jsp
		 * '管理员'->/admin/login/home.jsp
		 */
		User user = (User) request.getSession().getAttribute("user");
		if(user==null) {
				response.sendRedirect(request.getContextPath()+"/client/login.jsp");
				return;
		}
		String role = user.getRole();
		if(role.equals("user")) {
			response.sendRedirect(request.getContextPath()+"/client/myAccount.jsp");
			return;
		}else if(role.equals("business")) {
			response.sendRedirect(request.getContextPath()+"/businessman/login/home.jsp");
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/login/home.jsp");
			return;
		}
	}
}
