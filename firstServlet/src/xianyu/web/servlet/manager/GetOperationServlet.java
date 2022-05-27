package xianyu.web.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.ServletException;


import xianyu.domain.User;
import xianyu.service.OperationService;
import xianyu.utils.GetRealIPUtils;

public class GetOperationServlet extends HttpServlet{
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
		OperationService ops = new OperationService();
		List<Object[]> month_op = new ArrayList<Object[]>();
		try {
			String op_name;
			if(operationType.equals("search")) {
				op_name = "��ѯ��־";
			}else {
				op_name="������־";
			}
			month_op = ops.getOperation(year, month, userid, role, op_name,year+"��"+month+"�²�����־", ip);
			if (operationType.equals("search")) {
				// ������չʾ��ҳ����
				request.setAttribute("month_op", month_op);
				request.getRequestDispatcher("/admin/log/operation_list.jsp").forward(request, response);
			} else {
				// ��������
				String filename = year + "��" + month + "�²�����־.csv";
				response.setContentType(this.getServletContext().getMimeType(filename));
				response.setHeader("Content-Disposition",
						"attachement;filename=" + new String(filename.getBytes("GBK"), "iso8859-1"));
				response.setCharacterEncoding("gbk");
				PrintWriter out = response.getWriter();
				out.println("�û�ID,�û���ɫ,��������,��������,IP��ַ,����ʱ��");// д��̧ͷ
				
				 for(int i=0;i<month_op.size();i++) {//����д������ 
					 Object[] arr = month_op.get(i);//����List�е�һ��
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
