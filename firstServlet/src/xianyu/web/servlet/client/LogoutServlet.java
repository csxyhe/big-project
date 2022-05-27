// ��������û��˴ε�¼��ע�������ͷŸ��û�ռ�õ�ϵͳ��Դ�������ݿ������̣߳�
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
		// ����session�����е�user���ԣ���¼���ε���ǳ���Ϣ��Ȼ����ת����ҳ
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
