// ����������ӡ����ҡ���֤�û�
// �������ݿ����Ӻ�Ҫ�����ͷŸ��̣߳���������̵߳��˷Ѻ��ڴ�й¶����
package xianyu.Dao;
import xianyu.utils.DataSourceUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.*;
import org.apache.commons.dbutils.*;
import xianyu.domain.User;

public class UsersDao {
	// ����û�
	public void adduser(User user) throws SQLException{
		String sql = "insert into users(username,password,gender)"
				+"values (?,?,?)"; 
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, user.getUsername(),user.getPassword(),user.getGender());
		if (row == 0) {
			throw new RuntimeException();
		}
	}
	// �����û�������������û�
	public User findUserByUsernameAndPassword(String username,String password) throws SQLException{
		String sql = "select * from users where username = ? and password = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class), username, password);
	}
	/*
	  * �޸��û�����ͽ�ɫ ��Ӧ�ó����ж���
	 * 1.�ڹ���Աͬ���û���Ϊ������Ա��
	 * 2.���û���ͬ�⳷����������Ա����ݺ�
	 * 
	 * �������޸ĵ��û����塢�µ��û���ɫ���µ�����
	 * 
	 * �û��޸��Լ��������ʱ��Ҳ�����ã�ֻҪnewRole����֮ǰ��roleֵ����
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
	//�����û�ID�����ظ��û���д�ڱ��е������ַ
	public String returnEmail(int userid) throws SQLException{
		String sql = "select * from users where id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		User user =  runner.query(sql, new BeanHandler<User>(User.class), userid);
		return user.getEmail();
		
	}
	// ��д����д���û�����
	public void modifyEmail(String email, int id) throws SQLException{
		String sql = "update users set email = ? where id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, email,id);
	}
	// ��������roleΪ'business'���û���Ϣ
	public List<Object[]> returnBusiness() throws SQLException{
		String sql = "select id, username from users where role = 'business'";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler());
	}
	//��������޸ĵ�����Ϣ
	public void modify_map(String province, int userid) throws SQLException{
		String sql = "update users set map=? where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, province,userid);
		if (row == 0) {
			throw new RuntimeException();
		}
	}
}
