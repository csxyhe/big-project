package xianyu.web.servlet.manager;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;

import xianyu.service.ProductService;
import xianyu.Exception.ListProductException;
import xianyu.domain.Product;

public class FindProductByManyConditionServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		// 1.获取提交的表单的数据
		String id = request.getParameter("id");
		String category = request.getParameter("category");
		String name = request.getParameter("name");
		String minprice = request.getParameter("minprice");
		String maxprice = request.getParameter("maxprice");
		ProductService service = new ProductService();
		List<Product> ps;
		try {
			ps = service.findProductByManyCondition(id, name, category, minprice, maxprice);
			// 将查询结果放入request中，并转发给list.jsp做进一步的处理
			request.setAttribute("ps", ps);
			request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
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
