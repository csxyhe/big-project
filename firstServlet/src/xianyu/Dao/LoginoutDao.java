//直接操作数据库，对loginout表进行查询、插入等操作

package xianyu.Dao;
import xianyu.utils.DataSourceUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

public class LoginoutDao{
	//添加登入登出记录
	public void addLoginout(int userid,Timestamp logintime,Timestamp logouttime,String ip,String role,String province) 
			 throws SQLException{
		String sql = "insert into loginout(u_id,logintime,logouttime,ip,role,province) values(?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner();
		int row = runner.update(DataSourceUtils.getConnection(), sql, userid,logintime,logouttime,ip,role,province);
		if (row==0) {
			throw new RuntimeException();
		}
	}
	//根据输入时间范围查找所有满足条件的登入登出日志
	public List<Object[]> getLoginout(String year, String month, String ip, int userid, String role, String operationType) throws SQLException{
		String dateRange = year + "-" + month;
		List<Object> p = new ArrayList<Object>();
		p.add(dateRange);
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> p2 = new ArrayList<Object>();
		p2.add(userid);
		p2.add(role);
		if(operationType.equals("search")) {
			p2.add("查询日志");
		}else {
			p2.add("下载日志");
		}
		p2.add(year+"年"+month+"月登入登出日志");
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
