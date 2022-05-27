// �����û��Ƿ��Ѿ���¼��������ҳ�ض������
package xianyu.web.servlet.client;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import xianyu.domain.User;

public class MyAccountServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException, IOException{
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
		/*
		 * ����session���Ƿ���"user"����ֵ���ж��û��Ƿ��Ѿ���ɵ�¼��Ϊ
		 * ����"user"������֤���Ѿ���¼��
		 * δ��¼->/client/login.jsp
		 * ��ͨ��User�����е�role����ֵ���жϸ��û������
		 * '�û�'->/client/myAccount.jsp
		 * '����Ա'->/admin/login/home.jsp
		 */
		User user = (User) request.getSession().getAttribute("user");
		if(user==null) {
				response.sendRedirect(request.getContextPath()+"/client/login.jsp");
				return;
		}
		String role = user.getRole();
		if(role.equals("user")) {
			response.sendRedirect(request.getContextPath()+"/client/myAccount.jsp");
			return;
		}else if(role.equals("business")) {
			response.sendRedirect(request.getContextPath()+"/businessman/login/home.jsp");
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/login/home.jsp");
			return;
		}
	}
}
