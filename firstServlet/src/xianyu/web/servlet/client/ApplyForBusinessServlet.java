//ģ���û������Ա�ύ�������֤��������Ա���ͨ���󣬸���������ʾ�̼������Կ�Ĺ���
//�����û������Ա�ķ����������󣨲��ܷ��ʼ�[�����˿�ƽ̨��������]���ĳ������ݿ����½�һ�ű�����Щ����ID��
package xianyu.web.servlet.client;
import java.io.IOException;
import java.sql.SQLException;

import xianyu.service.UserService;
import xianyu.service.ApplicationService;
import javax.servlet.*;
import javax.servlet.http.*;
import xianyu.domain.User;
public class ApplyForBusinessServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doPost(req,resp);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		User user = ((User)req.getSession().getAttribute("user"));
		int id = user.getId();
		String newEmail = req.getParameter("email");
		String oldEmail = user.getEmail();
		if (oldEmail==null || !newEmail.equals(oldEmail)) {
			//���ԭ��û������������Ϣ����������д��������洢�����䲻һ�£�������д�����ݿ�
			UserService us = new UserService();
			us.modifyEmail(newEmail, id);
		}
		ApplicationService as = new ApplicationService();
		try {
			as.addApplication(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		//�������ת������ɹ�����
		resp.sendRedirect(req.getContextPath() + "/client/applysuccess.jsp");
	}
}
