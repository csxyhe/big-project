package xianyu.web.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import xianyu.service.BrowseService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xianyu.domain.User;
import xianyu.utils.GetRealIPUtils;

public class GetBrowseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operationType = request.getParameter("operation");
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
		User user = (User) request.getSession().getAttribute("user");
		int userid = user.getId();
		String role = user.getRole();
		String ip = GetRealIPUtils.getIP(request);
		BrowseService bs = new BrowseService();
		List<Object[]> month_browse = new ArrayList<Object[]>();
		try {
			String op_name;
			if(operationType.equals("search")) {
				op_name = "��ѯ��־";
			}else {
				op_name="������־";
			}
			month_browse = bs.returnmonth_all(year, month, userid, role, op_name,year+"��"+month+"�������־", ip);
			if (operationType.equals("search")) {
				// ������չʾ��ҳ����
				request.setAttribute("month_browse", month_browse);
				request.getRequestDispatcher("/admin/log/browse_list.jsp").forward(request, response);
			} else {
				// ��������
				String filename = year + "��" + month + "�������־.csv";
				response.setContentType(this.getServletContext().getMimeType(filename));
				response.setHeader("Content-Disposition",
						"attachement;filename=" + new String(filename.getBytes("GBK"), "iso8859-1"));
				response.setCharacterEncoding("gbk");
				PrintWriter out = response.getWriter();
				out.println("�û�ID,����ID,��ƷID,��ʼʱ��,����ʱ��,����ʱ�����룩");// д��̧ͷ
				
				 for(int i=0;i<month_browse.size();i++) {//����д������ 
					 Object[] arr = month_browse.get(i);//����List�е�һ��
					 out.println(arr[0]+","+arr[1]+","+arr[2]+","+arr[3]+","+ arr[4]+"," +arr[5]); 
				 }
				out.flush();// ��һ����˵��Ҫ�ȵ����������������ǿ�ư������������ջ�����
				out.close();// �ر��ļ�

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
