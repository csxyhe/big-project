package xianyu.web.servlet.client;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import xianyu.domain.PageBean;
import xianyu.service.ProductService;
public class MenuSearchServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getSession(false).getAttribute("browseuser")!=null) {
			request.getSession(false).removeAttribute("browseuser");
		}
		// 当前页码默认为1
		int currentPage=1;
		if (request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));// String转int
		}
		// 当前每页显示最大商品数默认为4
		int currentNum = 4;
		if (request.getParameter("currentNum")!=null) {
			currentNum = Integer.parseInt(request.getParameter("currentNum"));// String转int
		}

		String textfield = request.getParameter("textfield");
		// 如果内容还是默认值，则默认返回全部商品
		if(textfield.equals("请输入商品名称")) {
			request.getRequestDispatcher("/showProductByPage").forward(request, response);
			return;
		}
		// 否则用service提供的模糊搜索功能进行搜索，返回Pagebean对象
		ProductService service = new ProductService();
		PageBean bean = service.findProductByKeyword(currentPage, currentNum, textfield);
		//将找到的bean存到request中，将请求转发到商品展示页面中
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("/client/product_list.jsp").forward(request, response);
		
	}
}
