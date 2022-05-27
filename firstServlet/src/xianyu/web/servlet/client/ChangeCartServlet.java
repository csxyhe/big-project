//�Թ��ﳵ����Ʒ�����ĸ���
package xianyu.web.servlet.client;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import xianyu.domain.Product;
public class ChangeCartServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request,HttpServletResponse response)
		throws IOException, ServletException{
		String p_id = request.getParameter("p_id");
		Integer count = Integer.parseInt(request.getParameter("count"));// ������ת��������
		// ��session��ȡ�����ﳵ���޸�id��Ӧ����Ʒ������
		HttpSession session = request.getSession();
		Map<Product,Integer> cart = (Map<Product,Integer>) session.getAttribute("cart");
		Set<Product> keyset = cart.keySet();
		Iterator<Product> it = keyset.iterator();
		//Iterator�����ʣ�������it.hasNext()��ѭ���н��м�ֵ�Ե�ɾ��������ɾ��Ҫд��ѭ������
		if(count==0) {
			Product key = null;
			while (it.hasNext()) {
				key = it.next();
				if (key.getP_id().equals(p_id)) {
					break;
				}
			}
			cart.remove(key);
		}else {
			
			while (it.hasNext()) {
				// ��ȡ��
				Product key = it.next();
				if (key.getP_id().equals(p_id)) {
					cart.put(key, count);
				}
			}
		}
		response.sendRedirect(request.getContextPath() + "/client/cart.jsp");
		return;
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
		throws IOException, ServletException{
		doGet(request,response);
	}
}
