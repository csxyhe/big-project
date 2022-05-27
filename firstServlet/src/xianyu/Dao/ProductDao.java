// ��װ������Ʒ���Ե����ݿ����Ӳ���
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
	// �����Ʒ
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
	// �鿴������Ʒ��������Ա�汾��
		public List<Product> listAll_B(int b_id) throws SQLException {
			String sql = "select * from products where b_id=?";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			return runner.query(sql, new BeanListHandler<Product>(Product.class),b_id);
		}
	// �鿴������Ʒ
	public List<Product> listAll() throws SQLException {
		String sql = "select * from products";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class));
	}
	// ��������ѯ�Ĺ���Ա�汾
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
	// ��������ѯ��������Ա�汾
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
	// ����id��ѯ��Ʒ
	public Product findProductById(String id) throws SQLException {
		String sql = "select * from products where p_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<Product>(Product.class),id);
	}
	// �޸���Ʒ��Ϣ
	// �������Product pΪ�޸ĺ����Ʒ����
	public void editProduct(Product p) throws SQLException{
		List<Object> obj = new ArrayList<Object>();
		obj.add(p.getName());
		obj.add(p.getPrice());
		obj.add(p.getCategory());
		obj.add(p.getPnum());
		obj.add(p.getDescription());
		String sql = "update products set name=?,price=?,category=?,pnum=?,description=?";
		// ����ʾ��ǰͼƬ·����ֻ�е���ƷͼƬ��Ϣ��ķ����ı�ʱ��������¼�뵽���ݿ���
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
	// ��̨ϵͳר�ã����ڸ���idɾ����Ʒ��Ϣ
	public void deleteProduct(String id) throws SQLException{
		String sql = "delete from products where p_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
	}
	// ��ȡ����������������Ʒ�����������в���
	public int findAllCount(String category) throws SQLException{
		String sql = "select count(*) from products";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if (!category.equals("ȫ����Ʒ")) {
			sql += " where category=?";
			Long count = (Long) runner.query(sql, new ScalarHandler<Object>(), category);
			return count.intValue();
		} else {
			Long count = (Long) runner.query(sql, new ScalarHandler<Object>());
			return count.intValue();
		}
	}
	// ��ȡ����������������Ʒ�����ùؼ��ʽ��в���
	public int findAllCountKeyword(String keyword) throws SQLException{
		String sql = "select count(*) from products";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		sql += " where name like ?";
		String searchName = "%" + keyword + "%";
		Long count = (Long) runner.query(sql, new ScalarHandler<Object>(), searchName);
		return count.intValue();
	}
	// ����𷵻ص�ǰҳ����Ʒ��Ϣ
	public List<Product> findByPage(int totalCount,int currentPage,int currentNum,String category) throws SQLException{
		// totalCount�������һҳ��ʾ����Ʒ��<currentNumʱ�õģ���ֹ����
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from products";
		List<Product> pl = new ArrayList<Product>();
		if(category.equals("ȫ����Ʒ")) {
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
		// totalCount�������һҳ��ʾ����Ʒ��<currentNumʱ�õģ���ֹ����
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
	//���ݶ����޸���Ʒ�����
	public void changeProductNum(Order order) throws SQLException{
		List<OrderItem> items = order.getOrderItems();//�Ȱ�orderitems�б��order��ȡ����
		String sql = "update products set pnum=? where p_id=?";
		QueryRunner runner = new QueryRunner();
		for (int i=0;i<items.size();i++) {//����ȡ������orderitem�����ҵ��������Ʒ�������������Ӷ��޸Ķ�Ӧ�����
			OrderItem item = items.get(i);
			String product_id = item.getProduct().getP_id();
			int buynum = item.getBuynum();
			Product p = findProductById(product_id);
			//������ǹ��ģ���������
			//�����ǵ����û�ͬʱʹ����վ�����빺�ﳵ�����ύ�������Ⱥ����ͬ�����ܻ���ּӹ��ﳵ��ʱ�򻹹����ύ������ʱ���Ѿ������ˣ�
			//����ʹ�����ݿ�Ļع�������ʵ��
			int newPnum = p.getPnum()-buynum;
			runner.update(DataSourceUtils.getConnection(),sql,newPnum,p.getP_id());//��ΪnewPnumΪ�����������ʧ�ܣ��ᵼ�»ع�
		}
	}
	
}
