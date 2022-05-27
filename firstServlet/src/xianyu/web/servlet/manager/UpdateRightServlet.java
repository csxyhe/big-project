//�Ƿ�ִ������Ȩ�޲��������û���Ϊ������Ա��
//���������������ֱ��������ߵ��û�ID�͹���Ա�����ͬ��/��ͬ�⣨applyid and agree��
//���ͬ�����Ϊ������Ա����1.�޸�����users���е�role��2.��������һ��12λ������д�뵽Users���У������͵�����д�����䣬3.ɾ��applications���е�������¼
//�����ͬ�����Ϊ������Ա����1.���;ܾ�֪ͨ������д�����䣬3.ɾ��applications���е�������¼
package xianyu.web.servlet.manager;
import java.io.IOException;
import java.sql.SQLException;

import xianyu.utils.BusinessPasswordUtils;
import xianyu.utils.GetRealIPUtils;
import xianyu.domain.User;
import xianyu.service.ApplicationService;
import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.http.*;
import xianyu.service.UserService;
import xianyu.utils.MailUtils;
public class UpdateRightServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		int applyid = Integer.parseInt(req.getParameter("applyid"));
		String agree = req.getParameter("agree");
		UserService us = new UserService();
		String email = us.returnEmail(applyid);//�������û�������
		if(agree.equals("yes")) {//ͬ��
			String newPassword = BusinessPasswordUtils.BusinessPassword();
			User user = (User) req.getSession().getAttribute("user");
			int userid = user.getId();
			String role = user.getRole();
			String ip = GetRealIPUtils.getIP(req);
			us.modifyPasswordAndRole(applyid, "business", newPassword,userid,role,"�������",ip);
			String emailMsg="��ϲ����������ԱȨ�ޣ����µĵ�¼������"+ newPassword +"���뱣�ܺ���ĵ�¼���룡";
			try {
				MailUtils.sendMail(email, emailMsg, 2);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {//��ͬ��
			String emailMsg="�ܱ�Ǹ���������δ��ù���Ա��ͬ�⡣";
			try {
				MailUtils.sendMail(email, emailMsg, 2);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ApplicationService as = new ApplicationService();
		try {//���������application����ɾ��������¼
			as.deleteid(applyid);
			req.getRequestDispatcher("/showApplication").forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		doGet(req,resp);
	}
}
