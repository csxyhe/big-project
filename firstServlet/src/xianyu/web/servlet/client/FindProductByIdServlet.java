// 这个类是前台和后台共用的Servlet，用于根据商品id和销售人员id查询指定的商品信息
/**************还欠销售人员的没写，暂时不知道怎么写，先空着**********/
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
		// type用于区分查询请求从前台还是后台发出
		String type = request.getParameter("type");
		ProductService service = new ProductService();
		try {
			Product p = service.findProductById(p_id);
			request.setAttribute("p", p);
			if (type == null) {
				// 前台网站不传递type值，会跳转到前台网站的商品详细信息info.jsp页面
				request.getRequestDispatcher("/client/info.jsp").forward(request, response);
				return;
			}
			else if(type.equals("business")){
				// 后台网站传递type值为'business'，会跳转到商品编辑页面edit.jsp
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
