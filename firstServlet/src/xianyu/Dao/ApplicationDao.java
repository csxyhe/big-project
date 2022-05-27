package xianyu.Dao;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import xianyu.utils.DataSourceUtils;
public class ApplicationDao {
	//�������˵��û�IDд��applications����
	public void addApplication(int id) throws SQLException{
		String sql = "insert into applications values(?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
	}
	
	//�������������Ϊ������Ա���û�id
	public List<Object[]> returnall() throws SQLException{
		String sql = "select * from applications";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler());
	}
	//���������application����ɾ��������¼
	public void deleteid(int userid) throws SQLException{
		String sql = "delete from applications where u_id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, userid);
	}
}
