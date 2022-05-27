// 用于响应展示商品列表的请求
package xianyu.web.servlet.business;
import java.io.IOException;
import java.util.List;
import xianyu.Exception.ListProductException;
import xianyu.domain.Product;
import xianyu.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import xianyu.service.ProductService;
public class ListProductServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductService service = new ProductService();
		try {
			User user = (User) request.getSession().getAttribute("user");
			int user_id = user.getId();
			List<Product> ps = service.listAll_B(user_id);
			// 将查询的所有商品放进request域中，并将请求转发给list.jsp
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
			throws ServletException, IOException {
		doPost(request,response);
	}
}
