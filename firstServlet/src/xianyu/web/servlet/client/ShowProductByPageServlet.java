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
		// 当前页码默认为1
		int currentPage=1;
		if (request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));// String转int
		}
		// 当前每页显示最大商品数默认为9
		int currentNum = 9;
		if (request.getParameter("currentNum")!=null) {
			currentNum = Integer.parseInt(request.getParameter("currentNum"));// String转int
		}
		// 类别默认设置为"全部商品"
		String category = "全部商品";
		if (request.getParameter("category")!=null) {
			category = request.getParameter("category");
		}
		ProductService ps = new ProductService();
		PageBean bean = ps.findProductByPage(currentPage, currentNum, category);
		//将找到的bean存到request中，将请求转发到商品展示页面中
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("/client/product_list.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			doGet(request,response);
		}
}
