// 用于完成用户此次登录的注销，且释放该用户占用的系统资源（如数据库连接线程）
package xianyu.web.servlet.client;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import xianyu.utils.GetRealIPUtils;
import javax.servlet.*;
import javax.servlet.http.*;
import xianyu.domain.User;
import xianyu.service.LoginoutService;
public class LogoutServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		if(request.getSession(false).getAttribute("browseuser")!=null) {
			request.getSession(false).removeAttribute("browseuser");
		}
		// 销毁session对象中的user属性，记录本次登入登出信息，然后跳转到首页
		User user = (User) request.getSession().getAttribute("user");
		int userid = user.getId();
		String role = user.getRole();
		request.getSession().removeAttribute("user");
		String logoutTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		String ip = GetRealIPUtils.getIP(request);
		String province = user.getMap();
		String logintime="";
		Cookie[] cookies = request.getCookies();
		for(int i=0;cookies!=null && i<cookies.length;i++) {
			if (cookies[i].getName().equals("logintime")){
				logintime =URLDecoder.decode(cookies[i].getValue(),"utf-8");
				break;
			}
		}
		LoginoutService ls = new LoginoutService();
		try {
			ls.addLoginout(userid, Timestamp.valueOf(logintime), Timestamp.valueOf(logoutTime), ip, role, province);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/client/index.jsp");
	}
}
