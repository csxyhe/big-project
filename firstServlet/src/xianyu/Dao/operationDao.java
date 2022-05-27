package xianyu.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import xianyu.utils.DataSourceUtils;

public class operationDao {
	//写入一条操作日志
	public void addOperation(int user_id, String role, String op_name, String op_content, String ip) throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into operations(u_id,role,op_name,op_content,ip) values("+user_id+",'"+role+"','"+op_name+"','"+op_content+"','"+ip+"')";
		runner.update(sql);
		
	}
	//根据输入时间范围查找所有满足条件的登入登出日志
	public List<Object[]> getOperation(String year, String month) throws SQLException{
		String dateRange = year +"-"+month;
		List<Object> p = new ArrayList<Object>();
		p.add(dateRange);
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM operations WHERE DATE_FORMAT(op_time, '%Y-%m')=?";
		Object[] params = p.toArray();
		return runner.query(sql, new ArrayListHandler(),params);
	}
}
