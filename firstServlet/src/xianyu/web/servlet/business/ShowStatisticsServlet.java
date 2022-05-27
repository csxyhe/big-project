//����ͳ��ÿ����Ʒ���������͸�ҳ�����չʾ
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
			// �����������ڷ�Χ���Ϸ�������û���������ڷ�Χ�ģ�Ĭ��������µ�����
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
		int user_id = user.getId();//ֻ�ܲ�ѯ������̼Ҹ������Ʒ���������
		statis = service.calStatistics(year, month, user_id);
		request.setAttribute("statis", statis);
		
		request.getRequestDispatcher("/businessman/products/statis_list.jsp").forward(request, response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
}
