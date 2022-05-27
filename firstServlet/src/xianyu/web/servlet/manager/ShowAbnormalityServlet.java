package xianyu.web.servlet.manager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xianyu.service.OrderService;


public class ShowAbnormalityServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderService os = new OrderService();
		List<Object[]> abnormal = new ArrayList<Object[]>();
		try {
			abnormal = os.searchAbnormality();
			if(abnormal==null) {
				request.setAttribute("abnormal", null);
			}else {
				request.setAttribute("abnormal", abnormal);
			}
			
			request.getRequestDispatcher("/admin/user/abnormality_list.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

	
}
