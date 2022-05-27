//提供关于order的服务接口
package xianyu.service;
import xianyu.domain.Order;
import xianyu.Dao.OrderItemDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xianyu.Dao.OrderDao;
import xianyu.Dao.ProductDao;
import xianyu.Dao.operationDao;
import xianyu.utils.DataSourceUtils;
import xianyu.utils.MailUtils;
public class OrderService {
	private OrderDao od = new OrderDao();
	private OrderItemDao oid = new OrderItemDao();
	private ProductDao pd = new ProductDao();
	public void addOrder(Order order) {
		try {
			//要使用回滚机制，则使用自定义的事务处理
			// 1.开启事务
			DataSourceUtils.startTransaction();
			// 2.完成一系列的JDBC操作
			// 2.1.把订单信息保存到订单表orders
			od.addOrder(order);
			// 2.2.向订单条目表oderitems中添加数据
			oid.addOrderItem(order);
			// 2.3.修改products表中该商品的库存数量
			pd.changeProductNum(order);
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				// 捕获到异常，则回滚事务
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				//正常结束，释放连接
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//商品销量统计
	public List<Object[]> calStatistics(String year, String month,int user_id){
		List<Object[]> statis = new ArrayList<Object[]>();
		try {
			statis = oid.calStatistics(year,month,user_id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statis;
	}
	public List<Object[]> findBuyRecord(int myuid, String user_id,String gender, String year,String month) throws SQLException{
		return oid.findBuyRecord(myuid,user_id, gender, year, month);
	}
	//返回订单情况查询的结果
	public List<Object[]> findOrderByManyCondition(String user_id,String gender, String year,String month){
		List<Object[]> ls = new ArrayList<Object[]>();
		try {
			ls = oid.findOrderByManyCondition(user_id, gender, year, month);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ls;
	}
	//返回该销售人员管理商品的销售信息
	public List<Object[]> findDetailsByBid_Date(String b_id,String year,String month) throws SQLException{
		List<Object[]> psperb = new ArrayList<Object[]>();
		psperb = oid.findDetailsByBid_Date(b_id,year,month);
		return psperb;
	}
	//根据输入返回符合要求的单品的统计信息，包括ID，名称，单价，库存，某月销量
	public List<Object[]> statisticsProSales(String p_id,String category, String year, String month, String minprice, String maxprice, String month_minnum,String month_maxnum)
		throws SQLException{
		List<Object[]> psales = new ArrayList<Object[]>();
		psales = oid.statisticsProSales(p_id, category, year, month, minprice, maxprice, month_minnum, month_maxnum);
		return psales;
	}
	//根据输入返回六个类别的统计信息，包括类别名称，当月销量，当月销售额
	public List<Object[]> statisticsCategorySales(String year, String month) throws SQLException{
		List<Object[]> csales = new ArrayList<Object[]>();
		csales = oid.statisticsCategorySales(year, month);
		return csales;
	}
	//返回过去六天的数据，用于做销量预测
	public List<Integer> predictSales(String p_id)throws SQLException{
		return oid.predictSales(p_id);
	}
	//返回各个用户的用户画像数据
	public List<Object[]> getUserFigureData(int op_id,String role,String op_name,String ip)throws SQLException{
		operationDao oped = new operationDao();
		oped.addOperation(op_id, role, op_name, "", ip);
		return oid.getUserFigureData();
	}
	//返回近一月内订单
	public List<Object[]> searchMonthOrderRecord(int userid)throws SQLException{
		return od.searchMonthOrderRecord(userid);
	}
	//返回该用户所有订单
	public List<Object[]> searchAllOrderRecord(int userid) throws SQLException{
		return od.searchAllOrderRecord(userid);
	}
	//根据输入的订单号，返回该订单的购买详情
	public List<Object[]> returnOIDetails(String orderid) throws SQLException{
		return oid.returnOIDetails(orderid);
		
	}
	//推荐系统用
	public List<Object[]> returnUPB_recommend_need()throws SQLException{
		return oid.returnUPB_recommend_need();
	}
	//销售异常统计
	public List<Object[]> searchAbnormality() throws SQLException{
		return oid.searchAbnormality();
		
	}
}
