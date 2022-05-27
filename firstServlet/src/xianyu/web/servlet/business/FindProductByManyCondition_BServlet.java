//findproductbymanycondition��������Ա�汾
//����û�ֻ�ܲ鿴���Լ�������Ʒ
package xianyu.web.servlet.business;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;

import xianyu.service.ProductService;
import xianyu.utils.GetRealIPUtils;
import xianyu.Exception.ListProductException;
import xianyu.domain.Product;
import xianyu.domain.User;

public class FindProductByManyCondition_BServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		User user = (User) request.getSession().getAttribute("user");
		int user_id = user.getId();
		String role = user.getRole();
		String ip = GetRealIPUtils.getIP(request);
		// 1.��ȡ�ύ�ı�������
		String p_id = request.getParameter("p_id");
		String category = request.getParameter("category");
		String name = request.getParameter("name");
		String minprice = request.getParameter("minprice");
		String maxprice = request.getParameter("maxprice");
		ProductService service = new ProductService();
		List<Product> ps;
		try {
			ps = service.findProductByManyCondition_B(p_id, user_id, name, category, minprice, maxprice,role,"��ѯ��Ʒ",ip);
			// ����ѯ�������request�У���ת����list.jsp����һ���Ĵ���
			request.setAttribute("ps", ps);
			request.getRequestDispatcher("/businessman/products/list.jsp").forward(request, response);
			return;
		} catch (ListProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
			return;
		}
		
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			doPost(request,response);
		}
}
