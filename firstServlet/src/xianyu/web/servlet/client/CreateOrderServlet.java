//该类用于将订单信息保存到数据库
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
		// 获取用户信息和他的购物车信息
		User user = (User) session.getAttribute("user");
		int id = user.getId();
		String email;
		String newEmail = request.getParameter("receiverEmail");
		String oldEmail = user.getEmail();
		if (oldEmail==null || (oldEmail!=null&&!newEmail.equals(oldEmail))) {
			//如果原来没有填入邮箱信息，或者新填写的邮箱跟存储的邮箱不一致，则重新写入数据库
			UserService us = new UserService();
			us.modifyEmail(newEmail, id);
			email = newEmail;
		}else {
			email = oldEmail;
		}
		Map<Product,Integer> cart = (Map<Product,Integer>) session.getAttribute("cart");
		// 创建订单
		Order order = new Order();
		try {
			//将订单总金额，收货人地址，姓名，邮箱都封装到order中
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
			order.getOrderItems().add(item);//最终目的：将orderItem封装到order对象中
		}
		OrderService service = new OrderService();
		service.addOrder(order);
		//下单成功之后要清空购物车
		session.removeAttribute("cart");
		try {
			//发下单成功邮件
			String emailMsg = "你编号为" + orderid + "的订单已经发货，商品包括";
			for(Product p : cart.keySet()) {
				emailMsg += p.getName() + "*";
				emailMsg += cart.get(p) + "，";
			}
			emailMsg += "你的收货地址为" + request.getParameter("receiverAddress") + "，请注意查收。感谢你使用咸鱼电商！";
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
