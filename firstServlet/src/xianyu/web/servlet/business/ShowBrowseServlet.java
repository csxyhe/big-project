//用于展示数据库中该销售人员对应的所有的客户浏览记录
package xianyu.web.servlet.business;
import xianyu.service.BrowseService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import xianyu.domain.User;
public class ShowBrowseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		int b_id = user.getId();
		BrowseService service = new BrowseService();
		List<Object[]> bs = new ArrayList<Object[]>();
		try {
			bs = service.returnall(b_id);
			request.setAttribute("bs", bs);
			request.getRequestDispatcher("/businessman/browses/browse_list.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
}
