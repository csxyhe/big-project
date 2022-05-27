package xianyu.web.servlet.client;
import java.io.IOException;

import xianyu.service.ProductService;
import xianyu.domain.PageBean;
import javax.servlet.*;
import javax.servlet.http.*;

public class ShowProductByPageServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		if(request.getSession(false).getAttribute("browseuser")!=null) {
			request.getSession(false).removeAttribute("browseuser");
		}
		// ��ǰҳ��Ĭ��Ϊ1
		int currentPage=1;
		if (request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));// Stringתint
		}
		// ��ǰÿҳ��ʾ�����Ʒ��Ĭ��Ϊ9
		int currentNum = 9;
		if (request.getParameter("currentNum")!=null) {
			currentNum = Integer.parseInt(request.getParameter("currentNum"));// Stringתint
		}
		// ���Ĭ������Ϊ"ȫ����Ʒ"
		String category = "ȫ����Ʒ";
		if (request.getParameter("category")!=null) {
			category = request.getParameter("category");
		}
		ProductService ps = new ProductService();
		PageBean bean = ps.findProductByPage(currentPage, currentNum, category);
		//���ҵ���bean�浽request�У�������ת������Ʒչʾҳ����
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("/client/product_list.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			doGet(request,response);
		}
}
