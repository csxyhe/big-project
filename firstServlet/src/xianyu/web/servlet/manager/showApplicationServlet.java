//用于将申请成为销售人员的用户id展示出来
package xianyu.web.servlet.manager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xianyu.service.ApplicationService;
import javax.servlet.*;
import javax.servlet.http.*;
public class showApplicationServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		ApplicationService as = new ApplicationService();
		List<Object[]> al = new ArrayList<Object[]>();
		try {
			al = as.returnall();
			request.setAttribute("al", al);
			request.getRequestDispatcher("/admin/user/application_list.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws IOException, ServletException{
			doGet(request,response);
		}

}
