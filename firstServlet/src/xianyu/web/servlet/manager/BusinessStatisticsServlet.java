// 输入销售人员ID，将orderitems表中对应的产品全部读出
package xianyu.web.servlet.manager;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import xianyu.service.OrderService;
public class BusinessStatisticsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
			String b_id = request.getParameter("u_id");
			System.out.println(b_id);
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			OrderService os = new OrderService();
			List<Object[]> psperb = new ArrayList<Object[]>();
			//判断链表为空
			try {
				psperb = os.findDetailsByBid_Date(b_id,year,month);
				request.setAttribute("psperb", psperb);
				request.getRequestDispatcher("/admin/user/businessCount_list.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
		throws ServletException, IOException{
		doPost(request,response);
	}

}
