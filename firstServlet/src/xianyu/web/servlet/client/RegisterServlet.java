package xianyu.web.servlet.client;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import xianyu.utils.BusinessPasswordUtils;
import xianyu.domain.User;
import org.apache.commons.beanutils.BeanUtils;
import xianyu.service.UserService;
import xianyu.Exception.*;

// ���ע�����
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		try {
			// �Ƚ�user�е��������Է�װ��
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// ����UserService����user����Ϣ�������ݿ��� 
		UserService service = new UserService(); 
		try {
			service.register(user); 
			} 
		catch (RegisterException e) {
			e.printStackTrace();
		}
		// ���������Ϣ response.getWriter().write(e.getMessage()); return; }
		// ע��ɹ�����ת��registersuccess.jsp
		response.sendRedirect(request.getContextPath() + "/client/registersuccess.jsp");
	}
}
