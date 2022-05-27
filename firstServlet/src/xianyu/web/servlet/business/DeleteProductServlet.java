package xianyu.web.servlet.business;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import xianyu.domain.User;
import xianyu.service.ProductService;
import xianyu.utils.GetRealIPUtils;
public class DeleteProductServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String p_id = request.getParameter("p_id");
		ProductService service = new ProductService();
		User user = (User)request.getSession().getAttribute("user");
		int user_id = user.getId();
		String role = user.getRole();
		String ip = GetRealIPUtils.getIP(request);
		service.deleteProduct(p_id,user_id,role,"É¾³ýÉÌÆ·",ip);
		response.sendRedirect(request.getContextPath() + "/listProduct");
		return;
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
}
