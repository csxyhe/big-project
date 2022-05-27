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
		// ��ǰҳ��Ĭ��Ϊ1
		int currentPage=1;
		if (request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));// Stringתint
		}
		// ��ǰÿҳ��ʾ�����Ʒ��Ĭ��Ϊ4
		int currentNum = 4;
		if (request.getParameter("currentNum")!=null) {
			currentNum = Integer.parseInt(request.getParameter("currentNum"));// Stringתint
		}

		String textfield = request.getParameter("textfield");
		// ������ݻ���Ĭ��ֵ����Ĭ�Ϸ���ȫ����Ʒ
		if(textfield.equals("��������Ʒ����")) {
			request.getRequestDispatcher("/showProductByPage").forward(request, response);
			return;
		}
		// ������service�ṩ��ģ���������ܽ�������������Pagebean����
		ProductService service = new ProductService();
		PageBean bean = service.findProductByKeyword(currentPage, currentNum, textfield);
		//���ҵ���bean�浽request�У�������ת������Ʒչʾҳ����
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("/client/product_list.jsp").forward(request, response);
		
	}
}
