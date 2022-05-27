//用于统计每件商品的销量发送给页面进行展示
package xianyu.web.servlet.business;
import xianyu.domain.User;
import xianyu.service.OrderService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
public class ShowStatisticsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		if (year.length()==0 || month.length()==0) {
			// 如果输入的日期范围不合法，或者没有输入日期范围的，默认输出本月的数据
			Calendar now = Calendar.getInstance();
			year = now.get(Calendar.YEAR) + "";
			int mon = now.get(Calendar.MONTH) + 1;
			if (mon < 10) {
				month = "0" + mon;
			} else {
				month = "" + mon;
			}
		}
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		
		
		OrderService service = new OrderService();
		List<Object[]> statis = new ArrayList<Object[]>();
		User user  =(User)request.getSession().getAttribute("user");
		int user_id = user.getId();//只能查询到这个商家负责的商品的销售情况
		statis = service.calStatistics(year, month, user_id);
		request.setAttribute("statis", statis);
		
		request.getRequestDispatcher("/businessman/products/statis_list.jsp").forward(request, response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
}
