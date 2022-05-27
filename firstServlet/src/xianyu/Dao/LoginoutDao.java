//ֱ�Ӳ������ݿ⣬��loginout����в�ѯ������Ȳ���

package xianyu.Dao;
import xianyu.utils.DataSourceUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

public class LoginoutDao{
	//��ӵ���ǳ���¼
	public void addLoginout(int userid,Timestamp logintime,Timestamp logouttime,String ip,String role,String province) 
			 throws SQLException{
		String sql = "insert into loginout(u_id,logintime,logouttime,ip,role,province) values(?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner();
		int row = runner.update(DataSourceUtils.getConnection(), sql, userid,logintime,logouttime,ip,role,province);
		if (row==0) {
			throw new RuntimeException();
		}
	}
	//��������ʱ�䷶Χ�����������������ĵ���ǳ���־
	public List<Object[]> getLoginout(String year, String month, String ip, int userid, String role, String operationType) throws SQLException{
		String dateRange = year + "-" + month;
		List<Object> p = new ArrayList<Object>();
		p.add(dateRange);
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> p2 = new ArrayList<Object>();
		p2.add(userid);
		p2.add(role);
		if(operationType.equals("search")) {
			p2.add("��ѯ��־");
		}else {
			p2.add("������־");
		}
		p2.add(year+"��"+month+"�µ���ǳ���־");
		p2.add(ip);
		Object[] params2 = p2.toArray();
		String sql2= "INSERT INTO operations(u_id,role,op_name,op_content,ip) VALUES(?,?,?,?,?)";
		int row = runner.update(DataSourceUtils.getConnection(),sql2, params2);
		if (row == 0) {
			throw new RuntimeException();
		}
		String sql = "SELECT * FROM loginout where date_format(logintime, '%Y-%m')=?";
		Object[] params = p.toArray();
		return runner.query(sql, new ArrayListHandler(),params);
	}
}
