package xianyu.Dao;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import xianyu.utils.DataSourceUtils;
public class ApplicationDao {
	//将申请人的用户ID写入applications表中
	public void addApplication(int id) throws SQLException{
		String sql = "insert into applications values(?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
	}
	
	//读出所有请求成为销售人员的用户id
	public List<Object[]> returnall() throws SQLException{
		String sql = "select * from applications";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler());
	}
	//处理完后，在application表中删除这条记录
	public void deleteid(int userid) throws SQLException{
		String sql = "delete from applications where u_id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, userid);
	}
}
