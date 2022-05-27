package xianyu.web.servlet.client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xianyu.service.OrderService;

public class FindOrderItemServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException{
		String orderid = request.getParameter("orderid");
		OrderService os = new OrderService();
		List<Object[]> oi_list = new ArrayList<Object[]>();
		try {
			oi_list = os.returnOIDetails(orderid);
			request.setAttribute("oi_list", oi_list);
			request.getRequestDispatcher("/client/orderitem_list.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		doGet(request, response);
	}
}
