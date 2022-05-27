package xianyu.Dao;
import xianyu.utils.DataSourceUtils;
import java.sql.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import java.util.ArrayList;
import java.util.List;
public class BrowseDao {
	//���һ���ͻ������־����ȱ�������ʱ��ͳ���ʱ�䣨��λ���룩��
	public void addBrowse(int user_id,int business_id,String product_id,String begintime,String endtime,int during ) throws SQLException {
		String sql = "insert into browses(u_id,b_id,p_id,begintime,endtime,during) values(?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, user_id,business_id,product_id,begintime,endtime,during);
		if (row == 0) {
			throw new RuntimeException();
		}
	}
	//���������������������Ա����Ʒ���û������־
	public List<Object[]> returnall(int b_id) throws SQLException{
		String sql = "select users.username,products.name,begintime,endtime,during from browses,users,products where browses.b_id="+b_id+" and browses.u_id=users.id"
				+ " and browses.p_id=products.p_id";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler());
	}
	//���������·ݶ��������û������־
	public List<Object[]> returnmonth_all(String year,String month) throws SQLException{
		String dateRange = year +"-"+month;
		List<Object> p = new ArrayList<Object>();
		p.add(dateRange);
		String sql = "select * from browses where DATE_FORMAT(begintime,'%Y-%m')=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		Object[] params = p.toArray();
		return runner.query(sql, new ArrayListHandler(),params);
	}
	//�Ƽ�ϵͳ�ã����ظ��û���һ�����ڵ������pid+�������
	public List<Object[]> returnMB_recommend(int userid)throws SQLException{
		String sql = "select p_id,COUNT(*) from within_month_browses where u_id="+userid+" GROUP BY p_id";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler());
	}
}
