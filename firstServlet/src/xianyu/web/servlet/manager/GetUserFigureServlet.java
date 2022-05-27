package xianyu.web.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xianyu.domain.User;
import xianyu.service.OrderService;
import xianyu.utils.GetRealIPUtils;

public class GetUserFigureServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operationType = request.getParameter("operation");
		User user = (User) request.getSession().getAttribute("user");
		int userid = user.getId();
		String role = user.getRole();
		String ip = GetRealIPUtils.getIP(request);
		OrderService os = new OrderService();
		List<Object[]> figuredata = new ArrayList<Object[]>();
		try {
			String op_name;
			if(operationType.equals("search")) {
				op_name = "��ѯ�û�����";
			}else {
				op_name="�����û�����";
			}
			figuredata = os.getUserFigureData(userid,role,op_name,ip);
			if (operationType.equals("search")) {
				// ������չʾ��ҳ����
				request.setAttribute("figuredata", figuredata);
				request.setAttribute("isSearch", true);
				request.getRequestDispatcher("/admin/user/userFigure_list.jsp").forward(request, response);
			} else {
				// ��������
				String filename = "�û�����.csv";
				response.setContentType(this.getServletContext().getMimeType(filename));
				response.setHeader("Content-Disposition",
						"attachement;filename=" + new String(filename.getBytes("GBK"), "iso8859-1"));
				response.setCharacterEncoding("gbk");
				PrintWriter out = response.getWriter();
				out.println("�û�ID,�Ա�,����,����ƫ�����,���һ�����Ѽ��,�������Ѵ���,���������ܶ�");// д��̧ͷ
				
				 for(int i=0;i<figuredata.size();i++) {//����д������ 
					 Object[] arr = figuredata.get(i);//����List�е�һ��
					 out.println(arr[0]+","+arr[1]+","+arr[2]+","+arr[3]+","+ arr[4]+"," +arr[5]+","+arr[6]); 
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
