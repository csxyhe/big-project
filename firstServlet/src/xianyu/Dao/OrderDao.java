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
	//添加订单
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
	//返回近一月内订单
	public List<Object[]> searchMonthOrderRecord(int userid)throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from within_month_orders where user_id="+userid+" ORDER BY ordertime DESC";
		return runner.query(sql, new ArrayListHandler());
		
	}
	//返回用户所有订单（顺序：订单号，用户ID，总金额，下单时间）,按下单顺序降序排列（最近的在上面）
	public List<Object[]> searchAllOrderRecord(int userid) throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select id,user_id,money,ordertime from orders where user_id="+userid+" ORDER BY ordertime DESC";
		return runner.query(sql, new ArrayListHandler());
	}
}
