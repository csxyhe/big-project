// �������ǰ̨�ͺ�̨���õ�Servlet�����ڸ�����Ʒid��������Աid��ѯָ������Ʒ��Ϣ
/**************��Ƿ������Ա��ûд����ʱ��֪����ôд���ȿ���**********/
package xianyu.web.servlet.client;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;

import xianyu.domain.Product;
import xianyu.domain.User;
import xianyu.service.ProductService;
import xianyu.service.BrowseService;
public class FindProductByIdServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		String p_id = request.getParameter("p_id");
		/* String u_id = request.getParameter("u_id"); */
		// type�������ֲ�ѯ�����ǰ̨���Ǻ�̨����
		String type = request.getParameter("type");
		ProductService service = new ProductService();
		try {
			Product p = service.findProductById(p_id);
			request.setAttribute("p", p);
			if (type == null) {
				// ǰ̨��վ������typeֵ������ת��ǰ̨��վ����Ʒ��ϸ��Ϣinfo.jspҳ��
				request.getRequestDispatcher("/client/info.jsp").forward(request, response);
				return;
			}
			else if(type.equals("business")){
				// ��̨��վ����typeֵΪ'business'������ת����Ʒ�༭ҳ��edit.jsp
				request.getRequestDispatcher("/businessman/products/edit.jsp").forward(request, response);
				return;
			}else {
				
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		doGet(request,response);
	}
}
