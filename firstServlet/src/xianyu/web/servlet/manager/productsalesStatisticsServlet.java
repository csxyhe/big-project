//根据输入的查询条件，返回单品统计信息
package xianyu.web.servlet.manager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import xianyu.service.OrderService;
public class productsalesStatisticsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
			String product_id = request.getParameter("p_id");
			String category = request.getParameter("category");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			if(year.length()==0||month.length()==0) {//输入的日期无效or根本没有输入日期
				Calendar now = Calendar.getInstance();
				year = now.get(Calendar.YEAR) + "";
				int mon = now.get(Calendar.MONTH) + 1;
				if (mon<10) {
					month = "0" + mon;
				}else {
					month = "" + mon;
				}
			}
			String minprice = request.getParameter("minprice");
			String maxprice = request.getParameter("maxprice");
			String month_minnum = request.getParameter("minnum");
			String month_maxnum = request.getParameter("maxnum");
			OrderService os = new OrderService();
			List<Object[]> psales = new ArrayList<Object[]>();
			try {
				psales = os.statisticsProSales(product_id, category, year, month, minprice, maxprice, month_minnum, month_maxnum);
				request.setAttribute("psales", psales);
				request.getRequestDispatcher("/admin/products/productCount_list.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
		throws ServletException, IOException{
		doPost(request, response);
	}
}
