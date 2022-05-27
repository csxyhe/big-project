package xianyu.Dao;
import xianyu.domain.Product;
import xianyu.domain.Order;
import xianyu.domain.User;
import xianyu.utils.DataSourceUtils;
import xianyu.Dao.UsersDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.QueryRunner;
public class OrderDao {
	//��Ӷ���
	public void addOrder(Order order) throws SQLException{
		String sql = "insert into orders(id,money,receiverAddress,receiverName,receiverEmail,user_id)"
				+ " values(?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner();
		int row = runner.update(DataSourceUtils.getConnection(),sql,order.getId(),order.getMoney(),order.getReceiverAddress(),order.getReceiverName()
				,order.getReceiverEmail(),order.getUser().getId());
		if (row == 0) {
			throw new RuntimeException();
		}
	}
	//���ؽ�һ���ڶ���
	public List<Object[]> searchMonthOrderRecord(int userid)throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from within_month_orders where user_id="+userid+" ORDER BY ordertime DESC";
		return runner.query(sql, new ArrayListHandler());
		
	}
	//�����û����ж�����˳�򣺶����ţ��û�ID���ܽ��µ�ʱ�䣩,���µ�˳�������У�����������棩
	public List<Object[]> searchAllOrderRecord(int userid) throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select id,user_id,money,ordertime from orders where user_id="+userid+" ORDER BY ordertime DESC";
		return runner.query(sql, new ArrayListHandler());
	}
}
