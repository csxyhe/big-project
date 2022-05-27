package xianyu.Dao;
import xianyu.domain.Product;
import xianyu.domain.Order;
import xianyu.domain.OrderItem;
import xianyu.domain.User;
import xianyu.utils.DataSourceUtils;
import xianyu.Dao.UsersDao;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.QueryRunner;
import xianyu.domain.OrderItem;
public class OrderItemDao {
	//���һ��order��Ӧ��orderitem��¼
	public void addOrderItem(Order order) throws SQLException{
		List<OrderItem> items = order.getOrderItems();//�Ȱ�orderitems�б��order��ȡ����
		QueryRunner runner = new QueryRunner();
		String sql = "insert into orderitems(order_id,product_id,buynum) values(?,?,?)";
		for (int i=0;i<items.size();i++) {//����ȡ������orderitem���󣬲��������ݿ����趨�ĸ�ʽһ�������ȥ
			OrderItem item = items.get(i);
			runner.update(DataSourceUtils.getConnection(),sql,item.getOrder().getId(),item.getProduct().getP_id(),item.getBuynum());
		}
	}
	//���������ʱ����֪��Ҫ��Ҫ
	//ͳ��orderitems������ݣ���������Ʒ�������������ݸ�ʽΪ����Ʒ��ţ���Ʒ������Ʒ�����������
	public List<Object[]> calStatistics(String year, String month, int user_id) throws SQLException{
		List<Object> p = new ArrayList<Object>();
		String dateRange = year+"-"+month;
		p.add(user_id);
		p.add(dateRange);
		Object[] params = p.toArray();
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select product_id,this_products.name,SUM(buynum),pnum from orderitems,orders,(SELECT * FROM products WHERE b_id=?)AS this_products"
				+ " where orderitems.product_id=this_products.p_id AND orders.id=orderitems.order_id"
				+ " AND DATE_FORMAT(orders.ordertime,'%Y-%m')=? group by product_id order by buynum desc";
		return runner.query(sql, new ArrayListHandler(),params);
		
	}
	//����������ԱID��������������Ʒ����ϸ
	//������Ʒ���ơ���Ʒ�۸���Ʒ��������Ʒ��𡢽���ʱ��
	public List<Object[]> findDetailsByBid_Date(String b_id, String year, String month)throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> ls = new ArrayList<Object>();
		String sql = "select products.name, price, buynum, category, ordertime from orders,products,orderitems where" +
				" products.b_id = " + b_id + " and orders.id = orderitems.order_id and orderitems.product_id = products.p_id";
		if(year!=null && year.trim().length()>0) {
			sql +=" and year(ordertime)=?";
			ls.add(year);
		}
		if(month!=null && month.trim().length()>0) {
			sql +=" and month(ordertime)=?";
			ls.add(month);
		}
		Object[] params = ls.toArray();
		return runner.query(sql, new ArrayListHandler(),params);
	}
	//������Ա�鹺���¼
	public List<Object[]> findBuyRecord(int myuid, String user_id,String gender, String year,String month) throws SQLException{
		String sql="select orderitems.product_id,orders.user_id,gender,thisproducts.price,buynum,ordertime from orders,users,orderitems,(select * "+
				"from products where products.b_id=?)as thisproducts where orders.user_id=users.id and orders.id=orderitems.order_id and"
				+" orderitems.product_id=thisproducts.p_id";
		List<Object> ls = new ArrayList<Object>();
		ls.add(myuid);
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if(user_id!=null && user_id.trim().length()>0) {
			sql += " and orders.user_id=?";
			ls.add(user_id);
		}
		if(gender!=null && gender.trim().length()>0) {
			sql +=" and gender=?";
			ls.add(gender);
		}
		if(year!=null && year.trim().length()>0) {
			sql +=" and year(ordertime)=?";
			ls.add(year);
		}
		if(month!=null && month.trim().length()>0) {
			sql +=" and month(ordertime)=?";
			ls.add(month);
		}
		Object[] params = ls.toArray();
		return runner.query(sql, new ArrayListHandler(),params);
	}
	
	//��������ѯ�������ѯ������Щ��Ⱥ��������Щ��Ʒ��
	public List<Object[]> findOrderByManyCondition(String user_id,String gender, String year,String month) 
			throws SQLException{
		String sql="select orders.id,orders.user_id,gender,money,ordertime from orders,users where orders.user_id=users.id";
		List<Object> ls = new ArrayList<Object>();
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if(user_id!=null && user_id.trim().length()>0) {
			sql += " and orders.user_id=?";
			ls.add(user_id);
		}
		if(gender!=null && gender.trim().length()>0) {
			sql +=" and gender=?";
			ls.add(gender);
		}
		if(year!=null && year.trim().length()>0) {
			sql +=" and year(ordertime)=?";
			ls.add(year);
		}
		if(month!=null && month.trim().length()>0) {
			sql +=" and month(ordertime)=?";
			ls.add(month);
		}
		Object[] params = ls.toArray();
		return runner.query(sql, new ArrayListHandler(),params);
	}
	//�������뷵�ط���Ҫ��ĵ�Ʒ��ͳ����Ϣ������ID�����ƣ�ĳ������
	public List<Object[]> statisticsProSales(String p_id,String category, String year, String month, String minprice, String maxprice, String month_minnum,String month_maxnum)
		throws SQLException{
		List<Object> p = new ArrayList<Object>();
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		//���ڷ�Χ��ʽʱ"YYYY-MM"
		String dateRange = year + "-" + month;
		p.add(dateRange);
		//������������month_order��ɸѡ�����µĶ�����
		String sql = "select products.p_id, products.name, products.category, products.price,products.pnum, SUM(buynum) FROM products, orderitems," +
				"(select id from orders where date_format(ordertime, '%Y-%m')=?";
		sql += ")AS month_order WHERE products.p_id = orderitems.product_id and orderitems.order_id = month_order.id GROUP BY products.p_id";
		if(p_id.length()!=0) {
			sql+=" and product_id=?";
			p.add(p_id);
		}
		if(category.length()!=0) {
			sql+=" and products.category=?";
			p.add(category);
		}
		if(minprice.length()!=0) {
			sql+=" and products.price>=?";
			p.add(minprice);
		}
		if(maxprice.length()!=0) {
			sql+=" and products.price<=?";
			p.add(maxprice);
		}
		if(month_minnum.length()!=0&& month_maxnum.length()!=0) {
			sql +=" HAVING SUM(buynum)>=? and SUM(buynum)<=?";
			p.add(month_minnum);
			p.add(month_maxnum);
		}else if(month_maxnum.length()!=0) {
			sql +=" Having SUM(buynum)<=?";
			p.add(month_maxnum);
		}else if(month_minnum.length()!=0) {
			sql +=" HAVING SUM(buynum)>=?";
			p.add(month_minnum);
		}
		Object[] params = p.toArray();
		return runner.query(sql, new ArrayListHandler(),params);
		
	}
	//�������뷵����������ͳ����Ϣ������������ƣ������������������۶�
	public List<Object[]> statisticsCategorySales(String year, String month) throws SQLException{
		List<Object> c = new ArrayList<Object>();
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		//���ڷ�Χ��ʽʱ"YYYY-MM"
		String dateRange = year + "-" + month;
		c.add(dateRange);
		//������������month_order��ɸѡ�����µĶ�����
		String sql = "SELECT category, SUM(buynum), SUM(buynum*products.price) FROM products, orderitems," +
				"(select id from orders where date_format(ordertime, '%Y-%m')=?)AS month_order "
				+ "WHERE products.p_id = orderitems.product_id AND orderitems.order_id = month_order.id GROUP BY category";
		Object[] params = c.toArray();
		return runner.query(sql, new ArrayListHandler(),params);
	}
	
	@SuppressWarnings("static-access")
	public List<Integer> predictSales(String p_id)throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Integer> sales_list = new ArrayList<Integer>();
		String sql="select SUM(buynum) from orderitems,(SELECT * FROM orders WHERE DATE_FORMAT(ordertime,'%Y-%m-%d')=?)AS this_orders"+
				" where this_orders.id=orderitems.order_id AND product_id=?";
		Date date = new Date();//��ȡ��ǰʱ��
		List<Object> p = new ArrayList<Object>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//�������ݿ��ѯ�ĸ�ʽ
		for(int i=0;i<6;i++) {
			String day = sdf.format(date);
			if(i==0) {
				p.add(day);
				p.add(p_id);
			}else {
				p.set(0, day);
			}
			Object[] params = p.toArray();
			//��һ������һ������ݣ�֤����һ��û�и���Ʒû�����Ѽ�¼
			if(runner.query(sql,new ScalarHandler<BigDecimal>(), params)==null) {
				sales_list.add(0);
			}else {
				sales_list.add(runner.query(sql,new ScalarHandler<BigDecimal>(), params).intValue());//����ת����ת����integer����
			}
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.DATE, -1);
			date = calendar.getTime();//ǰһ��
		}
		Collections.reverse(sales_list);//��������Զ������С
		return sales_list;
		
		
	}
	//��ȡ�û��������ݣ�����һ�������й�����Ϊ���û����ɻ���
	//��һ�����������ܶ������
	public List<Object[]> getUserFigureData() throws SQLException{
		//��������������Ϣ
		String sql="SELECT id,gender,map,category,time_space,order_times,total_money FROM (SELECT id,gender,map,category FROM user_cate GROUP "+
				"BY ID HAVING MAX(CSUM)) AS TABLE1,(SELECT users.id uid,DATEDIFF(now(),MAX(ordertime)) time_space,COUNT(*) order_times,SUM(money) "+
				"total_money FROM users, within_month_orders WHERE users.id=within_month_orders.user_id GROUP BY users.id) AS TABLE2 WHERE TABLE1.id = TABLE2.uid"+
				" ORDER BY total_money DESC";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler());
	}
	//��������Ķ����ţ����ظö����Ĺ������飨��ƷID����Ʒ�������ۣ���𣬹���������
	public List<Object[]> returnOIDetails(String orderid) throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select product_id,name,price,category,buynum from products,(select * from orderitems where order_id=?)AS this_orderitems where this_orderitems.product_id=products.p_id";
		return runner.query(sql, new ArrayListHandler(),orderid);
	}
	//����һ���������ж����е�userid,product_id,buynum
	public List<Object[]> returnUPB_recommend_need()throws SQLException{
		String sql = "select within_month_orders.user_id,orderitems.product_id,buynum from within_month_orders,orderitems where within_month_orders.id=orderitems.order_id";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler());
	}
	//�����쳣ͳ��
	public List<Object[]> searchAbnormality() throws SQLException{
		String sql = "select bid, COUNT(*) triggerCount, DATE_FORMAT(ordertime, '%Y-%m-%d %H') triggerHour" + 
				" from (select products.b_id bid,products.p_id pid,ordertime from orders, orderitems, products where orders.id=orderitems.order_id" + 
				" and products.p_id=orderitems.product_id) AS all_sales GROUP BY triggerHour HAVING triggerCount>=20 ORDER BY triggerHour";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler());
	}
}
