// 该类用来添加、查找、验证用户
// 用完数据库连接后要马上释放该线程，避免造成线程的浪费和内存泄露问题
package xianyu.Dao;
import xianyu.utils.DataSourceUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.*;
import org.apache.commons.dbutils.*;
import xianyu.domain.User;

public class UsersDao {
	// 添加用户
	public void adduser(User user) throws SQLException{
		String sql = "insert into users(username,password,gender)"
				+"values (?,?,?)"; 
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, user.getUsername(),user.getPassword(),user.getGender());
		if (row == 0) {
			throw new RuntimeException();
		}
	}
	// 根据用户名和密码查找用户
	public User findUserByUsernameAndPassword(String username,String password) throws SQLException{
		String sql = "select * from users where username = ? and password = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class), username, password);
	}
	/*
	  * 修改用户密码和角色 ，应用场景有二：
	 * 1.在管理员同意用户成为销售人员后
	 * 2.在用户被同意撤销其销售人员的身份后
	 * 
	 * 输入欲修改的用户主体、新的用户角色和新的密码
	 * 
	 * 用户修改自己的密码的时候也可以用，只要newRole还是之前的role值就行
	 */
	public void modifyPw_R(int userid, String newRole,String newPassword) throws SQLException{
		String sql = "update users set password=?, role=? where id= ?";
		List<Object> obj = new ArrayList<Object>();
		obj.add(newPassword);
		obj.add(newRole);
		obj.add(userid);
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		Object[] params = obj.toArray();
		runner.update(sql, params);
	}
	//读入用户ID，返回该用户填写在表中的邮箱地址
	public String returnEmail(int userid) throws SQLException{
		String sql = "select * from users where id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		User user =  runner.query(sql, new BeanHandler<User>(User.class), userid);
		return user.getEmail();
		
	}
	// 改写或者写入用户邮箱
	public void modifyEmail(String email, int id) throws SQLException{
		String sql = "update users set email = ? where id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, email,id);
	}
	// 读出所有role为'business'的用户信息
	public List<Object[]> returnBusiness() throws SQLException{
		String sql = "select id, username from users where role = 'business'";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler());
	}
	//填入或者修改地域信息
	public void modify_map(String province, int userid) throws SQLException{
		String sql = "update users set map=? where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, province,userid);
		if (row == 0) {
			throw new RuntimeException();
		}
	}
}
