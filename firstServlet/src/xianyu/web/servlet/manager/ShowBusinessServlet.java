// �����ݿ��ж�������roleΪ'business'���û���Ϣ�����ҷ��ظ�business_list.jspҳ��
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
			bl = us.returnBusiness(userid, role,"��ѯ����","������Ա����",ip);
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
