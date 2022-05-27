// 封装关于商品属性的数据库连接操作
package xianyu.Dao;
import xianyu.domain.Product;
import xianyu.utils.DataSourceUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.QueryRunner;
import xianyu.domain.Order;
import xianyu.domain.OrderItem;
public class ProductDao {
	// 添加商品
	public void addProduct(Product p)throws SQLException{
		String sql = "insert into products(p_id,b_id,name,price,category,pnum,imgurl,description)"
				+ "values (?,?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, p.getP_id(),p.getB_id(),p.getName(),p.getPrice(),p.getCategory(),
				p.getPnum(),p.getImgurl(),p.getDescription());
		if (row == 0) {
			throw new RuntimeException();
		}
	}
	// 查看所有商品（销售人员版本）
		public List<Product> listAll_B(int b_id) throws SQLException {
			String sql = "select * from products where b_id=?";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			return runner.query(sql, new BeanListHandler<Product>(Product.class),b_id);
		}
	// 查看所有商品
	public List<Product> listAll() throws SQLException {
		String sql = "select * from products";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class));
	}
	// 多条件查询的管理员版本
	public List<Product> findProductByManyCondition(String id, String name,String category,String minprice,String maxprice) 
			throws SQLException{
		String sql = "select * from products where 1=1";
		List<Object> list = new ArrayList<Object>();
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if (id!=null && id.trim().length() >0) {
			sql += " and p_id=?";
			list.add(id);
		}
		if (name!=null && name.trim().length() >0) {
			sql += " and name=?";
			list.add(name);
		}
		if (category!=null && category.trim().length() >0) {
			sql += " and category=?";
			list.add(category);
		}
		if (minprice!=null && minprice.trim().length() >0 && maxprice!=null && maxprice.trim().length() >0) {
			sql += " and price between ? and ?";
			list.add(minprice);
			list.add(maxprice);
		}
		Object[] params = list.toArray();
		return runner.query(sql, new BeanListHandler<Product>(Product.class),params);
	}
	// 多条件查询的销售人员版本
	public List<Product> findProductByManyCondition_B(String p_id, int b_id,String name,String category,String minprice,String maxprice)
		throws SQLException{
		String sql = "select * from products where 1=1";
		List<Object> list = new ArrayList<Object>();
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if (p_id!=null && p_id.trim().length() >0) {
			sql += " and p_id=?";
			list.add(p_id);
		}
		sql +=" and b_id=?";
		list.add(b_id);
		if (name!=null && name.trim().length() >0) {
			sql += " and name=?";
			list.add(name);
		}
		if (category!=null && category.trim().length() >0) {
			sql += " and category=?";
			list.add(category);
		}
		if (minprice!=null && minprice.trim().length() >0 && maxprice!=null && maxprice.trim().length() >0) {
			sql += " and price between ? and ?";
			list.add(minprice);
			list.add(maxprice);
		}
		Object[] params = list.toArray();
		return runner.query(sql, new BeanListHandler<Product>(Product.class),params);
	}
	// 根据id查询商品
	public Product findProductById(String id) throws SQLException {
		String sql = "select * from products where p_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<Product>(Product.class),id);
	}
	// 修改商品信息
	// 传入参数Product p为修改后的商品对象
	public void editProduct(Product p) throws SQLException{
		List<Object> obj = new ArrayList<Object>();
		obj.add(p.getName());
		obj.add(p.getPrice());
		obj.add(p.getCategory());
		obj.add(p.getPnum());
		obj.add(p.getDescription());
		String sql = "update products set name=?,price=?,category=?,pnum=?,description=?";
		// 不显示当前图片路径，只有当商品图片信息真的发生改变时，才重新录入到数据库中
		if(p.getImgurl()!=null && p.getImgurl().trim().length()>0) {
			obj.add(p.getImgurl());
			sql += ",imgurl=?";
		}
		obj.add(p.getP_id());
		obj.add(p.getB_id());
		sql +=" where p_id=? and b_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		Object[] params = obj.toArray();
		runner.update(sql, params);
	}
	// 后台系统专用，用于根据id删除商品信息
	public void deleteProduct(String id) throws SQLException{
		String sql = "delete from products where p_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
	}
	// 获取符合条件的所有商品数，用类别进行查找
	public int findAllCount(String category) throws SQLException{
		String sql = "select count(*) from products";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if (!category.equals("全部商品")) {
			sql += " where category=?";
			Long count = (Long) runner.query(sql, new ScalarHandler<Object>(), category);
			return count.intValue();
		} else {
			Long count = (Long) runner.query(sql, new ScalarHandler<Object>());
			return count.intValue();
		}
	}
	// 获取符合条件的所有商品数，用关键词进行查找
	public int findAllCountKeyword(String keyword) throws SQLException{
		String sql = "select count(*) from products";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		sql += " where name like ?";
		String searchName = "%" + keyword + "%";
		Long count = (Long) runner.query(sql, new ScalarHandler<Object>(), searchName);
		return count.intValue();
	}
	// 按类别返回当前页的商品信息
	public List<Product> findByPage(int totalCount,int currentPage,int currentNum,String category) throws SQLException{
		// totalCount用于最后一页显示的商品数<currentNum时用的，防止报错
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from products";
		List<Product> pl = new ArrayList<Product>();
		if(category.equals("全部商品")) {
			pl = runner.query(sql, new BeanListHandler<Product>(Product.class));
		}else {
			sql += " where category=?";
			pl = runner.query(sql, new BeanListHandler<Product>(Product.class),category);
		}
		if ((currentPage)*currentNum<=totalCount) {
			return pl.subList((currentPage-1)*currentNum , (currentPage)*currentNum);
		}else {
			return pl.subList((currentPage-1)*currentNum , totalCount);
		}
		
	}
	public List<Product> findByKeyword(int totalCount, int currentPage, int currentNum, String keyword) throws SQLException {
		// totalCount用于最后一页显示的商品数<currentNum时用的，防止报错
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from products where name like ?";
		List<Product> pl = new ArrayList<Product>();
		String searchName = "%" + keyword + "%";
		pl = runner.query(sql, new BeanListHandler<Product>(Product.class),searchName);
		if ((currentPage)*currentNum<=totalCount) {
			return pl.subList((currentPage-1)*currentNum , (currentPage)*currentNum);
		}else {
			return pl.subList((currentPage-1)*currentNum , totalCount);
		}
	}
	//根据订单修改商品库存量
	public void changeProductNum(Order order) throws SQLException{
		List<OrderItem> items = order.getOrderItems();//先把orderitems列表从order中取出来
		String sql = "update products set pnum=? where p_id=?";
		QueryRunner runner = new QueryRunner();
		for (int i=0;i<items.size();i++) {//依次取出所有orderitem对象，找到他买的商品及购买数量，从而修改对应库存量
			OrderItem item = items.get(i);
			String product_id = item.getProduct().getP_id();
			int buynum = item.getBuynum();
			Product p = findProductById(product_id);
			//库存量是够的，则允许购买
			//（考虑到多用户同时使用网站，加入购物车后点击提交订单的先后次序不同，可能会出现加购物车的时候还够，提交订单的时候已经不够了）
			//这里使用数据库的回滚机制来实现
			int newPnum = p.getPnum()-buynum;
			runner.update(DataSourceUtils.getConnection(),sql,newPnum,p.getP_id());//若为newPnum为负数，则插入失败，会导致回滚
		}
	}
	
}
