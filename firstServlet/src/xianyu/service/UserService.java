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
	// 提供用户注册操作的服务，该步骤为将用户信息填入数据库中
	public void register(User user) throws RegisterException{ 
		try {
			ud.adduser(user);  
			}
		catch (Exception e){
			e.printStackTrace(); throw new RegisterException("注册失败"); 
			} 
		}

	//提供用户登陆的服务
	public User returnUser(String username,String password) throws LoginException{
//		UsersDao ud = new UsersDao();
		try {
			User user = ud.findUserByUsernameAndPassword(username, password);
			// 在数据库能找到该用户
			if (user!=null) {
				//用户已经激活成功，才允许登录
				return user;
			}
			throw new LoginException("用户名或密码错误");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//提供修改用户密码和角色的服务（销售人员和管理员用，提供写日志功能）
	public void modifyPasswordAndRole(int userid,String newRole,String newPassword, int op_userid, String op_role,String op_name,String ip) {
		try {
			ud.modifyPw_R(userid,newRole,newPassword);
			od.addOperation(op_userid, op_role, op_name, userid+"", ip);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//提供修改用户密码和角色的服务（用户用，不提供写日志功能）
	public void user_modifyPasswordAndRole(int userid,String newRole,String newPassword) {
		try {
			ud.modifyPw_R(userid,newRole,newPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//根据用户ID返回该用户的邮箱
	public String returnEmail(int userid) {
		try {
			return ud.returnEmail(userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//改写或者写入相应用户的邮箱
	public void modifyEmail(String email, int id) {
		try {
			ud.modifyEmail(email, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 读出所有role为'business'的用户信息
	public List<Object[]> returnBusiness(int op_id, String role, String op_name, String op_content, String ip) throws SQLException{
		od.addOperation(op_id, role, op_name, op_content, ip);
		return ud.returnBusiness();
	}
	// 修改或填入地域
	public void modify_map(String province,int userid)throws SQLException{
		ud.modify_map(province, userid);
	}
}
