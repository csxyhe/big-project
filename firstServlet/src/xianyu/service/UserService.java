package xianyu.service;
import xianyu.Dao.UsersDao;
import xianyu.domain.User;
import xianyu.Exception.*;
import java.sql.*;
import java.util.List;
import xianyu.Dao.operationDao;
public class UserService {
	private UsersDao ud = new UsersDao();
	private operationDao od = new operationDao();
	// �ṩ�û�ע������ķ��񣬸ò���Ϊ���û���Ϣ�������ݿ���
	public void register(User user) throws RegisterException{ 
		try {
			ud.adduser(user);  
			}
		catch (Exception e){
			e.printStackTrace(); throw new RegisterException("ע��ʧ��"); 
			} 
		}

	//�ṩ�û���½�ķ���
	public User returnUser(String username,String password) throws LoginException{
//		UsersDao ud = new UsersDao();
		try {
			User user = ud.findUserByUsernameAndPassword(username, password);
			// �����ݿ����ҵ����û�
			if (user!=null) {
				//�û��Ѿ�����ɹ����������¼
				return user;
			}
			throw new LoginException("�û������������");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//�ṩ�޸��û�����ͽ�ɫ�ķ���������Ա�͹���Ա�ã��ṩд��־���ܣ�
	public void modifyPasswordAndRole(int userid,String newRole,String newPassword, int op_userid, String op_role,String op_name,String ip) {
		try {
			ud.modifyPw_R(userid,newRole,newPassword);
			od.addOperation(op_userid, op_role, op_name, userid+"", ip);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�ṩ�޸��û�����ͽ�ɫ�ķ����û��ã����ṩд��־���ܣ�
	public void user_modifyPasswordAndRole(int userid,String newRole,String newPassword) {
		try {
			ud.modifyPw_R(userid,newRole,newPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�����û�ID���ظ��û�������
	public String returnEmail(int userid) {
		try {
			return ud.returnEmail(userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//��д����д����Ӧ�û�������
	public void modifyEmail(String email, int id) {
		try {
			ud.modifyEmail(email, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// ��������roleΪ'business'���û���Ϣ
	public List<Object[]> returnBusiness(int op_id, String role, String op_name, String op_content, String ip) throws SQLException{
		od.addOperation(op_id, role, op_name, op_content, ip);
		return ud.returnBusiness();
	}
	// �޸Ļ��������
	public void modify_map(String province,int userid)throws SQLException{
		ud.modify_map(province, userid);
	}
}
