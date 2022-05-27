package xianyu.web.servlet.client;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import xianyu.domain.Product;
import xianyu.service.ProductService;

public class AddCartServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request,HttpServletResponse response)
		throws IOException, ServletException{
		
		if(request.getSession(false).getAttribute("browseuser")!=null) {
			request.getSession(false).removeAttribute("browseuser");
		}
		// 获取用户当前想要购买的商品的id
		String id = request.getParameter("id");
		ProductService service = new ProductService();
		try {
			Product pd = service.findProductById(id);
			// 从session中获取购物车属性
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Map<Product,Integer> cart = (Map<Product,Integer>) session.getAttribute("cart");
			if (cart == null) {
				// 如果该用户第一次购买商品，则创建出购物车
				cart = new HashMap<Product,Integer>();
			}
			String flag= "first";
			Set<Product> keyset = cart.keySet();
			Iterator<Product> it = keyset.iterator();
			// 遍历搜索购物车中是否已有该商品的键
			while (it.hasNext()) {
				// 获取键
				Product key = it.next();
				if (key.getP_id().equals(id)) {
					Integer count = cart.get(key);
					cart.put(key, count + 1);
					flag="n";
				}
			}
			if(flag.equals("first")) {
				cart.put(pd, 1);// 若本身没有该键值对，将其加入购物车
			}
			session.setAttribute("cart", cart);
			response.sendRedirect(request.getContextPath() + "/client/cart.jsp");
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws IOException, ServletException{
			doGet(request,response);
		}
}
