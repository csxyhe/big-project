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

// 完成注册操作
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		try {
			// 先将user中的其他属性封装好
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// 启动UserService将该user的信息填入数据库中 
		UserService service = new UserService(); 
		try {
			service.register(user); 
			} 
		catch (RegisterException e) {
			e.printStackTrace();
		}
		// 输出错误信息 response.getWriter().write(e.getMessage()); return; }
		// 注册成功，跳转到registersuccess.jsp
		response.sendRedirect(request.getContextPath() + "/client/registersuccess.jsp");
	}
}
