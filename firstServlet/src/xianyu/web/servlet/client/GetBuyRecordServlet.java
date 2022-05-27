package xianyu.web.servlet.client;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xianyu.domain.User;
import xianyu.service.LoginoutService;
import xianyu.utils.GetProvinceUtils;
import xianyu.utils.GetRealIPUtils;
import xianyu.service.OrderService;
public class GetBuyRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
		User user = (User)request.getSession(false).getAttribute("user");
		int userid = user.getId();
		OrderService os = new OrderService();
		List<Object[]> searchedRecord = new ArrayList<Object[]>();
		if(request.getParameter("within_month")!=null) {//查询近一个月内记录
			try {
				searchedRecord = os.searchMonthOrderRecord(userid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				searchedRecord = os.searchAllOrderRecord(userid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.setAttribute("searchedRecord", searchedRecord);
		request.getRequestDispatcher("client/order_list.jsp").forward(request,response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		doGet(request,response);
	}

}
