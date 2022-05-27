//�������ڽ�������Ϣ���浽���ݿ�
package xianyu.web.servlet.client;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.beanutils.BeanUtils;
import xianyu.service.OrderService;
import xianyu.service.UserService;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import xianyu.domain.Order;
import xianyu.domain.OrderItem;
import xianyu.domain.User;
import xianyu.domain.Product;
import xianyu.utils.IdUtils;
import xianyu.utils.MailUtils;

public class CreateOrderServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException{
		HttpSession session = request.getSession(false);
		// ��ȡ�û���Ϣ�����Ĺ��ﳵ��Ϣ
		User user = (User) session.getAttribute("user");
		int id = user.getId();
		String email;
		String newEmail = request.getParameter("receiverEmail");
		String oldEmail = user.getEmail();
		if (oldEmail==null || (oldEmail!=null&&!newEmail.equals(oldEmail))) {
			//���ԭ��û������������Ϣ����������д��������洢�����䲻һ�£�������д�����ݿ�
			UserService us = new UserService();
			us.modifyEmail(newEmail, id);
			email = newEmail;
		}else {
			email = oldEmail;
		}
		Map<Product,Integer> cart = (Map<Product,Integer>) session.getAttribute("cart");
		// ��������
		Order order = new Order();
		try {
			//�������ܽ��ջ��˵�ַ�����������䶼��װ��order��
			BeanUtils.populate(order, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String orderid = IdUtils.getUUID();
		order.setId(orderid);
		order.setUser(user);
		for(Product p: cart.keySet()) {
			OrderItem item = new OrderItem();
			item.setBuynum(cart.get(p));
			item.setOrder(order);
			item.setProduct(p);
			order.getOrderItems().add(item);//����Ŀ�ģ���orderItem��װ��order������
		}
		OrderService service = new OrderService();
		service.addOrder(order);
		//�µ��ɹ�֮��Ҫ��չ��ﳵ
		session.removeAttribute("cart");
		try {
			//���µ��ɹ��ʼ�
			String emailMsg = "����Ϊ" + orderid + "�Ķ����Ѿ���������Ʒ����";
			for(Product p : cart.keySet()) {
				emailMsg += p.getName() + "*";
				emailMsg += cart.get(p) + "��";
			}
			emailMsg += "����ջ���ַΪ" + request.getParameter("receiverAddress") + "����ע����ա���л��ʹ��������̣�";
			MailUtils.sendMail(email, emailMsg,1);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/client/createOrderSuccess.jsp");
		return;
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException{
		doPost(request,response);
	}
}
