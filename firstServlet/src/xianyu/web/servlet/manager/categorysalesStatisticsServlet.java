//���·�����������ͳ�����
package xianyu.web.servlet.manager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.*;
import javax.servlet.http.*;

import xianyu.service.OrderService;
public class categorysalesStatisticsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			if(year.length()==0||month.length()==0) {
				//�����������ڷ�Χ���Ϸ�������û���������ڷ�Χ�ģ�Ĭ��������µ�����
				Calendar now = Calendar.getInstance();
				year = now.get(Calendar.YEAR) + "";
				int mon = now.get(Calendar.MONTH) + 1;
				if (mon<10) {
					month = "0" + mon;
				}else {
					month = "" + mon;
				}
			}
			OrderService os = new OrderService();
			List<Object[]> csales = new ArrayList<Object[]>();
			try {
				csales = os.statisticsCategorySales(year, month);
				//������Щ�����ȫ������ȥ��ͳ�Ƶ�ʱ��ҲҪд��
				if(csales.size()!=6) {
					TreeSet<String> ts = new TreeSet<String>();
					for(int k=0;k<csales.size();k++) {//��ȡĿǰ��ͳ�����ݵ��������
						ts.add(csales.get(k)[0].toString());
					}
					String[] all_category = new String[6];
					all_category[0]="�ľ�";
					all_category[1]="�鼮";
					all_category[2]="�����Ʒ";
					all_category[3]="����Ʒ";
					all_category[4]="�˶�����";
					all_category[5]="��װ";
					for(int k=0;k<6;k++) {
						if(ts.add(all_category[k])) {//û�и������ݣ����һ���ռ�¼
							Object[] temp = new Object[3];
							temp[0]=all_category[k];
							temp[1]=0;
							temp[2]=0.0;
							csales.add(temp);
						}
					}
				}
				request.setAttribute("csales",csales);
				request.setAttribute("isShow",true);
				request.getRequestDispatcher("/admin/products/categoryCount_list.jsp").forward(request, response);
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
