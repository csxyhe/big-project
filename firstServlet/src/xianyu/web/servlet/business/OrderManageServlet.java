//��������ģ��
//��Ҫ�ṩ�����İ�������ѯ����
//����������ȫ�����û�id���û��Ա𣬰����or�·ݲ�ѯ
package xianyu.web.servlet.business;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import xianyu.domain.User;
import xianyu.service.OrderService;
import javax.servlet.*;
import javax.servlet.http.*;
public class OrderManageServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		User user = (User)request.getSession(false).getAttribute("user");
		int myuid = user.getId();
		String user_id = request.getParameter("user_id");
		String gender = request.getParameter("gender");
		if(gender.length()!=0) {
			if(gender.equals("��")) {
				gender="M";
			}else if(gender.equals("Ů")){
				gender="F";
			}else {
				gender="";
			}
		}
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		OrderService service = new OrderService();
		List<Object[]> od;
		try {
			od = service.findBuyRecord(myuid,user_id, gender, year, month);
			request.setAttribute("od", od);
			request.getRequestDispatcher("/businessman/orders/order_list.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			doPost(request,response);
		}
}
